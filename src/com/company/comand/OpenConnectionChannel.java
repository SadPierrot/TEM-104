package com.company.comand;

import com.company.dto.DeviceCounterDto;

public class OpenConnectionChannel {

    public byte[] makeDialogueConnection(DeviceCounterDto deviceCounterDto) {

        return new byte[]{
                0x55,
                (byte) deviceCounterDto.getNumberCounter(),
                reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                0x00,
                0x00,
                0x00,
                checkSum(
                        0x55,
                        (byte) deviceCounterDto.getNumberCounter(),
                        reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                        0x00,
                        0x00,
                        0x00
                )
        };
    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }

    private byte checkSum(int i, byte numberCounter, byte reverseNumberCounter, int i1, int i2, int i3) {
        return (byte) ~(i+numberCounter+reverseNumberCounter+i1+i2+i3);
    }
}



