package com.ucarhu.demo.sharelink.util;

public class DebugTimer {

    private static final java.lang.String f203a = "DebugTools";

    private static final android.util.ArrayMap<java.lang.String, java.lang.Long> f204b = new android.util.ArrayMap<>();

    public static void endEvent(java.lang.String str) {
        long jCurrentTimeMillis = java.lang.System.currentTimeMillis();
        android.util.ArrayMap<java.lang.String, java.lang.Long> arrayMap = f204b;
        synchronized (arrayMap) {
            if (arrayMap.containsKey(str)) {
                java.lang.Long lRemove = arrayMap.remove(str);
                if (lRemove != null) {
                    com.ucarhu.demo.logging.EasyLogger.info(f203a, "Event: " + str + " cost " + (jCurrentTimeMillis - lRemove.longValue()) + " ms.");
                }
            } else {
                com.ucarhu.demo.logging.EasyLogger.warn(f203a, "Event: " + str + " not exist.");
            }
        }
    }

    public static void startEvent(java.lang.String str) {
        long jCurrentTimeMillis = java.lang.System.currentTimeMillis();
        com.ucarhu.demo.logging.EasyLogger.info(f203a, "Event: " + str + " start.");
        android.util.ArrayMap<java.lang.String, java.lang.Long> arrayMap = f204b;
        synchronized (arrayMap) {
            arrayMap.put(str, java.lang.Long.valueOf(jCurrentTimeMillis));
        }
    }
}
