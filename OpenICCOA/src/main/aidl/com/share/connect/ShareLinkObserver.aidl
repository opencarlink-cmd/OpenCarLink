package com.share.connect;

import com.share.connect.Device;

interface ShareLinkObserver {
    void onOpenResult(boolean success);
    void onScanResult(boolean scanning);
    void onDeviceDiscover(boolean matched, in Device device);
    void receivedClientInfo(String clientInfo);
    void onConnected();
    void receivedClientAddress(String address);
    void receivedClientHello(String deviceId, String moduleId, String extra);
    void onAuthenticationOk();
    void onDisconnected();
    void onConnectFailed(int reason);
    void onProgress(int progress);
    void onUserInterventionNeeded(boolean needed);
}
