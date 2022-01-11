package com.company.comand;

import com.company.build.ConstructionProfilePowerFlash;
import com.company.dto.DeviceCounterDto;
import com.company.dto.ProfilePowerFlashDto;

import javax.annotation.processing.SupportedSourceVersion;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class CommandProfilePowerFlash {

    public byte[] interactionBytePower(DeviceCounterDto deviceCounterDto, byte[] arr, byte biasBytes, byte byteOffsetSize) {

        return new byte[]{
                (byte) 0x55,
                (byte) deviceCounterDto.getNumberCounter(),
                (byte) reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                (byte) 0x0F,
                (byte) 0x03,
                (byte) 0x05,
                byteOffsetSize,
                arr[0],
                arr[1],
                arr[2],
                (byte) (arr[3] + biasBytes)
        };
    }

    public void buildDataRecording(byte[] arrBytePower) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTimeCurrent = LocalDateTime.of(
                2000 + Integer.parseInt(String.format("%X", arrBytePower[9])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[8])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[7])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[6])), 0, 0
        );
        LocalDateTime localDateTimePast = LocalDateTime.of(
                2000 + Integer.parseInt(String.format("%X", arrBytePower[13])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[12])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[11])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[10])), 0, 0
        );

//            ConstructionProfilePowerFlash.tekDat = localDateTime;
            System.out.println("Время и дата записи:       " + localDateTimeCurrent.format(formatter));
//            ConstructionProfilePowerFlash.prevDat = localDateTime;
            System.out.println("Время и дата предыдущей:                        " + localDateTimePast.format(formatter));
//            ConstructionProfilePowerFlash.lIntV = ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Дробная часть интеграторов объема по каналам:   " +
//                    mainArr[0] + " м³" + " " +
//                    mainArr[1] + " м³" + " " +
//                    mainArr[2] + " м³" + " " +
//                    mainArr[3] + " м³");
    }

//    public void buildDataFlashFloat(byte[] arrBytePower) {
//
//        byte[] arr = new byte[arrBytePower.length - 6];
//        int count = 0;
//        for (int i = 6; i < arrBytePower.length; i++) {
//            arr[count++] = arrBytePower[i];
//        }
//        byte[] arr1 = {arr[0], arr[1], arr[2], arr[3]};
//        byte[] arr2 = {arr[4], arr[5], arr[6], arr[7]};
//        byte[] arr3 = {arr[8], arr[9], arr[10], arr[11]};
//        byte[] arr4 = {arr[12], arr[13], arr[14], arr[15]};
//
//        float[] mainArr = {
//                ByteBuffer.wrap(arr1).getFloat(),
//                ByteBuffer.wrap(arr2).getFloat(),
//                ByteBuffer.wrap(arr3).getFloat(),
//                ByteBuffer.wrap(arr4).getFloat()
//        };
//
//        if (biasByte == 0x08) {
//            ConstructionProfilePowerFlash.lIntV = ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Дробная часть интеграторов объема по каналам:   " +
//                    mainArr[0] + " м³" + " " +
//                    mainArr[1] + " м³" + " " +
//                    mainArr[2] + " м³" + " " +
//                    mainArr[3] + " м³");
//
//        } else if (biasByte == 0x18) {
//            ConstructionProfilePowerFlash.lIntM = ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Дробная часть интеграторов массы по каналам:    " +
//                    mainArr[0] + " т" + " " +
//                    mainArr[1] + " т" + " " +
//                    mainArr[2] + " т" + " " +
//                    mainArr[3] + " т");
//
//        } else if (biasByte == 0x28) {
//            ConstructionProfilePowerFlash.lIntQ = ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Дробная часть интеграторов энергии по системам: " +
//                    mainArr[0] + " МВт" + " " +
//                    mainArr[1] + " МВт" + " " +
//                    mainArr[2] + " МВт" + " " +
//                    mainArr[3] + " МВт");
//
//        } else if (biasByte == 0x38) {
//            ConstructionProfilePowerFlash.hIntV = ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Целая часть интеграторов объема по каналам:     " +
//                    mainArr[0] + " м³" + " " +
//                    mainArr[1] + " м³" + " " +
//                    mainArr[2] + " м³" + " " +
//                    mainArr[3] + " м³");
//
//        } else if (biasByte == 0x48) {
//            ConstructionProfilePowerFlash.hIntM = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Целая часть интеграторов массы по каналам:      " +
//                    mainArr[0] + " Т" + " " +
//                    mainArr[1] + " Т" + " " +
//                    mainArr[2] + " Т" + " " +
//                    mainArr[3] + " Т");
//
//        } else if (biasByte == 0x58) {
//            ConstructionProfilePowerFlash.hIntQ = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Целая часть интеграторов энергии по системам:   " +
//                    mainArr[0] + " МВт" + " " +
//                    mainArr[1] + " МВт" + " " +
//                    mainArr[2] + " МВт" + " " +
//                    mainArr[3] + " МВт");
//
//        } else if (biasByte == 0x68) {
//            ConstructionProfilePowerFlash.tRab = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Время работы прибора при поданном питании:      " + mainArr[0] + " сек");
//        } else if (biasByte == 0x6C) {
//            ConstructionProfilePowerFlash.tNar = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Время работы систем без ошибок:                 " +
//                    mainArr[0] + " сек" + " " +
//                    mainArr[1] + " сек" + " " +
//                    mainArr[2] + " сек" + " " +
//                    mainArr[3] + " сек");
//
//        } else if (biasByte == 0x7C) {
//            ConstructionProfilePowerFlash.tMin = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Расход меньше минимального:                     " +
//                    mainArr[0] + " сек" + " " +
//                    mainArr[1] + " сек" + " " +
//                    mainArr[2] + " сек" + " " +
//                    mainArr[3] + " сек");
//
//        } else if (biasByte == (byte) 0x8C) {
//            ConstructionProfilePowerFlash.tMax = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Расход больше максимального:                    " +
//                    mainArr[0] + " сек" + " " +
//                    mainArr[1] + " сек" + " " +
//                    mainArr[2] + " сек" + " " +
//                    mainArr[3] + " сек");
//        } else if (biasByte == (byte) 0x9C) {
//            ConstructionProfilePowerFlash.tDt = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Разность температур меньше минимальной:         " +
//                    mainArr[0] + " сек" + " " +
//                    mainArr[1] + " сек" + " " +
//                    mainArr[2] + " сек" + " " +
//                    mainArr[3] + " сек");
//
//        } else if (biasByte == (byte) 0xAC) {
//            ConstructionProfilePowerFlash.tTn = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Техническая неисправность:                      " +
//                    mainArr[0] + " сек" + " " +
//                    mainArr[1] + " сек" + " " +
//                    mainArr[2] + " сек" + " " +
//                    mainArr[3] + " сек");
//        } else if (biasByte == (byte) 0xEC) {
//            ConstructionProfilePowerFlash.rshv = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Интеграторы объемного расхода по каналам:       " +
//                    mainArr[0] + " м³/ч" + " " +
//                    mainArr[1] + " м³/ч" + " " +
//                    mainArr[2] + " м³/ч" + " " +
//                    mainArr[3] + " м³/ч");
//        } else {
//            System.out.println("Err");
//        }
//    }

    public void  buildDataOther(byte[] arrBytePower, byte biasByte) {
        byte[] arr = new byte[arrBytePower.length - 6];
        int count = 0;
        for (int i = 6; i < arrBytePower.length; i++) {
            arr[count++] = arrBytePower[i];
        }

        if (biasByte == (byte) 0xBC) {
            ConstructionProfilePowerFlash.firTeher = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Ошибки по системам (1):                         "
                    + arr[0] + " " + arr[1] + " " +  arr[2] + " " +  arr[3]);

        }
//        else if (biasByte == (byte) 0xC0) {
//            ConstructionProfilePowerFlash.secTeher = (int) ByteBuffer.wrap(arr).getFloat();
//            System.out.println("Ошибки по системам (2):                         " +
//                    ByteBuffer.wrap(arr).getChar());
//        }
    }
    public void all(byte[] arrBytePower, byte biasByte) {

        byte[] arr1 = Arrays.copyOfRange(arrBytePower, 6, 8);
        byte[] arr2 = Arrays.copyOfRange(arrBytePower, 8, 10);
        byte[] arr3 = Arrays.copyOfRange(arrBytePower, 10, 12);
        byte[] arr4 = Arrays.copyOfRange(arrBytePower, 12, 14);

        int[] arr = {
                ByteBuffer.wrap(arr1).getShort(),
                ByteBuffer.wrap(arr2).getShort(),
                ByteBuffer.wrap(arr3).getShort(),
                ByteBuffer.wrap(arr4).getShort()
        };

        if (biasByte == (byte) 0xC8) {
            ConstructionProfilePowerFlash.t = arr;
            System.out.println("Температура по системам:                        " + arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " °C/100");
        } else if (biasByte == (byte) 0xE0) {
            ConstructionProfilePowerFlash.p = arr;
            System.out.println("Давление по системам:                           " + arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " МПа/100");
        }
    }

    public void buildCheckSum(byte[] arrBytePower, byte biasByte) {
        System.out.print(arrBytePower[0] + " ");
        System.out.print(arrBytePower[1] + " ");
        System.out.print(arrBytePower[2] + " ");
        System.out.print(arrBytePower[3] + " ");
        System.out.print(arrBytePower[4] + " ");
        System.out.print(arrBytePower[5] + " ");
        System.out.print(arrBytePower[6] + " ");
        System.out.print(arrBytePower[7] + " ");
        System.out.print(arrBytePower[8] + " ");
    }

    public void buildDataFlashLong(byte[] arrBytePower, byte biasByte) {

        byte[] arr = new byte[arrBytePower.length - 6];
        int count = 0;
        for (int i = 6; i < arrBytePower.length - 6; i++) {
            arr[count++] = arrBytePower[i];
        }
        if (biasByte == 0x38) {
            ConstructionProfilePowerFlash.hIntV = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Целая часть интеграторов объема по каналам:     " + ByteBuffer.wrap(arr).getFloat() + " м³");
        } else if (biasByte == 0x48) {
            ConstructionProfilePowerFlash.hIntM = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Целая часть интеграторов массы по каналам:      " + ByteBuffer.wrap(arr).getFloat() + " Т");
        } else if (biasByte == 0x58) {
            ConstructionProfilePowerFlash.hIntQ = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Целая часть интеграторов энергии по системам:   " + ByteBuffer.wrap(arr).getFloat() + " МВт");
        } else if (biasByte == 0x68) {
            ConstructionProfilePowerFlash.tRab = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Время работы прибора при поданном питании:      " + ByteBuffer.wrap(arr).getFloat() + " сек");
        } else if (biasByte == 0x6C) {
            ConstructionProfilePowerFlash.tNar = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Время работы систем без ошибок:                 " + ByteBuffer.wrap(arr).getFloat() + " сек");
        } else if (biasByte == 0x7C) {
            ConstructionProfilePowerFlash.tMin = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Расход меньше минимального:                     " + ByteBuffer.wrap(arr).getFloat() + " сек");
        } else if (biasByte == (byte) 0x8C) {
            ConstructionProfilePowerFlash.tMax = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Расход больше максимального:                    " + ByteBuffer.wrap(arr).getFloat() + " сек");
        } else if (biasByte == (byte) 0x9C) {
            ConstructionProfilePowerFlash.tDt = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Разность температур меньше минимальной:         " + ByteBuffer.wrap(arr).getFloat() + " сек");
        } else if (biasByte == (byte) 0xAC) {
            ConstructionProfilePowerFlash.tTn = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Техническая неисправность:                      " + ByteBuffer.wrap(arr).getFloat() + " сек");
        } else if (biasByte == (byte) 0xBC) {
            ConstructionProfilePowerFlash.firTeher = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Ошибки по системам (1):                         " + ByteBuffer.wrap(arr).getFloat());
        } else if (biasByte == (byte) 0xC0) {
            ConstructionProfilePowerFlash.secTeher = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Ошибки по системам (2):                         " + ByteBuffer.wrap(arr).getFloat());
        } else if (biasByte == (byte) 0xEC) {
            ConstructionProfilePowerFlash.rshv = (int) ByteBuffer.wrap(arr).getFloat();
            System.out.println("Интеграторы объемного расхода по каналам:       " + ByteBuffer.wrap(arr).getFloat() + " м³/ч");
        } else {
            System.out.println("Err");
        }
    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }


    public void buildRandDate(byte[] arrBytePower, byte biasByte) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.of(
                2000 + Integer.parseInt(String.format("%X", arrBytePower[9])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[8])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[7])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[6])), 0, 0
        );

        if (biasByte == 0x00) {
            ConstructionProfilePowerFlash.tekDat = localDateTime;
            System.out.println("Время и дата записи:                            " + localDateTime.format(formatter));
        } else if (biasByte == 0x04) {
            ConstructionProfilePowerFlash.prevDat = localDateTime;
            System.out.println("Время и дата предыдущей:                        " + localDateTime.format(formatter));
        } else {
            System.out.println("Нет байта");
        }
    }



}
