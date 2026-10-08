package com.ucarhu.demo.connection;

public class ConnectionManager {

    private static final java.lang.String f254a = "ConnectManager";

    private static final com.ucarhu.demo.connection.ConnectionManager f255b = new com.ucarhu.demo.connection.ConnectionManager();

    private ConnectionManager() {
    }

    public static com.ucarhu.demo.connection.ConnectionManager getInstance() {
        return f255b;
    }

    public void registerAoaServerSocketIfNeeded(int i, boolean z) {
        java.lang.String strM24a = com.ucarhu.demo.sharelink.core.ConnectionStateHolder.getInstance().getState();
        if (strM24a.equals(com.ucarhu.demo.sharelink.core.ConnectionStateHolder.STATE_USB_CONNECTED)) {
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().registerServerSocketIfNeeded(i, z);
            return;
        }
        if (strM24a.equals(com.ucarhu.demo.sharelink.core.ConnectionStateHolder.STATE_WIFI_CONNECTED)) {
            com.ucarhu.demo.logging.EasyLogger.info(f254a, "Not need register socket , because the current connect type is " + strM24a);
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.error(f254a, "The current connect state is " + strM24a + " , is not allowed register server socket");
    }
}
