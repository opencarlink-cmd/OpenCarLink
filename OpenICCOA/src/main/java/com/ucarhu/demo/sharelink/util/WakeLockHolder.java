package com.ucarhu.demo.sharelink.util;

public class WakeLockHolder {

    private static final java.lang.String f207a = "Locker";

    private static final java.lang.Object f208b = new java.lang.Object();

    private static android.os.PowerManager.WakeLock f209c;

    public static void release() {
        synchronized (f208b) {
            if (f209c != null) {
                com.ucarhu.demo.logging.EasyLogger.debug(f207a, "Release wake lock.");
                f209c.release();
                f209c = null;
            } else {
                com.ucarhu.demo.logging.EasyLogger.warn(f207a, "Lock was release already.");
            }
        }
    }

    public static void acquire(android.content.Context context) {
        synchronized (f208b) {
            android.os.PowerManager powerManager = (android.os.PowerManager) context.getSystemService("power");
            if (f209c == null) {
                f209c = powerManager.newWakeLock(1, "vivoShare::AppWakeLock");
                com.ucarhu.demo.logging.EasyLogger.debug(f207a, "Acquire wake lock.");
                f209c.acquire(3600000L);
            } else {
                com.ucarhu.demo.logging.EasyLogger.warn(f207a, "Already on lock.");
            }
        }
    }
}
