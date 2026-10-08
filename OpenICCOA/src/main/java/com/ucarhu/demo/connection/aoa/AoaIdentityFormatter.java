package com.ucarhu.demo.connection.aoa;

public class AoaIdentityFormatter {

    private static final java.lang.String f363a = "\\.";

    private static final int f364b = 2;

    private static final int f365c = 12;

    private static final int f366d = 8;

    private static final int f367e = 16;

    private static final int f368f = 12;

    private static final int f369g = 4;

    private static java.lang.String safeIdentityPart(java.lang.String str) {
        if (str == null) {
            return "";
        }
        byte[] bytes = str.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        if (bytes.length <= 16) {
            return str;
        }
        while (bytes.length > 16 && !str.isEmpty()) {
            str = str.substring(0, str.length() - 1);
            bytes = (str + "…").getBytes(java.nio.charset.StandardCharsets.UTF_8);
        }
        return new java.lang.String(bytes, java.nio.charset.StandardCharsets.UTF_8);
    }

    private static java.lang.String limitLength(java.lang.String str, int i) {
        return str == null ? "" : str.length() > i ? str.substring(0, i) : str;
    }

    public static java.lang.String formatIdentity(java.lang.String str, java.lang.String str2, java.lang.String str3, java.lang.String str4, java.lang.String str5, java.lang.String str6) {
        return java.lang.String.format("%s;%s;%s;%s;%s;%s", limitLength(str, 12), limitLength(str2, 8), safeIdentityPart(str3), limitLength(str4, 12), normalizeVersion(str5), limitLength(str6, 4));
    }

    private static java.lang.String normalizeVersion(java.lang.String str) {
        if (str == null) {
            return "";
        }
        java.lang.String[] strArrSplit = str.split(f363a);
        if (strArrSplit.length < 2) {
            throw new java.lang.IllegalArgumentException();
        }
        byte[] bArr = new byte[2];
        for (int i = 0; i < 2; i++) {
            bArr[i] = (byte) java.lang.Integer.parseInt(strArrSplit[i]);
        }
        return java.lang.String.format(java.util.Locale.ENGLISH, "%d.%d", java.lang.Integer.valueOf(java.lang.Byte.toUnsignedInt(bArr[0])), java.lang.Integer.valueOf(java.lang.Byte.toUnsignedInt(bArr[1])));
    }
}
