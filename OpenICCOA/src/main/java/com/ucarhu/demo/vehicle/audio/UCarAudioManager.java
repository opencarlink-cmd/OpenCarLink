package com.ucarhu.demo.vehicle.audio;

/**
 * 车机音频管理器，管理媒体通道、麦克风录音、音频播放控制和音频焦点。
 * 接收手机端音频数据后交给播放器，同时把车机麦克风数据按协议发送回手机端。
 */


public class UCarAudioManager {

    private static final java.lang.String f650g = "UCarAudioManager";

    private com.ucarhu.demo.sharelink.channel.ShareLinkChannel f651a;

    private java.util.concurrent.Future<java.lang.Boolean> f653c;

    private com.ucarhu.demo.vehicle.media.AudioPlayerManager f654d;

    private boolean f656f;

    private boolean f652b = false;

    private com.ucarhu.demo.vehicle.recorder.CarAudioRecorder f655e = null;

    public class a extends com.ucarhu.demo.sharelink.channel.ShareLinkChannel {
        public a(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z, boolean z2) {
            super(enumC0057b, z, z2);
        }

        @Override
        public void mo349Z() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.audio.UCarAudioManager.f650g, "audio channel ready.");
            com.ucarhu.demo.vehicle.audio.UCarAudioManager.this.f652b = true;
        }

        @Override
        public void mo351a(com.ucarhu.demo.protocol.UCarMessage c0102w) {
            super.mo351a(c0102w);
            if (c0102w == null || !com.ucarhu.demo.protocol.MediaRawMessages.isAudioRawMessage(c0102w)) {
                return;
            }
            com.ucar.vehiclesdk.UCarCommon.AudioType audioTypeFromInt = com.ucar.vehiclesdk.UCarCommon.AudioType.fromInt(com.ucarhu.demo.protocol.MediaRawMessages.getAudioType(c0102w).getNumber());
            byte[] bArrM315q = com.ucarhu.demo.protocol.MediaRawMessages.getRawPayload(c0102w);
            com.ucarhu.demo.vehicle.audio.UCarAudioManager.this.playIncomingAudioData(audioTypeFromInt, bArrM315q.length, bArrM315q);
        }

        @Override
        public void mo352a(boolean z) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.audio.UCarAudioManager.f650g, "audio channel closed.");
            com.ucarhu.demo.vehicle.audio.UCarAudioManager.this.f652b = false;
        }
    }

    public static class b {

        public static final int f658d = 600;

        public static final int f659e = 300;

        public static final int f660f = 100;

        public static final int f661g = 200;

        public static final int f662h = 100;

        public static final int f663i = 10;

        private com.ucar.vehiclesdk.UCarCommon.AudioType f664a = com.ucar.vehiclesdk.UCarCommon.AudioType.STREAM_UNDEFINED;

        private int f665b = -1;

        private int f666c = -1;

        public com.ucar.vehiclesdk.UCarCommon.AudioType getAudioType() {
            return this.f664a;
        }

        public void setBufferingCount(int i) {
            this.f665b = i;
        }

        public void setAudioType(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
            this.f664a = audioType;
        }

        public int getBufferingCount() {
            return this.f665b;
        }

        public void setSpeedAdjustStep(int i) {
            this.f666c = i;
        }

        public int getSpeedAdjustStep() {
            return this.f666c;
        }

        public java.lang.String toString() {
            return "AudioPlayerControl{mAudioType=" + this.f664a + ", mBufferingCount=" + this.f665b + ", mSpeedAdjustStep=" + this.f666c + '}';
        }
    }

    public interface c {
        void onRequestAudioFocus(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, int i);
    }

    public enum d {
        START_PLAYER(0),
        STOP_PLAYER(1),
        PAUSE_PLAYER(2),
        RESUME_PLAYER(3);


        private static com.ucarhu.demo.vehicle.audio.UCarAudioManager.d[] f671g = null;

        private final int f673b;

        d(int i) {
            this.f673b = i;
        }

        public static com.ucarhu.demo.vehicle.audio.UCarAudioManager.d fromValue(int i) {
            if (f671g == null) {
                f671g = values();
            }
            int i2 = 0;
            while (true) {
                com.ucarhu.demo.vehicle.audio.UCarAudioManager.d[] dVarArr = f671g;
                if (i2 >= dVarArr.length) {
                    return STOP_PLAYER;
                }
                if (dVarArr[i2].f673b == i) {
                    return dVarArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.f673b;
        }
    }

    public UCarAudioManager(android.content.Context context, boolean z, com.ucarhu.demo.vehicle.audio.UCarAudioManager.c cVar) {
        this.f656f = z;
        com.ucarhu.demo.vehicle.audio.UCarAudioManager.a aVar = new com.ucarhu.demo.vehicle.audio.UCarAudioManager.a(com.ucarhu.demo.protocol.channel.ChannelType.MEDIA, false, true);
        this.f651a = aVar;
        aVar.m413I0(true);
        this.f654d = new com.ucarhu.demo.vehicle.media.AudioPlayerManager(context, cVar);
    }

    public static void dispatchMicRecordData(short[] sArr, int i) {
        com.ucar.vehiclesdk.UCarAdapter.getInstance().sendMicRecordData(i, sArr, (int) (new java.util.GregorianCalendar().getTimeInMillis() / 1000));
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "onCarAudioRtpDataCallback...");
    }

    private void clearAudioResources() {
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "clearAudioResources");
        stopAudioRecord();
        stopAndClearAudioPlayers();
    }

    private boolean ensureAudioChannelReady() {
        java.util.concurrent.Future<java.lang.Boolean> future;
        if (!this.f652b && (future = this.f653c) != null) {
            try {
                future.get();
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f650g, "Wait start audio channel error.", e2);
            }
        }
        return this.f651a.mo353b();
    }

    private void stopAndClearAudioPlayers() {
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "stopAndClearAudioPlayers");
        this.f654d.abandonCurrentAudioFocus();
        this.f654d.m988q();
    }

    public void applyAudioPlayerControl(com.ucarhu.demo.vehicle.audio.UCarAudioManager.b bVar) {
        if (bVar != null) {
            this.f654d.m983b(bVar);
        }
    }

    public void startAudioRecord(com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, boolean z) {
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "startAudioRecord");
        com.ucarhu.demo.vehicle.recorder.CarAudioRecorder runnableC0139a = this.f655e;
        if (runnableC0139a != null) {
            runnableC0139a.stopRecording();
            this.f655e = null;
        }
        com.ucar.vehiclesdk.recorder.AudioConfig carConfig = com.ucar.vehiclesdk.recorder.AudioConfig.getCarConfig(audioFormat);
        if (z) {
            carConfig.setSource(7);
        }
        com.ucarhu.demo.vehicle.recorder.CarAudioRecorder runnableC0139a2 = new com.ucarhu.demo.vehicle.recorder.CarAudioRecorder(carConfig, this.f656f, new com.ucarhu.demo.vehicle.recorder.CarAudioRecorder.a() {
            @Override
            public final void mo770a(short[] sArr, int i) {
                com.ucarhu.demo.vehicle.audio.UCarAudioManager.dispatchMicRecordData(sArr, i);
            }
        });
        this.f655e = runnableC0139a2;
        runnableC0139a2.startRecording();
    }

    public void handleMicRecordRequest(com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, boolean z, boolean z2) {
        com.ucarhu.demo.logging.EasyLogger.debug(f650g, "onMicRecordRequest, isTurnOn:" + z);
        if (z) {
            startAudioRecord(audioFormat, z2);
        } else {
            stopAudioRecord();
        }
    }

    public void playIncomingAudioData(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, int i, byte[] bArr) {
        this.f654d.enqueueAudioData(audioType, java.nio.ByteBuffer.wrap(bArr, 0, i));
    }

    public void handleAudioPlayerStateChanged(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVar, com.ucar.vehiclesdk.UCarCommon.AudioAttributes audioAttributes) {
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "handleAudioStateChanged type " + audioType + " format " + audioFormat.toString() + " state " + dVar + " audioAttributes " + audioAttributes);
        this.f654d.manageAudioPlayerState(audioType, audioFormat, dVar, audioAttributes);
    }

    public void startAudioChannel(java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "startAudioChannel start address:" + str);
        try {
            this.f653c = this.f651a.m417a0(0, str);
        } catch (java.io.IOException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f650g, "start audio channel error.", e2);
        }
    }

    public boolean abandonAudioFocus() {
        return this.f654d.abandonCurrentAudioFocus();
    }

    public boolean sendMicRecordData(java.lang.String str, int i, short[] sArr, long j) {
        if (!ensureAudioChannelReady()) {
            com.ucarhu.demo.logging.EasyLogger.error(f650g, " sendMicRecordData() Channel not ready");
            return false;
        }
        int i2 = i * 2;
        byte[] bArrM536b = com.ucarhu.demo.protocol.ProtocolBuffers.obtainByteArray(i2);
        java.nio.ByteBuffer.wrap(bArrM536b).order(java.nio.ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(sArr);
        this.f651a.mo355c(com.ucarhu.demo.protocol.MediaRawMessages.createAudioRaw(java.nio.ByteBuffer.wrap(bArrM536b, 0, i2), com.ucar.databus.proto.UCarProto.AudioType.STREAM_MICROPHONE), new com.ucarhu.demo.protocol.channel.SendCallback() {
            @Override
            public final void onFailure(java.lang.Exception exc) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.audio.UCarAudioManager.f650g, "Send mic record data error", exc);
            }
        });
        return true;
    }

    public void pauseCurrentAudio() {
        this.f654d.m987m();
    }

    public void stopAudioChannel() {
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "stopAudioChannel");
        try {
            clearAudioResources();
            this.f651a.mo359q0();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f650g, "stopAudioChannel error: " + e2);
        }
    }

    public void stopAudioRecord() {
        com.ucarhu.demo.logging.EasyLogger.info(f650g, "stopAudioRecord");
        com.ucarhu.demo.vehicle.recorder.CarAudioRecorder runnableC0139a = this.f655e;
        if (runnableC0139a != null) {
            runnableC0139a.stopRecording();
            this.f655e = null;
        }
    }
}
