package com.ucarhu.demo.logging;

public class EasyLogger {

    private static java.lang.String tagPrefix = "easylog_";

    public static final int LEVEL_ALL = 0;

    public static final int LEVEL_VERBOSE = 2;

    public static final int LEVEL_INFO = 3;

    public static final int LEVEL_DEBUG = 4;

    public static final int LEVEL_WARN = 5;

    public static final int LEVEL_ERROR = 6;

    public static final int LEVEL_OFF = 7;

    private static int currentLogLevel;

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.SOURCE)
    public @interface LogLevel {
    }

    public static void setLogLevel(int i) {
        currentLogLevel = i;
    }

    public static void setTagPrefix(@androidx.annotation.NonNull java.lang.String str) {
        if (android.text.TextUtils.isEmpty(str)) {
            str = "";
        }
        tagPrefix = str;
    }

    public static void info(@androidx.annotation.NonNull java.lang.String str, java.lang.String str2) {
        if (currentLogLevel <= 3) {
            android.util.Log.i(tagPrefix + str, str2);
        }
    }

    public static void infoWithThrowable(@androidx.annotation.NonNull java.lang.String str, java.lang.String str2, java.lang.Throwable th) {
        if (currentLogLevel <= 3) {
            android.util.Log.i(tagPrefix + str, str2, th);
        }
    }

    public static void error(java.lang.String str, java.lang.String str2) {
        if (currentLogLevel <= 6) {
            android.util.Log.e(tagPrefix + str, str2);
        }
    }

    public static void errorWithThrowable(java.lang.String str, java.lang.String str2, java.lang.Throwable th) {
        if (currentLogLevel <= 6) {
            android.util.Log.e(tagPrefix + str, str2, th);
        }
    }

    public static void debug(java.lang.String str, java.lang.String str2) {
        if (currentLogLevel <= 4) {
            android.util.Log.i(tagPrefix + str, str2);
        }
    }

    public static void debugWithThrowable(java.lang.String str, java.lang.String str2, java.lang.Throwable th) {
        if (currentLogLevel <= 4) {
            android.util.Log.i(tagPrefix + str, str2, th);
        }
    }

    public static void verbose(@androidx.annotation.NonNull java.lang.String str, java.lang.String str2) {
        if (currentLogLevel <= 2) {
            android.util.Log.v(tagPrefix + str, str2);
        }
    }

    public static void verboseWithThrowable(@androidx.annotation.NonNull java.lang.String str, java.lang.String str2, java.lang.Throwable th) {
        if (currentLogLevel <= 2) {
            android.util.Log.v(tagPrefix + str, str2, th);
        }
    }

    public static void warn(java.lang.String str, java.lang.String str2) {
        if (currentLogLevel <= 5) {
            android.util.Log.w(tagPrefix + str, str2);
        }
    }

    public static void warnWithThrowable(java.lang.String str, java.lang.String str2, java.lang.Throwable th) {
        if (currentLogLevel <= 5) {
            android.util.Log.w(tagPrefix + str, str2, th);
        }
    }
}
