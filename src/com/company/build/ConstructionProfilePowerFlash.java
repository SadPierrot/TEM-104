package com.company.build;

import com.company.dto.ProfilePowerFlashDto;
import com.company.dto.ProfileResponse128Dto;

import java.time.LocalDateTime;

public class ConstructionProfilePowerFlash {

    public static LocalDateTime tekDat;
    public static LocalDateTime prevDat;
    public static float lIntV;
    public static float lIntM;
    public static float lIntQ;
    public static int hIntV;
    public static int hIntM;
    public static int hIntQ;
    public static int tRab;
    public static int tNar;
    public static int tMin;
    public static int tMax;
    public static int tDt;
    public static int tTn;
    public static int firTeher;
    public static int secTeher;
    public static int[] t;
    public static int[] p;
    public static int rshv;



    public static ProfilePowerFlashDto returnPowerObtainFlash512K() {
        return new ProfilePowerFlashDto(
                tekDat,prevDat,lIntV,lIntM,lIntQ,hIntV,hIntM,hIntQ,tRab,tNar,tMin,tMax,tDt,tTn,firTeher,secTeher,t,p,rshv
        );
    }
}
