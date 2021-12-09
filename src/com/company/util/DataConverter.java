package com.company.util;

public class DataConverter {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Перевод из массива байт в INT
     */

    public static int translateBytesToInt(byte[] bytes) {
        int value = 0;
        for (byte aByte : bytes) {
            value = (value << 8) + (aByte & 0xff);
        }
        return value;
    }

    /**
     * Перевод из массива байт в HEX
     */

    public static String translateBytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Преобразует из входщего INT (1 / 0) в булевое значение (TRUE / FALSE)
     */

    public static boolean translateIntToBool(int a) {
        if (a == 1) {
            return true;
        } else if (a == 0) {
            return false;
        } else {
            System.out.println("Err");
        }
        return false;
    }
}
