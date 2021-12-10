package com.company.dto;

import java.time.LocalDateTime;

public class ProfilePowerFlashDto {
    LocalDateTime tekDat;
    LocalDateTime prevDat;
    float lIntV;
    float lIntM;
    float lIntQ;
    int hIntV;
    int hIntM;
    int hIntQ;
    int tRab;
    int tNar;
    int tMin;
    int tMax;
    int tDt;
    int tTn;
    int firTeher;
    int secTeher;
    int[] t;
    int[] p;
    int rshv;

    public ProfilePowerFlashDto(LocalDateTime tekDat, LocalDateTime prevDat,
                                float lIntV, float lIntM, float lIntQ,
                                int hIntV, int hIntM, int hIntQ, int tRab, int tNar, int tMin, int tMax, int tDt,
                                int tTn, int firTeher, int secTeher, int[] t, int[] p, int rshv) {
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

    public int gethIntV() {
        return hIntV;
    }

    public void sethIntV(int hIntV) {
        this.hIntV = hIntV;
    }

    public int gethIntM() {
        return hIntM;
    }

    public void sethIntM(int hIntM) {
        this.hIntM = hIntM;
    }

    public int gethIntQ() {
        return hIntQ;
    }

    public void sethIntQ(int hIntQ) {
        this.hIntQ = hIntQ;
    }

    public int gettRab() {
        return tRab;
    }

    public void settRab(int tRab) {
        this.tRab = tRab;
    }

    public int gettNar() {
        return tNar;
    }

    public void settNar(int tNar) {
        this.tNar = tNar;
    }

    public int gettMin() {
        return tMin;
    }

    public void settMin(int tMin) {
        this.tMin = tMin;
    }

    public int gettMax() {
        return tMax;
    }

    public void settMax(int tMax) {
        this.tMax = tMax;
    }

    public int gettDt() {
        return tDt;
    }

    public void settDt(int tDt) {
        this.tDt = tDt;
    }

    public int gettTn() {
        return tTn;
    }

    public void settTn(int tTn) {
        this.tTn = tTn;
    }

    public int getFirTeher() {
        return firTeher;
    }

    public void setFirTeher(int firTeher) {
        this.firTeher = firTeher;
    }

    public int getSecTeher() {
        return secTeher;
    }

    public void setSecTeher(int secTeher) {
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

    public int getRshv() {
        return rshv;
    }

    public void setRshv(int rshv) {
        this.rshv = rshv;
    }
}
