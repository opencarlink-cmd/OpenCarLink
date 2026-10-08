package com.ucarhu.demo.protocol;

public class SensorMessages extends com.ucarhu.demo.protocol.ProtocolMessageRegistry {

    public static class a extends java.util.HashMap<java.lang.Integer, java.lang.String> {
        public a() {
            put(1, "gps");
            put(2, "lights");
            put(3, "gyro_scope");
            put(4, "acceleration");
            put(5, "oil");
            put(6, "gear_info");
            put(7, "light_sensor_info");
        }
    }

    public static com.ucar.databus.proto.UCarProto.Acceleration m320A(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m334q(4, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.Acceleration.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseAccelerationMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.GearInfo m321B(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m334q(6, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.GearInfo.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseGearInfoMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.Gps m322C(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m334q(1, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.Gps.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseGpsMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.GyroScope m323D(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m334q(3, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.GyroScope.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseGyroScopeMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.LightSensorInfo m324E(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m334q(7, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.LightSensorInfo.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseLightSensorInfoMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.Lights m325F(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m334q(2, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.Lights.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseLightsMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.Oil m326G(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m334q(5, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.Oil.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseOilMessage error: " + e2.getMessage());
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage m327j(com.ucar.databus.proto.UCarProto.Acceleration acceleration) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m337t().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(4).buildProtobuf(acceleration));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m328k(com.ucar.databus.proto.UCarProto.GearInfo gearInfo) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m337t().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(6).buildProtobuf(gearInfo));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m329l(com.ucar.databus.proto.UCarProto.Gps gps) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m337t().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(1).buildProtobuf(gps));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m330m(com.ucar.databus.proto.UCarProto.GyroScope gyroScope) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m337t().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(3).buildProtobuf(gyroScope));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m331n(com.ucar.databus.proto.UCarProto.LightSensorInfo lightSensorInfo) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m337t().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(7).buildProtobuf(lightSensorInfo));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m332o(com.ucar.databus.proto.UCarProto.Lights lights) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m337t().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(2).buildProtobuf(lights));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m333p(com.ucar.databus.proto.UCarProto.Oil oil) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m337t().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(5).buildProtobuf(oil));
    }

    private static void m334q(int i, com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessage(com.ucarhu.demo.protocol.DataFormat.PB3, com.ucarhu.demo.protocol.CommandCategory.SENSOR, i, c0102w);
    }

    public static void m335r() {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.registerMethodNames(com.ucarhu.demo.protocol.CommandCategory.SENSOR, new com.ucarhu.demo.protocol.SensorMessages.a());
    }

    public static boolean m336s(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.SENSOR && c0102w.isProtobuf() && c0102w.getMethodId() == 4;
    }

    private static com.ucarhu.demo.protocol.UCarMessageBuilder m337t() {
        return com.ucarhu.demo.protocol.UCarMessage.newBuilder().category(com.ucarhu.demo.protocol.CommandCategory.SENSOR);
    }

    public static boolean m338u(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.SENSOR && c0102w.isProtobuf() && c0102w.getMethodId() == 6;
    }

    public static boolean m339v(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.SENSOR && c0102w.isProtobuf() && c0102w.getMethodId() == 1;
    }

    public static boolean m340w(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.SENSOR && c0102w.isProtobuf() && c0102w.getMethodId() == 3;
    }

    public static boolean m341x(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.SENSOR && c0102w.isProtobuf() && c0102w.getMethodId() == 7;
    }

    public static boolean m342y(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.SENSOR && c0102w.isProtobuf() && c0102w.getMethodId() == 2;
    }

    public static boolean m343z(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.SENSOR && c0102w.isProtobuf() && c0102w.getMethodId() == 5;
    }
}
