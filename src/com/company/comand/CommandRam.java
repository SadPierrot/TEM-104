package com.company.comand;

import com.company.dto.DeviceCounterDto;
import com.company.dto.ProfilePowerRamObtainDto;

import java.util.ArrayList;

public class CommandRam {
    public byte[] interactionRam(DeviceCounterDto deviceCounterDto, byte biasBytes) {
        return new byte[]{
                (byte) 0x55,
                (byte) deviceCounterDto.getNumberCounter(),
                (byte) reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                (byte) 0x0C,
                (byte) 0x01,
                (byte) 0x03,
                (byte) 0x22,
                (byte) biasBytes,
                (byte) 0x16
        };
    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }

}
