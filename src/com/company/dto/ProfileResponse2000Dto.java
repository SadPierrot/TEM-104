package com.company.dto;

public class ProfileResponse2000Dto {
    int systems;
    int type_g;
    int type_t;
    int net_num;
    int number;
    int diam;
    String g_max;
    int g_pcnt_max;
    int g_pcnt_min;
    int f_max;
    int weight;
    int next_hour;
    int next_day;
    int next_month;

    public ProfileResponse2000Dto(int systems, int type_g, int type_t, int net_num, int number,
                                  int diam, String g_max, int g_pcnt_max, int g_pcnt_min, int f_max,
                                  int weight, int next_hour, int next_day, int next_month) {
        this.systems = systems;
        this.type_g = type_g;
        this.type_t = type_t;
        this.net_num = net_num;
        this.number = number;
        this.diam = diam;
        this.g_max = g_max;
        this.g_pcnt_max = g_pcnt_max;
        this.g_pcnt_min = g_pcnt_min;
        this.f_max = f_max;
        this.weight = weight;
        this.next_hour = next_hour;
        this.next_day = next_day;
        this.next_month = next_month;
    }

    public int getSystems() {
        return systems;
    }

    public void setSystems(int systems) {
        this.systems = systems;
    }

    public int getType_g() {
        return type_g;
    }

    public void setType_g(int type_g) {
        this.type_g = type_g;
    }

    public int getType_t() {
        return type_t;
    }

    public void setType_t(int type_t) {
        this.type_t = type_t;
    }

    public int getNet_num() {
        return net_num;
    }

    public void setNet_num(int net_num) {
        this.net_num = net_num;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiam() {
        return diam;
    }

    public void setDiam(int diam) {
        this.diam = diam;
    }

    public String getG_max() {
        return g_max;
    }

    public void setG_max(String g_max) {
        this.g_max = g_max;
    }

    public int getG_pcnt_max() {
        return g_pcnt_max;
    }

    public void setG_pcnt_max(int g_pcnt_max) {
        this.g_pcnt_max = g_pcnt_max;
    }

    public int getG_pcnt_min() {
        return g_pcnt_min;
    }

    public void setG_pcnt_min(int g_pcnt_min) {
        this.g_pcnt_min = g_pcnt_min;
    }

    public int getF_max() {
        return f_max;
    }

    public void setF_max(int f_max) {
        this.f_max = f_max;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNext_hour() {
        return next_hour;
    }

    public void setNext_hour(int next_hour) {
        this.next_hour = next_hour;
    }

    public int getNext_day() {
        return next_day;
    }

    public void setNext_day(int next_day) {
        this.next_day = next_day;
    }

    public int getNext_month() {
        return next_month;
    }

    public void setNext_month(int next_month) {
        this.next_month = next_month;
    }
}
