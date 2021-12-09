package com.company.util;

public class WorkingWithArrays {
    public static byte[] countArrays(byte[] arr1, byte[] arr2) {
        byte[] c = new byte[arr1.length + arr2.length];
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            c[i] = arr1[i];
            count++;
        }
        for (byte item : arr2) {
            c[count++] = item;
        }
        return c;
    }
}
