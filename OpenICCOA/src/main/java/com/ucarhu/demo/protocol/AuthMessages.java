package com.ucarhu.demo.protocol;

public class AuthMessages extends com.ucarhu.demo.protocol.ProtocolMessageRegistry {

    public static class a extends java.util.HashMap<java.lang.Integer, java.lang.String> {
        public a() {
            put(1, "auth_request");
            put(2, "auth_response");
            put(3, "auth_confirm");
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage m593j(com.ucar.databus.proto.UCarProto.AuthConfirm authConfirm) {
        return m599p().messageType(com.ucarhu.demo.protocol.MessageType.SEND).methodId(3).buildProtobuf(authConfirm);
    }

    public static com.ucarhu.demo.protocol.UCarMessage m594k(com.ucar.databus.proto.UCarProto.AuthRequest authRequest) {
        return m599p().messageType(com.ucarhu.demo.protocol.MessageType.REQ).methodId(1).buildProtobuf(authRequest);
    }

    public static com.ucarhu.demo.protocol.UCarMessage m595l(com.ucar.databus.proto.UCarProto.AuthResponse authResponse, int i) {
        return m599p().messageType(com.ucarhu.demo.protocol.MessageType.RES).methodId(2).buildProtobuf(authResponse).updateHeaderLength(i);
    }

    private static void m596m(int i, com.ucarhu.demo.protocol.UCarMessage c0102w, com.ucarhu.demo.protocol.MessageType enumC0093n) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessageType(com.ucarhu.demo.protocol.DataFormat.PB3, com.ucarhu.demo.protocol.CommandCategory.AUTH, i, c0102w, enumC0093n);
    }

    public static void m597n() {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.registerMethodNames(com.ucarhu.demo.protocol.CommandCategory.AUTH, new com.ucarhu.demo.protocol.AuthMessages.a());
    }

    public static boolean m598o(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.AUTH && c0102w.isProtobuf() && c0102w.getMethodId() == 3;
    }

    private static com.ucarhu.demo.protocol.UCarMessageBuilder m599p() {
        return com.ucarhu.demo.protocol.UCarMessage.newBuilder().sourceDevice(com.ucarhu.demo.protocol.ProtocolConfig.getLocalDevice()).category(com.ucarhu.demo.protocol.CommandCategory.AUTH);
    }

    public static boolean m600q(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.AUTH && c0102w.isProtobuf() && c0102w.getMethodId() == 1;
    }

    public static boolean m601r(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.AUTH && c0102w.isProtobuf() && c0102w.getMethodId() == 2;
    }

    public static com.ucar.databus.proto.UCarProto.AuthConfirm m602s(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m596m(3, c0102w, com.ucarhu.demo.protocol.MessageType.SEND);
        try {
            return com.ucar.databus.proto.UCarProto.AuthConfirm.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseAuthConfirmMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.AuthRequest m603t(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m596m(1, c0102w, com.ucarhu.demo.protocol.MessageType.REQ);
        try {
            return com.ucar.databus.proto.UCarProto.AuthRequest.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseAuthRequestMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.AuthResponse m604u(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m596m(2, c0102w, com.ucarhu.demo.protocol.MessageType.RES);
        try {
            return com.ucar.databus.proto.UCarProto.AuthResponse.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseAuthResponseMessage error: " + e2.getMessage());
        }
    }
}
