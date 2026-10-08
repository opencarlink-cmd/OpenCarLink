package com.ucarhu.demo.protocol.crypto;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HKDF 密钥派生工具。
 *
 * <p>用于 ECDH 共享密钥到 AES 会话密钥的派生，算法流程保持 RFC 5869 的
 * extract-then-expand 结构，替代原来混淆后的 Tink 兼容源码。</p>
 */
public final class Hkdf {

    private Hkdf() {
    }

    public static byte[] deriveKey(String macAlgorithm, byte[] inputKeyMaterial, byte[] salt, byte[] info, int size)
            throws GeneralSecurityException {
        Mac mac = Mac.getInstance(macAlgorithm);
        if (size > mac.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }

        byte[] realSalt = salt == null || salt.length == 0 ? new byte[mac.getMacLength()] : salt;
        mac.init(new SecretKeySpec(realSalt, macAlgorithm));
        byte[] pseudoRandomKey = mac.doFinal(inputKeyMaterial);

        mac.init(new SecretKeySpec(pseudoRandomKey, macAlgorithm));
        byte[] out = new byte[size];
        byte[] previous = new byte[0];
        int offset = 0;
        int counter = 1;
        while (offset < size) {
            mac.update(previous);
            if (info != null) {
                mac.update(info);
            }
            mac.update((byte) counter);
            previous = mac.doFinal();
            int copyLength = Math.min(previous.length, size - offset);
            System.arraycopy(previous, 0, out, offset, copyLength);
            offset += copyLength;
            counter++;
        }
        return out;
    }
}
