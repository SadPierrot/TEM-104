package com.company.dto;

public class ProfileFeatureDto {
    private final int tariff;
    private final boolean profile;
    private final boolean seasonTime;
    private final boolean fulfillment;
    private final boolean incomplete;
    private final boolean flagOverflow;

    public ProfileFeatureDto(int tariff, boolean profile, boolean seasonTime, boolean fulfillment, boolean incomplete, boolean flagOverflow) {
        this.tariff = tariff;
        this.profile = profile;
        this.seasonTime = seasonTime;
        this.fulfillment = fulfillment;
        this.incomplete = incomplete;
        this.flagOverflow = flagOverflow;
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
}