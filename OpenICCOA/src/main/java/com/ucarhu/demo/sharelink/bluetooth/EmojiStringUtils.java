package com.ucarhu.demo.sharelink.bluetooth;

public class EmojiStringUtils {

    private static final java.lang.String f130a = "EmojiUtils";

    public static int codePointCount(java.lang.String str) {
        if (str == null) {
            return 0;
        }
        return str.codePointCount(0, str.length());
    }

    public static java.lang.String substringByCodePoint(java.lang.String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        try {
            return str.substring(str.offsetByCodePoints(0, i), str.offsetByCodePoints(0, i2));
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.warnWithThrowable(f130a, "Get substring by codePoint failed.", e2);
            return str.substring(i, i2);
        }
    }
}
