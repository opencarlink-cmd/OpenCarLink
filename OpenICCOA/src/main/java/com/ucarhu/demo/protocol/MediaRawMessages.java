package com.ucarhu.demo.protocol;

public class MediaRawMessages extends com.ucarhu.demo.protocol.ProtocolMessageRegistry {

    public static class a extends java.util.HashMap<java.lang.Integer, java.lang.String> {
        public a() {
            put(1, "ip_call");
            put(2, "modem_call");
            put(3, "ai_assistant");
            put(4, "ring");
            put(5, "notification");
            put(6, "tts");
            put(7, "system");
            put(10, "num");
            put(9, "microphone");
            put(0, "undefined");
        }
    }

    public static class b extends java.util.HashMap<java.lang.Integer, java.lang.String> {
        public b() {
            put(3, "camera_preview");
            put(4, "camera_picture");
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage createAudioRaw(java.nio.ByteBuffer byteBuffer, com.ucar.databus.proto.UCarProto.AudioType audioType) {
        com.ucarhu.demo.protocol.UCarMessage c0102wM664H = com.ucarhu.demo.protocol.UCarMessage.obtain();
        c0102wM664H.getHeader().initOutgoing(byteBuffer.remaining() + 20, com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.MessageType.SEND, com.ucarhu.demo.protocol.CommandCategory.AUDIO, audioType.getNumber());
        c0102wM664H.setBody(byteBuffer);
        return c0102wM664H;
    }

    public static com.ucarhu.demo.protocol.UCarMessage createCameraPreviewRaw(java.nio.ByteBuffer byteBuffer, com.ucar.databus.proto.UCarProto.VideoType videoType) {
        com.ucarhu.demo.protocol.UCarMessage c0102wM664H = com.ucarhu.demo.protocol.UCarMessage.obtain();
        c0102wM664H.getHeader().initOutgoing(byteBuffer.remaining() + 20, com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.MessageType.SEND, com.ucarhu.demo.protocol.CommandCategory.VIDEO, videoType.getNumber());
        c0102wM664H.setBody(byteBuffer);
        return c0102wM664H;
    }

    public static com.ucarhu.demo.protocol.UCarMessage createCameraPictureRaw(java.nio.ByteBuffer byteBuffer, com.ucar.databus.proto.UCarProto.VideoType videoType, short s) {
        com.ucarhu.demo.protocol.UCarMessage c0102wM700b = com.ucarhu.demo.protocol.UCarMessage.newBuilder().format(com.ucarhu.demo.protocol.DataFormat.RAW).category(com.ucarhu.demo.protocol.CommandCategory.VIDEO).methodId(videoType.getNumber()).sourceDevice(com.ucarhu.demo.protocol.ProtocolMessageRegistry.getLocalDevice()).buildRaw(byteBuffer);
        c0102wM700b.getHeader().m562k(s);
        return c0102wM700b;
    }

    public static com.ucarhu.demo.protocol.UCarMessage m311m(byte[] bArr, com.ucar.databus.proto.UCarProto.AudioType audioType) {
        return com.ucarhu.demo.protocol.UCarMessage.newBuilder().format(com.ucarhu.demo.protocol.DataFormat.RAW).category(com.ucarhu.demo.protocol.CommandCategory.AUDIO).methodId(audioType.getNumber()).sourceDevice(com.ucarhu.demo.protocol.ProtocolMessageRegistry.getLocalDevice()).buildRaw(bArr);
    }

    public static void m312n() {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.registerMethodNames(com.ucarhu.demo.protocol.CommandCategory.AUDIO, new com.ucarhu.demo.protocol.MediaRawMessages.a());
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.registerMethodNames(com.ucarhu.demo.protocol.CommandCategory.VIDEO, new com.ucarhu.demo.protocol.MediaRawMessages.b());
    }

    public static boolean isAudioRawMessage(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w.isRaw() && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.AUDIO;
    }

    public static boolean isVideoRawMessage(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w.isRaw() && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.VIDEO;
    }

    public static byte[] getRawPayload(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessage(com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.CommandCategory.AUDIO, -1, c0102w);
        return c0102w.getBodyBytes();
    }

    public static com.ucar.databus.proto.UCarProto.AudioType getAudioType(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessage(com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.CommandCategory.AUDIO, -1, c0102w);
        return com.ucar.databus.proto.UCarProto.AudioType.forNumber(c0102w.getMethodId());
    }

    public static short m317s(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w.getResponseSeqId();
    }

    public static byte[] m318t(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessage(com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.CommandCategory.VIDEO, -1, c0102w);
        return c0102w.getBodyBytes();
    }

    public static com.ucar.databus.proto.UCarProto.VideoType m319u(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessage(com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.CommandCategory.VIDEO, -1, c0102w);
        return com.ucar.databus.proto.UCarProto.VideoType.forNumber(c0102w.getMethodId());
    }
}
