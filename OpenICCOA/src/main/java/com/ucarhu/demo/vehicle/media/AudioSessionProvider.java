package com.ucarhu.demo.vehicle.media;

public class AudioSessionProvider {

    private static final java.lang.String TAG = "MediaSessionProvider";

    private int audioSessionId;

    private android.content.Context context;

    private static class Holder {

        @android.annotation.SuppressLint({"StaticFieldLeak"})
        private static final com.ucarhu.demo.vehicle.media.AudioSessionProvider INSTANCE = new com.ucarhu.demo.vehicle.media.AudioSessionProvider();

        private Holder() {
        }
    }

    private AudioSessionProvider() {
        this.audioSessionId = 0;
    }

    private synchronized int getOrCreateAudioSessionId() {
        if (this.audioSessionId != 0) {
            return this.audioSessionId;
        }
        android.media.AudioManager audioManager = (android.media.AudioManager) this.context.getSystemService("audio");
        if (audioManager == null) {
            throw new java.lang.UnsupportedOperationException("can't get audio service");
        }
        this.audioSessionId = audioManager.generateAudioSessionId();
        com.ucarhu.demo.logging.EasyLogger.debug(TAG, "session id is " + this.audioSessionId);
        return this.audioSessionId;
    }

    public static com.ucarhu.demo.vehicle.media.AudioSessionProvider getInstance(android.content.Context context) {
        com.ucarhu.demo.vehicle.media.AudioSessionProvider provider = com.ucarhu.demo.vehicle.media.AudioSessionProvider.Holder.INSTANCE;
        if (provider.context == null) {
            provider.context = context;
        }
        return provider;
    }

    public static com.ucarhu.demo.vehicle.media.AudioSessionProvider getInstance() {
        return getInstance(null);
    }

    public static int nextAudioSessionId() {
        return getInstance().getOrCreateAudioSessionId();
    }
}
