package com.company;

import com.company.build.ConstructionProfilePowerFlash;
import com.company.comand.*;
import com.company.crc.CS_NOT;
import com.company.dto.*;
import com.company.exception.ExceedingAttemptsException;
import com.company.exception.InvalidCharacterException;
import com.company.util.WorkingWithArrays;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CounterDevice {
    DeviceCounterDto deviceCounterDto;
    ConnectionChannel connectionChannel;

    int quantityProfilePower128 = 0;
    int quantityProfilePower2000 = 0;
    int quantityProfilePowerFlash512k = 0;
    int quantityRam = 0;
    int quantityRecordingByDate = 0;

    List<CommandDeviceCounter> correctCommandsCounter = new ArrayList<>();

    ProfileResponse128Dto profilePower128Dto;
    ProfileResponse2000Dto profilePower2000Dto;
    ProfilePowerFlashDto profilePowerFlashDto;
    ProfilePowerRamObtainDto profilePowerRamObtainDto;
    ProfileRecordingByDateObtainDto profileRecordingByDateObtainDto;

    public CounterDevice(DeviceCounterDto deviceCounterDto, ConnectionChannel connectionChannel) {
        this.deviceCounterDto = deviceCounterDto;
        this.connectionChannel = connectionChannel;
    }

    public ResponseDeviceDTO runInstruction() {
        try {
            openChannel();
            distributeCommand();
        } catch (Exception | InvalidCharacterException e) {
            System.out.println("Исключение: " + e);
        }
        return new ResponseDeviceDTO(
                deviceCounterDto.getNumberCounter(),
                correctCommandsCounter,
                profilePower128Dto,
                profilePower2000Dto,
                profilePowerFlashDto,
                profilePowerRamObtainDto
        );
    }

    private void openChannel() throws ExceedingAttemptsException, InvalidCharacterException {
        OpenConnectionChannel openingConnectionChannel = new OpenConnectionChannel();
        System.out.println("Канал связи");
        sendRequestVerification(openingConnectionChannel.makeDialogueConnection(deviceCounterDto), 14);
    }

    private byte[] sendRequestVerification(byte[] mainArr, int size) {
        return connectionChannel.interactionResultOnRequest(WorkingWithArrays.countArrays(mainArr, CS_NOT.calculate(mainArr)), size);
    }

    private void distributeCommand() {
        for (CommandDeviceCounter commandDeviceCounter : deviceCounterDto.getCommandsCounter()) {
            System.out.println(commandDeviceCounter.getTitle());
            if (commandDeviceCounter == CommandDeviceCounter.PROFILE_POWER_128 && quantityProfilePower128 == 0) {
                profilePowerObtaining128();
                quantityProfilePower128++;
            }
            if (commandDeviceCounter == CommandDeviceCounter.PROFILE_POWER_2000 && quantityProfilePower2000 == 0) {
                profilePowerObtain2000();
                quantityProfilePower2000++;
            }
            if (commandDeviceCounter == CommandDeviceCounter.PROFILE_POWER_FLASH_512K && quantityProfilePowerFlash512k == 0) {
                profilePowerObtainFlash512K();
                quantityProfilePowerFlash512k++;
            }
            if (commandDeviceCounter == CommandDeviceCounter.RAM && quantityRam == 0) {
                profileRamObtain();
                quantityRam++;
            }
            if (commandDeviceCounter == CommandDeviceCounter.RECORDING_BY_DATE && quantityRecordingByDate == 0) {
                recordingByDateObtain();
                quantityRecordingByDate++;
            }
        }
    }


    private void profilePowerObtaining128() {
        ArrayList<String> dateTime = new ArrayList<>();
        CommandProfilePower128 commandProfilePower = new CommandProfilePower128();

        byte[] a = {0x010, 0x012, 0x014, 0x017, 0x018, 0x019};
        byte[] arrBytePower;
        for (byte b : a) {
            arrBytePower = sendRequestVerification(commandProfilePower.interactionBytePower(deviceCounterDto, b), 8);

            for (int j = 6; j < arrBytePower.length - 1; j++) {
                dateTime.add(String.format("%X", (int) arrBytePower[j] & 0xFF));
            }
        }
        profilePower128Dto = commandProfilePower.buildProfileResponse128(commandProfilePower, dateTime);
        correctCommandsCounter.add(CommandDeviceCounter.PROFILE_POWER_128);
    }

    private void profilePowerObtain2000() {
        CommandProfilePower2000 commandProfilePower2000 = new CommandProfilePower2000();
        String dateTime = "";
        byte[] lastByte = {(byte) 0x00, (byte) 0x01, (byte) 0x0C, (byte) 0x78, (byte) 0x7C, (byte) 0xC4, (byte) 0xCC,
                (byte) 0xDC, (byte) 0xE0, (byte) 0xE4, (byte) 0x74, (byte) 0xF4, (byte) 0xF8, (byte) 0xFC
        };

        int[] dataTypeAnswer = {0x01, 0x01, 0x01, 0x04, 0x04, 0x08, 0x16, 0x04, 0x04, 0x08, 0x08, 0x04, 0x04, 0x04};
        byte[] arrBytePower;
        for (int x = 0; x < lastByte.length; x++) {
            arrBytePower = sendRequestVerification(commandProfilePower2000.interactionBytePower(
                    deviceCounterDto, lastByte[x], (byte) dataTypeAnswer[x]), 7 + dataTypeAnswer[x]);

            for (int j = 6; j < arrBytePower.length - 1; j++) {
                dateTime = dateTime.concat(String.format("%X", (int) arrBytePower[j] & 0xFF));
            }
            dateTime = dateTime.concat(" ");
        }
        profilePower2000Dto = commandProfilePower2000.buildProfileResponse2000(dateTime);
        correctCommandsCounter.add(CommandDeviceCounter.PROFILE_POWER_2000);
    }

    private void profilePowerObtainFlash512K() {
        CommandProfilePowerFlash commandProfilePowerFlash = new CommandProfilePowerFlash();
        ArrayList<FlashSysIntDto> recording = new ArrayList<FlashSysIntDto>();
        int count = 0;
        for (int i = 0; i < 393216; i = i + 256) { //64
            try {
                byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();

                byte[] biasBytes = {0x00, 0x40, (byte) 0x80, (byte) 0xC0};
                byte[] byteOffsetSize = {0x40};

                byte[] arr = new byte[260]; //Работает - не трогай

                int count1 = 0;
                System.out.println("Запись: " + count++);
                byte[] arrBytePower64;
                for (int k = 0; k < biasBytes.length; k++) {
                    arrBytePower64 = sendRequestVerification(commandProfilePowerFlash.interactionBytePower(
                            deviceCounterDto, bytes, biasBytes[k], byteOffsetSize[0]), 71);

                    for (int m = 6; m < arrBytePower64.length; m++) {
                        arr[count1++] = arrBytePower64[m];
                    }
                }

                int[] countInquiries = { //19
                        0, 4, 8, 24, 40, 56, 72, 88, 104, 108, 124, 140, 156, 172, 188, 192, 200, 224, 236
                };
                recording.add(printAllData(countInquiries, arr));
            } catch (Exception e) {
                System.out.println("Ex" + e);
            }
            System.out.println();

        }
        profilePowerFlashDto = ConstructionProfilePowerFlash.returnPowerObtainFlash512K();
        correctCommandsCounter.add(CommandDeviceCounter.RAM);
    }

    private FlashSysIntDto printAllData(int[] countInquiries, byte[] arr) {
        return new FlashSysIntDto(
                printDataBCD4(countInquiries[0], arr, "Время и дата записи:                            ", "yyyy-MM-dd HH:mm:ss"),
                printDataBCD4(countInquiries[1], arr, "Время и дата предыдущей:                        ", "yyyy-MM-dd HH:mm:ss"),
                printDataFloat4(countInquiries[2], arr, "Дробная часть интеграторов объема по каналам:   ", " м³"),
                printDataFloat4(countInquiries[3], arr, "Дробная часть интеграторов массы по каналам:    ", " Т"),
                printDataFloat4(countInquiries[4], arr, "Дробная часть интеграторов энергии по системам: ", " МВт"),
                printDataFloat4(countInquiries[5], arr, "Целая часть интеграторов объема по каналам:     ", " м³"),
                printDataFloat4(countInquiries[6], arr, "Целая часть интеграторов массы по каналам:      ", " Т"),
                printDataFloat4(countInquiries[7], arr, "Целая часть интеграторов энергии по каналам:    ", " МВт"),
                printDataFloat(countInquiries[8], arr, "Время работы при поданом питании:               ", " сек"),
                printDataFloat4(countInquiries[9], arr, "Время работы системы без ошибок:                ", " сек"),
                printDataFloat4(countInquiries[10], arr, "Расход меньше минимального:                     ", " сек"),
                printDataFloat4(countInquiries[11], arr, "Расход больше максимального:                    ", " сек"),
                printDataFloat4(countInquiries[12], arr, "Разность температур меньше минимальной:         ", " сек"),
                printDataFloat4(countInquiries[13], arr, "Техническая неисправность:                      ", " сек"),
                printDataByte4(countInquiries[14], arr, "Ошибки по системам:                             ", " сек"),
                printDataShort(countInquiries[15], arr, "Ошибки по системам:                             ", " сек"),
                printDataShort12(countInquiries[16], arr, "Температура по системам:                        ", " °C/100"),
                printDataByte12(countInquiries[17], arr, "Давление по системам:                           ", " МПа/100"),
                printDataFloat4(countInquiries[18], arr, "Интеграторы объемного расхода по каналам:       ", " м³/ч")
        );
    }


    private LocalDateTime printDataBCD4(int countInquiry, byte[] arr, String left, String formatterString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterString);
        LocalDateTime localDateTime = buildTime(arr, countInquiry);
        System.out.println(left + localDateTime.format(formatter));
        return localDateTime;
    }

    private byte[] printDataByte4(int countInquiry, byte[] arr, String left, String right) {
        System.out.println(left +
                arr[0] + " " +
                arr[1] + " " +
                arr[2] + " " +
                arr[3] + " "
        );
        return arr;
    }

    private short[] printDataShort12(int countInquiry, byte[] arr, String left, String right) {
        short[] aaa = buildArrForShort12(arr, countInquiry);
        System.out.println(left +
                aaa[0] + right + " " +
                aaa[1] + right + " " +
                aaa[2] + right + " " +
                aaa[3] + right + " " +
                aaa[4] + right + " " +
                aaa[5] + right + " " +
                aaa[6] + right + " " +
                aaa[7] + right + " " +
                aaa[8] + right + " " +
                aaa[9] + right + " " +
                aaa[10] + right + " " +
                aaa[11] + right + " "
        );
        return aaa;
    }

    private byte[] printDataByte12(int countInquiry, byte[] arr, String left, String right) {
        System.out.println("Давление по системам:                           " +
                arr[0] + " МПа/100" + " " +
                arr[1] + " МПа/100" + " " +
                arr[2] + " МПа/100" + " " +
                arr[3] + " МПа/100" + " " +
                arr[4] + " МПа/100" + " " +
                arr[5] + " МПа/100" + " " +
                arr[6] + " МПа/100" + " " +
                arr[7] + " МПа/100" + " " +
                arr[8] + " МПа/100" + " " +
                arr[9] + " МПа/100" + " " +
                arr[10] + " МПа/100" + " " +
                arr[11] + " МПа/100" + " "
        );
        return arr;
    }

    private short[] printDataShort(int countInquiry, byte[] arr, String left, String right) {
        short[] aaa = buildArrForShort4(arr, countInquiry);
        System.out.println(left + aaa[0] + right + " " + aaa[1] + right + " " + aaa[2] + " " + right + aaa[3] + " " + right);
        return aaa;
    }

    private float[] printDataFloat4(int countInquiries, byte[] arr, String left, String right) {
        float[] arrFloat = buildArrForFloat(arr, countInquiries);
        System.out.println(left
                + arrFloat[0] + right + " "
                + arrFloat[1] + right + " "
                + arrFloat[2] + right + " "
                + arrFloat[3] + right);
        return arrFloat;
    }

    private float printDataFloat(int countInquiries, byte[] arr, String left, String right) {
        byte[] arr1 = {arr[countInquiries], arr[countInquiries + 1], arr[countInquiries + 2], arr[countInquiries + 3]};
        System.out.println(left + ByteBuffer.wrap(arr1).getInt() + right);
        return ByteBuffer.wrap(arr1).getInt();
    }

    private short[] buildArrForShort4(byte[] arr, int i) {
        byte[] arr2 = {arr[i], arr[i + 1]};
        byte[] arr3 = {arr[i + 2], arr[i + 3]};
        byte[] arr4 = {arr[i + 4], arr[i + 5]};
        byte[] arr5 = {arr[i + 6], arr[i + 7]};

        return new short[]{
                ByteBuffer.wrap(arr2).getShort(),
                ByteBuffer.wrap(arr3).getShort(),
                ByteBuffer.wrap(arr4).getShort(),
                ByteBuffer.wrap(arr5).getShort()
        };
    }

    private short[] buildArrForShort12(byte[] arr, int i) {

        byte[] arr1 = {arr[i], arr[i + 1]};
        byte[] arr2 = {arr[i + 2], arr[i + 3]};
        byte[] arr3 = {arr[i + 4], arr[i + 5]};
        byte[] arr4 = {arr[i + 6], arr[i + 7]};
        byte[] arr5 = {arr[i + 8], arr[i + 9]};
        byte[] arr6 = {arr[i + 10], arr[i + 11]};
        byte[] arr7 = {arr[i + 12], arr[i + 13]};
        byte[] arr8 = {arr[i + 14], arr[i + 15]};
        byte[] arr9 = {arr[i + 16], arr[i + 17]};
        byte[] arr10 = {arr[i + 18], arr[i + 19]};
        byte[] arr11 = {arr[i + 20], arr[i + 21]};
        byte[] arr12 = {arr[i + 22], arr[i + 23]};

        return new short[]{
                ByteBuffer.wrap(arr1).getShort(),
                ByteBuffer.wrap(arr2).getShort(),
                ByteBuffer.wrap(arr3).getShort(),
                ByteBuffer.wrap(arr4).getShort(),
                ByteBuffer.wrap(arr5).getShort(),
                ByteBuffer.wrap(arr6).getShort(),
                ByteBuffer.wrap(arr7).getShort(),
                ByteBuffer.wrap(arr8).getShort(),
                ByteBuffer.wrap(arr9).getShort(),
                ByteBuffer.wrap(arr10).getShort(),
                ByteBuffer.wrap(arr11).getShort(),
                ByteBuffer.wrap(arr12).getShort()
        };
    }

    private float[] buildArrForFloat(byte[] arr, int i) {
        byte[] arr1 = {arr[i], arr[i + 1], arr[i + 2], arr[i + 3]};
        byte[] arr2 = {arr[i + 4], arr[i + 5], arr[i + 6], arr[i + 7]};
        byte[] arr3 = {arr[i + 8], arr[i + 9], arr[i + 10], arr[i + 11]};
        byte[] arr4 = {arr[i + 12], arr[i + 13], arr[i + 14], arr[i + 15]};

        return new float[]{
                ByteBuffer.wrap(arr1).getFloat(),
                ByteBuffer.wrap(arr2).getFloat(),
                ByteBuffer.wrap(arr3).getFloat(),
                ByteBuffer.wrap(arr4).getFloat()
        };
    }

    private LocalDateTime buildTime(byte[] arr, int i) {
        return LocalDateTime.of(
                2000 + Integer.parseInt(String.format("%X", arr[i + 3])),
                Integer.parseInt(String.format("%X", (int) arr[i + 2])),
                Integer.parseInt(String.format("%X", (int) arr[i + 1])),
                Integer.parseInt(String.format("%X", (int) arr[i])), 0, 0
        );
    }

    private void profileRamObtain() {
        byte[] biasBytes = {
                0x00, 0x10, 0x20, 0x30, 0x40, 0x50, 0x60,
//                0x70, 0x72
        };
        CommandRam commandRam = new CommandRam();
        for (int i = 0; i < biasBytes.length; i++) {
            byte[] arrBytePower = sendRequestVerification(commandRam.interactionRam(deviceCounterDto, biasBytes[i]), 26);

            byte[] arr = new byte[arrBytePower.length - 6];
            System.out.println(ByteBuffer.wrap(arr).getFloat());
        }
        correctCommandsCounter.add(CommandDeviceCounter.PROFILE_POWER_FLASH_512K);
        System.out.println();

    }


    private void recordingByDateObtain() {
        RecordingByDate recordingByDate = new RecordingByDate();
        byte[] arrBytePower = sendRequestVerification(recordingByDate.interactionRecording(deviceCounterDto), 30);

        for (int j = 0; j < arrBytePower.length - 1; j++) {
            System.out.printf("%X ", (int) arrBytePower[j] & 0xFF);
        }
    }
}