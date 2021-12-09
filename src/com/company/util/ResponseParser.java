package com.company.util;

public class ResponseParser {
    /**
     * Преобразует входящую строку в номер тарифа
     */
    public static int translateCurrentTariff(String s) {
        switch (s) {
            case "00" -> {
                return 1;
            }
            case "01" -> {
                return 2;
            }
            case "10" -> {
                return 3;
            }
            case "11" -> {
                return 4;
            }
            default -> System.out.println("Error");
        }
        return 0;
    }
}
