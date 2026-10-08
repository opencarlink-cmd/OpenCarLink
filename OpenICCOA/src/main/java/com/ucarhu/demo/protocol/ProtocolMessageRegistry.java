package com.ucarhu.demo.protocol;

public class ProtocolMessageRegistry {

    private static com.ucarhu.demo.protocol.SourceDevice localDevice = null;

    private static final java.util.Map<com.ucarhu.demo.protocol.CommandCategory, java.util.Map<java.lang.Integer, java.lang.String>> METHOD_NAMES_BY_CATEGORY;

    public static final int UNKNOWN_METHOD = -1;

    public static class AckMethodNames extends java.util.HashMap<java.lang.Integer, java.lang.String> {
        public AckMethodNames() {
            put(0, "ack");
        }
    }

    static {
        java.util.HashMap map = new java.util.HashMap();
        METHOD_NAMES_BY_CATEGORY = map;
        localDevice = com.ucarhu.demo.protocol.SourceDevice.PHONE;
        map.put(com.ucarhu.demo.protocol.CommandCategory.ACK, new com.ucarhu.demo.protocol.ProtocolMessageRegistry.AckMethodNames());
    }

    public static com.ucarhu.demo.protocol.SourceDevice getLocalDevice() {
        return localDevice;
    }

    public static com.ucarhu.demo.protocol.UCarMessage requireLocalDeviceMessage(com.ucarhu.demo.protocol.UCarMessage message) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(message, "message");
        com.ucarhu.demo.protocol.CommandDescriptor commandDescriptor = message.getHeader().getCommandDescriptor();
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(commandDescriptor, "message.header.cmdDescription");
        com.ucarhu.demo.protocol.SourceDevice sourceDevice = commandDescriptor.getSourceDevice();
        if (sourceDevice == localDevice) {
            return message;
        }
        throw new com.ucarhu.demo.protocol.ProtocolException("Can not create " + sourceDevice + " message in " + localDevice + " device");
    }

    public static java.lang.String getMethodName(com.ucarhu.demo.protocol.CommandCategory category, int methodId) {
        java.util.Map<java.lang.Integer, java.lang.String> methodNames;
        return (category == null || (methodNames = METHOD_NAMES_BY_CATEGORY.get(category)) == null || methodNames.isEmpty() || !methodNames.containsKey(java.lang.Integer.valueOf(methodId))) ? "unknown" : methodNames.get(java.lang.Integer.valueOf(methodId));
    }

    public static java.util.Map<java.lang.Integer, java.lang.String> ensureCategory(com.ucarhu.demo.protocol.CommandCategory category) {
        return new java.util.HashMap();
    }

    public static void registerMethodNames(com.ucarhu.demo.protocol.CommandCategory category, java.util.Map<java.lang.Integer, java.lang.String> methodNames) {
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(category, "category");
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(methodNames, "methodNames");
        METHOD_NAMES_BY_CATEGORY.computeIfAbsent(category, new java.util.function.Function<com.ucarhu.demo.protocol.CommandCategory, java.util.Map<java.lang.Integer, java.lang.String>>() {
            @Override
            public final java.util.Map<java.lang.Integer, java.lang.String> apply(com.ucarhu.demo.protocol.CommandCategory category) {
                return com.ucarhu.demo.protocol.ProtocolMessageRegistry.ensureCategory(category);
            }
        }).putAll(methodNames);
    }

    public static void checkMessage(com.ucarhu.demo.protocol.DataFormat expectedDataFormat, com.ucarhu.demo.protocol.CommandCategory expectedCategory, int expectedMethodId, com.ucarhu.demo.protocol.UCarMessage message) {
        checkMessageType(expectedDataFormat, expectedCategory, expectedMethodId, message, null);
    }

    public static void checkMessageType(com.ucarhu.demo.protocol.DataFormat expectedDataFormat, com.ucarhu.demo.protocol.CommandCategory expectedCategory, int expectedMethodId, com.ucarhu.demo.protocol.UCarMessage message, com.ucarhu.demo.protocol.MessageType expectedMessageType) {
        com.ucarhu.demo.protocol.CommandDescriptor commandDescriptor = message.getHeader().getCommandDescriptor();
        if (expectedDataFormat != commandDescriptor.getDataFormat()) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Check dataFormat error: " + commandDescriptor.getDataFormat() + ", expect: " + expectedDataFormat);
        }
        if (expectedCategory != commandDescriptor.getCategory()) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Check category error: " + commandDescriptor.getCategory() + ", expect: " + expectedCategory);
        }
        if (expectedMethodId != UNKNOWN_METHOD && expectedMethodId != commandDescriptor.getMethodId()) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Check method error: " + commandDescriptor.getMethodId() + ", expect: " + expectedMethodId);
        }
        if (expectedMessageType == null || expectedMessageType == message.getMessageType()) {
            return;
        }
        throw new com.ucarhu.demo.protocol.ProtocolException("Check MessageType error: " + message.getMessageType() + ", expect: " + expectedMessageType);
    }

    public static void setLocalDevice(com.ucarhu.demo.protocol.SourceDevice sourceDevice) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(sourceDevice, "sourceDevice");
        localDevice = sourceDevice;
    }
}
