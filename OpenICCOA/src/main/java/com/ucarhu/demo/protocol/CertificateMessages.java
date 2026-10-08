package com.ucarhu.demo.protocol;

public class CertificateMessages extends com.ucarhu.demo.protocol.ProtocolMessageRegistry {

    public static class a extends java.util.HashMap<java.lang.Integer, java.lang.String> {
        public a() {
            put(1, "certificate");
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage m605j(com.ucar.databus.proto.UCarProto.CarCertificate carCertificate) {
        return m608m().methodId(1).buildProtobuf(carCertificate);
    }

    public static void m606k() {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.registerMethodNames(com.ucarhu.demo.protocol.CommandCategory.CERT, new com.ucarhu.demo.protocol.CertificateMessages.a());
    }

    public static boolean m607l(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.CERT && c0102w.isProtobuf();
    }

    private static com.ucarhu.demo.protocol.UCarMessageBuilder m608m() {
        return com.ucarhu.demo.protocol.UCarMessage.newBuilder().sourceDevice(com.ucarhu.demo.protocol.ProtocolConfig.getLocalDevice()).category(com.ucarhu.demo.protocol.CommandCategory.CERT);
    }
}
