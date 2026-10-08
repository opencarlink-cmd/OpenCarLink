package com.ucarhu.demo.vehicle.camera;

/**
 * 车辆摄像头管理器，负责摄像头通道、摄像头增删、状态通知以及预览帧发送。
 * 与 CameraProvider 协作完成相机打开、编码和数据上报。
 */


public class VehicleCameraManager {

    private static final java.lang.String f687f = "Vehicle_CameraManager";

    private static com.ucarhu.demo.vehicle.camera.CameraProvider f688g;

    private com.ucar.vehiclesdk.ICameraInfoListener f689a = null;

    private com.ucarhu.demo.sharelink.channel.ShareLinkChannel f690b = null;

    private java.util.concurrent.Future<java.lang.Boolean> f691c = null;

    private java.util.concurrent.atomic.AtomicBoolean f692d = new java.util.concurrent.atomic.AtomicBoolean(false);

    private java.util.concurrent.atomic.AtomicBoolean f693e = new java.util.concurrent.atomic.AtomicBoolean(false);

    public class a extends com.ucarhu.demo.sharelink.channel.ShareLinkChannel {
        public a(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z, boolean z2) {
            super(enumC0057b, z, z2);
        }

        @Override
        public void mo349Z() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.VehicleCameraManager.f687f, "camera channel ready.");
            com.ucarhu.demo.vehicle.camera.VehicleCameraManager.this.f693e.set(true);
        }

        @Override
        public void mo352a(boolean z){
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.VehicleCameraManager.f687f, "camera channel closed.");
            com.ucarhu.demo.vehicle.camera.VehicleCameraManager.this.f693e.set(false);
            com.ucarhu.demo.vehicle.camera.VehicleCameraManager.this.stopCameraChannel();
        }
    }

    public class b implements com.ucarhu.demo.protocol.channel.SendCallback {
        public b() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.camera.VehicleCameraManager.f687f, "send camera data error", exc);
            com.ucarhu.demo.vehicle.camera.VehicleCameraManager.this.f693e.set(false);
            com.ucarhu.demo.vehicle.camera.VehicleCameraManager.this.stopCameraChannel();
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            if (bool.booleanValue()) {
                return;
            }
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.camera.VehicleCameraManager.f687f, "send camera data failed");
        }
    }

    public VehicleCameraManager(android.content.Context context) {
        f688g = com.ucarhu.demo.vehicle.camera.CameraProvider.m817a(context);
    }

    public static com.ucar.databus.proto.UCarProto.NotifyAddCamera m804a(com.ucar.vehiclesdk.UCarCommon.CameraInfo cameraInfo) {
        try {
            android.util.Size[] supportedSizes = cameraInfo.getSupportedSizes();
            android.util.Range<java.lang.Integer>[] fpsRanges = cameraInfo.getFpsRanges();
            com.ucar.databus.proto.UCarProto.NotifyAddCamera.Builder maxSize = com.ucar.databus.proto.UCarProto.NotifyAddCamera.newBuilder().setCameraId(cameraInfo.getId()).setName(cameraInfo.getName()).setLensFacingValue(cameraInfo.getLensFacing().getValue()).setOrientationValue(com.ucar.vehiclesdk.UCarCommon.Orientation.ORIENTATION_0.getValue()).setMaxSize(com.ucar.databus.proto.UCarProto.PictureSize.newBuilder().setWidth(supportedSizes[0].getWidth()).setHeight(supportedSizes[0].getHeight()).build());
            for (android.util.Size size : supportedSizes) {
                maxSize.addSupportedSizes(com.ucar.databus.proto.UCarProto.PictureSize.newBuilder().setWidth(size.getWidth()).setHeight(size.getHeight()).build());
            }
            for (android.util.Range<java.lang.Integer> range : fpsRanges) {
                maxSize.addFpsRanges(com.ucar.databus.proto.UCarProto.FpsRange.newBuilder().setMax(((java.lang.Integer) range.getUpper()).intValue()).setMin(((java.lang.Integer) range.getLower()).intValue()).build());
            }
            return maxSize.build();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f687f, "createNotifyAddMessage" + e2.getMessage());
            return null;
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyRemoveCamera m805b(java.lang.String[] strArr) {
        try {
            return com.ucar.databus.proto.UCarProto.NotifyRemoveCamera.newBuilder().addAllIds(java.util.Arrays.asList(strArr)).build();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f687f, "createNotifyRemoveMessage" + e2.getMessage());
            return null;
        }
    }

    private void m807h(java.nio.ByteBuffer byteBuffer, com.ucar.databus.proto.UCarProto.VideoType videoType, short s) {
        com.ucarhu.demo.protocol.UCarMessage c0102wM664H = com.ucarhu.demo.protocol.UCarMessage.obtain();
        c0102wM664H.getHeader().initFields(byteBuffer.remaining() + 20, s, com.ucarhu.demo.protocol.ProtocolConfig.getLocalDevice(), com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.MessageType.SEND, com.ucarhu.demo.protocol.CommandCategory.VIDEO, videoType.getNumber());
        c0102wM664H.setBody(byteBuffer, byteBuffer.remaining());
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a = this.f690b;
        if (c0016a != null) {
            c0016a.mo355c(c0102wM664H, new com.ucarhu.demo.vehicle.camera.VehicleCameraManager.b());
        }
    }

    private boolean m808m() {
        if (this.f691c != null && !m813i()) {
            try {
                this.f691c.get();
                this.f691c = null;
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f687f, "Wait start camera channel error.", e2);
            }
        }
        return m813i();
    }

    public void m809d(int i) {
        com.ucar.vehiclesdk.ICameraInfoListener iCameraInfoListener;
        com.ucarhu.demo.logging.EasyLogger.info(f687f, "onConnectStateChanged state= " + i);
        if (i == 2) {
            com.ucarhu.demo.vehicle.camera.CameraProvider c0114c = f688g;
            if (c0114c != null && (iCameraInfoListener = this.f689a) != null) {
                c0114c.m829j(iCameraInfoListener.getAndroidCameraInfo());
                f688g.m832n(this.f689a.getNativeCamera());
            }
            this.f692d.set(true);
            return;
        }
        if (i == 1) {
            this.f692d.set(false);
            stopCameraChannel();
            com.ucarhu.demo.vehicle.camera.CameraProvider c0114c2 = f688g;
            if (c0114c2 != null) {
                c0114c2.m833p();
            }
        }
    }

    public void m810e(com.ucar.vehiclesdk.ICameraInfoListener iCameraInfoListener) {
        com.ucarhu.demo.logging.EasyLogger.info(f687f, "registerInfoListener");
        this.f689a = iCameraInfoListener;
        if (!this.f692d.get() || this.f689a == null || f688g == null) {
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f687f, "registerInfoListener: register cameras");
        f688g.m829j(this.f689a.getAndroidCameraInfo());
        f688g.m832n(this.f689a.getNativeCamera());
    }

    public synchronized void startCameraChannel(java.lang.String str) {
        if (this.f690b == null) {
            this.f690b = new com.ucarhu.demo.vehicle.camera.VehicleCameraManager.a(com.ucarhu.demo.protocol.channel.ChannelType.VIDEO, false, false);
        }
        if (this.f691c == null) {
            com.ucarhu.demo.logging.EasyLogger.info(f687f, "startCameraChannel start address:" + str);
            try {
                this.f690b.m412F0(5);
                this.f691c = this.f690b.m417a0(0, str);
            } catch (java.io.IOException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f687f, "start camera channel error.", e2);
            }
        }
    }

    public void m812g(java.lang.String str, com.ucar.vehiclesdk.UCarCommon.CameraAction cameraAction, com.ucar.vehiclesdk.UCarCommon.CameraActionArgs cameraActionArgs) {
        com.ucarhu.demo.vehicle.camera.CameraProvider c0114c = f688g;
        if (c0114c != null) {
            c0114c.handleCameraRequest(cameraActionArgs.getCameraId(), cameraAction, cameraActionArgs.getPictureSize(), cameraActionArgs.getFpsRange());
        }
    }

    public boolean m813i() {
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a;
        return this.f693e.get() && (c0016a = this.f690b) != null && c0016a.mo353b();
    }

    public boolean sendCameraFrame(com.ucar.vehiclesdk.UCarCommon.VideoType videoType, java.nio.ByteBuffer byteBuffer, short s) {
        if (!m808m()) {
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f687f, "sendCameraData len = " + byteBuffer.remaining() + ", frameNumber=" + ((int) s));
        m807h(byteBuffer, com.ucar.databus.proto.UCarProto.VideoType.forNumber(videoType.getValue()), s);
        return true;
    }

    public void stopCameraChannel() {
        com.ucarhu.demo.logging.EasyLogger.info(f687f, "stopCameraChannel");
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a = this.f690b;
        if (c0016a != null) {
            c0016a.mo359q0();
            this.f690b = null;
        }
        this.f691c = null;
    }

    public void m816l(com.ucar.vehiclesdk.ICameraInfoListener iCameraInfoListener) {
        com.ucarhu.demo.vehicle.camera.CameraProvider c0114c;
        com.ucarhu.demo.logging.EasyLogger.info(f687f, "unRegisterInfoListener");
        if (this.f692d.get() && (c0114c = f688g) != null) {
            c0114c.m833p();
        }
        this.f689a = null;
    }
}
