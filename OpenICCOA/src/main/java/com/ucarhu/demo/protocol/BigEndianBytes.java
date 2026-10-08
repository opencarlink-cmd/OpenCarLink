package com.ucarhu.demo.protocol;

public final class BigEndianBytes {
    private BigEndianBytes() {
    }

    public static int readInt(byte[] bArr, int i) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "byteArray");
        if (bArr.length < i + 4) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Read int from protocol bytes error");
        }
        return (bArr[i + 3] & 0xFF) | ((bArr[i] & 0xFF) << 24) | ((bArr[i + 1] & 0xFF) << 16) | ((bArr[i + 2] & 0xFF) << 8);
    }

    public static void writeInt(byte[] bArr, int i, int i2) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "byteArray");
        if (bArr.length < i + 4) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Read int from protocol bytes error");
        }
        bArr[i] = (byte) ((i2 >>> 24) & 255);
        bArr[i + 1] = (byte) ((i2 >>> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >>> 8) & 255);
        bArr[i + 3] = (byte) (i2 & 255);
    }

    public static void writeShort(byte[] bArr, int i, short s) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "byteArray");
        if (bArr.length < i + 2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Read int from protocol bytes error");
        }
        bArr[i] = (byte) ((s >>> 8) & 255);
        bArr[i + 1] = (byte) (s & 255);
    }

    public static short readShort(byte[] bArr, int i) {
        com.ucarhu.demo.protocol.NullChecks.requireNonNull(bArr, "byteArray");
        if (bArr.length < i + 2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("Read int from protocol bytes error");
        }
        return (short) (((bArr[i + 1] & 0xFF) | ((bArr[i] & 0xFF) << 8)) & 65535);
    }
}
