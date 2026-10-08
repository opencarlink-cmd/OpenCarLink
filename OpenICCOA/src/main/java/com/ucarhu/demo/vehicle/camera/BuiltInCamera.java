package com.ucarhu.demo.vehicle.camera;

public class BuiltInCamera implements com.ucar.vehiclesdk.camera.AbstractCamera {

    private static final java.lang.String f674j = "BuiltInCamera";

    private final android.hardware.camera2.CameraManager f675a;

    private final java.lang.String f676b;

    private final java.lang.String f677c;

    private android.view.Surface f681g;

    private android.hardware.camera2.CameraDevice f678d = null;

    private android.hardware.camera2.CaptureRequest.Builder f679e = null;

    private android.hardware.camera2.CameraCaptureSession f680f = null;

    private final java.util.concurrent.atomic.AtomicBoolean f682h = new java.util.concurrent.atomic.AtomicBoolean(false);

    private final android.hardware.camera2.CameraCaptureSession.StateCallback f683i = new com.ucarhu.demo.vehicle.camera.BuiltInCamera.b();

    public class a extends android.hardware.camera2.CameraDevice.StateCallback {

        public final com.ucarhu.demo.vehicle.camera.CameraProvider.e f684a;

        public a(com.ucarhu.demo.vehicle.camera.CameraProvider.e eVar) {
            this.f684a = eVar;
        }

        @Override
        public void onClosed(android.hardware.camera2.CameraDevice cameraDevice) {
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f682h.set(false);
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f678d = null;
            this.f684a.mo838b();
        }

        @Override
        public void onDisconnected(android.hardware.camera2.CameraDevice cameraDevice) {
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f682h.set(false);
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f678d = null;
            this.f684a.onDisconnected();
        }

        @Override
        public void onError(android.hardware.camera2.CameraDevice cameraDevice, int i) {
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f682h.set(false);
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f678d = null;
            this.f684a.mo837a(i, "");
        }

        @Override
        public void onOpened(android.hardware.camera2.CameraDevice cameraDevice) {
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f682h.set(true);
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f678d = cameraDevice;
            this.f684a.mo836a();
        }
    }

    public class b extends android.hardware.camera2.CameraCaptureSession.StateCallback {
        public b() {
        }

        @Override
        public void onConfigureFailed(@androidx.annotation.NonNull android.hardware.camera2.CameraCaptureSession cameraCaptureSession) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.camera.BuiltInCamera.f674j, "onConfigureFailed");
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f682h.set(false);
        }

        @Override
        public void onConfigured(@androidx.annotation.NonNull android.hardware.camera2.CameraCaptureSession cameraCaptureSession){
            if (!com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f682h.get()) {
                com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.BuiltInCamera.f674j, "onConfigured, camera " + com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f676b + " has closed");
                return;
            }
            com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f680f = cameraCaptureSession;
            try {
                com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f680f.setRepeatingRequest(com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f679e.build(), null, null);
            } catch (android.hardware.camera2.CameraAccessException | java.lang.IllegalStateException e2) {
                com.ucarhu.demo.vehicle.camera.BuiltInCamera.this.f682h.set(false);
                com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.camera.BuiltInCamera.f674j, "onConfigured, setRepeatingRequest: " + e2.getMessage());
            }
        }
    }

    public BuiltInCamera(@androidx.annotation.NonNull android.hardware.camera2.CameraManager cameraManager, java.lang.String str, java.lang.String str2) {
        this.f675a = cameraManager;
        this.f676b = str;
        this.f677c = str2;
    }

    @Override
    public void changeConfiguration(@androidx.annotation.NonNull android.view.Surface surface, android.util.Size size, android.util.Range<java.lang.Integer> range){
        android.hardware.camera2.CameraCaptureSession cameraCaptureSession = this.f680f;
        if (cameraCaptureSession != null) {
            try {
                cameraCaptureSession.stopRepeating();
            } catch (android.hardware.camera2.CameraAccessException e2) {
                com.ucarhu.demo.logging.EasyLogger.error(f674j, "changeConfiguration, stopRepeating: " + e2.getMessage());
            }
        }
        android.hardware.camera2.CaptureRequest.Builder builder = this.f679e;
        if (builder == null || this.f678d == null) {
            return;
        }
        builder.removeTarget(this.f681g);
        this.f681g = surface;
        this.f679e.addTarget(surface);
        try {
            this.f678d.createCaptureSession(java.util.Collections.singletonList(surface), this.f683i, null);
        } catch (android.hardware.camera2.CameraAccessException e3) {
            this.f682h.set(false);
            com.ucarhu.demo.logging.EasyLogger.error(f674j, "changeConfiguration, createCaptureSession: " + e3.getMessage());
        }
    }

    @Override
    public void close(){
        com.ucarhu.demo.logging.EasyLogger.info(f674j, "close");
        this.f682h.set(false);
        android.hardware.camera2.CameraCaptureSession cameraCaptureSession = this.f680f;
        if (cameraCaptureSession != null) {
            try {
                cameraCaptureSession.stopRepeating();
                this.f680f = null;
            } catch (android.hardware.camera2.CameraAccessException | java.lang.IllegalStateException e2) {
                com.ucarhu.demo.logging.EasyLogger.error(f674j, "close, stopRepeating: " + e2.getMessage());
            }
        }
        android.hardware.camera2.CameraDevice cameraDevice = this.f678d;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.f678d = null;
        }
    }

    @Override
    public com.ucar.vehiclesdk.UCarCommon.CameraInfo getInfo() throws java.lang.Exception {
        android.hardware.camera2.CameraCharacteristics cameraCharacteristics = this.f675a.getCameraCharacteristics(this.f676b);
        int iIntValue = ((java.lang.Integer) cameraCharacteristics.get(android.hardware.camera2.CameraCharacteristics.LENS_FACING)).intValue();
        if (iIntValue == 0) {
            iIntValue = 1;
        } else if (iIntValue == 1) {
            iIntValue = 0;
        }
        return new com.ucar.vehiclesdk.UCarCommon.CameraInfo(this.f676b, this.f677c, com.ucar.vehiclesdk.UCarCommon.LensFacing.fromInt(iIntValue), com.ucar.vehiclesdk.UCarCommon.Orientation.fromInt(((java.lang.Integer) cameraCharacteristics.get(android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION)).intValue()), ((android.hardware.camera2.params.StreamConfigurationMap) cameraCharacteristics.get(android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(android.view.SurfaceHolder.class), (android.util.Range[]) cameraCharacteristics.get(android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
    }

    @Override
    @android.annotation.SuppressLint({"MissingPermission"})
    public void open(@androidx.annotation.NonNull com.ucarhu.demo.vehicle.camera.CameraProvider.e eVar){
        com.ucarhu.demo.logging.EasyLogger.info(f674j, "open");
        try {
            this.f675a.openCamera(this.f676b, new com.ucarhu.demo.vehicle.camera.BuiltInCamera.a(eVar), (android.os.Handler) null);
        } catch (android.hardware.camera2.CameraAccessException | java.lang.IllegalArgumentException e2) {
            this.f682h.set(false);
            com.ucarhu.demo.logging.EasyLogger.error(f674j, "open, openCamera: " + e2.getMessage());
            eVar.mo837a(-1, e2.getMessage());
        }
    }

    @Override
    public void startPreview(@androidx.annotation.NonNull android.view.Surface surface, @androidx.annotation.NonNull android.util.Range<java.lang.Integer> range){
        try {
            android.hardware.camera2.CaptureRequest.Builder builderCreateCaptureRequest = this.f678d.createCaptureRequest(1);
            this.f679e = builderCreateCaptureRequest;
            builderCreateCaptureRequest.set(android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE, 3);
            this.f679e.set(android.hardware.camera2.CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, range);
            this.f679e.addTarget(surface);
            this.f681g = surface;
            this.f678d.createCaptureSession(java.util.Collections.singletonList(surface), this.f683i, null);
        } catch (android.hardware.camera2.CameraAccessException e2) {
            this.f682h.set(false);
            com.ucarhu.demo.logging.EasyLogger.error(f674j, "startPreview, createCaptureSession: " + e2.getMessage());
        }
    }

    @Override
    public void takePicture() {
        com.ucarhu.demo.logging.EasyLogger.warn(f674j, "This feature is not implemented yet");
    }
}
