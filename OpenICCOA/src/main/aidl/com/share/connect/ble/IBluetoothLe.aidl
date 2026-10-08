package com.share.connect.ble;

import com.share.connect.ble.BluetoothLeObserver;

interface IBluetoothLe {
    void open(String carId, String moduleId, String carName, String shortName, String vendorName, String customData, boolean reconnect);
    void close();
    void startScan();
    void stopScan();
    boolean isDeviceInMatch(String deviceId);
    Map getDevicesSignal(int limit, int timeoutMs);
    boolean connect(String deviceId);
    void disconnect();
    void connectDone();
    void disconnectWithoutState();
    void registerBluetoothLeObserver(BluetoothLeObserver observer);
    void unregisterBluetoothLeObserver(BluetoothLeObserver observer);
    void setBandSupported(int band);
    void setP2pDeviceMac(String mac);
    void notifyServerInfo(String deviceId, String moduleId, String address, int port, int channel);
    void stopAdvertise();
    void allowProcessNewConnection();
}
