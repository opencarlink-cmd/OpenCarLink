package com.ucarhu.demo.vehicle.cast;

public interface CastEventListener {
    void onAudioFormatChanged(com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat);

    void onCastStopped(java.lang.String str);

    void onVideoSizeChanged(java.lang.String str, int i, int i2, boolean z);
}
