package com.company.comand;

public enum CommandDeviceCounter {
    PROFILE_POWER_128("READING 128 BYTE"),
    PROFILE_POWER_2000("READING 2K BYTE"),
    PROFILE_POWER_FLASH_512K("READING FLASH 512K BYTE"),
    RAM("READING RAM"),
    RECORDING_BY_DATE("SEARCH RECORD BY DATE");


    private final String title;

    CommandDeviceCounter(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "title='" + title + '\'' +
                '}';
    }
}
