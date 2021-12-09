package com.company.comand;

import com.company.dto.DeviceCounterDto;

public class RecordingByDate {
    public byte[] interactionRecording(DeviceCounterDto deviceCounterDto) {
        return new byte[]{
                (byte) 0x55,
                (byte) deviceCounterDto.getNumberCounter(),
                (byte) reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                (byte) 0x0F,
                (byte) 0x03,
                (byte) 0x05,
                (byte) 0x00,
                (byte) 0x00,
                (byte) 0x01,
                (byte) 0x00,
                (byte) 0x00
        };

    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }
}
