package com.company.dto;

import com.company.comand.CommandDeviceCounter;

import java.util.List;

public class ResponseDeviceDTO {
    int numberCounter;
    List<CommandDeviceCounter> commandsCounterPrint;
    ProfileResponse128Dto profilePower128Dto;
    ProfileResponse2000Dto profilePower2000Dto;
    ProfilePowerFlashDto profilePowerFlash;
    ProfilePowerRamObtainDto profilePowerRamObtainDto;


    public ResponseDeviceDTO(int numberCounter, List<CommandDeviceCounter> commandsCounterPrint,
                             ProfileResponse128Dto profilePower128Dto, ProfileResponse2000Dto profilePower2000Dto,
                             ProfilePowerFlashDto profilePowerFlash, ProfilePowerRamObtainDto profilePowerRamObtainDto) {

        this.numberCounter = numberCounter;
        this.commandsCounterPrint = commandsCounterPrint;
        this.profilePower128Dto = profilePower128Dto;
        this.profilePower2000Dto = profilePower2000Dto;
        this.profilePowerFlash = profilePowerFlash;
        this.profilePowerRamObtainDto = profilePowerRamObtainDto;
    }

    public int getNumberCounter() {
        return numberCounter;
    }

    public void setNumberCounter(int numberCounter) {
        this.numberCounter = numberCounter;
    }

    public List<CommandDeviceCounter> getCommandsCounterPrint() {
        return commandsCounterPrint;
    }

    public void setCommandsCounterPrint(List<CommandDeviceCounter> commandsCounterPrint) {
        this.commandsCounterPrint = commandsCounterPrint;
    }

    public ProfileResponse128Dto getProfilePower128Dto() {
        return profilePower128Dto;
    }

    public void setProfilePower128Dto(ProfileResponse128Dto profilePower128Dto) {
        this.profilePower128Dto = profilePower128Dto;
    }

    public ProfileResponse2000Dto getProfilePower2000Dto() {
        return profilePower2000Dto;
    }

    public void setProfilePower2000Dto(ProfileResponse2000Dto profilePower2000Dto) {
        this.profilePower2000Dto = profilePower2000Dto;
    }

    public ProfilePowerFlashDto getProfilePowerFlash() {
        return profilePowerFlash;
    }

    public void setProfilePowerFlash(ProfilePowerFlashDto profilePowerFlash) {
        this.profilePowerFlash = profilePowerFlash;
    }

    public ProfilePowerRamObtainDto getProfilePowerRamObtainDto() {
        return profilePowerRamObtainDto;
    }

    public void setProfilePowerRamObtainDto(ProfilePowerRamObtainDto profilePowerRamObtainDto) {
        this.profilePowerRamObtainDto = profilePowerRamObtainDto;
    }
}
