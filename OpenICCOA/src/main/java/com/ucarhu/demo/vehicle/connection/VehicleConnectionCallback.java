package com.ucarhu.demo.vehicle.connection;

public interface VehicleConnectionCallback {
    void onConnectionClosed();

    void onConnectionStarted(java.lang.String str);

    void onConnectionFailed(java.lang.String str, int i);

    void onClientAddressReceived(java.lang.String str, java.lang.String str2);

    void onConnectionRejected();

    void onConnecting(java.lang.String str);

    void onConnectionProgress(java.lang.String str, int i);

    void onDeviceDisconnected(java.lang.String str);

    void onConnected();

    void onPinCode(java.lang.String str, java.lang.String str2);

    void onUserInterventionNeeded(boolean z);
}
