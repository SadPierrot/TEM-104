package com.company;

import com.company.comand.*;
import com.company.crc.CS_NOT;
import com.company.dto.*;
import com.company.exception.ExceedingAttemptsException;
import com.company.exception.InvalidCharacterException;
import com.company.util.WorkingWithArrays;

import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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
        return connectionChannel.interactionResultOnRequest(mainArr, size);
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
            arrBytePower = sendRequestVerification(
                    WorkingWithArrays.countArrays(
                            commandProfilePower.interactionBytePower(deviceCounterDto, b),
                            CS_NOT.calculate(commandProfilePower.interactionBytePower(deviceCounterDto, b))
                    ), 8);

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
        byte[] a;
        for (int x = 0; x < lastByte.length; x++) {
            arrBytePower = sendRequestVerification(
                    WorkingWithArrays.countArrays(
                            commandProfilePower2000.interactionBytePower(
                                    deviceCounterDto, lastByte[x], (byte) dataTypeAnswer[x]
                            ),
                            CS_NOT.calculate(commandProfilePower2000.interactionBytePower(
                                    deviceCounterDto, lastByte[x], (byte) dataTypeAnswer[x]
                            ))
                    ), 7 + dataTypeAnswer[x]
            );

            for (int j = 6; j < arrBytePower.length - 1; j++) {
                dateTime = dateTime.concat(String.format("%X", (int) arrBytePower[j] & 0xFF));
            }
            dateTime = dateTime.concat(" ");
        }
        profilePower2000Dto = commandProfilePower2000.buildProfileResponse2000(dateTime);
        correctCommandsCounter.add(CommandDeviceCounter.PROFILE_POWER_2000);
    }

    private void profilePowerObtainFlash512K() {
        LocalDateTime buffer1 = LocalDateTime.of(1, 1, 1, 1, 0);
        CommandProfilePowerFlash commandProfilePowerFlash = new CommandProfilePowerFlash();

        int count = 0;
        for (int i = 0; i < 393216; i = i + 256) {
            try {
                byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();

                byte[] biasBytes = {
                       0x00, 0x04, 0x08, 0x18, 0x28, 0x38, 0x48, 0x58, 0x68, 0x6C, 0x7C,
                        (byte) 0x8C, (byte) 0x9C, (byte) 0xAC, (byte) 0xBC, (byte) 0xC0,
                        (byte) 0xC8, (byte) 0xE0, (byte) 0xEC,

//                        (byte) 0xFF
                };

                byte[] byteOffsetSize = {
                        0x04, 0x04, 0x16, 0x16, 0x16, 0x16, 0x16,
                        0x16, 0x16, 0x16, 0x16, 0x16, 0x16, 0x16,
                        0x08, 0x08, 0x24, 0x30, 0x16, 0x16, 0x16, 0x16
                };

                System.out.println("Запись: " + count++);
                for (int k = 0; k < biasBytes.length; k++) {
                    byte[] arrBytePower = sendRequestVerification(
                            WorkingWithArrays.countArrays(
                                    commandProfilePowerFlash.interactionBytePower(
                                            deviceCounterDto, bytes, biasBytes[k], byteOffsetSize[k]),
                                    CS_NOT.calculate(commandProfilePowerFlash.interactionBytePower(
                                            deviceCounterDto, bytes, biasBytes[k], byteOffsetSize[k]))
                            ), 7 + byteOffsetSize[k]); //11

                    switch (biasBytes[k]) {
                        case 0x00, 0x04 -> System.out.println(commandProfilePowerFlash.buildRandDate(arrBytePower));
                        case 0x08, 0x18, 0x28 -> commandProfilePowerFlash.buildDataFlashFloat(arrBytePower);
                        case 0x38, 0x48, 0x58, 0x68, 0x6C, 0x7C,
                                (byte) 0x8C, (byte) 0x9C, (byte) 0xAC, (byte) 0xBC,
                                (byte) 0xC0, (byte) 0xEC -> commandProfilePowerFlash.buildDataFlashLong(arrBytePower);
                        case (byte) 0xC8, (byte) 0xE0 -> commandProfilePowerFlash.all(arrBytePower);
                        default -> System.out.println();
                    }
                }
            } catch (Exception e) {
                System.out.println("Ex" + e);
            }
            System.out.println();
        }

        profilePowerFlashDto = commandProfilePowerFlash.buildProfilePowerFlash(buffer1);
        correctCommandsCounter.add(CommandDeviceCounter.RAM);
    }

    private void profileRamObtain() {
        ArrayList<String> dateTime = new ArrayList<>();
        CommandRam commandRam = new CommandRam();
        byte[] arrBytePower = sendRequestVerification(
                WorkingWithArrays.countArrays(
                        commandRam.interactionRam(deviceCounterDto),
                        CS_NOT.calculate(commandRam.interactionRam(deviceCounterDto))
                ), 30);

        for (int j = 0; j < arrBytePower.length - 1; j++) {
            System.out.print(String.valueOf(arrBytePower[j]) + " ");
            dateTime.add(String.format("%X ", (int) arrBytePower[j] & 0xFF));
        }

        profilePowerRamObtainDto = commandRam.buildProfileResponseRamObtain(dateTime);
        correctCommandsCounter.add(CommandDeviceCounter.PROFILE_POWER_FLASH_512K);

    }


    private void recordingByDateObtain() {
        RecordingByDate recordingByDate = new RecordingByDate();
        byte[] arrBytePower = sendRequestVerification(
                WorkingWithArrays.countArrays(
                        recordingByDate.interactionRecording(deviceCounterDto),
                        CS_NOT.calculate(recordingByDate.interactionRecording(deviceCounterDto))
                ), 30);

        for (int j = 0; j < arrBytePower.length - 1; j++) {
            System.out.printf("%X ", (int) arrBytePower[j] & 0xFF);
        }
    }
}


//TODO: Смещение не побитовое, найти от какой точки идёт смещение (может быть от 0)