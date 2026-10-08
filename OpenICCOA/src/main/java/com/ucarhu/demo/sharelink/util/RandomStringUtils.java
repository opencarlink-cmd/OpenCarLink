package com.ucarhu.demo.sharelink.util;

public class RandomStringUtils {
    public static java.lang.String randomAscii(int i) {
        java.util.Random random = new java.util.Random(java.util.UUID.randomUUID().getMostSignificantBits());
        char[] charArray = "`-=~!@#$%^&*()_+,./;'[]\\<>?:\"{}|abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(charArray[random.nextInt(charArray.length)]);
        }
        return sb.toString();
    }

    public static java.lang.String randomDigits(int i) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        java.util.Random random = new java.util.Random();
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }
}
