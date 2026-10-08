package com.ucarhu.demo.vehicle.util;

public final class QualcommPlatformUtils {

    private static final java.lang.String f963a = "qcom";

    private QualcommPlatformUtils() {
    }

    public static boolean isQualcommHardware() {
        java.lang.String str = android.os.Build.HARDWARE;
        if (android.text.TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches(f963a);
    }
}
