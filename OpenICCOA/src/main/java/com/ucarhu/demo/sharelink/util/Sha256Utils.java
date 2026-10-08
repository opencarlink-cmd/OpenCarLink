package com.ucarhu.demo.sharelink.util;

public class Sha256Utils {

    private static final java.lang.String f210a = "ShaUtil";

    public static java.lang.String sha256Hex(java.lang.String str) {
        if (android.text.TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            java.lang.String str2 = "";
            for (byte b2 : java.security.MessageDigest.getInstance("sha-256").digest(str.getBytes())) {
                java.lang.String hexString = java.lang.Integer.toHexString(b2 & 0xFF);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (java.security.NoSuchAlgorithmException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f210a, "ShaUtil error", e2);
            return "";
        }
    }
}
