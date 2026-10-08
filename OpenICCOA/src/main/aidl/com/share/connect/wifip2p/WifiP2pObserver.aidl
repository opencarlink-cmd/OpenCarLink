package com.share.connect.wifip2p;

import android.net.wifi.p2p.WifiP2pGroup;

interface WifiP2pObserver {
    void onOpenSuccess(String address, int port);
    void onOpenFailed();
    void onDeviceChanged(String deviceName);
    void onGroupCreated(in WifiP2pGroup group, int port);
    void onConnected();
    void onDisconnected();
    void onCreateGroupFailed();
    void onAcceptFailed();
    void onConnectFailed();
}
