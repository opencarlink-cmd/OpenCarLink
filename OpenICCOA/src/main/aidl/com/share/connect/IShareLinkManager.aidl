package com.share.connect;

import com.share.connect.ShareLinkObserver;

interface IShareLinkManager {
    void open(boolean supportP2p, int band, int channel, boolean supportSoftAp);
    void close();
    void startScan();
    void stopScan();
    boolean isDeviceInMatch(String deviceId);
    Map getDevicesSignal(int limit, int timeoutMs);
    void enableUsbDeviceScanning(boolean enabled);
    void connect(String deviceId);
    void disconnect();
    void disconnectBle();
    void registerLinkObserver(ShareLinkObserver observer);
    void unregisterLinkObserver(ShareLinkObserver observer);
    void stopAdvertise();
    void updatePinCode(String pinCode);
}
