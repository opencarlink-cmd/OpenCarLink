package com.ucarhu.demo.vehicle.camera;

public class H264CameraPreviewEncoder extends android.media.MediaCodec.Callback {

    private static final java.lang.String f718f = "VideoEncoder";

    private static final int f719g = 1920;

    private static final int f720h = 1080;

    private static final int f721i = 5242880;

    private static final int f722j = 10485760;

    private static final int f723k = 60;

    private static final int f724l = 15;

    private final android.util.Size f725a;

    private final int f726b;

    private final com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder.a f727c;

    private android.media.MediaCodec f728d;

    private android.os.HandlerThread f729e;

    public interface a {
        void mo834a(java.lang.String str);

        void mo835a(java.nio.ByteBuffer byteBuffer, int i);
    }

    public H264CameraPreviewEncoder(@androidx.annotation.NonNull android.util.Size size, int i, @androidx.annotation.NonNull com.ucarhu.demo.vehicle.camera.H264CameraPreviewEncoder.a aVar) {
        this.f725a = size;
        if (i <= 0 || i >= 60) {
            this.f726b = 60;
        } else {
            this.f726b = i;
        }
        this.f727c = aVar;
    }

    private android.media.MediaFormat m839b() {
        android.media.MediaFormat mediaFormatCreateVideoFormat = android.media.MediaFormat.createVideoFormat("video/avc", this.f725a.getWidth(), this.f725a.getHeight());
        mediaFormatCreateVideoFormat.setInteger("bitrate", (this.f725a.getWidth() < f719g || this.f725a.getHeight() < f720h) ? f721i : f722j);
        mediaFormatCreateVideoFormat.setInteger("bitrate-mode", 1);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", this.f726b);
        mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 15);
        mediaFormatCreateVideoFormat.setInteger("profile", 1);
        mediaFormatCreateVideoFormat.setInteger("level", 1);
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            mediaFormatCreateVideoFormat.setInteger("max-bframes", 0);
        }
        return mediaFormatCreateVideoFormat;
    }

    public final android.view.Surface createInputSurface() {
        try {
            android.media.MediaCodec mediaCodecCreateEncoderByType = android.media.MediaCodec.createEncoderByType("video/avc");
            this.f728d = mediaCodecCreateEncoderByType;
            mediaCodecCreateEncoderByType.configure(m839b(), (android.view.Surface) null, (android.media.MediaCrypto) null, 1);
            return this.f728d.createInputSurface();
        } catch (java.io.IOException e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f718f, "createInputSurface" + e2.getMessage());
            return null;
        }
    }

    public void startEncoder() {
        try {
            android.os.HandlerThread handlerThread = new android.os.HandlerThread(f718f);
            this.f729e = handlerThread;
            handlerThread.start();
            this.f728d.setCallback(this, new android.os.Handler(this.f729e.getLooper()));
            this.f728d.start();
            com.ucarhu.demo.logging.EasyLogger.info(f718f, "started");
        } catch (java.lang.Exception unused) {
            com.ucarhu.demo.logging.EasyLogger.error(f718f, "start failed");
        }
    }

    public void stopEncoder() {
        try {
            android.media.MediaCodec mediaCodec = this.f728d;
            if (mediaCodec != null) {
                mediaCodec.stop();
                this.f728d.release();
                this.f728d = null;
            }
            android.os.HandlerThread handlerThread = this.f729e;
            if (handlerThread != null) {
                handlerThread.quitSafely();
                this.f729e = null;
            }
            com.ucarhu.demo.logging.EasyLogger.info(f718f, "stopped");
        } catch (java.lang.Exception unused) {
            com.ucarhu.demo.logging.EasyLogger.error(f718f, "stop failed");
        }
    }

    @Override
    public void onError(android.media.MediaCodec mediaCodec, android.media.MediaCodec.CodecException codecException) {
        com.ucarhu.demo.logging.EasyLogger.info(f718f, "onError " + codecException.getMessage());
        this.f727c.mo834a(codecException.getMessage());
    }

    @Override
    public void onInputBufferAvailable(android.media.MediaCodec mediaCodec, int i) {
        com.ucarhu.demo.logging.EasyLogger.info(f718f, "onInputBufferAvailable");
    }

    @Override
    public void onOutputBufferAvailable(android.media.MediaCodec mediaCodec, int i, android.media.MediaCodec.BufferInfo bufferInfo) {
        try {
            com.ucarhu.demo.logging.EasyLogger.info(f718f, "onOutputBufferAvailable " + i + ", timestamps = " + java.lang.System.currentTimeMillis());
            this.f727c.mo835a(mediaCodec.getOutputBuffer(i), bufferInfo.flags);
            mediaCodec.releaseOutputBuffer(i, false);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f718f, "onOutputBufferAvailable exception : " + e2.getMessage());
        }
    }

    @Override
    public void onOutputFormatChanged(android.media.MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) {
        com.ucarhu.demo.logging.EasyLogger.info(f718f, "onOutputFormatChanged " + mediaFormat);
    }
}
