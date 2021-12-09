package com.company.build;

import com.company.dto.ProfileResponse128Dto;

import java.time.LocalDateTime;

public class ConstructionProfileResponse128 {

    public static LocalDateTime localDateTime;
    public static String errorProfile;

    public static ProfileResponse128Dto returnProfilePower128() {
        return new ProfileResponse128Dto(
                localDateTime, errorProfile
        );
    }
}
