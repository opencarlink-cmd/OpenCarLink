package com.ucarhu.demo.util.binary;

public class ByteOrderUtils {
    public static int readInt32Be(byte[] bArr) {
        return ((bArr[0] & 0xFF) << 24) | (bArr[3] & 0xFF) | ((bArr[2] & 0xFF) << 8) | ((bArr[1] & 0xFF) << 16);
    }

    public static int readInt32Be(byte[] bArr, int i) {
        return ((bArr[i] & 0xFF) << 24) | (bArr[i + 3] & 0xFF) | ((bArr[i + 2] & 0xFF) << 8) | ((bArr[i + 1] & 0xFF) << 16);
    }

    public static void writeInt32Be(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i & 255);
        bArr[i2 + 2] = (byte) ((i >> 8) & 255);
        bArr[i2 + 1] = (byte) ((i >> 16) & 255);
        bArr[i2] = (byte) ((i >> 24) & 255);
    }

    public static void writeInt64Be(long j, byte[] bArr, int i) {
        bArr[i + 7] = (byte) (j & 255);
        bArr[i + 6] = (byte) ((j >> 8) & 255);
        bArr[i + 5] = (byte) ((j >> 16) & 255);
        bArr[i + 4] = (byte) ((j >> 24) & 255);
        bArr[i + 3] = (byte) ((j >> 32) & 255);
        bArr[i + 2] = (byte) ((j >> 40) & 255);
        bArr[i + 1] = (byte) ((j >> 48) & 255);
        bArr[i] = (byte) ((j >> 56) & 255);
    }

    public static void writeInt16Be(short s, byte[] bArr, int i) {
        bArr[i + 1] = (byte) (s & 255);
        bArr[i] = (byte) ((s >> 8) & 255);
    }

    public static byte[] toInt32BeBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] toInt64BeBytes(long j) {
        return new byte[]{(byte) ((j >> 56) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255)};
    }

    public static byte[] toInt16BeBytes(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }

    public static long readInt64Be(byte[] bArr) {
        return ((bArr[0] & 255) << 56) | ((bArr[1] & 255) << 48) | ((bArr[2] & 255) << 40) | ((bArr[3] & 255) << 32) | ((bArr[4] & 255) << 24) | ((bArr[5] & 255) << 16) | ((bArr[6] & 255) << 8) | (255 & bArr[7]);
    }

    public static long readInt64Be(byte[] bArr, int i) {
        return (bArr[i + 7] & 255) | ((bArr[i] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }

    public static void writeUInt8(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i & 255);
    }

    public static void writeUInt32Be(long j, byte[] bArr, int i) {
        bArr[i + 3] = (byte) j;
        bArr[i + 2] = (byte) ((j >> 8) & 255);
        bArr[i + 1] = (byte) ((j >> 16) & 255);
        bArr[i] = (byte) ((j >> 24) & 255);
    }

    public static byte[] toUInt8Bytes(int i) {
        return new byte[]{(byte) (i & 255)};
    }

    public static byte[] toUInt32BeBytes(long j) {
        return new byte[]{(byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255)};
    }

    public static short readInt16Be(byte[] bArr) {
        return (short) (((bArr[0] & 0xFF) << 8) | (bArr[1] & 0xFF));
    }

    public static short readInt16Be(byte[] bArr, int i) {
        return (short) (((bArr[i] & 0xFF) << 8) | (bArr[i + 1] & 0xFF));
    }

    public static void writeUInt16Be(int i, byte[] bArr, int i2) {
        bArr[i2 + 1] = (byte) (i & 255);
        bArr[i2] = (byte) ((i >> 8) & 255);
    }

    public static byte[] toUInt16BeBytes(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static int readUInt8(byte[] bArr) {
        return bArr[0] & 0xFF;
    }

    public static int readUInt8(byte[] bArr, int i) {
        return bArr[i] & 0xFF;
    }

    public static long readUInt32Be(byte[] bArr) {
        return (bArr[3] & 0xFF) | ((bArr[2] & 0xFF) << 8) | ((bArr[1] & 0xFF) << 16) | ((bArr[0] & 0xFF) << 24);
    }

    public static long readUInt32Be(byte[] bArr, int i) {
        return ((bArr[i] & 0xFF) << 24) | (bArr[i + 3] & 0xFF) | ((bArr[i + 2] & 0xFF) << 8) | ((bArr[i + 1] & 0xFF) << 16);
    }

    public static int readUInt16Be(byte[] bArr) {
        return ((bArr[0] & 0xFF) << 8) | (bArr[1] & 0xFF);
    }

    public static int readUInt16Be(byte[] bArr, int i) {
        return ((bArr[i] & 0xFF) << 8) | (bArr[i + 1] & 0xFF);
    }
}
