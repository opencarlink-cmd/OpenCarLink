package com.ucar.vehiclesdk;

public interface ICarConnectListener {
    void onConnectStateChanged(String str, int i, int i2);

    void onConnectingProgress(String str, String str2, int i);

    void onPinCode(String str, String str2);
}
