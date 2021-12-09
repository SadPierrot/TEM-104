package com.company.comand;

import com.company.dto.DeviceCounterDto;
import com.company.dto.ProfilePowerRamObtainDto;

import java.util.ArrayList;

public class CommandRam {
    public byte[] interactionRam(DeviceCounterDto deviceCounterDto) {
        return new byte[]{
                (byte) 0x55,
                (byte) deviceCounterDto.getNumberCounter(),
                (byte) reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                (byte) 0x0C,
                (byte) 0x01,
                (byte) 0x03,
                (byte) 0x22,
                (byte) 0x00,
                (byte) 0x40
        };
    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }

    public ProfilePowerRamObtainDto buildProfileResponseRamObtain(ArrayList<String> dateTime) {
        return new ProfilePowerRamObtainDto(
                createTmp(dateTime)
        );
    }

    private String createTmp(ArrayList<String> dateTime) {
        return dateTime.get(0) +
                dateTime.get(1) +
                dateTime.get(2) +
                dateTime.get(3) +
                dateTime.get(4) +
                dateTime.get(5) +
                dateTime.get(6) +
                dateTime.get(7);
    }

}
