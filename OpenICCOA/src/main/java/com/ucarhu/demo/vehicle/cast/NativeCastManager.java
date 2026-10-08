package com.ucarhu.demo.vehicle.cast;

/**
 * Native 投屏管理器，封装 SinkNative 回调、投屏启动/暂停/恢复/停止以及视频渲染控制。
 * 音频数据转交给 UCarAudioManager，视频数据进入 H264VideoPlayer 解码队列。
 */


public class NativeCastManager {

    private static final java.lang.String f730l = "CastManager";

    private com.ucar.vehiclesdk.MDevice f731a;

    private int f732b;

    public com.ucarhu.demo.vehicle.cast.CastEventListener f733c;

    public int f734d;

    public int f735e;

    public java.lang.String f736f;

    public boolean f737g;

    private com.ucarhu.demo.vehicle.media.H264VideoPlayer f738h;

    public com.ucarhu.demo.vehicle.audio.UCarAudioManager f739i;

    private com.ucarhu.demo.vehicle.media.ByteBufferQueue f740j;

    public com.ucarsink.sink.natives.SinkNative.SinkNativeCallback sinkNativeCallback;

    public class CastSinkCallback implements com.ucarsink.sink.natives.SinkNative.SinkNativeCallback {
        public CastSinkCallback() {
        }

        @Override
        public void onSinkStopped(java.lang.String str) {
            com.ucarhu.demo.vehicle.cast.CastEventListener interfaceC0117a = com.ucarhu.demo.vehicle.cast.NativeCastManager.this.f733c;
            if (interfaceC0117a != null) {
                interfaceC0117a.onCastStopped(str);
            }
        }

        @Override
        public void onAudioDataReceived(java.lang.String str, com.ucar.vehiclesdk.UCarCommon.AudioType audioType, int i, byte[] bArr) {
            com.ucarhu.demo.vehicle.audio.UCarAudioManager c0111c = com.ucarhu.demo.vehicle.cast.NativeCastManager.this.f739i;
            if (c0111c != null) {
                c0111c.playIncomingAudioData(audioType, i, bArr);
            }
        }

        @Override
        public void onVideoDataReceived(java.lang.String str, com.ucar.vehiclesdk.UCarCommon.VideoType videoType, int i, byte[] bArr) {
            com.ucarhu.demo.vehicle.cast.NativeCastManager.this.f740j.enqueue(java.nio.ByteBuffer.wrap(bArr, 0, i));
        }

        @Override
        public void onCastAudioInitialized(java.lang.String str, java.lang.String str2, int i, int i2) {
            com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat = new com.ucar.vehiclesdk.UCarCommon.AudioFormat(str2, 2, i, i2 == 1 ? 16 : 12);
            com.ucarhu.demo.vehicle.cast.CastEventListener interfaceC0117a = com.ucarhu.demo.vehicle.cast.NativeCastManager.this.f733c;
            if (interfaceC0117a != null) {
                interfaceC0117a.onAudioFormatChanged(audioFormat);
            }
        }

        @Override
        public void onUibcEncryptionChanged(java.lang.String str, boolean z) {
            com.ucarhu.demo.vehicle.cast.NativeCastManager c0118b = com.ucarhu.demo.vehicle.cast.NativeCastManager.this;
            c0118b.f736f = str;
            c0118b.f737g = z;
        }

        @Override
        public void onCastVideoInitialized(java.lang.String str, java.lang.String str2, int i, int i2) {
            com.ucarhu.demo.vehicle.cast.NativeCastManager c0118b = com.ucarhu.demo.vehicle.cast.NativeCastManager.this;
            c0118b.f734d = i;
            c0118b.f735e = i2;
            com.ucarhu.demo.vehicle.cast.CastEventListener interfaceC0117a = c0118b.f733c;
            if (interfaceC0117a != null) {
                interfaceC0117a.onVideoSizeChanged(c0118b.f736f, i, i2, c0118b.f737g);
            }
        }
    }

    public NativeCastManager(com.ucarhu.demo.vehicle.audio.UCarAudioManager c0111c, int i, com.ucarhu.demo.vehicle.cast.CastEventListener interfaceC0117a) {
        this(c0111c, i, null, interfaceC0117a);
    }

    public NativeCastManager(com.ucarhu.demo.vehicle.audio.UCarAudioManager c0111c, int i, com.ucar.vehiclesdk.MDevice mDevice, com.ucarhu.demo.vehicle.cast.CastEventListener interfaceC0117a) {
        this.f740j = new com.ucarhu.demo.vehicle.media.ByteBufferQueue("video", true);
        this.sinkNativeCallback = new com.ucarhu.demo.vehicle.cast.NativeCastManager.CastSinkCallback();
        this.f732b = i;
        this.f731a = mDevice;
        this.f739i = c0111c;
        if (this.f738h == null) {
            this.f738h = new com.ucarhu.demo.vehicle.media.H264VideoPlayer(this.f740j, i);
        }
        this.f733c = interfaceC0117a;
    }

    private synchronized void m847i() {
        com.ucarhu.demo.vehicle.media.H264VideoPlayer c0138j = this.f738h;
        if (c0138j != null) {
            c0138j.release();
            this.f738h = null;
        }
    }

    public synchronized void pauseVideoRender() {
        com.ucarhu.demo.vehicle.media.H264VideoPlayer c0138j = this.f738h;
        if (c0138j != null && c0138j.m1051l()) {
            this.f738h.pause();
            this.f738h.m1049e(false);
        }
    }

    public void startNativeCast(int i, int i2, int i3, int i4) {
        com.ucar.vehiclesdk.MDevice mDevice = this.f731a;
        if (mDevice == null || android.text.TextUtils.isEmpty(mDevice.getAddress())) {
            com.ucarhu.demo.logging.EasyLogger.error(f730l, "MDevice is null, can not cast");
        } else {
            com.ucarsink.sink.natives.SinkNative.registerCallback(this.sinkNativeCallback);
            com.ucarsink.sink.natives.SinkNative.start(this.f731a.getAddress(), this.f731a.getPort(), i, i2, i3, i4);
        }
    }

    public void m850d(com.ucar.vehiclesdk.MDevice mDevice) {
        this.f731a = mDevice;
    }

    public void pauseCast(java.lang.String str) {
        com.ucarsink.sink.natives.SinkNative.pause(str);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean startVideoRender(android.view.Surface surface, int i, int i2, boolean z) {
        try {
            com.ucarhu.demo.vehicle.media.H264VideoPlayer c0138j = this.f738h;
            if (c0138j == null || c0138j.m1050i()) {
                if (c0138j != null) {
                    c0138j.release();
                }
                this.f738h = new com.ucarhu.demo.vehicle.media.H264VideoPlayer(this.f740j, this.f732b);
            }
            this.f738h.configure(surface, i, i2, z);
            if (!this.f738h.m1052n()) {
                this.f738h.start();
            }
            this.f738h.m1049e(true);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f730l, "start cast exception", e2);
            return false;
        }
        return true;
    }

    public void m853g() {
        com.ucarsink.sink.natives.SinkNative.clearCallback();
    }

    public void resumeCast(java.lang.String str) {
        com.ucarsink.sink.natives.SinkNative.resume(str);
    }

    public void stopNativeCast(java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.debug(f730l, "stop Cast");
        pauseVideoRender();
        m847i();
        com.ucarsink.sink.natives.SinkNative.stop(str);
        com.ucarsink.sink.natives.SinkNative.clearCallback();
    }
}
