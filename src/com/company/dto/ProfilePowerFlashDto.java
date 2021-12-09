package com.company.dto;

import java.time.LocalDateTime;

public class ProfilePowerFlashDto {
    LocalDateTime time;

    public ProfilePowerFlashDto(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
