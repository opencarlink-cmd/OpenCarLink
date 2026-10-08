package com.ucarhu.demo.util.binary;

import java.util.Base64;

/**
 * 十六进制与 Base64 编解码工具。
 *
 * <p>原工程里这部分能力来自混淆后的 Guava BaseEncoding 兼容源码。这里改成项目内的
 * 语义化工具类，保留原有大小写、补零和无填充 Base64 的行为。</p>
 */
public final class HexCodec {

    private static final char[] LOWER_HEX = "0123456789abcdef".toCharArray();

    private HexCodec() {
    }

    /**
     * 将字节数组编码为小写十六进制字符串。
     */
    public static String encodeLower(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes == null");
        }
        char[] chars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int value = bytes[i] & 0xFF;
            chars[i * 2] = LOWER_HEX[value >>> 4];
            chars[(i * 2) + 1] = LOWER_HEX[value & 0x0F];
        }
        return new String(chars);
    }

    /**
     * 将十六进制字符串解码为字节数组。
     */
    public static byte[] decode(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if ((hex.length() & 1) != 0) {
            throw new IllegalArgumentException("hex length must be even");
        }
        byte[] out = new byte[hex.length() / 2];
        for (int i = 0; i < out.length; i++) {
            int high = Character.digit(hex.charAt(i * 2), 16);
            int low = Character.digit(hex.charAt((i * 2) + 1), 16);
            if (high < 0 || low < 0) {
                throw new IllegalArgumentException("invalid hex character");
            }
            out[i] = (byte) ((high << 4) | low);
        }
        return out;
    }

    /**
     * 使用标准 Base64 编码并去掉尾部填充符，保持原兼容类的输出格式。
     */
    public static String encodeBase64NoPadding(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes == null");
        }
        return Base64.getEncoder().withoutPadding().encodeToString(bytes);
    }
}
