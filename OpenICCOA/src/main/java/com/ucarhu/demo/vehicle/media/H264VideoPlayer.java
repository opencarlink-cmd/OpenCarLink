package com.ucarhu.demo.vehicle.media;

public class H264VideoPlayer {

    private static final java.lang.String f905r = "vendor.qti-ext-dec-low-latency.enable";

    private static final java.lang.String f906s = "vendor.qti-ext-dec-picture-order.enable";

    private static final float f907t = 15.0f;

    private static final java.lang.String f908u = "VideoPlayerV2";

    private static final java.lang.String f909v = "video/avc";

    private static final int f910w = 5;

    private static final int f911x = 100;

    private float f917f;

    private android.media.MediaCodec f919h;

    private android.os.HandlerThread f920i;

    private android.os.Handler f921j;

    private com.ucarhu.demo.vehicle.media.ByteBufferQueue f922k;

    private final int f923l;

    private final int f924m;

    private android.view.Surface f925n;

    private android.graphics.SurfaceTexture f926o;

    private int f927p;

    private int f928q;

    private volatile boolean f912a = false;

    private volatile boolean f913b = false;

    private volatile boolean f914c = false;

    private volatile boolean f915d = false;

    private long f916e = 0;

    private long f918g = 0;

    public class a extends android.media.MediaCodec.Callback {
        public a() {
        }

        public void m1056a(android.media.MediaCodec mediaCodec, int i) {
            java.nio.ByteBuffer byteBufferM1030d = com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f922k.take();
            if (byteBufferM1030d != null) {
                try {
                    if (byteBufferM1030d.remaining() > 0) {
                        try {
                            java.nio.ByteBuffer inputBuffer = mediaCodec.getInputBuffer(i);
                            inputBuffer.clear();
                            int iRemaining = byteBufferM1030d.remaining();
                            inputBuffer.limit(iRemaining);
                            inputBuffer.put(byteBufferM1030d);
                            mediaCodec.queueInputBuffer(i, 0, iRemaining, 5 * com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f918g, 0);
                            com.ucarhu.demo.vehicle.media.H264VideoPlayer.m1047s(com.ucarhu.demo.vehicle.media.H264VideoPlayer.this);
                        } catch (java.lang.IllegalStateException e2) {
                            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.media.H264VideoPlayer.f908u, "get input buffer failed ", e2);
                            com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f915d = true;
                        }
                    }
                } finally {
                    com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f922k.recycle(byteBufferM1030d);
                }
            }
        }

        @Override
        public void onError(android.media.MediaCodec mediaCodec, android.media.MediaCodec.CodecException codecException) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.media.H264VideoPlayer.f908u, "onError: when decode data. errorCode: " + codecException.getErrorCode() + ", isTransient: " + codecException.isTransient() + ", isRecoverable: " + codecException.isRecoverable() + ", message: " + codecException.getMessage());
            com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f915d = true;
        }

        @Override
        public void onInputBufferAvailable(final android.media.MediaCodec mediaCodec, final int i) {
            if (i >= 0) {
                com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f921j.post(new java.lang.Runnable() {
                    @Override
                    public final void run() {
                        a.this.m1056a(mediaCodec, i);
                    }
                });
                return;
            }
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.media.H264VideoPlayer.f908u, " index " + i);
        }

        @Override
        public void onOutputBufferAvailable(android.media.MediaCodec mediaCodec, int i, android.media.MediaCodec.BufferInfo bufferInfo) {
            int iM1033g = com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f922k.queueSize();
            long jElapsedRealtime = android.os.SystemClock.elapsedRealtime();
            boolean z = com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f914c;
            if (z && iM1033g >= com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f924m && com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f916e != 0 && jElapsedRealtime - com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f916e < com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f917f) {
                z = false;
            }
            try {
                mediaCodec.releaseOutputBuffer(i, z);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.media.H264VideoPlayer.f908u, "release output buffer failed,", e2);
                com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f915d = true;
            }
            if (z) {
                com.ucarhu.demo.vehicle.media.H264VideoPlayer.this.f916e = jElapsedRealtime;
            }
        }

        @Override
        public void onOutputFormatChanged(android.media.MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) {
            com.ucarhu.demo.logging.EasyLogger.warn(com.ucarhu.demo.vehicle.media.H264VideoPlayer.f908u, "onOutputFormatChanged: " + mediaFormat);
        }
    }

    public H264VideoPlayer(com.ucarhu.demo.vehicle.media.ByteBufferQueue c0137i, int i) {
        this.f917f = 0.0f;
        this.f923l = i;
        float f2 = i;
        this.f917f = 1000.0f / f2;
        this.f924m = (int) (f2 * 0.1f);
        this.f922k = c0137i;
        android.os.HandlerThread handlerThread = new android.os.HandlerThread("VideoSinkInputThread");
        this.f920i = handlerThread;
        handlerThread.start();
        android.os.Looper looper = this.f920i.getLooper();
        if (looper == null) {
            throw new java.lang.RuntimeException("Failed to start wait input thread!");
        }
        this.f921j = new android.os.Handler(looper);
        try {
            com.ucarhu.demo.logging.EasyLogger.info(f908u, "createDecoderByType video/avc");
            this.f919h = android.media.MediaCodec.createDecoderByType(f909v);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "createDecoderByType failed", e2);
        }
    }

    private void m1038c() {
        if (this.f926o == null || this.f925n == null) {
            android.graphics.SurfaceTexture surfaceTexture = new android.graphics.SurfaceTexture(0);
            this.f926o = surfaceTexture;
            surfaceTexture.setDefaultBufferSize(this.f927p, this.f928q);
            this.f925n = new android.view.Surface(this.f926o);
        }
    }

    private void m1041h() {
        android.graphics.SurfaceTexture surfaceTexture = this.f926o;
        if (surfaceTexture != null) {
            try {
                surfaceTexture.release();
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "dummy texture release failed", e2);
            }
            this.f926o = null;
        }
        android.view.Surface surface = this.f925n;
        if (surface != null) {
            try {
                surface.release();
            } catch (java.lang.Exception e3) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "dummy surface release failed", e3);
            }
            this.f925n = null;
        }
    }

    public static long m1047s(com.ucarhu.demo.vehicle.media.H264VideoPlayer c0138j) {
        long j = c0138j.f918g;
        c0138j.f918g = 1 + j;
        return j;
    }

    public synchronized void configure(android.view.Surface surface, int i, int i2, boolean z) throws java.lang.IllegalStateException, java.lang.IllegalArgumentException {
        java.lang.String str = null;
        if (surface == null || i <= 0 || i2 <= 0) {
            com.ucarhu.demo.logging.EasyLogger.error(f908u, "failed to init");
            return;
        }
        if (this.f912a) {
            com.ucarhu.demo.logging.EasyLogger.debug(f908u, "init, mHasConfigured is true, reset output surface");
            try {
                this.f919h.setOutputSurface(surface);
            } catch (java.lang.IllegalStateException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "init: setOutputSurface failed", e2);
                this.f915d = true;
            }
        } else {
            com.ucarhu.demo.logging.EasyLogger.info(f908u, "init, configureDecoder");
            android.media.MediaFormat mediaFormatCreateVideoFormat = android.media.MediaFormat.createVideoFormat(f909v, i, i2);
            if (z) {
                if (com.ucarhu.demo.vehicle.util.QualcommPlatformUtils.isQualcommHardware()) {
                    mediaFormatCreateVideoFormat.setInteger(f905r, 1);
                    str = f906s;
                } else if (android.os.Build.VERSION.SDK_INT >= 30) {
                    str = "low-latency";
                }
                if (str != null) {
                    mediaFormatCreateVideoFormat.setInteger(str, 1);
                }
            }
            try {
                this.f919h.configure(mediaFormatCreateVideoFormat, surface, (android.media.MediaCrypto) null, 0);
                android.os.Bundle bundle = new android.os.Bundle();
                bundle.putFloat("operating-rate", this.f923l + f907t);
                this.f919h.setParameters(bundle);
                this.f912a = true;
            } catch (java.lang.IllegalArgumentException | java.lang.IllegalStateException e3) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "codec configured failed", e3);
            }
        }
        this.f927p = i;
        this.f928q = i2;
        com.ucarhu.demo.logging.EasyLogger.info(f908u, "init done");
    }

    public void m1049e(boolean z) {
        com.ucarhu.demo.logging.EasyLogger.debug(f908u, "enableRender " + z);
        this.f914c = z;
    }

    public synchronized boolean m1050i() {
        return this.f915d;
    }

    public synchronized boolean m1051l() {
        return this.f912a;
    }

    public synchronized boolean m1052n() {
        return this.f913b;
    }

    public synchronized void pause() {
        com.ucarhu.demo.logging.EasyLogger.info(f908u, "pause");
        m1038c();
        try {
            this.f919h.setOutputSurface(this.f925n);
        } catch (java.lang.IllegalStateException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "pause: setOutputSurface failed", e2);
            this.f915d = true;
        }
    }

    public synchronized void release() {
        android.media.MediaCodec mediaCodec;
        com.ucarhu.demo.logging.EasyLogger.info(f908u, "release");
        try {
            android.os.HandlerThread handlerThread = this.f920i;
            if (handlerThread != null) {
                handlerThread.quit();
                this.f920i.interrupt();
                this.f920i.join();
                this.f920i = null;
            }
            com.ucarhu.demo.vehicle.media.ByteBufferQueue c0137i = this.f922k;
            if (c0137i != null) {
                c0137i.clear();
                this.f922k = null;
            }
        } catch (java.lang.IllegalStateException | java.lang.InterruptedException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "release video player exception", e2);
        }
        try {
            try {
                android.media.MediaCodec mediaCodec2 = this.f919h;
                if (mediaCodec2 != null) {
                    mediaCodec2.stop();
                }
                mediaCodec = this.f919h;
            } catch (java.lang.IllegalStateException e3) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f908u, "release video player exception", e3);
                mediaCodec = this.f919h;
                if (mediaCodec != null) {
                }
            }
            if (mediaCodec != null) {
                mediaCodec.release();
                this.f919h = null;
            }
            m1041h();
        } catch (java.lang.Throwable th) {
            android.media.MediaCodec mediaCodec3 = this.f919h;
            if (mediaCodec3 != null) {
                mediaCodec3.release();
                this.f919h = null;
            }
            throw new RuntimeException();
        }
    }

    public synchronized void start() {
        com.ucarhu.demo.logging.EasyLogger.info(f908u, "startCodec");
        if (!this.f913b) {
            this.f919h.setCallback(new com.ucarhu.demo.vehicle.media.H264VideoPlayer.a());
            this.f919h.start();
            this.f913b = true;
        }
    }
}
