package com.share.connect.wifiap;

import com.share.connect.wifiap.WifiApObserver;

interface IWifiAp {
    void open();
    void close();
    void createSoftAp(int channel);
    void cancelConnect();
    void registerWifiApObserver(WifiApObserver observer);
    void unregisterWifiApObserver(WifiApObserver observer);
}
