package com.company.dto;

import java.time.LocalDateTime;

public class ReadPowerAnswerDto {
    private final int tariff;
    private final boolean profile;
    private final boolean seasonTime;
    private final boolean fulfillment;
    private final boolean incomplete;
    private final boolean flagOverflow;
    private final LocalDateTime time;
    private final int period;

    public ReadPowerAnswerDto(int tariff, boolean profile, boolean seasonTime, boolean fulfillment, boolean incomplete, boolean flagOverflow, LocalDateTime time, int period) {
        this.tariff = tariff;
        this.profile = profile;
        this.seasonTime = seasonTime;
        this.fulfillment = fulfillment;
        this.incomplete = incomplete;
        this.flagOverflow = flagOverflow;
        this.time = time;
        this.period = period;
    }

    public int getTariff() {
        return tariff;
    }

    public boolean isProfile() {
        return profile;
    }

    public boolean isSeasonTime() {
        return seasonTime;
    }

    public boolean isFulfillment() {
        return fulfillment;
    }

    public boolean isIncomplete() {
        return incomplete;
    }

    public boolean isFlagOverflow() {
        return flagOverflow;
    }

    public LocalDateTime getObjectTime() {
        return time;
    }

    public int getPeriod() {
        return period;
    }
}
