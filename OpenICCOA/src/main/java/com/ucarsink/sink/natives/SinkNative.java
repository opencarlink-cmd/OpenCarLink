package com.ucarsink.sink.natives;

import com.ucar.vehiclesdk.UCarCommon;
import com.ucarhu.demo.logging.EasyLogger;

public class SinkNative {

    private static final String TAG = "SinkNative";

    private static SinkNativeCallback sinkNativeCallback;

    private static SinkNative instance;

    /**
     * Native sink 层回调给 Java 的事件监听器。
     * 方法名保持业务含义，JNI 入口方法仍保留原始名称，避免影响 native 反射调用。
     */
    public interface SinkNativeCallback {
        void onSinkStopped(String deviceId);

        void onAudioDataReceived(String deviceId, UCarCommon.AudioType audioType, int dataLength, byte[] payload);

        void onVideoDataReceived(String deviceId, UCarCommon.VideoType videoType, int dataLength, byte[] payload);

        void onCastAudioInitialized(String deviceId, String mimeType, int sampleRate, int channelCount);

        void onUibcEncryptionChanged(String sessionId, boolean encrypted);

        void onCastVideoInitialized(String deviceId, String mimeType, int width, int height);
    }

    static {
        System.loadLibrary("ovmsink");
    }

    public static SinkNative getInstance() {
        if (instance == null) {
            synchronized (SinkNative.class) {
                if (instance == null) {
                    instance = new SinkNative();
                }
            }
        }
        return instance;
    }

    public static void registerCallback(SinkNativeCallback callback) {
        sinkNativeCallback = callback;
    }

    public static void clearCallback() {
        sinkNativeCallback = null;
    }

    public static void onNativeAudioDataReceived(String str, int i, int i2, byte[] bArr) {
        if (sinkNativeCallback == null) {
            return;
        }
        sinkNativeCallback.onAudioDataReceived(str, UCarCommon.AudioType.fromInt(i), i2, bArr);
    }

    public static void onNativeCastAudioInitialized(String str, String str2, int i, int i2) {
        EasyLogger.debug(TAG, "onNativeCastAudioInitialized deviceId:" + str + ",mimeType:" + str2 + ",sampleRate:" + i + ",channelCount" + i2);
        SinkNativeCallback callback = sinkNativeCallback;
        if (callback == null) {
            return;
        }
        callback.onCastAudioInitialized(str, str2, i, i2);
    }

    public static void onNativeCastVideoInitialized(String str, String str2, int i, int i2) {
        EasyLogger.debug(TAG, "onNativeCastVideoInitialized deviceId:" + str + ",mimeType:" + str2 + ",width:" + i + ",height" + i2);
        SinkNativeCallback callback = sinkNativeCallback;
        if (callback == null) {
            return;
        }
        callback.onCastVideoInitialized(str, str2, i, i2);
    }

    public static void onNativeVideoDataReceived(String str, int i, int i2, byte[] bArr) {
        if (sinkNativeCallback == null) {
            return;
        }
        sinkNativeCallback.onVideoDataReceived(str, UCarCommon.VideoType.fromInt(i), i2, bArr);
    }

    public static void onNativeWfdSinkStopped(String str) {
        EasyLogger.debug(TAG, "onNativeWfdSinkStopped");
        SinkNativeCallback callback = sinkNativeCallback;
        if (callback != null) {
            callback.onSinkStopped(str);
        }
    }

    public static void onUibcEncrypted(String str, boolean z) {
        EasyLogger.debug(TAG, "onUibcEncrypted,session=" + str + "enable= " + z);
        SinkNativeCallback callback = sinkNativeCallback;
        if (callback != null) {
            callback.onUibcEncryptionChanged(str, z);
        }
    }

    public static native boolean onUibcEvent(String str, byte[] bArr);

    public static native boolean pause(String str);

    public static native boolean resume(String str);

    public static native boolean start(String str, int i, int i2, int i3, int i4, int i5);

    public static native boolean stop(String str);
}
