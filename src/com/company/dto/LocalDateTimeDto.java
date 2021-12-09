package com.company.dto;

public class LocalDateTimeDto {
    private final int hour;
    private final int min;
    private final int day;
    private final int month;
    private final int year;

    public LocalDateTimeDto(int hour, int min, int day, int month, int year) {
        this.hour = hour;
        this.min = min;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
