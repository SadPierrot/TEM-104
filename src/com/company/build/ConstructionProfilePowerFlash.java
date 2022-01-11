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
    public static float hIntV;
    public static float hIntM;
    public static float hIntQ;
    public static float tRab;
    public static float tNar;
    public static float tMin;
    public static float tMax;
    public static float tDt;
    public static float tTn;
    public static float firTeher;
    public static float secTeher;
    public static int[] t;
    public static int[] p;
    public static float rshv;



    public static ProfilePowerFlashDto returnPowerObtainFlash512K() {
        return new ProfilePowerFlashDto(
                tekDat,prevDat,lIntV,lIntM,lIntQ,hIntV,hIntM,hIntQ,tRab,tNar,tMin,tMax,tDt,tTn,firTeher,secTeher,t,p,rshv
        );
    }
}
