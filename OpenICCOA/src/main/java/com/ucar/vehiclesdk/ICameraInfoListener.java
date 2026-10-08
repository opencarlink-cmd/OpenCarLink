package com.ucar.vehiclesdk;

import com.ucar.vehiclesdk.UCarCommon;
import com.ucar.vehiclesdk.camera.AbstractCamera;
import java.util.ArrayList;

public interface ICameraInfoListener {
    ArrayList<UCarCommon.CameraInfo> getAndroidCameraInfo();

    ArrayList<AbstractCamera> getNativeCamera();
}
