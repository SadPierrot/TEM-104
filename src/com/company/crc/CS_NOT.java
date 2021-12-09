package com.company.crc;

public class CS_NOT {
    public static byte[] calculate(byte[] bytes) {
        byte[] a = new byte[1];
        for (byte aByte : bytes) {
            a[0] = (byte) (a[0] + aByte);
        }
        a[0] = (byte) ~a[0];
        return a;
    }
}
