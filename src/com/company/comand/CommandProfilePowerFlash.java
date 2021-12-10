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

    public void buildDataFlashFloat(byte[] arrBytePower, byte biasByte) {
        byte[] arr = new byte[arrBytePower.length - 6];
        int count = 0;
        for (int i = 6; i < arrBytePower.length; i++) {
            arr[count++] = arrBytePower[i];
        }

        if (biasByte == 0x08) {
            ConstructionProfilePowerFlash.lIntV = ByteBuffer.wrap(arr).getFloat();
            System.out.println("Дробная часть интеграторов объема по каналам:   " + ByteBuffer.wrap(arr).getFloat() + " м³");
        } else if (biasByte == 0x18) {
            ConstructionProfilePowerFlash.lIntM = ByteBuffer.wrap(arr).getFloat();
            System.out.println("Дробная часть интеграторов массы по каналам:    " + ByteBuffer.wrap(arr).getFloat() + " т");
        } else if (biasByte == 0x28) {
            ConstructionProfilePowerFlash.lIntQ = ByteBuffer.wrap(arr).getFloat();
            System.out.println("Дробная часть интеграторов энергии по системам: " + ByteBuffer.wrap(arr).getFloat() + " МВт");
        } else {
            System.out.println("Err");
        }
    }

    public void all(byte[] arrBytePower, byte biasByte) {

        byte[] arr1 = Arrays.copyOfRange(arrBytePower, 6, 10);
        byte[] arr2 = Arrays.copyOfRange(arrBytePower, 10, 14);
        byte[] arr3 = Arrays.copyOfRange(arrBytePower, 14, 18);

        int[] arr = {
                ByteBuffer.wrap(arr1).getInt(),
                ByteBuffer.wrap(arr2).getInt(),
                ByteBuffer.wrap(arr3).getInt()
        };

        if (biasByte == (byte) 0xC8) {
            ConstructionProfilePowerFlash.t = arr;
            System.out.println("Температура по системам:                        " + arr[0] + " " + arr[1] + " " + arr[2] + " °C/100");
        } else if (biasByte == (byte) 0xE0) {
            ConstructionProfilePowerFlash.p = arr;
            System.out.println("Давление по системам:                           " + arr[0]  + " " + arr[1] + " " + arr[2] + " МПа/100");
        }

    }

    public void buildCheckSum(byte[] arrBytePower, byte biasByte) {

    }

    public void buildDataFlashLong(byte[] arrBytePower, byte biasByte) {
        byte[] arr = new byte[arrBytePower.length - 6];
        int count = 0;
        for (int i = 6; i < arrBytePower.length - 6; i++) {
            arr[count++] = arrBytePower[i];
        }
        if (biasByte == 0x38) {
            ConstructionProfilePowerFlash.hIntV = ByteBuffer.wrap(arr).getInt();
            System.out.println("Целая часть интеграторов объема по каналам:     " + ByteBuffer.wrap(arr).getInt() + " м³");
        } else if (biasByte == 0x48) {
            ConstructionProfilePowerFlash.hIntM = ByteBuffer.wrap(arr).getInt();
            System.out.println("Целая часть интеграторов массы по каналам:      " + ByteBuffer.wrap(arr).getInt() + " Т");
        } else if (biasByte == 0x58) {
            ConstructionProfilePowerFlash.hIntQ = ByteBuffer.wrap(arr).getInt();
            System.out.println("Целая часть интеграторов энергии по системам:   " + ByteBuffer.wrap(arr).getInt() + " МВт");
        } else if (biasByte == 0x68) {
            ConstructionProfilePowerFlash.tRab = ByteBuffer.wrap(arr).getInt();
            System.out.println("Время работы прибора при поданном питании:      " + ByteBuffer.wrap(arr).getInt() + " сек");
        } else if (biasByte == 0x6C) {
            ConstructionProfilePowerFlash.tNar = ByteBuffer.wrap(arr).getInt();
            System.out.println("Время работы систем без ошибок:                 " + ByteBuffer.wrap(arr).getInt() + " сек");
        } else if (biasByte == 0x7C) {
            ConstructionProfilePowerFlash.tMin = ByteBuffer.wrap(arr).getInt();
            System.out.println("Расход меньше минимального:                     " + ByteBuffer.wrap(arr).getInt() + " сек");
        } else if (biasByte == (byte) 0x8C) {
            ConstructionProfilePowerFlash.tMax = ByteBuffer.wrap(arr).getInt();
            System.out.println("Расход больше максимального:                    " + ByteBuffer.wrap(arr).getInt() + " сек");
        } else if (biasByte == (byte) 0x9C) {
            ConstructionProfilePowerFlash.tDt = ByteBuffer.wrap(arr).getInt();
            System.out.println("Разность температур меньше минимальной:         " + ByteBuffer.wrap(arr).getInt() + " сек");
        } else if (biasByte == (byte) 0xAC) {
            ConstructionProfilePowerFlash.tTn = ByteBuffer.wrap(arr).getInt();
            System.out.println("Техническая неисправность:                      " + ByteBuffer.wrap(arr).getInt() + " сек");
        } else if (biasByte == (byte) 0xBC) {
            ConstructionProfilePowerFlash.firTeher = ByteBuffer.wrap(arr).getInt();
            System.out.println("Ошибки по системам (1):                         " + ByteBuffer.wrap(arr).getInt());
        } else if (biasByte == (byte) 0xC0) {
            ConstructionProfilePowerFlash.secTeher = ByteBuffer.wrap(arr).getInt();
            System.out.println("Ошибки по системам (2):                         " + ByteBuffer.wrap(arr).getInt());
        } else if (biasByte == (byte) 0xEC) {
            ConstructionProfilePowerFlash.rshv = ByteBuffer.wrap(arr).getInt();
            System.out.println("Интеграторы объемного расхода по каналам:       " + ByteBuffer.wrap(arr).getInt() + " м³/ч");
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
