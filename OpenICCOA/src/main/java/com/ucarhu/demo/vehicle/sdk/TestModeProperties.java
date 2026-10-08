package com.ucarhu.demo.vehicle.sdk;

public class TestModeProperties {
    private static java.lang.String m1066a(java.lang.String str, java.lang.String str2) {
        try {
            java.lang.Class<?> cls = java.lang.Class.forName("android.os.SystemProperties");
            return (java.lang.String) cls.getMethod("get", java.lang.String.class, java.lang.String.class).invoke(cls, str, str2);
        } catch (java.lang.Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static boolean isTestActionEnabled() {
        java.lang.String strM1066a = m1066a("ucar.test.action", "false");
        return strM1066a != null && strM1066a.equals("true");
    }

    public static boolean isTestModeEnabled() {
        java.lang.String strM1066a = m1066a("ucar.test.mode", "false");
        return strM1066a != null && strM1066a.equals("true");
    }
}
