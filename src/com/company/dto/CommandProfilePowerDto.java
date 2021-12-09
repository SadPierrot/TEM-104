package com.company.dto;

public class CommandProfilePowerDto {
    private final ProfileFeatureDto conditionDto;
    private final LocalDateTimeDto timeDto;
    private final PeriodDto periodDto;

    public CommandProfilePowerDto(ProfileFeatureDto conditionDto, LocalDateTimeDto timeDto, PeriodDto periodDto) {
        this.conditionDto = conditionDto;
        this.timeDto = timeDto;
        this.periodDto = periodDto;
    }

    public ProfileFeatureDto getConditionDto() {
        return conditionDto;
    }

    public LocalDateTimeDto getTimeDto() {
        return timeDto;
    }

    public PeriodDto getPeriodDto() {
        return periodDto;
    }
}


