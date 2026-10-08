package com.ucarhu.demo.vehicle.camera;

public class ExternalCameraStub implements com.ucar.vehiclesdk.camera.AbstractCamera {

    private static final java.lang.String f716b = "ExternalCamera";

    public com.ucar.vehiclesdk.UCarCommon.CameraInfo f717a;

    @Override
    public void changeConfiguration(@androidx.annotation.NonNull android.view.Surface surface, android.util.Size size, android.util.Range<java.lang.Integer> range) {
        com.ucarhu.demo.logging.EasyLogger.warn(f716b, "changeConfiguration is not implemented yet");
    }

    @Override
    public void close() {
        com.ucarhu.demo.logging.EasyLogger.warn(f716b, "close is not implemented yet");
    }

    @Override
    public com.ucar.vehiclesdk.UCarCommon.CameraInfo getInfo() throws java.lang.Exception {
        return null;
    }

    @Override
    public void open(@androidx.annotation.NonNull com.ucarhu.demo.vehicle.camera.CameraProvider.e eVar) {
        com.ucarhu.demo.logging.EasyLogger.warn(f716b, "open is not implemented yet");
    }

    @Override
    public void startPreview(@androidx.annotation.NonNull android.view.Surface surface, @androidx.annotation.NonNull android.util.Range<java.lang.Integer> range) {
        com.ucarhu.demo.logging.EasyLogger.warn(f716b, "startPreview is not implemented yet");
    }

    @Override
    public void takePicture() {
        com.ucarhu.demo.logging.EasyLogger.warn(f716b, "takePicture is not implemented yet");
    }
}
