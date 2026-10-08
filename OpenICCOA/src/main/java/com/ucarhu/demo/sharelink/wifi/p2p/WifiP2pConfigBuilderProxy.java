package com.ucarhu.demo.sharelink.wifi.p2p;

@android.annotation.SuppressLint({"NewApi", "PrivateApi"})
public class WifiP2pConfigBuilderProxy {

    private static final java.lang.String f241a = "ConfigBuilderProxy";

    private static java.lang.Class f242b;

    private static java.lang.reflect.Field f243c;

    private static java.lang.reflect.Method f244d;

    private static java.lang.reflect.Method f245e;

    private static java.lang.reflect.Method f246f;

    private static java.lang.reflect.Method f247g;

    static {
        try {
            java.lang.Class<?> cls = java.lang.Class.forName("android.net.wifi.p2p.WifiP2pConfig$Builder");
            f242b = cls;
            f244d = cls.getDeclaredMethod("setNetworkName", java.lang.String.class);
            f245e = f242b.getDeclaredMethod("setPassphrase", java.lang.String.class);
            f246f = f242b.getDeclaredMethod("setGroupOperatingFrequency", java.lang.Integer.TYPE);
            f247g = f242b.getDeclaredMethod("build", new java.lang.Class[0]);
            try {
                java.lang.reflect.Field declaredField = f242b.getDeclaredField("mNetworkName");
                f243c = declaredField;
                declaredField.setAccessible(true);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.warn(f241a, "Reflect private field failed, reasonable. " + e2.getMessage());
            }
        } catch (java.lang.Exception e3) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f241a, "Reflect initialize failed.", e3);
        }
    }

    public static android.net.wifi.p2p.WifiP2pConfig buildConfig(com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pGroupInfo c0038a) {
        try {
            java.lang.Object objNewInstance = f242b.newInstance();
            try {
                f243c.set(objNewInstance, c0038a.getNetworkName());
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.warn(f241a, "Set network name by field failed. " + e2.getMessage());
                f244d.invoke(objNewInstance, c0038a.getNetworkName());
            }
            f245e.invoke(objNewInstance, c0038a.getPassphrase());
            f246f.invoke(objNewInstance, java.lang.Integer.valueOf(c0038a.getFrequency()));
            return (android.net.wifi.p2p.WifiP2pConfig) f247g.invoke(objNewInstance, new java.lang.Object[0]);
        } catch (java.lang.Exception e3) {
            com.ucarhu.demo.logging.EasyLogger.error(f241a, "Get WifiP2pConfig failed. " + e3.getMessage());
            return null;
        }
    }
}
