package com.company.dto;

import com.company.comand.CommandDeviceCounter;

import java.util.List;

public class DeviceCounterDto {

    private final int numberCounter;
    private final List<CommandDeviceCounter> commandsCounter;
    private final int repetitions;

    public DeviceCounterDto(int numberCounter, List<CommandDeviceCounter> commandsCounter, int repetitions) {
        this.numberCounter = numberCounter;
        this.commandsCounter = commandsCounter;
        this.repetitions = repetitions;
    }

    public int getNumberCounter() {
        return numberCounter;
    }

    public List<CommandDeviceCounter> getCommandsCounter() {
        return commandsCounter;
    }

    public int getRepetitions() {
        return repetitions;
    }
}