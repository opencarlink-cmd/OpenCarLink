package com.ucarhu.demo.protocol;

public class NullChecks {
    private static java.lang.String checkArgument(java.lang.String str) {
        java.lang.StackTraceElement stackTraceElement = java.lang.Thread.currentThread().getStackTrace()[4];
        return "Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    private static <T extends java.lang.Throwable> T checkArgument(T t) {
        return (T) checkNotNull(t, com.ucarhu.demo.protocol.NullChecks.class.getName());
    }

    private static <T extends java.lang.Throwable> T checkNotNull(T t, java.lang.String str) {
        java.lang.StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((java.lang.StackTraceElement[]) java.util.Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }

    public static void checkNotNull(java.lang.Object obj, java.lang.String str) {
        if (obj != null) {
            return;
        }
        throw ((java.lang.NullPointerException) checkArgument(new java.lang.NullPointerException(str + " must not be null")));
    }

    public static boolean checkState(java.lang.Object obj, java.lang.Object obj2) {
        return java.util.Objects.equals(obj, obj2);
    }

    public static void requireNonNull(java.lang.Object obj, java.lang.String str) {
        if (obj == null) {
            checkState(str);
        }
    }

    private static void checkState(java.lang.String str) {
        throw ((java.lang.NullPointerException) checkArgument(new java.lang.NullPointerException(checkArgument(str))));
    }
}
