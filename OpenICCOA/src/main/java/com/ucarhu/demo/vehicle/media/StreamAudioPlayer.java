package com.ucarhu.demo.vehicle.media;

public class StreamAudioPlayer {

    private static final java.lang.String f843f = "AudioPlayer";

    private static final int f844g = 131072;

    private static final int f845h = 50;

    private static final java.lang.ThreadLocal<byte[]> f846i = java.lang.ThreadLocal.withInitial(new java.util.function.Supplier() {
        @Override
        public final java.lang.Object get() {
            return com.ucarhu.demo.vehicle.media.StreamAudioPlayer.createTempAudioBuffer();
        }
    });

    private final com.ucarhu.demo.vehicle.media.ByteBufferQueue f847a;

    private com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioPlaybackThread f848b;

    private com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder f849c;

    private final int f850d;

    private boolean f851e = false;

    /**
     * AAC 输入流同步解码器，负责把压缩音频送入 MediaCodec 并把 PCM 输出写入播放队列。
     */
    public static class AudioDecoder {

        private static final java.lang.String f852g = "AudioPlayer.Decode";

        private static final java.lang.String f853h = "adec-input";

        private static final java.lang.String f854i = "adec-output";

        private static final long f855j = 500000;

        private static final long f856k = 1000;

        private final java.util.concurrent.atomic.AtomicInteger f857a = new java.util.concurrent.atomic.AtomicInteger(0);

        private final java.util.concurrent.atomic.AtomicBoolean f858b = new java.util.concurrent.atomic.AtomicBoolean(false);

        private final com.ucarhu.demo.vehicle.media.ByteBufferQueue f859c = new com.ucarhu.demo.vehicle.media.ByteBufferQueue("aac", false);

        private final com.ucarhu.demo.vehicle.media.ByteBufferQueue f860d;

        private java.lang.Thread f861e;

        private android.media.MediaCodec f862f;

        /** MediaCodec 输入线程：从 AAC 队列取数据并写入解码器输入缓冲区。 */
        public class DecoderInputThread extends java.lang.Thread {
            public DecoderInputThread(java.lang.String str) {
                super(str);
            }

            @Override
            public void run() {
                com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.f857a.incrementAndGet();
                while (com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.f858b.get()) {
                    com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.feedDecoderInput();
                }
                if (com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.f857a.decrementAndGet() <= 0) {
                    com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.releaseDecoder();
                }
            }
        }

        /** MediaCodec 输出线程：取出 PCM 数据并转交给 AudioTrack 播放队列。 */
        public class DecoderOutputThread extends java.lang.Thread {
            public DecoderOutputThread(java.lang.String str) {
                super(str);
            }

            @Override
            public void run() {
                com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.f857a.incrementAndGet();
                while (com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.f858b.get()) {
                    com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.drainDecoderOutput();
                }
                if (com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.f857a.decrementAndGet() <= 0) {
                    com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.this.releaseDecoder();
                }
            }
        }

        public AudioDecoder(@androidx.annotation.NonNull com.ucarhu.demo.vehicle.media.ByteBufferQueue c0137i, java.lang.String str, int i, int i2) {
            this.f860d = c0137i;
            int i3 = i == 4 ? 1 : 2;
            android.media.MediaFormat mediaFormatCreateAudioFormat = android.media.MediaFormat.createAudioFormat(str, i2, i3);
            if (!str.equals("audio/mp4a-latm")) {
                throw new java.lang.RuntimeException("not support " + str);
            }
            com.ucarhu.demo.logging.EasyLogger.info(f852g, "init Sync Codec, mimeType is AAC channels = " + i3);
            mediaFormatCreateAudioFormat.setInteger("aac-profile", 2);
            mediaFormatCreateAudioFormat.setInteger("is-adts", 1);
            mediaFormatCreateAudioFormat.setByteBuffer("csd-0", java.nio.ByteBuffer.wrap(new byte[]{17, -112}));
            try {
                android.media.MediaCodec mediaCodecCreateDecoderByType = android.media.MediaCodec.createDecoderByType(str);
                this.f862f = mediaCodecCreateDecoderByType;
                mediaCodecCreateDecoderByType.configure(mediaFormatCreateAudioFormat, (android.view.Surface) null, (android.media.MediaCrypto) null, 0);
                this.f862f.start();
            } catch (java.io.IOException unused) {
                throw new java.lang.RuntimeException("failed to create decoder");
            }
        }

        public void feedDecoderInput() {
            java.nio.ByteBuffer byteBufferM1030d = this.f859c.take();
            if (byteBufferM1030d == null || byteBufferM1030d.remaining() == 0) {
                return;
            }
            int iDequeueInputBuffer = this.f862f.dequeueInputBuffer(f855j);
            if (iDequeueInputBuffer >= 0) {
                long jCurrentTimeMillis = java.lang.System.currentTimeMillis();
                try {
                    try {
                        int iRemaining = byteBufferM1030d.remaining();
                        java.nio.ByteBuffer inputBuffer = this.f862f.getInputBuffer(iDequeueInputBuffer);
                        inputBuffer.clear();
                        inputBuffer.limit(iRemaining);
                        inputBuffer.put(byteBufferM1030d);
                        this.f862f.queueInputBuffer(iDequeueInputBuffer, 0, iRemaining, jCurrentTimeMillis, 0);
                    } catch (java.lang.Exception e2) {
                        com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f852g, "get input buffer failed ", e2);
                    }
                } finally {
                    this.f859c.recycle(byteBufferM1030d);
                }
            }
        }

        public void drainDecoderOutput() {
            android.media.MediaCodec.BufferInfo bufferInfo = new android.media.MediaCodec.BufferInfo();
            int iDequeueOutputBuffer = this.f862f.dequeueOutputBuffer(bufferInfo, f855j);
            if (iDequeueOutputBuffer < 0 || bufferInfo.size == 0) {
                return;
            }
            if (java.lang.System.currentTimeMillis() - bufferInfo.presentationTimeUs < f856k) {
                java.nio.ByteBuffer outputBuffer = this.f862f.getOutputBuffer(iDequeueOutputBuffer);
                this.f860d.enqueue(outputBuffer);
                outputBuffer.clear();
            }
            this.f862f.releaseOutputBuffer(iDequeueOutputBuffer, false);
        }

        public void releaseDecoder() {
            android.media.MediaCodec mediaCodec;
            com.ucarhu.demo.logging.EasyLogger.info(f852g, "release audio decoder");
            try {
                try {
                    android.media.MediaCodec mediaCodec2 = this.f862f;
                    if (mediaCodec2 != null) {
                        mediaCodec2.stop();
                    }
                    mediaCodec = this.f862f;
                    if (mediaCodec == null) {
                        return;
                    }
                } catch (java.lang.Exception e2) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f852g, "release audio decoder exception", e2);
                    mediaCodec = this.f862f;
                    if (mediaCodec == null) {
                        return;
                    }
                }
                try {
                    mediaCodec.release();
                } catch (java.lang.Exception e3) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f852g, "release audio decoder exception", e3);
                }
                this.f862f = null;
            } catch (java.lang.Throwable th) {
                android.media.MediaCodec mediaCodec3 = this.f862f;
                if (mediaCodec3 != null) {
                    try {
                        mediaCodec3.release();
                    } catch (java.lang.Exception e4) {
                        com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f852g, "release audio decoder exception", e4);
                    }
                    this.f862f = null;
                }
                throw new RuntimeException();
            }
        }

        public com.ucarhu.demo.vehicle.media.ByteBufferQueue getInputQueue() {
            return this.f859c;
        }

        public void startDecoder() {
            if (this.f858b.get()) {
                return;
            }
            this.f858b.set(true);
            com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.DecoderInputThread inputThread = new com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.DecoderInputThread(f853h);
            this.f861e = inputThread;
            inputThread.start();
            new com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder.DecoderOutputThread(f854i).start();
        }

        public void stopDecoder() {
            com.ucarhu.demo.logging.EasyLogger.info(f852g, "stop decoder " + this.f862f);
            this.f858b.set(false);
            java.lang.Thread thread = this.f861e;
            if (thread != null) {
                try {
                    thread.interrupt();
                    this.f861e.join(50L);
                } catch (java.lang.InterruptedException e2) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f852g, "stop audio decoder exception", e2);
                }
                this.f861e = null;
            }
        }
    }

    /**
     * PCM 播放线程，维护 AudioTrack、缓冲水位和播放速度微调策略。
     */
    public static class AudioPlaybackThread extends java.lang.Thread {

        private static final java.lang.String f865o = "AudioPlayer.Play";

        private static final float f866p = 1.0f;

        private static final float f867q = 1.2f;

        private static final int f868r = 2;

        private static final int f869s = 16;

        private static final int f870t = 1;

        private static final int f871u = 2;

        private static final int f872v = 4;

        private final com.ucarhu.demo.vehicle.media.ByteBufferQueue f873b;

        private final int f874c;

        private final int f875d;

        private final int f876e;

        private int f877f = 100;

        private float f878g = f866p;

        private volatile boolean f879h = false;

        private volatile boolean f880i = false;

        private android.media.AudioTrack f881j;

        private int f882k;

        private int f883l;

        private int f884m;

        private int f885n;

        public AudioPlaybackThread(int i, int i2, int i3, android.media.AudioAttributes audioAttributes) {
            this.f873b = new com.ucarhu.demo.vehicle.media.ByteBufferQueue("pcm@" + audioAttributes.getUsage(), false);
            this.f874c = bytesPerSample(i) * java.lang.Integer.bitCount(i3) * i2;
            if (audioAttributes.getUsage() == 1) {
                this.f875d = com.ucarhu.demo.vehicle.audio.UCarAudioManager.b.f658d;
                this.f876e = 200;
            } else {
                this.f875d = com.ucarhu.demo.vehicle.audio.UCarAudioManager.b.f659e;
                this.f876e = 100;
                updateAudioPlayParams(100, 10);
            }
            this.f881j = createAudioTrack(i, i2, i3, audioAttributes);
        }

        private int bytesPerSample(int i) {
            if (i == 13 || i == 1 || i == 2) {
                return 2;
            }
            if (i == 3) {
                return 1;
            }
            if (i == 4) {
                return 4;
            }
            throw new java.lang.IllegalArgumentException("Bad audio format " + i);
        }

        private int calculateAudioTrackBufferSize(int i, int i2, int i3) {
            return ((((int) java.lang.Math.ceil(android.media.AudioTrack.getMinBufferSize(i2, i3, i) * f867q)) / 16) + 1) * 16;
        }

        private android.media.AudioTrack createAudioTrack(int i, int i2, int i3, android.media.AudioAttributes audioAttributes) {
            int iM1012b = calculateAudioTrackBufferSize(i, i2, i3);
            android.media.AudioFormat audioFormatBuild = new android.media.AudioFormat.Builder().setEncoding(i).setSampleRate(i2).setChannelMask(i3).build();
            com.ucarhu.demo.logging.EasyLogger.debug(f865o, "create audio track, encoding = " + i + ", sampleRate = " + i2 + ", adjustStep = " + this.f877f + "ms, channels = " + i3 + ", bufferSize:" + iM1012b + ", audioAttributes:" + audioAttributes);
            return new android.media.AudioTrack.Builder().setAudioAttributes(audioAttributes).setAudioFormat(audioFormatBuild).setTransferMode(1).setBufferSizeInBytes(iM1012b).setSessionId((audioAttributes.getUsage() == 2 && audioAttributes.getContentType() == 1) ? com.ucarhu.demo.vehicle.media.AudioSessionProvider.nextAudioSessionId() : 0).build();
        }

        private void adjustPlaybackSpeed(float f2) {
            if (f2 == this.f878g) {
                return;
            }
            com.ucarhu.demo.logging.EasyLogger.info(f865o, "try adjust playback speed from x" + this.f878g + " to x" + f2);
            try {
                android.media.AudioTrack audioTrack = this.f881j;
                if (audioTrack != null) {
                    android.media.PlaybackParams playbackParams = audioTrack.getPlaybackParams();
                    playbackParams.setSpeed(f2);
                    this.f881j.setPlaybackParams(playbackParams);
                    this.f878g = f2;
                }
            } catch (java.lang.IllegalArgumentException e2) {
                com.ucarhu.demo.logging.EasyLogger.warn(f865o, "set speed msg = " + e2.getMessage());
            }
        }

        public com.ucarhu.demo.vehicle.media.ByteBufferQueue getPcmQueue() {
            return this.f873b;
        }

        public android.media.AudioAttributes buildAudioAttributes(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
            return new android.media.AudioAttributes.Builder().setUsage(1).setContentType(2).build();
        }

        public void updateAudioPlayParams(int i, int i2) {
            if (i > this.f875d || i2 > this.f876e || i < 100 || i2 < 10) {
                return;
            }
            this.f877f = i2;
            this.f884m = i;
            int i3 = (this.f874c * i) / 1000;
            this.f885n = i3;
            this.f882k = i3 * 2;
            this.f883l = i3 / 2;
            com.ucarhu.demo.logging.EasyLogger.debug(f865o, "updateAudioPlayParams mBytesPerSecond = " + this.f874c + ", mBuffingBytes = " + this.f885n + ", mBuffingCount = " + i + "ms");
        }

        public void pausePlayback() throws java.lang.IllegalStateException {
            this.f880i = true;
            this.f873b.clear();
            android.media.AudioTrack audioTrack = this.f881j;
            if (audioTrack == null || audioTrack.getPlayState() != 3) {
                return;
            }
            this.f881j.pause();
            this.f881j.flush();
        }

        public boolean waitBufferToFill(int i) {
            com.ucarhu.demo.logging.EasyLogger.info(f865o, "wait buffer to fill bytes: " + i);
            while (this.f873b.queuedBytes() < i) {
                this.f873b.waitForData();
                if (!this.f879h) {
                    return false;
                }
            }
            com.ucarhu.demo.logging.EasyLogger.info(f865o, "buffer fill done, number: " + this.f873b.queueSize() + ", bytes: " + this.f873b.queuedBytes());
            return true;
        }

        public void resumePlayback() throws java.lang.IllegalStateException {
            this.f880i = false;
            this.f873b.clear();
            android.media.AudioTrack audioTrack = this.f881j;
            if (audioTrack == null) {
                com.ucarhu.demo.logging.EasyLogger.warn(f865o, "track is null");
            } else if (audioTrack.getPlayState() == 2) {
                this.f881j.play();
            }
        }

        public void stopPlaybackThread() {
            this.f879h = false;
            try {
                interrupt();
                join(50L);
            } catch (java.lang.InterruptedException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f865o, "stop audio player exception", e2);
            }
        }

        @Override
        public void run(){
            this.f879h = true;
            waitBufferToFill(this.f885n);
            this.f881j.play();
            this.f881j.setVolume(f866p);
            while (this.f879h && (this.f873b.queuedBytes() > 0 || waitBufferToFill(this.f883l))) {
                java.nio.ByteBuffer byteBufferM1030d = this.f873b.take();
                if (byteBufferM1030d != null) {
                    if (this.f880i) {
                        this.f873b.recycle(byteBufferM1030d);
                    } else {
                        if (byteBufferM1030d.hasArray()) {
                            this.f881j.write(byteBufferM1030d.array(), byteBufferM1030d.arrayOffset(), byteBufferM1030d.remaining(), 0);
                        } else {
                            byte[] bArrM991f = com.ucarhu.demo.vehicle.media.StreamAudioPlayer.getTempBytes(byteBufferM1030d.remaining());
                            byteBufferM1030d.get(bArrM991f, 0, bArrM991f.length);
                            byteBufferM1030d.clear();
                            this.f881j.write(bArrM991f, 0, bArrM991f.length, 0);
                        }
                        this.f873b.recycle(byteBufferM1030d);
                    }
                }
                if (!this.f879h) {
                    break;
                }
                int iM1032f = this.f873b.queuedBytes();
                if (iM1032f != 0) {
                    if (iM1032f > this.f874c) {
                        this.f873b.clear();
                        com.ucarhu.demo.logging.EasyLogger.error(f865o, "discard call remain data: " + iM1032f);
                    } else if (iM1032f > this.f882k) {
                        adjustPlaybackSpeed(f867q);
                        com.ucarhu.demo.logging.EasyLogger.error(f865o, "play faster when remain data: " + iM1032f + ", and try increase buffering");
                        int i = this.f884m;
                        int i2 = this.f877f;
                        updateAudioPlayParams(i + i2, i2);
                    } else if (iM1032f >= this.f883l || this.f878g == f866p) {
                    }
                    adjustPlaybackSpeed(f866p);
                }
            }
            this.f881j.stop();
            this.f881j.release();
            this.f881j = null;
            this.f873b.clear();
        }
    }

    public StreamAudioPlayer(android.media.AudioAttributes audioAttributes, com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat) {
        com.ucarhu.demo.vehicle.media.ByteBufferQueue c0137iM1008a;
        this.f849c = null;
        this.f850d = audioAttributes.getUsage();
        int encodingFormat = audioFormat.getEncodingFormat();
        int sampleRate = audioFormat.getSampleRate();
        int channelConfig = audioFormat.getChannelConfig();
        java.lang.String mimeType = audioFormat.getMimeType();
        this.f848b = new com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioPlaybackThread(encodingFormat, sampleRate, channelConfig, audioAttributes);
        if (mimeType.equals("audio/pcm")) {
            c0137iM1008a = this.f848b.getPcmQueue();
        } else {
            try {
                com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder aVar = new com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder(this.f848b.getPcmQueue(), mimeType, channelConfig, sampleRate);
                this.f849c = aVar;
                aVar.startDecoder();
                c0137iM1008a = this.f849c.getInputQueue();
            } catch (java.lang.RuntimeException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f843f, "init decoder failed", e2);
                c0137iM1008a = this.f848b.getPcmQueue();
            }
        }
        this.f847a = c0137iM1008a;
    }

    public static byte[] createTempAudioBuffer() {
        return new byte[131072];
    }

    public static byte[] getTempBytes(int i) {
        if (i < 131072) {
            return f846i.get();
        }
        com.ucarhu.demo.logging.EasyLogger.warn(f843f, "getTempBytes not use thread local array, maybe MAX_TEMP_ARRAY_SIZE is too small, require:" + i);
        return new byte[i];
    }

    public void updateAudioPlayParams(int i, int i2) {
        if (i > 0 && i2 > 0) {
            com.ucarhu.demo.logging.EasyLogger.debug(f843f, "updateAudioPlayParams by manual");
            com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioPlaybackThread bVar = this.f848b;
            if (bVar != null) {
                bVar.updateAudioPlayParams(i, i2);
                return;
            }
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.error(f843f, "error count = " + i + ", step = " + i2);
    }

    public void enqueueAudio(java.nio.ByteBuffer byteBuffer) {
        if (this.f851e) {
            return;
        }
        this.f847a.enqueue(byteBuffer);
    }

    public void pause() throws java.lang.IllegalStateException {
        com.ucarhu.demo.logging.EasyLogger.info(f843f, "pause player type " + this.f850d);
        if (this.f848b == null || this.f851e) {
            return;
        }
        this.f851e = true;
        this.f847a.clear();
        this.f848b.pausePlayback();
    }

    public void startPlayback() {
        this.f848b.start();
    }

    public void release() {
        com.ucarhu.demo.logging.EasyLogger.info(f843f, "release player type " + this.f850d);
        this.f849c = null;
        this.f848b = null;
    }

    public void resume() throws java.lang.IllegalStateException {
        com.ucarhu.demo.logging.EasyLogger.info(f843f, "resume player type " + this.f850d);
        if (this.f848b == null || !this.f851e) {
            return;
        }
        this.f851e = false;
        this.f847a.clear();
        this.f848b.resumePlayback();
    }

    public void stop() {
        com.ucarhu.demo.logging.EasyLogger.info(f843f, "stop player type " + this.f850d);
        com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioDecoder aVar = this.f849c;
        if (aVar != null) {
            aVar.stopDecoder();
        }
        com.ucarhu.demo.vehicle.media.StreamAudioPlayer.AudioPlaybackThread bVar = this.f848b;
        if (bVar != null) {
            bVar.stopPlaybackThread();
        }
    }
}
