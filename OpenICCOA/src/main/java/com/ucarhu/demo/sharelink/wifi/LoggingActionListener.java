package com.ucarhu.demo.sharelink.wifi;

public class LoggingActionListener implements android.net.wifi.p2p.WifiP2pManager.ActionListener {

    private java.lang.String f225a;

    private java.lang.String f226b;

    public LoggingActionListener(java.lang.String str, java.lang.String str2) {
        this.f225a = str;
        this.f226b = str2;
    }

    @Override
    public void onFailure(int i) {
        com.ucarhu.demo.logging.EasyLogger.warn(this.f225a, this.f226b + " onFailure with reason: " + i);
    }

    @Override
    public void onSuccess() {
        com.ucarhu.demo.logging.EasyLogger.debug(this.f225a, this.f226b + " onSuccess.");
    }
}
