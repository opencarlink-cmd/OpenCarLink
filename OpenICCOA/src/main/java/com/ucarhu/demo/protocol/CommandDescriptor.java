package com.ucarhu.demo.protocol;

public final class CommandDescriptor {

    public static final java.lang.String UNKNOWN_METHOD = "unknown";

    private com.ucarhu.demo.protocol.SourceDevice sourceDevice;

    private com.ucarhu.demo.protocol.DataFormat dataFormat;

    private com.ucarhu.demo.protocol.MessageType messageType;

    private com.ucarhu.demo.protocol.CommandCategory category;

    private int methodId;

    private short reserved;

    static {
        com.ucarhu.demo.protocol.MediaRawMessages.m312n();
        com.ucarhu.demo.protocol.AuthMessages.m597n();
        com.ucarhu.demo.protocol.ControlMessages.m623O();
        com.ucarhu.demo.protocol.SensorMessages.m335r();
        com.ucarhu.demo.protocol.CertificateMessages.m606k();
    }

    public CommandDescriptor() {
    }

    public CommandDescriptor(com.ucarhu.demo.protocol.SourceDevice sourceDevice, com.ucarhu.demo.protocol.DataFormat dataFormat, com.ucarhu.demo.protocol.MessageType messageType, com.ucarhu.demo.protocol.CommandCategory category, int methodId) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(sourceDevice, "sourceDevice");
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(dataFormat, "dataFormat");
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(category, "category");
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(messageType, "messageType");
        this.sourceDevice = sourceDevice;
        this.dataFormat = dataFormat;
        this.messageType = messageType;
        this.category = category;
        this.methodId = methodId;
        this.reserved = (short) 0;
    }

    public final com.ucarhu.demo.protocol.CommandCategory getCategory() {
        return this.category;
    }

    public void setMessageType(com.ucarhu.demo.protocol.MessageType messageType) {
        if (messageType == null) {
            throw new com.ucarhu.demo.protocol.ProtocolException("messageType can not be null");
        }
        if (getMessageType() != com.ucarhu.demo.protocol.MessageType.SEND || messageType != com.ucarhu.demo.protocol.MessageType.SEND_SYNC) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Only SEND MessageType can be changed");
        }
        this.messageType = messageType;
    }

    public void setFields(com.ucarhu.demo.protocol.SourceDevice sourceDevice, com.ucarhu.demo.protocol.DataFormat dataFormat, com.ucarhu.demo.protocol.MessageType messageType, com.ucarhu.demo.protocol.CommandCategory category, int methodId) {
        this.sourceDevice = sourceDevice;
        this.dataFormat = dataFormat;
        this.messageType = messageType;
        this.category = category;
        this.methodId = methodId;
    }

    public final com.ucarhu.demo.protocol.DataFormat getDataFormat() {
        return this.dataFormat;
    }

    public com.ucarhu.demo.protocol.MessageType getMessageType() {
        return this.messageType;
    }

    public boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof com.ucarhu.demo.protocol.CommandDescriptor)) {
            return false;
        }
        com.ucarhu.demo.protocol.CommandDescriptor other = (com.ucarhu.demo.protocol.CommandDescriptor) obj;
        return com.ucarhu.demo.protocol.NullChecks.checkState(this.sourceDevice, other.sourceDevice)
                && com.ucarhu.demo.protocol.NullChecks.checkState(this.dataFormat, other.dataFormat)
                && com.ucarhu.demo.protocol.NullChecks.checkState(this.category, other.category)
                && com.ucarhu.demo.protocol.NullChecks.checkState(this.messageType, other.messageType)
                && this.methodId == other.methodId;
    }

    public final int getMethodId() {
        return this.methodId;
    }

    public final com.ucarhu.demo.protocol.SourceDevice getSourceDevice() {
        return this.sourceDevice;
    }

    public int hashCode() {
        int sourceHash = this.sourceDevice != null ? this.sourceDevice.hashCode() : 0;
        int formatHash = this.dataFormat != null ? this.dataFormat.hashCode() : 0;
        int categoryHash = this.category != null ? this.category.hashCode() : 0;
        return (((sourceHash * 31) + formatHash) * 31 + categoryHash) * 31 + java.lang.Integer.hashCode(this.methodId);
    }

    public java.lang.String toString() {
        return "from=" + this.sourceDevice
                + "|format=" + this.dataFormat
                + "|type=" + this.messageType
                + "|category=" + this.category
                + "|method=" + com.ucarhu.demo.protocol.ProtocolMessageRegistry.getMethodName(this.category, this.methodId)
                + "(" + this.methodId + ")";
    }
}
