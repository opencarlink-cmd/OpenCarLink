package com.ucarhu.demo.protocol;

public class UCarMessageBuilder {

    private com.ucarhu.demo.protocol.CommandCategory category;

    private int methodId;

    private com.ucarhu.demo.protocol.SourceDevice sourceDevice = com.ucarhu.demo.protocol.ProtocolConfig.getLocalDevice();

    private com.ucarhu.demo.protocol.MessageType messageType = com.ucarhu.demo.protocol.MessageType.SEND;

    private com.ucarhu.demo.protocol.DataFormat dataFormat = com.ucarhu.demo.protocol.DataFormat.RAW;

    public com.ucarhu.demo.protocol.UCarMessage buildProtobuf(com.google.protobuf.GeneratedMessageLite<?, ?> protobufMessage) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(protobufMessage, "protobufMessage");
        format(com.ucarhu.demo.protocol.DataFormat.PB3);
        byte[] byteArray = protobufMessage.toByteArray();
        return new com.ucarhu.demo.protocol.UCarMessage(new com.ucarhu.demo.protocol.UCarMessageHeader(byteArray.length + 20, new com.ucarhu.demo.protocol.CommandDescriptor(this.sourceDevice, this.dataFormat, this.messageType, this.category, this.methodId)), byteArray);
    }

    public final com.ucarhu.demo.protocol.UCarMessage buildRaw(java.nio.ByteBuffer byteBuffer) {
        return new com.ucarhu.demo.protocol.UCarMessage(new com.ucarhu.demo.protocol.UCarMessageHeader(byteBuffer.remaining() + 20, new com.ucarhu.demo.protocol.CommandDescriptor(this.sourceDevice, this.dataFormat, this.messageType, this.category, this.methodId)), byteBuffer, byteBuffer.remaining());
    }

    public final com.ucarhu.demo.protocol.UCarMessage buildRaw(byte[] bodyBytes) {
        if (bodyBytes == null) {
            bodyBytes = new byte[0];
        }
        return new com.ucarhu.demo.protocol.UCarMessage(new com.ucarhu.demo.protocol.UCarMessageHeader(bodyBytes.length + 20, new com.ucarhu.demo.protocol.CommandDescriptor(this.sourceDevice, this.dataFormat, this.messageType, this.category, this.methodId)), bodyBytes);
    }

    public com.ucarhu.demo.protocol.UCarMessageBuilder methodId(int methodId) {
        this.methodId = methodId;
        return this;
    }

    public com.ucarhu.demo.protocol.UCarMessageBuilder category(com.ucarhu.demo.protocol.CommandCategory category) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(category, "cmdCategory");
        this.category = category;
        return this;
    }

    public com.ucarhu.demo.protocol.UCarMessageBuilder format(com.ucarhu.demo.protocol.DataFormat dataFormat) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(dataFormat, "dataFormat");
        this.dataFormat = dataFormat;
        return this;
    }

    public com.ucarhu.demo.protocol.UCarMessageBuilder messageType(com.ucarhu.demo.protocol.MessageType messageType) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(this.dataFormat, "dataFormat");
        this.messageType = messageType;
        return this;
    }

    public com.ucarhu.demo.protocol.UCarMessageBuilder sourceDevice(com.ucarhu.demo.protocol.SourceDevice sourceDevice) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(sourceDevice, "sourceDevice");
        this.sourceDevice = sourceDevice;
        return this;
    }
}
