package com.company.comand;

import com.company.build.ConstructionProfileResponse128;
import com.company.dto.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommandProfilePower128 {

    public byte[] interactionBytePower(DeviceCounterDto deviceCounterDto, byte byteTime) {
        return new byte[]{
                (byte) 0x55,
                (byte) deviceCounterDto.getNumberCounter(),
                (byte) reverseNumberCounter(deviceCounterDto.getNumberCounter()),
                (byte) 0x0F,
                (byte) 0x02,
                (byte) 0x02,
                (byte) byteTime,
                (byte) 0x01
        };
    }

    public ProfileResponse128Dto buildProfileResponse128(CommandProfilePower128 commandProfilePower, ArrayList<String> dateTime) {
        ConstructionProfileResponse128.localDateTime =
                commandProfilePower.formattingDateTime(dateTime);
        ConstructionProfileResponse128.errorProfile =
                commandProfilePower.disassembleIntoErrorMessage(Integer.parseInt(dateTime.get(dateTime.size() - 1)));

        if (ConstructionProfileResponse128.localDateTime == null || ConstructionProfileResponse128.errorProfile == null) {
            throw new NullPointerException("localDateTime или errorProfile равны нулю");
        }
        return ConstructionProfileResponse128.returnProfilePower128();
    }

    private LocalDateTime formattingDateTime(ArrayList<String> dateTime) {
        return LocalDateTime.of(
                Integer.parseInt(dateTime.get(5)) + 2000,
                Integer.parseInt(dateTime.get(4)),
                Integer.parseInt(dateTime.get(3)),
                Integer.parseInt(dateTime.get(2)),
                Integer.parseInt(dateTime.get(1)),
                Integer.parseInt(dateTime.get(0))
        );
    }

    private String disassembleIntoErrorMessage(int dateTime) {
        return switch (dateTime) {
            case 0 -> "G1 < min";
            case 1 -> "G2 < min";
            case 2 -> "G1 > max";
            case 3 -> "G2 > max";
            case 4 -> "dt < min";
            case 5 -> "техническая неисправность канала температуры";
            case 6 -> "техническая неисправность канала давления";
            case 7 -> "выключение питания";
            default -> "ERROR";
        };
    }

    private byte reverseNumberCounter(int num) {
        return (byte) (~Integer.parseInt(String.valueOf(num)) & 0xFF);
    }


}
