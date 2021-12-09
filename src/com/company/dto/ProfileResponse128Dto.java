package com.company.dto;

import java.time.LocalDateTime;

public class ProfileResponse128Dto {
    private final LocalDateTime localDateTime;
    private final String profileError;

    public ProfileResponse128Dto(LocalDateTime localDateTime, String profileError) {
        this.localDateTime = localDateTime;
        this.profileError = profileError;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getProfileError() {
        return profileError;
    }
}
