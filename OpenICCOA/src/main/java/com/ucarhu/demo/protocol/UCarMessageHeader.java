package com.ucarhu.demo.protocol;

/**
 * UCar 协议头，负责读写消息长度、序列号、时间戳、命令描述和校验字段。
 * 所有偏移和大小均按原协议固定布局处理，不能随意调整。
 */


public class UCarMessageHeader {

    public static final int f575g = 20;

    private static final int f576h = 0;

    private static final int f577i = 4;

    private static final int f578j = 8;

    private static final int f579k = 12;

    private static final int f580l = 13;

    private static final int f581m = 14;

    private static final int f582n = 16;

    private static final int f583o = 18;

    private static final int f584p = 18;

    private static final java.lang.ThreadLocal<byte[]> f585q = java.lang.ThreadLocal.withInitial(new java.util.function.Supplier() {
        @Override
        public final java.lang.Object get() {
            return com.ucarhu.demo.protocol.UCarMessageHeader.newHeaderBytes();
        }
    });

    private static final byte f586r = 6;

    private static final byte f587s = 24;

    private final com.ucarhu.demo.protocol.CommandDescriptor f588a;

    private int f589b;

    private int f590c;

    private int f591d;

    private short f592e;

    private short f593f;

    private UCarMessageHeader(int i, int i2, int i3, com.ucarhu.demo.protocol.CommandDescriptor c0088i, short s, short s2) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(c0088i, "cmdDescription");
        this.f589b = i;
        this.f590c = i2;
        this.f591d = i3;
        this.f588a = c0088i;
        this.f593f = s;
        this.f592e = s2;
    }

    public UCarMessageHeader(int i, com.ucarhu.demo.protocol.CommandDescriptor c0088i) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(c0088i, "cmdDescription");
        this.f589b = i;
        if (i < 20) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Illegal message size: " + i);
        }
        this.f590c = com.ucarhu.demo.protocol.SequenceIdGenerator.nextSequenceId();
        this.f591d = (int) (java.lang.System.currentTimeMillis() / 1000);
        this.f588a = c0088i;
        this.f593f = (short) 0;
        refreshChecksum();
    }

    public static byte[] newHeaderBytes() {
        return new byte[20];
    }

    public static com.ucarhu.demo.protocol.UCarMessageHeader createDefault() {
        return new com.ucarhu.demo.protocol.UCarMessageHeader(20, new com.ucarhu.demo.protocol.CommandDescriptor(com.ucarhu.demo.protocol.ProtocolMessageRegistry.getLocalDevice(), com.ucarhu.demo.protocol.DataFormat.RAW, com.ucarhu.demo.protocol.MessageType.SEND, com.ucarhu.demo.protocol.CommandCategory.CONTROL, 0));
    }

    public static byte[] getThreadLocalHeaderBytes() {
        return f585q.get();
    }

    private void refreshChecksum() {
        writeTo(getThreadLocalHeaderBytes());
    }

    private static com.ucarhu.demo.protocol.CommandDescriptor m546a(byte[] bArr, com.ucarhu.demo.protocol.CommandDescriptor c0088i) {
        int i = bArr[12] & 1;
        com.ucarhu.demo.protocol.SourceDevice enumC0098s = com.ucarhu.demo.protocol.SourceDevice.PHONE;
        if (i != enumC0098s.getCode()) {
            enumC0098s = com.ucarhu.demo.protocol.SourceDevice.CAR;
        }
        com.ucarhu.demo.protocol.SourceDevice enumC0098s2 = enumC0098s;
        int i2 = (bArr[12] & 6) >>> 1;
        com.ucarhu.demo.protocol.DataFormat enumC0090k = com.ucarhu.demo.protocol.DataFormat.PB3;
        c0088i.setFields(enumC0098s2, i2 == enumC0090k.getCode() ? enumC0090k : com.ucarhu.demo.protocol.DataFormat.RAW, m547g(bArr[12]), m548m(bArr[13]), (bArr[14] << 8) + bArr[15]);
        return c0088i;
    }

    private static com.ucarhu.demo.protocol.MessageType m547g(byte b2) {
        int i = (b2 & 24) >>> 3;
        com.ucarhu.demo.protocol.MessageType enumC0093n = com.ucarhu.demo.protocol.MessageType.REQ;
        if (i == enumC0093n.getCode()) {
            return enumC0093n;
        }
        com.ucarhu.demo.protocol.MessageType enumC0093n2 = com.ucarhu.demo.protocol.MessageType.RES;
        if (i == enumC0093n2.getCode()) {
            return enumC0093n2;
        }
        com.ucarhu.demo.protocol.MessageType enumC0093n3 = com.ucarhu.demo.protocol.MessageType.SEND_SYNC;
        return i == enumC0093n3.getCode() ? enumC0093n3 : com.ucarhu.demo.protocol.MessageType.SEND;
    }

    private static com.ucarhu.demo.protocol.CommandCategory m548m(int i) {
        com.ucarhu.demo.protocol.CommandCategory enumC0087h = com.ucarhu.demo.protocol.CommandCategory.NONE;
        com.ucarhu.demo.protocol.CommandCategory[] enumC0087hArrValues = com.ucarhu.demo.protocol.CommandCategory.values();
        int i2 = 0;
        while (true) {
            if (i2 >= 8) {
                break;
            }
            com.ucarhu.demo.protocol.CommandCategory enumC0087h2 = enumC0087hArrValues[i2];
            if (i == enumC0087h2.getCode()) {
                enumC0087h = enumC0087h2;
                break;
            }
            i2++;
        }
        if (enumC0087h != com.ucarhu.demo.protocol.CommandCategory.NONE) {
            return enumC0087h;
        }
        throw new com.ucarhu.demo.protocol.ProtocolException("Unknown CmdCategory: " + i);
    }

    public static com.ucarhu.demo.protocol.UCarMessageHeader parseHeader(byte[] bArr) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "rawBytes");
        if (bArr.length < 20) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Protocol header bytes size[" + bArr.length + "] < 20");
        }
        int iM519a = com.ucarhu.demo.protocol.BigEndianBytes.readInt(bArr, 0);
        int iM519a2 = com.ucarhu.demo.protocol.BigEndianBytes.readInt(bArr, 4);
        int iM519a3 = com.ucarhu.demo.protocol.BigEndianBytes.readInt(bArr, 8);
        com.ucarhu.demo.protocol.CommandDescriptor c0088iM546a = m546a(bArr, new com.ucarhu.demo.protocol.CommandDescriptor());
        short sM522d = com.ucarhu.demo.protocol.BigEndianBytes.readShort(bArr, 16);
        short sM522d2 = com.ucarhu.demo.protocol.BigEndianBytes.readShort(bArr, 18);
        short sM532b = com.ucarhu.demo.protocol.Crc16Checksum.compute(bArr, 0, 18);
        if (sM522d2 == sM532b) {
            return new com.ucarhu.demo.protocol.UCarMessageHeader(iM519a, iM519a2, iM519a3, c0088iM546a, sM522d, sM522d2);
        }
        throw new com.ucarhu.demo.protocol.ProtocolException("Invalid header, checksum expect: " + java.lang.Integer.toHexString(sM532b) + ", actual: " + java.lang.Integer.toHexString(sM522d2));
    }

    private void m550p(java.nio.ByteBuffer byteBuffer) {
        com.ucarhu.demo.protocol.SourceDevice enumC0098sM530g = this.f588a.getSourceDevice();
        com.ucarhu.demo.protocol.DataFormat enumC0090kM527d = this.f588a.getDataFormat();
        com.ucarhu.demo.protocol.CommandCategory enumC0087hM524a = this.f588a.getCategory();
        com.ucarhu.demo.protocol.MessageType enumC0093nM528e = this.f588a.getMessageType();
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0098sM530g, "sourceDevice");
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0090kM527d, "dataFormat");
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0090kM527d, "dataFormat");
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0087hM524a, "messageType");
        byteBuffer.put((byte) (enumC0098sM530g.getCode() + (enumC0090kM527d.getCode() << 1) + (enumC0093nM528e.getCode() << 3)));
        byteBuffer.put((byte) enumC0087hM524a.getCode());
        byteBuffer.put((byte) this.f588a.getMethodId());
    }

    private void m551v(byte[] bArr) {
        com.ucarhu.demo.protocol.SourceDevice enumC0098sM530g = this.f588a.getSourceDevice();
        com.ucarhu.demo.protocol.DataFormat enumC0090kM527d = this.f588a.getDataFormat();
        com.ucarhu.demo.protocol.CommandCategory enumC0087hM524a = this.f588a.getCategory();
        com.ucarhu.demo.protocol.MessageType enumC0093nM528e = this.f588a.getMessageType();
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0098sM530g, "sourceDevice");
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0090kM527d, "dataFormat");
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0090kM527d, "dataFormat");
        com.ucarhu.demo.protocol.NullChecks.checkNotNull(enumC0087hM524a, "messageType");
        bArr[12] = (byte) enumC0098sM530g.getCode();
        bArr[12] = (byte) (bArr[12] + ((byte) (enumC0090kM527d.getCode() << 1)));
        bArr[12] = (byte) (bArr[12] + ((byte) (enumC0093nM528e.getCode() << 3)));
        bArr[13] = (byte) enumC0087hM524a.getCode();
        bArr[14] = (byte) ((this.f588a.getMethodId() >>> 8) & 255);
        bArr[15] = (byte) (this.f588a.getMethodId() & 255);
    }

    public final byte[] toHeaderBytes() {
        byte[] bArr = f585q.get();
        writeTo(bArr);
        return bArr;
    }

    public final void refreshTimestamp() {
        this.f591d = (int) (java.lang.System.currentTimeMillis() / 1000);
        refreshChecksum();
    }

    public com.ucarhu.demo.protocol.UCarMessageHeader copy() {
        return new com.ucarhu.demo.protocol.UCarMessageHeader(getMessageLength(), getSequenceId(), getTimestampSeconds(), getCommandDescriptor(), getResponseSeqId(), this.f592e);
    }

    public com.ucarhu.demo.protocol.UCarMessageHeader initOutgoing(int i, com.ucarhu.demo.protocol.DataFormat enumC0090k, com.ucarhu.demo.protocol.MessageType enumC0093n, com.ucarhu.demo.protocol.CommandCategory enumC0087h, int i2) {
        return initFields(i, (short) 0, com.ucarhu.demo.protocol.ProtocolConfig.getLocalDevice(), enumC0090k, enumC0093n, enumC0087h, i2);
    }

    public com.ucarhu.demo.protocol.UCarMessageHeader resetAsResponseTo(int i, com.ucarhu.demo.protocol.UCarMessageHeader c0092m, int i2) {
        this.f589b = i + 20;
        this.f590c = c0092m.f590c;
        this.f591d = (int) (java.lang.System.currentTimeMillis() / 1000);
        com.ucarhu.demo.protocol.CommandDescriptor c0088iM565q = c0092m.getCommandDescriptor();
        this.f588a.setFields(com.ucarhu.demo.protocol.ProtocolConfig.getLocalDevice(), c0088iM565q.getDataFormat(), com.ucarhu.demo.protocol.MessageType.RES, c0088iM565q.getCategory(), i2);
        this.f593f = c0092m.f593f;
        refreshChecksum();
        return this;
    }

    public com.ucarhu.demo.protocol.UCarMessageHeader initFields(int i, short s, com.ucarhu.demo.protocol.SourceDevice enumC0098s, com.ucarhu.demo.protocol.DataFormat enumC0090k, com.ucarhu.demo.protocol.MessageType enumC0093n, com.ucarhu.demo.protocol.CommandCategory enumC0087h, int i2) {
        this.f589b = i;
        this.f590c = com.ucarhu.demo.protocol.SequenceIdGenerator.nextSequenceId();
        this.f591d = (int) (java.lang.System.currentTimeMillis() / 1000);
        this.f588a.setFields(enumC0098s, enumC0090k, enumC0093n, enumC0087h, i2);
        this.f593f = s;
        refreshChecksum();
        return this;
    }

    public com.ucarhu.demo.protocol.UCarMessageHeader copyFrom(com.ucarhu.demo.protocol.UCarMessageHeader c0092m) {
        this.f589b = c0092m.f589b;
        this.f590c = c0092m.f590c;
        this.f591d = c0092m.f591d;
        com.ucarhu.demo.protocol.CommandDescriptor c0088iM565q = c0092m.getCommandDescriptor();
        this.f588a.setFields(c0088iM565q.getSourceDevice(), c0088iM565q.getDataFormat(), c0088iM565q.getMessageType(), c0088iM565q.getCategory(), c0088iM565q.getMethodId());
        this.f593f = c0092m.f593f;
        return this;
    }

    public final void setMessageLength(int i) {
        if (i >= 20) {
            this.f589b = i;
            return;
        }
        throw new com.ucarhu.demo.protocol.ProtocolException("Update message length error: " + i);
    }

    public void setMessageType(com.ucarhu.demo.protocol.MessageType enumC0093n) {
        getCommandDescriptor().setMessageType(enumC0093n);
        refreshChecksum();
    }

    public final void writeTo(java.nio.ByteBuffer byteBuffer) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(byteBuffer, "byteBuf");
        if (byteBuffer.remaining() < 20) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Can not write header to ByteBuffer");
        }
        byteBuffer.putInt(this.f589b);
        byteBuffer.putInt(this.f590c);
        byteBuffer.putInt(this.f591d);
        m550p(byteBuffer);
        byteBuffer.putShort(this.f593f);
        short sM531a = com.ucarhu.demo.protocol.Crc16Checksum.compute(byteBuffer, 0, 18);
        this.f592e = sM531a;
        byteBuffer.putShort(sM531a);
    }

    public final void m562k(short s) {
        this.f593f = s;
        refreshChecksum();
    }

    public void m563l(byte[] bArr) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "rawBytes");
        if (bArr.length < 20) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Protocol header bytes size[" + bArr.length + "] < 20");
        }
        int iM519a = com.ucarhu.demo.protocol.BigEndianBytes.readInt(bArr, 0);
        int iM519a2 = com.ucarhu.demo.protocol.BigEndianBytes.readInt(bArr, 4);
        int iM519a3 = com.ucarhu.demo.protocol.BigEndianBytes.readInt(bArr, 8);
        short sM522d = com.ucarhu.demo.protocol.BigEndianBytes.readShort(bArr, 16);
        short sM522d2 = com.ucarhu.demo.protocol.BigEndianBytes.readShort(bArr, 18);
        short sM532b = com.ucarhu.demo.protocol.Crc16Checksum.compute(bArr, 0, 18);
        if (sM522d2 == sM532b) {
            m546a(bArr, getCommandDescriptor());
            this.f589b = iM519a;
            this.f590c = iM519a2;
            this.f591d = iM519a3;
            this.f593f = sM522d;
            return;
        }
        throw new com.ucarhu.demo.protocol.ProtocolException("Invalid header, checksum expect: " + java.lang.Integer.toHexString(sM532b) + ", actual: " + java.lang.Integer.toHexString(sM522d2));
    }

    public final java.lang.String m564o() {
        return "--------------------------------\nlength: " + this.f589b + ", seqId: " + this.f590c + ", checksum: " + java.lang.Integer.toHexString(this.f592e & 65535) + "\ntimestamp(s): " + this.f591d + " (" + new java.util.Date(this.f591d * 1000) + ")\ncmd: " + this.f588a + "\n--------------------------------\n";
    }

    public final com.ucarhu.demo.protocol.CommandDescriptor getCommandDescriptor() {
        return this.f588a;
    }

    public final void m566r(int i) {
        if (i < 0) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Message body length must not less than 0");
        }
        this.f589b = i + 20;
        refreshChecksum();
    }

    public final void writeTo(byte[] bArr) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "headerBytes");
        if (bArr.length < 20) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Can not write bytes to headerBytes");
        }
        com.ucarhu.demo.protocol.BigEndianBytes.writeInt(bArr, 0, this.f589b);
        com.ucarhu.demo.protocol.BigEndianBytes.writeInt(bArr, 4, this.f590c);
        com.ucarhu.demo.protocol.BigEndianBytes.writeInt(bArr, 8, this.f591d);
        m551v(bArr);
        com.ucarhu.demo.protocol.BigEndianBytes.writeShort(bArr, 16, this.f593f);
        short sM532b = com.ucarhu.demo.protocol.Crc16Checksum.compute(bArr, 0, 18);
        this.f592e = sM532b;
        com.ucarhu.demo.protocol.BigEndianBytes.writeShort(bArr, 18, sM532b);
    }

    public final int getMessageLength() {
        return this.f589b;
    }

    @androidx.annotation.NonNull
    public java.lang.String toString() {
        return m564o();
    }

    public final void m569u(int i) {
        if (i >= 20) {
            this.f589b = i;
            refreshChecksum();
        } else {
            throw new com.ucarhu.demo.protocol.ProtocolException("Update message length error: " + i);
        }
    }

    public short getResponseSeqId() {
        return this.f593f;
    }

    public final void setResponseSeqId(int i) {
        if (i < 0) {
            throw new com.ucarhu.demo.protocol.ProtocolException("seq id must greater than 0");
        }
        if (this.f588a.getMessageType() == com.ucarhu.demo.protocol.MessageType.RES) {
            this.f590c = i;
            refreshChecksum();
        } else {
            throw new com.ucarhu.demo.protocol.ProtocolException(this.f588a.getMessageType() + " can not update seq id");
        }
    }

    public final int getSequenceId() {
        return this.f590c;
    }

    public final int getTimestampSeconds() {
        return this.f591d;
    }
}
