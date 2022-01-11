package com.company.dto;

import java.time.LocalDateTime;

public class ProfilePowerFlashDto {
    LocalDateTime tekDat;
    LocalDateTime prevDat;
    float lIntV;
    float lIntM;
    float lIntQ;
    float hIntV;
    float hIntM;
    float hIntQ;
    float tRab;
    float tNar;
    float tMin;
    float tMax;
    float tDt;
    float tTn;
    float firTeher;
    float secTeher;
    int[] t;
    int[] p;
    float rshv;

    public ProfilePowerFlashDto(LocalDateTime tekDat, LocalDateTime prevDat,
                                float lIntV, float lIntM, float lIntQ, float hIntV, float hIntM,
                                float hIntQ, float tRab, float tNar, float tMin, float tMax, float tDt,
                                float tTn, float firTeher, float secTeher, int[] t, int[] p, float rshv) {
        this.tekDat = tekDat;
        this.prevDat = prevDat;
        this.lIntV = lIntV;
        this.lIntM = lIntM;
        this.lIntQ = lIntQ;
        this.hIntV = hIntV;
        this.hIntM = hIntM;
        this.hIntQ = hIntQ;
        this.tRab = tRab;
        this.tNar = tNar;
        this.tMin = tMin;
        this.tMax = tMax;
        this.tDt = tDt;
        this.tTn = tTn;
        this.firTeher = firTeher;
        this.secTeher = secTeher;
        this.t = t;
        this.p = p;
        this.rshv = rshv;
    }

    public LocalDateTime getTekDat() {
        return tekDat;
    }

    public void setTekDat(LocalDateTime tekDat) {
        this.tekDat = tekDat;
    }

    public LocalDateTime getPrevDat() {
        return prevDat;
    }

    public void setPrevDat(LocalDateTime prevDat) {
        this.prevDat = prevDat;
    }

    public float getlIntV() {
        return lIntV;
    }

    public void setlIntV(float lIntV) {
        this.lIntV = lIntV;
    }

    public float getlIntM() {
        return lIntM;
    }

    public void setlIntM(float lIntM) {
        this.lIntM = lIntM;
    }

    public float getlIntQ() {
        return lIntQ;
    }

    public void setlIntQ(float lIntQ) {
        this.lIntQ = lIntQ;
    }

    public float gethIntV() {
        return hIntV;
    }

    public void sethIntV(float hIntV) {
        this.hIntV = hIntV;
    }

    public float gethIntM() {
        return hIntM;
    }

    public void sethIntM(float hIntM) {
        this.hIntM = hIntM;
    }

    public float gethIntQ() {
        return hIntQ;
    }

    public void sethIntQ(float hIntQ) {
        this.hIntQ = hIntQ;
    }

    public float gettRab() {
        return tRab;
    }

    public void settRab(float tRab) {
        this.tRab = tRab;
    }

    public float gettNar() {
        return tNar;
    }

    public void settNar(float tNar) {
        this.tNar = tNar;
    }

    public float gettMin() {
        return tMin;
    }

    public void settMin(float tMin) {
        this.tMin = tMin;
    }

    public float gettMax() {
        return tMax;
    }

    public void settMax(float tMax) {
        this.tMax = tMax;
    }

    public float gettDt() {
        return tDt;
    }

    public void settDt(float tDt) {
        this.tDt = tDt;
    }

    public float gettTn() {
        return tTn;
    }

    public void settTn(float tTn) {
        this.tTn = tTn;
    }

    public float getFirTeher() {
        return firTeher;
    }

    public void setFirTeher(float firTeher) {
        this.firTeher = firTeher;
    }

    public float getSecTeher() {
        return secTeher;
    }

    public void setSecTeher(float secTeher) {
        this.secTeher = secTeher;
    }

    public int[] getT() {
        return t;
    }

    public void setT(int[] t) {
        this.t = t;
    }

    public int[] getP() {
        return p;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public float getRshv() {
        return rshv;
    }

    public void setRshv(float rshv) {
        this.rshv = rshv;
    }
}
