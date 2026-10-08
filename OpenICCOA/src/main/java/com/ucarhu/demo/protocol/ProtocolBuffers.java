package com.ucarhu.demo.protocol;

public class ProtocolBuffers {

    public static final byte[] f570a;

    public static final java.nio.ByteBuffer f571b;

    private static final int f572c = 131072;

    private static final java.lang.ThreadLocal<byte[]> f573d = java.lang.ThreadLocal.withInitial(new java.util.function.Supplier() {
        @Override
        public final java.lang.Object get() {
            return com.ucarhu.demo.protocol.ProtocolBuffers.allocByteArray();
        }
    });

    private static final java.lang.ThreadLocal<java.nio.ByteBuffer> f574e = java.lang.ThreadLocal.withInitial(new java.util.function.Supplier() {
        @Override
        public final java.lang.Object get() {
            return com.ucarhu.demo.protocol.ProtocolBuffers.obtainByteBuffer();
        }
    });

    static {
        byte[] bArr = new byte[0];
        f570a = bArr;
        f571b = java.nio.ByteBuffer.wrap(bArr);
    }

    public static byte[] allocByteArray() {
        return new byte[131072];
    }

    public static byte[] obtainByteArray(int i) {
        return i <= 131072 ? f573d.get() : new byte[i];
    }

    public static java.nio.ByteBuffer obtainByteBuffer() {
        return java.nio.ByteBuffer.wrap(obtainByteArray(131072));
    }

    public static java.nio.ByteBuffer allocateByteBuffer(int i) {
        java.nio.ByteBuffer byteBufferAllocate = i <= 131072 ? f574e.get() : java.nio.ByteBuffer.allocate(i);
        if (byteBufferAllocate != null) {
            byteBufferAllocate.clear();
            byteBufferAllocate.limit(i);
        }
        return byteBufferAllocate;
    }
}
