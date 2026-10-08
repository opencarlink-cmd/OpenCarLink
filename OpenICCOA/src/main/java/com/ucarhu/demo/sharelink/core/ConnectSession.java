package com.ucarhu.demo.sharelink.core;

public class ConnectSession {

    private static final java.lang.String f16f = "ConnectSession";

    private int f17a = 1;

    private java.lang.String f18b;

    private java.lang.String f19c;

    private java.lang.String f20d;

    private int f21e;

    public int getBand() {
        return this.f17a;
    }

    public com.ucarhu.demo.sharelink.core.ConnectSession setBand(int i) {
        this.f17a = i;
        return this;
    }

    public com.ucarhu.demo.sharelink.core.ConnectSession setDeviceId(java.lang.String str) {
        this.f18b = str;
        return this;
    }

    public int getConnectType() {
        return this.f21e;
    }

    public com.ucarhu.demo.sharelink.core.ConnectSession setConnectType(int i) {
        this.f21e = i;
        return this;
    }

    public com.ucarhu.demo.sharelink.core.ConnectSession setDeviceName(java.lang.String str) {
        this.f19c = str;
        return this;
    }

    public com.ucarhu.demo.sharelink.core.ConnectSession setP2pMac(java.lang.String str) {
        this.f20d = str;
        return this;
    }

    public java.lang.String getDeviceId() {
        return this.f18b;
    }

    public java.lang.String getDeviceName() {
        return this.f19c;
    }

    public java.lang.String getP2pMac() {
        return this.f20d;
    }

    public void reset() {
        com.ucarhu.demo.logging.EasyLogger.debug(f16f, "Reset.");
        setDeviceId(null);
        setDeviceName(null);
        setBand(1);
        setP2pMac(null);
        setConnectType(0);
    }

    public java.lang.String toString() {
        return "ConnectSession{Id='" + this.f18b + "', Name=" + this.f19c + ", Band=" + this.f17a + ", P2pMac=" + this.f20d + ", ConnectType=" + this.f21e + '}';
    }
}
