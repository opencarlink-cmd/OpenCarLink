package com.share.connect.wifip2p;

import com.share.connect.wifip2p.WifiP2pObserver;

interface IWifiP2p {
    void open();
    void close();
    void createGroupForClient(int band, String deviceName, int channel);
    void connectGroupOwner(String ssid, String password, String address, int port);
    void cancelConnect();
    void registerWifiP2pObserver(WifiP2pObserver observer);
    void unregisterWifiP2pObserver(WifiP2pObserver observer);
}
