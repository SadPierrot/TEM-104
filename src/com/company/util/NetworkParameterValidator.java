package com.company.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class NetworkParameterValidator {
    public static boolean validationValidIP(String ip) {
        if (ip == null || ip.isEmpty()) {
            System.out.println("IP Адресс не обнаружен");
            return false;
        }
        ip = ip.trim();
        if (ip.length() < 6 || ip.length() > 15) {
            System.out.println("IP Адресс недопустимого размера");
            return false;
        }
        try {
            Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
            Matcher matcher = pattern.matcher(ip);
            if (matcher.matches()) {
                return true;
            } else {
                System.out.println("IP Адресс состоит из недопустимых символов");
                return false;
            }
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }

    public static boolean validationValidPort(int port) {
        if (port <= 0 || port >= 65535) {
            System.out.println("Порт некорретного размера");
            return false;
        }
        return true;
    }
}
