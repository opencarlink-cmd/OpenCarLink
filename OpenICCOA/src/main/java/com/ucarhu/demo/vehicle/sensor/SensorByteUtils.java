package com.ucarhu.demo.vehicle.sensor;

public class SensorByteUtils {

    public static final java.lang.String f799a = "UTF-8";

    public static int m947a(byte b2, byte b3, byte b4, byte b5) {
        int i = (b5 & 0xFF) << 24;
        int i2 = (b4 & 0xFF) << 16;
        return (b2 & 0xFF) | ((b3 & 0xFF) << 8) | i2 | i;
    }

    public static java.lang.String m948b(byte[] bArr, int i) {
        if (bArr.length < i) {
            return null;
        }
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(java.lang.String.format("%02x-", java.lang.Byte.valueOf(bArr[i2])));
        }
        return sb.toString();
    }

    public static byte[] m949c(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i & 255);
            i >>= 8;
        }
        return bArr;
    }

    public static byte[] m950d(java.util.Calendar calendar) {
        return m949c((int) (calendar.getTimeInMillis() / 1000));
    }
}
