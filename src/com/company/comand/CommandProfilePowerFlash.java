package com.company.comand;

import com.company.dto.DeviceCounterDto;
import com.company.dto.ProfilePowerFlashDto;

import javax.annotation.processing.SupportedSourceVersion;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
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

    public ProfilePowerFlashDto buildProfilePowerFlash(LocalDateTime format) {
        return new ProfilePowerFlashDto(format);
    }

    public void buildDataFlashFloat(byte[] arrBytePower) {
        byte[] arr = new byte[arrBytePower.length - 6];
        int count = 0;
        for (int i = 6; i < arrBytePower.length; i++) {
            arr[count++] = arrBytePower[i];
        }
        System.out.println(ByteBuffer.wrap(arr).getFloat());
    }

    public void all(byte[] arrBytePower) {

        byte[] arr1 = Arrays.copyOfRange(arrBytePower, 6, 10);
        byte[] arr2 = Arrays.copyOfRange(arrBytePower, 10, 14);
        byte[] arr3 = Arrays.copyOfRange(arrBytePower, 14, 18);

//        System.out.println("Last arr1 " + Arrays.toString(Arrays.copyOfRange(arrBytePower, 6, 10)));
//        System.out.println("Last arr2 " + Arrays.toString(Arrays.copyOfRange(arrBytePower, 10, 14)));
//        System.out.println("Last arr3 " + Arrays.toString(Arrays.copyOfRange(arrBytePower, 14, 18)));
        //437982432 1513226240
        //435098827 1513226240

        System.out.print(ByteBuffer.wrap(arr1).getInt() + " ");
        System.out.print(ByteBuffer.wrap(arr2).getInt() + " ");
        System.out.print(ByteBuffer.wrap(arr3).getInt() + " ");
    }

    public void buildDataFlashLong(byte[] arrBytePower) {
        byte[] arr = new byte[arrBytePower.length - 6];
        int count = 0;
        for (int i = 6; i < arrBytePower.length - 6; i++) {
            arr[count++] = arrBytePower[i];
        }
        System.out.println(ByteBuffer.wrap(arr).getInt());
    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }


    public LocalDateTime buildRandDate(byte[] arrBytePower) {
        return LocalDateTime.of(
                2000 + Integer.parseInt(String.format("%X", arrBytePower[9])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[8])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[7])),
                Integer.parseInt(String.format("%X", (int) arrBytePower[6])), 0, 0
        );
    }
}
