package com.share.connect.wifiap;

interface WifiApObserver {
    void onOpenSuccess();
    void onOpenFailed();
    void onApCreated(String ssid, String password, String address, String gateway, int port);
    void onApCreateFailed();
    void onConnected();
    void onDisconnected();
    void onAcceptFailed();
}
