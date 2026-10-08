package com.ucarhu.demo.protocol.crypto;

public class AesGcmSessionCrypto {

    private static final java.lang.String TAG = "SecurityManager";

    private static final int INT32_BYTES = 4;

    private static final int GCM_AUTH_TAG_BITS = 128;

    private static final java.util.Map<java.lang.String, javax.crypto.SecretKey> SESSION_KEYS = new java.util.concurrent.ConcurrentHashMap();

    private java.lang.String sessionKeyName;

    private java.security.SecureRandom secureRandom;

    private javax.crypto.Cipher encryptCipher;

    private javax.crypto.Cipher decryptCipher;

    public AesGcmSessionCrypto() {
        this("session_key");
    }

    public AesGcmSessionCrypto(java.lang.String sessionKeyName) {
        try {
            this.sessionKeyName = sessionKeyName;
            this.encryptCipher = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding");
            this.decryptCipher = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding");
            this.secureRandom = java.security.SecureRandom.getInstance("SHA1PRNG");
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(TAG, "Init SecurityManager error.", e2);
        }
    }

    private static int readInt32Be(byte[] bytes, int offset, int length) {
        return java.nio.ByteBuffer.wrap(bytes, offset, length).order(java.nio.ByteOrder.BIG_ENDIAN).getInt();
    }

    private void checkCryptoReady(javax.crypto.SecretKey secretKey) {
        if (secretKey == null) {
            throw new java.lang.SecurityException("no session key: " + this.sessionKeyName);
        }
        if (this.decryptCipher == null) {
            throw new java.lang.SecurityException("encryption cipher is not created");
        }
        if (this.secureRandom == null) {
            throw new java.lang.SecurityException("random generator is not created");
        }
    }

    public static void putSessionKey(byte[] keyBytes, java.lang.String keyName) {
        SESSION_KEYS.put(keyName, new javax.crypto.spec.SecretKeySpec(keyBytes, "AES"));
    }

    private static byte[] toInt32BeBytes(int value) {
        return java.nio.ByteBuffer.allocate(INT32_BYTES).order(java.nio.ByteOrder.BIG_ENDIAN).putInt(value).array();
    }

    public static byte[] getSessionKey(java.lang.String keyName) {
        javax.crypto.SecretKey secretKey = SESSION_KEYS.get(keyName);
        if (secretKey != null) {
            return secretKey.getEncoded();
        }
        return null;
    }

    public java.nio.ByteBuffer decryptToHeapBuffer(java.nio.ByteBuffer byteBuffer) {
        return decrypt(byteBuffer, true);
    }

    public java.nio.ByteBuffer decrypt(java.nio.ByteBuffer byteBuffer, boolean useHeapBuffer) {
        java.nio.ByteBuffer outputBuffer;
        if (byteBuffer == null || byteBuffer.remaining() == 0) {
            return com.ucarhu.demo.protocol.ProtocolBuffers.f571b;
        }
        javax.crypto.SecretKey secretKey = SESSION_KEYS.get(this.sessionKeyName);
        synchronized (this) {
            try {
                try {
                    checkCryptoReady(secretKey);
                    int ivLength = byteBuffer.getInt();
                    byte[] iv = com.ucarhu.demo.protocol.ProtocolBuffers.obtainByteArray(ivLength);
                    byteBuffer.get(iv, 0, ivLength);
                    this.decryptCipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey, new javax.crypto.spec.GCMParameterSpec(GCM_AUTH_TAG_BITS, iv, 0, ivLength));
                    int cipherLength = byteBuffer.getInt();
                    byte[] cipherBytes = com.ucarhu.demo.protocol.ProtocolBuffers.obtainByteArray(cipherLength);
                    byteBuffer.get(cipherBytes, 0, cipherLength);
                    int outputSize = this.decryptCipher.getOutputSize(cipherLength);
                    outputBuffer = useHeapBuffer ? java.nio.ByteBuffer.allocate(outputSize) : com.ucarhu.demo.protocol.ProtocolBuffers.allocateByteBuffer(outputSize);
                    this.decryptCipher.doFinal(java.nio.ByteBuffer.wrap(cipherBytes, 0, cipherLength), outputBuffer);
                    outputBuffer.flip();
                } catch (java.lang.Exception e2) {
                    com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(TAG, "decrypt error.", e2);
                    return com.ucarhu.demo.protocol.ProtocolBuffers.f571b;
                }
            } catch (java.lang.Throwable th) {
                throw new RuntimeException(th);
            }
        }
        return outputBuffer;
    }

    public byte[] decryptBytes(byte[] encryptedBytes) {
        byte[] plainBytes;
        javax.crypto.SecretKey secretKey = SESSION_KEYS.get(this.sessionKeyName);
        synchronized (this) {
            try {
                try {
                    checkCryptoReady(secretKey);
                    java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();
                    int offset = 0;
                    while (offset < encryptedBytes.length) {
                        int ivLength = readInt32Be(encryptedBytes, offset, INT32_BYTES);
                        int ivOffset = offset + INT32_BYTES;
                        this.decryptCipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey, new javax.crypto.spec.GCMParameterSpec(GCM_AUTH_TAG_BITS, encryptedBytes, ivOffset, ivLength));
                        int cipherLengthOffset = ivOffset + ivLength;
                        int cipherLength = readInt32Be(encryptedBytes, cipherLengthOffset, INT32_BYTES);
                        byteArrayOutputStream.write(this.decryptCipher.doFinal(encryptedBytes, cipherLengthOffset + INT32_BYTES, cipherLength));
                        offset += ivLength + INT32_BYTES + INT32_BYTES + cipherLength;
                    }
                    plainBytes = byteArrayOutputStream.toByteArray();
                } catch (java.lang.Exception e2) {
                    com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(TAG, "decrypt error.", e2);
                    return new byte[0];
                }
            } catch (java.lang.Throwable th) {
                throw new RuntimeException(th);
            }
        }
        return plainBytes;
    }

    public java.nio.ByteBuffer encrypt(java.nio.ByteBuffer byteBuffer) {
        java.nio.ByteBuffer outputBuffer;
        javax.crypto.SecretKey secretKey = SESSION_KEYS.get(this.sessionKeyName);
        if (byteBuffer == null || byteBuffer.remaining() == 0) {
            return com.ucarhu.demo.protocol.ProtocolBuffers.f571b;
        }
        synchronized (this) {
            try {
                checkCryptoReady(secretKey);
                this.encryptCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey, this.secureRandom);
                byte[] iv = this.encryptCipher.getIV();
                int outputSize = this.encryptCipher.getOutputSize(byteBuffer.remaining());
                if (iv == null) {
                    throw new java.lang.SecurityException("invalid IV for encryption");
                }
                if (outputSize <= 0) {
                    throw new java.lang.SecurityException("encryption returns nothing");
                }
                outputBuffer = com.ucarhu.demo.protocol.ProtocolBuffers.allocateByteBuffer(iv.length + INT32_BYTES + INT32_BYTES + outputSize);
                outputBuffer.putInt(iv.length);
                outputBuffer.put(iv);
                outputBuffer.putInt(outputSize);
                this.encryptCipher.doFinal(byteBuffer, outputBuffer);
                outputBuffer.flip();
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(TAG, "encrypt error.", e2);
                return com.ucarhu.demo.protocol.ProtocolBuffers.f571b;
            }
        }
        return outputBuffer;
    }

    public byte[] encryptBytes(byte[] plainBytes) {
        byte[] encryptedBytes;
        javax.crypto.SecretKey secretKey = SESSION_KEYS.get(this.sessionKeyName);
        synchronized (this) {
            try {
                checkCryptoReady(secretKey);
                this.encryptCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey, this.secureRandom);
                byte[] iv = this.encryptCipher.getIV();
                byte[] cipherBytes = this.encryptCipher.doFinal(plainBytes);
                if (iv == null) {
                    throw new java.lang.SecurityException("invalid IV for encryption");
                }
                if (cipherBytes == null) {
                    throw new java.lang.SecurityException("encryption returns nothing");
                }
                encryptedBytes = com.ucarhu.demo.util.binary.CryptoByteUtils.concat(toInt32BeBytes(iv.length), iv, toInt32BeBytes(cipherBytes.length), cipherBytes);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(TAG, "encrypt error.", e2);
                return new byte[0];
            }
        }
        return encryptedBytes;
    }
}
