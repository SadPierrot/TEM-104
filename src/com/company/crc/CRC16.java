package com.company.crc;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class CRC16 {
    public static byte[] calculate(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }

        byte[] buf = ByteBuffer.allocate(4).putInt(CRC).array();
        byte[] x = new byte[2];

        x[0] = buf[buf.length - 1];
        x[1] = buf[buf.length - 2];

        return x;
    }

    public static boolean compareChecksum(byte[] mainArray, byte[] crc) {
        return Arrays.equals(CRC16.calculate(mainArray), crc);
    }
}
