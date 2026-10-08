package com.ucarhu.demo.protocol;

/**
 * UCar 协议消息对象，由固定 20 字节协议头和可变长度消息体组成。
 * 支持对象池复用、原始字节解析、Protobuf body 解析和序列化写出。
 */


public class UCarMessage {

    private final com.ucarhu.demo.protocol.UCarMessageHeader f620a;

    private java.nio.ByteBuffer f621b;

    private int f622c;

    private final boolean f623d;

    private final int f624e;

    public UCarMessage(com.ucarhu.demo.protocol.UCarMessageHeader c0092m, java.nio.ByteBuffer byteBuffer) {
        this(c0092m, byteBuffer, byteBuffer.remaining());
    }

    public UCarMessage(com.ucarhu.demo.protocol.UCarMessageHeader c0092m, java.nio.ByteBuffer byteBuffer, int i) {
        if (byteBuffer.remaining() != i) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Create message from ByteBuffer error: body len invalid");
        }
        this.f620a = c0092m;
        this.f623d = false;
        this.f621b = byteBuffer;
        this.f622c = i;
        this.f624e = -1;
    }

    public UCarMessage(com.ucarhu.demo.protocol.UCarMessageHeader c0092m, byte[] bArr) {
        this(c0092m, bArr, bArr.length);
    }

    public UCarMessage(com.ucarhu.demo.protocol.UCarMessageHeader c0092m, byte[] bArr, int i) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(c0092m, "header");
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "body");
        this.f620a = c0092m;
        this.f623d = false;
        this.f622c = i;
        this.f621b = java.nio.ByteBuffer.wrap(bArr);
        this.f624e = -1;
    }

    private UCarMessage(boolean z) {
        this.f620a = com.ucarhu.demo.protocol.UCarMessageHeader.createDefault();
        this.f623d = z;
        this.f624e = z ? getSequenceId() : -1;
    }

    public static com.ucarhu.demo.protocol.UCarMessageBuilder newBuilder() {
        return new com.ucarhu.demo.protocol.UCarMessageBuilder();
    }

    public static com.ucarhu.demo.protocol.UCarMessage obtain() {
        return com.ucarhu.demo.protocol.UCarMessagePool.getInstance().obtain();
    }

    public static com.ucarhu.demo.protocol.UCarMessage createAckResponse(int i) {
        com.ucarhu.demo.protocol.UCarMessage c0102wM664H = obtain();
        c0102wM664H.getHeader().initOutgoing(20, com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.MessageType.RES, com.ucarhu.demo.protocol.CommandCategory.ACK, 0).setResponseSeqId(i);
        c0102wM664H.setBody(com.ucarhu.demo.protocol.ProtocolBuffers.f571b, 0);
        return c0102wM664H;
    }

    public static com.ucarhu.demo.protocol.UCarMessage createPooled(boolean z) {
        return new com.ucarhu.demo.protocol.UCarMessage(z);
    }

    public static com.ucarhu.demo.protocol.UCarMessage parseFrom(byte[] bArr) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "rawMessageBytes");
        if (bArr.length < 20) {
            throw new com.ucarhu.demo.protocol.ProtocolException("message too short: " + bArr.length);
        }
        com.ucarhu.demo.protocol.UCarMessageHeader c0092mM549n = com.ucarhu.demo.protocol.UCarMessageHeader.parseHeader(bArr);
        if (c0092mM549n.getMessageLength() == bArr.length) {
            int length = bArr.length - 20;
            return new com.ucarhu.demo.protocol.UCarMessage(c0092mM549n, java.nio.ByteBuffer.wrap(bArr, 20, length), length);
        }
        throw new com.ucarhu.demo.protocol.ProtocolException("Message invalid header length not correct:" + c0092mM549n.getMessageLength());
    }

    public static boolean isSendSync(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w != null && c0102w.getMessageType() == com.ucarhu.demo.protocol.MessageType.SEND_SYNC;
    }

    private int m669j(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = i6 | (i6 >>> 16);
        if (i7 < 0) {
            return 1;
        }
        return 1 + i7;
    }

    public short getResponseSeqId() {
        return this.f620a.getResponseSeqId();
    }

    public int getSequenceId() {
        return this.f620a.getSequenceId();
    }

    public byte[] getBodyArray() {
        if (this.f621b.hasArray()) {
            return this.f621b.array();
        }
        byte[] bArrM536b = com.ucarhu.demo.protocol.ProtocolBuffers.obtainByteArray(getBodyLength());
        this.f621b.get(bArrM536b, 0, getBodyLength());
        this.f621b.position(0);
        return bArrM536b;
    }

    public boolean isProtobuf() {
        com.ucarhu.demo.protocol.CommandDescriptor c0088iM565q = getHeader().getCommandDescriptor();
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(c0088iM565q, "header.cmdDescription");
        return c0088iM565q.getDataFormat() == com.ucarhu.demo.protocol.DataFormat.PB3;
    }

    public boolean isPooled() {
        return this.f623d;
    }

    public boolean isRaw() {
        com.ucarhu.demo.protocol.CommandDescriptor c0088iM565q = getHeader().getCommandDescriptor();
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(c0088iM565q, "header.cmdDescription");
        return c0088iM565q.getDataFormat() == com.ucarhu.demo.protocol.DataFormat.RAW;
    }

    public synchronized void recycle() {
        if (isPooled()) {
            com.ucarhu.demo.protocol.UCarMessagePool.getInstance().recycle(this);
        }
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[getTotalLength()];
        writeTo(bArr);
        return bArr;
    }

    public com.ucarhu.demo.protocol.UCarMessage setMessageType(com.ucarhu.demo.protocol.MessageType enumC0093n) {
        this.f620a.setMessageType(enumC0093n);
        return this;
    }

    public <T extends com.google.protobuf.GeneratedMessageLite<T, BuilderType>, BuilderType extends com.google.protobuf.GeneratedMessageLite.Builder<T, BuilderType>> T parseProtobufBody(java.lang.Class<T> cls) {
        com.ucarhu.demo.protocol.DataFormat enumC0090kM527d = this.f620a.getCommandDescriptor().getDataFormat();
        if (enumC0090kM527d != com.ucarhu.demo.protocol.DataFormat.PB3) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Parse protobuf data error: dataFormat is " + enumC0090kM527d);
        }
        try {
            return (T) cls.getDeclaredMethod("parseFrom", java.nio.ByteBuffer.class).invoke(cls, getBodyBuffer());
        } catch (java.lang.Exception e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Parse message " + getSequenceId() + " protobuf data error: " + e2.getMessage());
        }
    }

    public java.lang.String describe() {
        java.lang.String str = "PB3 Data(";
        java.lang.StringBuilder sb = new java.lang.StringBuilder(this.f620a.toString());
        if (this.f620a.getCommandDescriptor().getDataFormat() != com.ucarhu.demo.protocol.DataFormat.PB3) {
            str = this.f620a.getCommandDescriptor().getDataFormat() == com.ucarhu.demo.protocol.DataFormat.RAW ? "RAW Data(" : "Protobuf Data(";
            sb.append("--------------------------------\n");
            return sb.toString();
        }
        sb.append(str);
        sb.append(this.f622c);
        sb.append(")\n");
        sb.append("--------------------------------\n");
        return sb.toString();
    }

    public void writeBodyTo(java.nio.ByteBuffer byteBuffer) {
        this.f621b.mark();
        byteBuffer.put(this.f621b);
        this.f621b.reset();
    }

    public void copyBodyFrom(java.nio.ByteBuffer byteBuffer, int i) {
        if (byteBuffer == null && i != 0) {
            throw new com.ucarhu.demo.protocol.ProtocolException("null body expect 0 body length, but get: " + i);
        }
        if (byteBuffer != null && byteBuffer.remaining() != i) {
            throw new com.ucarhu.demo.protocol.ProtocolException("invalid param, expect body length: " + byteBuffer.remaining() + ", but get:" + i);
        }
        this.f622c = i;
        if (byteBuffer == null || i == 0) {
            this.f621b = com.ucarhu.demo.protocol.ProtocolBuffers.f571b;
            return;
        }
        java.nio.ByteBuffer byteBuffer2 = this.f621b;
        if (byteBuffer2 != null && byteBuffer2.capacity() > 131072) {
            this.f621b = null;
        }
        java.nio.ByteBuffer byteBuffer3 = this.f621b;
        if (byteBuffer3 == null || byteBuffer3.capacity() < i) {
            int iM669j = m669j(i);
            if (iM669j >= i) {
                i = iM669j;
            }
            this.f621b = java.nio.ByteBuffer.allocate(i);
        } else {
            this.f621b.clear();
        }
        this.f621b.put(byteBuffer);
        this.f621b.flip();
    }

    public java.nio.ByteBuffer getBodyBuffer() {
        return this.f621b;
    }

    public void setBody(java.nio.ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            byteBuffer = com.ucarhu.demo.protocol.ProtocolBuffers.f571b;
        }
        setBody(byteBuffer, byteBuffer.remaining());
    }

    public void setBody(java.nio.ByteBuffer byteBuffer, int i) {
        this.f621b = byteBuffer;
        this.f622c = i;
    }

    public void writeTo(byte[] bArr) {
        this.f620a.writeTo(bArr);
        this.f621b.mark();
        this.f621b.get(bArr, 20, getBodyLength());
        this.f621b.reset();
    }

    public java.nio.ByteBuffer writeTo() {
        return this.f621b;
    }

    public final void isCategory(int i) {
        getHeader().m566r(i);
        this.f622c = i;
    }

    public void isMethod(java.nio.ByteBuffer byteBuffer) {
        setBody(byteBuffer, byteBuffer.remaining());
    }

    public com.ucarhu.demo.protocol.UCarMessage updateHeaderLength(int i) {
        this.f620a.setResponseSeqId(i);
        return this;
    }

    public byte[] getBodyBytes() {
        if (!isPooled() && this.f621b.hasArray() && this.f621b.array().length == this.f622c) {
            return this.f621b.array();
        }
        byte[] bArr = new byte[this.f622c];
        this.f621b.mark();
        this.f621b.get(bArr, 0, this.f622c);
        this.f621b.reset();
        return bArr;
    }

    public int getBodyLength() {
        return this.f622c;
    }

    @androidx.annotation.NonNull
    public java.lang.String toString() {
        return describe();
    }

    public com.ucarhu.demo.protocol.CommandCategory getCategory() {
        return this.f620a.getCommandDescriptor().getCategory();
    }

    public com.ucarhu.demo.protocol.UCarMessageHeader getHeader() {
        return this.f620a;
    }

    public int getTotalLength() {
        return this.f620a.getMessageLength();
    }

    public com.ucarhu.demo.protocol.MessageType getMessageType() {
        return this.f620a.getCommandDescriptor().getMessageType();
    }

    public int getMethodId() {
        return this.f620a.getCommandDescriptor().getMethodId();
    }

    public int m698z() {
        return this.f624e;
    }
}
