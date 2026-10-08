package com.ucarhu.demo.vehicle.camera;

public class CameraProvider {

    private static final java.lang.String f696g = "CameraProvider";

    private static final java.lang.String f697h = "1";

    private static final java.lang.String f698i = "Car_Camera_1";

    private static volatile com.ucarhu.demo.vehicle.camera.CameraProvider f699j;

    private static short f700k;

    private static android.hardware.camera2.CameraManager f701l;

    private android.content.Context f702a;

    private final java.util.Map<java.lang.String, com.ucar.vehiclesdk.camera.AbstractCamera> f703b = new java.util.concurrent.ConcurrentHashMap();

    private com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder f704c = null;

    private java.lang.String f705d = null;

    private java.lang.String f706e = null;

    private final com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder.a f707f = new com.ucarhu.demo.vehicle.camera.CameraProvider.b();

    public class a extends android.hardware.camera2.CameraManager.AvailabilityCallback {
        public a() {
        }

        @Override
        public void onCameraAvailable(@androidx.annotation.NonNull java.lang.String str) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.CameraProvider.f696g, "onCameraAvailable " + str);
            if (str.equals(com.ucarhu.demo.vehicle.camera.CameraProvider.this.f706e)) {
                com.ucarhu.demo.vehicle.camera.CameraProvider.this.f706e = null;
            } else if (str.equals(com.ucarhu.demo.vehicle.camera.CameraProvider.this.f705d)) {
                com.ucar.vehiclesdk.UCarAdapter.getInstance().notifyCameraStateChanged(str, com.ucar.vehiclesdk.UCarCommon.CameraState.CAMERA_STATE_PREEMPTED);
            }
        }

        @Override
        public void onCameraUnavailable(@androidx.annotation.NonNull java.lang.String str) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.CameraProvider.f696g, "onCameraUnavailable " + str);
        }
    }

    public class b implements com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder.a {
        public b() {
        }

        @Override
        public void mo834a(java.lang.String str) {
            com.ucar.vehiclesdk.UCarAdapter.getInstance().notifyCameraStateChanged(com.ucarhu.demo.vehicle.camera.CameraProvider.this.f705d, com.ucar.vehiclesdk.UCarCommon.CameraState.CAMERA_STATE_ERROR);
        }

        @Override
        public void mo835a(java.nio.ByteBuffer byteBuffer, int i) {
            com.ucar.vehiclesdk.UCarAdapter.getInstance().sendCameraData(com.ucar.vehiclesdk.UCarCommon.VideoType.STREAM_CAMERA_PREVIEW, byteBuffer, com.ucarhu.demo.vehicle.camera.CameraProvider.m821e());
        }
    }

    public class c implements com.ucarhu.demo.vehicle.camera.CameraProvider.e {

        public final java.lang.String f710a;

        public final android.util.Size f711b;

        public final android.util.Range f712c;

        public final com.ucar.vehiclesdk.camera.AbstractCamera f713d;

        public c(java.lang.String str, android.util.Size size, android.util.Range range, com.ucar.vehiclesdk.camera.AbstractCamera abstractCamera) {
            this.f710a = str;
            this.f711b = size;
            this.f712c = range;
            this.f713d = abstractCamera;
        }

        @Override
        public void mo836a() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.CameraProvider.f696g, "onOpened");
            com.ucar.vehiclesdk.UCarAdapter.getInstance().notifyCameraStateChanged(this.f710a, com.ucar.vehiclesdk.UCarCommon.CameraState.CAMERA_STATE_OPENED);
            com.ucarhu.demo.vehicle.camera.CameraProvider.this.f704c = new com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder(this.f711b, ((java.lang.Integer) this.f712c.getUpper()).intValue(), com.ucarhu.demo.vehicle.camera.CameraProvider.this.f707f);
            android.view.Surface surfaceM840a = com.ucarhu.demo.vehicle.camera.CameraProvider.this.f704c.createInputSurface();
            if (surfaceM840a != null) {
                this.f713d.startPreview(surfaceM840a, this.f712c);
                com.ucarhu.demo.vehicle.camera.CameraProvider.this.f704c.startEncoder();
            }
        }

        @Override
        public void mo837a(int i, java.lang.String str) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.CameraProvider.f696g, "onError");
            com.ucar.vehiclesdk.UCarAdapter.getInstance().notifyCameraStateChanged(this.f710a, com.ucar.vehiclesdk.UCarCommon.CameraState.CAMERA_STATE_ERROR);
            com.ucarhu.demo.vehicle.camera.CameraProvider.this.closeCamera(this.f710a);
        }

        @Override
        public void mo838b() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.CameraProvider.f696g, "onClosed");
            com.ucar.vehiclesdk.UCarAdapter.getInstance().notifyCameraStateChanged(this.f710a, com.ucar.vehiclesdk.UCarCommon.CameraState.CAMERA_STATE_CLOSED);
        }

        @Override
        public void onDisconnected() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.CameraProvider.f696g, "onDisconnected");
            com.ucarhu.demo.vehicle.camera.CameraProvider.this.closeCamera(this.f710a);
        }
    }

    public static class d {

        public static final int[] f715a;

        static {
            com.ucar.vehiclesdk.UCarCommon.CameraAction.values();
            int[] iArr = new int[4];
            f715a = iArr;
            try {
                com.ucar.vehiclesdk.UCarCommon.CameraAction cameraAction = com.ucar.vehiclesdk.UCarCommon.CameraAction.CAMERA_OPEN;
                iArr[0] = 1;
            } catch (java.lang.NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = f715a;
                com.ucar.vehiclesdk.UCarCommon.CameraAction cameraAction2 = com.ucar.vehiclesdk.UCarCommon.CameraAction.CAMERA_CLOSE;
                iArr2[2] = 2;
            } catch (java.lang.NoSuchFieldError unused2) {
            }
        }
    }

    public interface e {
        void mo836a();

        void mo837a(int i, java.lang.String str);

        void mo838b();

        void onDisconnected();
    }

    public CameraProvider(@androidx.annotation.NonNull android.content.Context context) {
        this.f702a = context;
        f701l = (android.hardware.camera2.CameraManager) context.getSystemService("camera");
        com.ucarhu.demo.logging.EasyLogger.info(f696g, "CarCameraProvider getSystemService: context=" + context);
        f701l.registerAvailabilityCallback(new com.ucarhu.demo.vehicle.camera.CameraProvider.a(), (android.os.Handler) null);
    }

    public static synchronized com.ucarhu.demo.vehicle.camera.CameraProvider m817a(@androidx.annotation.NonNull android.content.Context context) {
        if (f699j == null) {
            f699j = new com.ucarhu.demo.vehicle.camera.CameraProvider(context);
        }
        return f699j;
    }

    public static short m821e() {
        short s = f700k;
        f700k = (short) (s + 1);
        return s;
    }

    public void m825f(com.ucar.vehiclesdk.camera.AbstractCamera abstractCamera) {
        try {
            com.ucar.vehiclesdk.UCarAdapter.getInstance().addCamera(abstractCamera.getInfo());
            this.f703b.put(abstractCamera.getInfo().getId(), abstractCamera);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f696g, "addCamera" + e2.getMessage());
        }
    }

    public void finalize() throws java.lang.Throwable {
        try {
            m830l();
        } finally {
            super.finalize();
        }
    }

    public synchronized void closeCamera(java.lang.String str) {
        com.ucar.vehiclesdk.camera.AbstractCamera abstractCamera;
        if (str == null) {
            return;
        }
        this.f706e = str;
        this.f705d = null;
        f700k = (short) 0;
        if (this.f703b.containsKey(str) && (abstractCamera = this.f703b.get(str)) != null) {
            abstractCamera.close();
        }
        com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder c0116e = this.f704c;
        if (c0116e != null) {
            c0116e.stopEncoder();
            this.f704c = null;
        }
    }

    public synchronized void openCamera(java.lang.String str, android.util.Size size, android.util.Range<java.lang.Integer> range) {
        com.ucar.vehiclesdk.camera.AbstractCamera abstractCamera;
        com.ucarhu.demo.logging.EasyLogger.info(f696g, "openCamera cameraId=" + str + ", width=" + size.getWidth() + ", height=" + size.getHeight() + ", fpsRange=(" + range.getLower() + ", " + range.getUpper() + ")");
        if (android.text.TextUtils.isEmpty(str.trim())) {
            com.ucarhu.demo.logging.EasyLogger.warn(f696g, "openCamera: cameraId should never be empty");
            return;
        }
        com.ucar.vehiclesdk.camera.AbstractCamera abstractCamera2 = this.f703b.get(str);
        if (abstractCamera2 == null) {
            com.ucarhu.demo.logging.EasyLogger.warn(f696g, "openCamera: camera:" + str + " is not available");
            return;
        }
        if (!str.equals(this.f705d)) {
            if (!android.text.TextUtils.isEmpty(this.f705d) && (abstractCamera = this.f703b.get(this.f705d)) != null) {
                abstractCamera.close();
            }
            f700k = (short) 0;
            abstractCamera2.open(new com.ucarhu.demo.vehicle.camera.CameraProvider.c(str, size, range, abstractCamera2));
            this.f705d = str;
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f696g, "openCamera: already exist, configure camera:" + str);
        com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder c0116e = this.f704c;
        if (c0116e != null) {
            c0116e.stopEncoder();
            this.f704c = null;
        }
        com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder c0116e2 = new com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder(size, ((java.lang.Integer) range.getUpper()).intValue(), this.f707f);
        this.f704c = c0116e2;
        android.view.Surface surfaceM840a = c0116e2.createInputSurface();
        if (surfaceM840a != null) {
            abstractCamera2.changeConfiguration(surfaceM840a, size, range);
            this.f704c.startEncoder();
        }
    }

    public void handleCameraRequest(java.lang.String str, com.ucar.vehiclesdk.UCarCommon.CameraAction cameraAction, android.util.Size size, android.util.Range<java.lang.Integer> range) {
        android.util.Log.d(f696g, "onCarCameraRequest action = " + cameraAction);
        int iOrdinal = cameraAction.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 2) {
                return;
            }
            closeCamera(str);
        } else if (androidx.core.content.ContextCompat.checkSelfPermission(this.f702a, "android.permission.CAMERA") != 0) {
            com.ucar.vehiclesdk.UCarAdapter.getInstance().notifyCameraStateChanged(str, com.ucar.vehiclesdk.UCarCommon.CameraState.CAMERA_STATE_NO_PERMISSION);
        } else {
            openCamera(str, size, range);
        }
    }

    public void m829j(java.util.ArrayList<com.ucar.vehiclesdk.UCarCommon.CameraInfo> arrayList) {
        com.ucarhu.demo.logging.EasyLogger.info(f696g, "registerCarCameras: register android builtin Cameras");
        java.util.Iterator<com.ucar.vehiclesdk.UCarCommon.CameraInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            com.ucar.vehiclesdk.UCarCommon.CameraInfo next = it.next();
            java.lang.String id = next.getId();
            if (!this.f703b.containsKey(id)) {
                m825f(new com.ucarhu.demo.vehicle.camera.BuiltInCamera(f701l, id, next.getName()));
            }
        }
    }

    public void m830l() {
        closeCamera(this.f705d);
        this.f703b.clear();
    }

    public void m831m(com.ucar.vehiclesdk.camera.AbstractCamera abstractCamera) {
        try {
            com.ucar.vehiclesdk.UCarAdapter.getInstance().removeCamera(new java.lang.String[]{abstractCamera.getInfo().getId()});
            this.f703b.remove(abstractCamera.getInfo().getId());
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f696g, "removeCamera" + e2.getMessage());
        }
    }

    public void m832n(java.util.ArrayList<com.ucar.vehiclesdk.camera.AbstractCamera> arrayList) {
        com.ucarhu.demo.logging.EasyLogger.warn(f696g, "registerExternalCarCameras: This feature is not implemented yet");
    }

    public void m833p() {
        try {
            com.ucarhu.demo.logging.EasyLogger.info(f696g, "unregister all Cameras");
            com.ucar.vehiclesdk.UCarAdapter.getInstance().removeCamera((java.lang.String[]) this.f703b.keySet().toArray(new java.lang.String[this.f703b.size()]));
            m830l();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f696g, "unregisterCameras" + e2.getMessage());
        }
    }
}
