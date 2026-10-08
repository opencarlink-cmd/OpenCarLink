package com.ucarhu.demo.sharelink.wifi;

public class WifiChannelUtils {

    private static final java.lang.String f233a = "WifiUtils";

    private static final java.util.LinkedHashMap<java.lang.Integer, java.lang.Integer> f234b = new com.ucarhu.demo.sharelink.wifi.WifiChannelUtils.a();

    private static final java.util.LinkedHashMap<java.lang.Integer, java.lang.Integer> f235c = new com.ucarhu.demo.sharelink.wifi.WifiChannelUtils.b();

    public static class a extends java.util.LinkedHashMap<java.lang.Integer, java.lang.Integer> {
        public a() {
            put(1, 2412);
            put(2, 2417);
            put(3, 2422);
            put(4, 2427);
            put(5, 2432);
            put(6, 2437);
            put(7, 2442);
            put(8, 2447);
            put(9, 2452);
            put(10, 2457);
            put(11, 2462);
            put(36, 5180);
            put(40, 5200);
            put(44, 5220);
            put(48, 5240);
            put(149, 5745);
            put(153, 5765);
            put(157, 5785);
            put(161, 5805);
            put(165, 5825);
        }
    }

    public static class b extends java.util.LinkedHashMap<java.lang.Integer, java.lang.Integer> {
        public b() {
            put(1, 2412);
            put(6, 2437);
            put(11, 2462);
            put(36, 5180);
            put(149, 5745);
        }
    }

    public static int frequencyToChannel(int i) {
        for (java.util.Map.Entry<java.lang.Integer, java.lang.Integer> entry : f234b.entrySet()) {
            if (entry.getValue().intValue() == i) {
                return entry.getKey().intValue();
            }
        }
        return 0;
    }

    public static int selectOperatingChannel(int i, int i2, android.net.wifi.WifiManager wifiManager) {
        int iM188c = getCurrentApFrequency(wifiManager);
        int iM186a = (iM188c == -1 || ((iM188c <= 5000 || i != 1) && (iM188c >= 5000 || i != 0))) ? 0 : frequencyToChannel(iM188c);
        com.ucarhu.demo.logging.EasyLogger.debug(f233a, "Selected channel: " + iM186a);
        return iM186a > 0 ? iM186a : i2;
    }

    public static int getCurrentApFrequency(android.net.wifi.WifiManager wifiManager) {
        android.net.wifi.WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        int frequency = -1;
        if (connectionInfo != null) {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                frequency = connectionInfo.getFrequency();
            } else {
                for (android.net.wifi.ScanResult scanResult : wifiManager.getScanResults()) {
                    if (scanResult.BSSID.equalsIgnoreCase(connectionInfo.getBSSID())) {
                        frequency = scanResult.frequency;
                    }
                }
            }
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f233a, "Current AP frequency: " + frequency);
        return frequency;
    }

    public static void closeQuietly(java.io.Closeable closeable) throws java.io.IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (java.lang.Exception unused) {
            }
        }
    }

    public static int channelToFrequency(int i) {
        java.util.LinkedHashMap<java.lang.Integer, java.lang.Integer> c0715a = f234b;
        if (c0715a.containsKey(java.lang.Integer.valueOf(i))) {
            return c0715a.get(java.lang.Integer.valueOf(i)).intValue();
        }
        return 0;
    }

    private static boolean m191f(android.net.wifi.WifiManager wifiManager) {
        boolean zIs5GHzBandSupported = wifiManager.is5GHzBandSupported();
        com.ucarhu.demo.logging.EasyLogger.debug(f233a, "System 5GHz supported=" + zIs5GHzBandSupported);
        return zIs5GHzBandSupported;
    }

    private static boolean m192g(int i) {
        java.util.LinkedHashMap<java.lang.Integer, java.lang.Integer> c0715a = f234b;
        if (c0715a.containsKey(java.lang.Integer.valueOf(i))) {
            return c0715a.get(java.lang.Integer.valueOf(i)).intValue() >= 5000;
        }
        com.ucarhu.demo.logging.EasyLogger.warn(f233a, "Channel: " + i + " isn't recognized channel, returning false.");
        return false;
    }

    public static boolean is5GHzSupported(android.net.wifi.WifiManager wifiManager) {
        return m191f(wifiManager);
    }
}
