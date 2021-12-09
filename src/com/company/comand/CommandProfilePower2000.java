package com.company.comand;

import com.company.dto.DeviceCounterDto;
import com.company.dto.ProfileResponse2000Dto;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandProfilePower2000 {
    public byte[] interactionBytePower(DeviceCounterDto deviceCounterDto, byte leastByte, byte dataTypeAnswer) {
        return new byte[]{
                (byte) 0x55,
                (byte) deviceCounterDto.getNumberCounter(),
                (byte) reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                (byte) 0x0F,
                (byte) 0x01,
                (byte) 0x03,
                (byte) 0x00,
                (byte) leastByte,
                (byte) dataTypeAnswer
        };
    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }

    public ProfileResponse2000Dto buildProfileResponse2000(String dateTime) {

        String s1[] = dateTime.split("[ ]+");
        ArrayList<String> dateTimeArr = new ArrayList<>(Arrays.asList(s1));

        return new ProfileResponse2000Dto(
                (int) Long.parseLong(dateTimeArr.get(0),16),
                (int) Long.parseLong(dateTimeArr.get(1),16),
                (int) Long.parseLong(dateTimeArr.get(2),16),
                (int) Long.parseLong(dateTimeArr.get(3),16),
                (int) Long.parseLong(dateTimeArr.get(4),16),
                (int) Long.parseLong(dateTimeArr.get(5),16),
//                (int) Long.parseLong(dateTimeArr.get(6),16),
                dateTimeArr.get(6),
                (int) Long.parseLong(dateTimeArr.get(7),16),
                (int) Long.parseLong(dateTimeArr.get(8),16),
                (int) Long.parseLong(dateTimeArr.get(9),16),
                (int) Long.parseLong(dateTimeArr.get(10),16),
                (int) Long.parseLong(dateTimeArr.get(11),16),
                (int) Long.parseLong(dateTimeArr.get(12),16),
                (int) Long.parseLong(dateTimeArr.get(13),16)
        );
    }
}



