package com.share.connect.ble;

import com.share.connect.Device;

interface BluetoothLeObserver {
    void onSuccess(int type);
    void onFailure(int type, int reason);
    void onDeviceMatch(in Device device);
    void onDeviceLost(in Device device);
    void onPinAvailable(String pinCode);
    void onServerInfoReceived(String deviceId, String moduleId, String address, int port);
    void onClientInfoReceived(String deviceId, String moduleId, String address, int port, String extra, int channel);
    void onServerAddressSent();
}
