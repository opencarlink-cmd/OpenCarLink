package com.ucarhu.demo.util.binary;

/**
 * 加密协议中使用的字节数组工具。
 *
 * <p>这些方法承接原来混淆 Tink/Guava 工具类里的少量能力，避免继续依赖
 * 原来的混淆包名。</p>
 */
public final class CryptoByteUtils {

    private CryptoByteUtils() {
    }

    /**
     * 拼接多个字节数组。为兼容旧工具类，传入 null 时会跳过。
     */
    public static byte[] concat(byte[]... chunks) {
        int length = 0;
        for (byte[] chunk : chunks) {
            if (chunk == null) {
                continue;
            }
            if (length > Integer.MAX_VALUE - chunk.length) {
                throw new IllegalArgumentException("byte array is too large");
            }
            length += chunk.length;
        }
        byte[] out = new byte[length];
        int offset = 0;
        for (byte[] chunk : chunks) {
            if (chunk == null) {
                continue;
            }
            System.arraycopy(chunk, 0, out, offset, chunk.length);
            offset += chunk.length;
        }
        return out;
    }
}
