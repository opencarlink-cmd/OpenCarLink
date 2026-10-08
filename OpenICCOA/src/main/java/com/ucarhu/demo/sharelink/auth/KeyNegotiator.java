package com.ucarhu.demo.sharelink.auth;

/**
 * 车机与手机之间的密钥协商基类，封装随机数、ECDH、签名、HMAC、会话密钥保存等流程。
 *
 * <p>业务逻辑保持原样，注释用于说明每个步骤在认证链路中的职责。</p>
 */
public class KeyNegotiator {

    private static final java.lang.String f132d = "KeyNegotiator";

    private static final int f133e = 1;

    private static final java.lang.String f134f = "session_key";

    private static final java.lang.String f135g = "uibc_session_key";

    private static final int f136h = 16;

    private static final int f137i = 6;

    public static com.share.connect.security.PeerDatabase f138j;

    private byte[] f139a;

    private byte[] f140b;

    private java.security.KeyPair f141c;

    public static class a {

        public static final int f142a = 0;

        public static final int f143b = 1;

        public static final int f144c = 2;

        public static final int f145d = 3;
    }

    public KeyNegotiator(android.content.Context context) {
        initPeerDatabase(context);
    }

    public static int getProtocolVersion() {
        return 1;
    }

    private java.security.interfaces.ECPublicKey m96b(java.lang.String str, byte[] bArr) throws java.lang.Exception {
        java.security.AlgorithmParameters algorithmParameters = java.security.AlgorithmParameters.getInstance("EC");
        algorithmParameters.init(new java.security.spec.ECGenParameterSpec(str));
        java.security.spec.ECParameterSpec eCParameterSpec = (java.security.spec.ECParameterSpec) algorithmParameters.getParameterSpec(java.security.spec.ECParameterSpec.class);
        int iBitLength = eCParameterSpec.getOrder().bitLength() / 8;
        if (bArr.length != iBitLength * 2) {
            throw new java.lang.RuntimeException("encoded key with wrong size");
        }
        return (java.security.interfaces.ECPublicKey) java.security.KeyFactory.getInstance("EC").generatePublic(new java.security.spec.ECPublicKeySpec(new java.security.spec.ECPoint(new java.math.BigInteger(1, java.util.Arrays.copyOfRange(bArr, 0, iBitLength)), new java.math.BigInteger(1, java.util.Arrays.copyOfRange(bArr, iBitLength, iBitLength + iBitLength))), eCParameterSpec));
    }

    public static void initPeerDatabase(android.content.Context context) {
        if (f138j == null) {
            synchronized (com.ucarhu.demo.sharelink.auth.KeyNegotiator.class) {
                if (f138j == null) {
                    f138j = (com.share.connect.security.PeerDatabase) androidx.room.Room.databaseBuilder(context, com.share.connect.security.PeerDatabase.class, "peer.db").allowMainThreadQueries().build();
                }
            }
        }
    }

    public static void removePeer(byte[] bArr, java.lang.String str) {
        try {
            deleteKeyStoreEntry(str);
            f138j.peerDao().delete(new java.lang.String(bArr, java.nio.charset.StandardCharsets.UTF_8));
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f132d, "removePeer Exception", e2);
        }
    }

    public static org.json.JSONObject buildVersionJson() throws java.lang.Exception {
        return new org.json.JSONObject().put("ver", 1);
    }

    public static void deleteKeyStoreEntry(java.lang.String str) throws java.security.NoSuchAlgorithmException, java.io.IOException, java.security.KeyStoreException, java.security.cert.CertificateException {
        java.security.KeyStore keyStore = java.security.KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keyStore.deleteEntry(str);
    }

    public boolean updatePeerLastConnection(byte[] bArr) {
        return f138j.peerDao().updateLast(new java.lang.String(bArr, java.nio.charset.StandardCharsets.UTF_8)) > 0;
    }

    public byte[] getPinCode() {
        return this.f139a;
    }

    public java.security.interfaces.ECPublicKey generateEphemeralEcPublicKey() throws java.lang.Exception {
        java.security.KeyPairGenerator keyPairGenerator = java.security.KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new java.security.spec.ECGenParameterSpec("secp256r1"));
        java.security.KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        this.f141c = keyPairGenerateKeyPair;
        return (java.security.interfaces.ECPublicKey) keyPairGenerateKeyPair.getPublic();
    }

    public java.security.interfaces.ECPublicKey verifySignedAgreementKey(byte[] bArr, byte[] bArr2, byte[] bArr3, java.security.PublicKey publicKey) throws java.lang.Exception {
        if (publicKey == null) {
            return null;
        }
        java.security.Signature signature = java.security.Signature.getInstance("SHA256withECDSA");
        signature.initVerify(publicKey);
        signature.update(com.ucarhu.demo.util.binary.CryptoByteUtils.concat(bArr, bArr2));
        if (signature.verify(bArr3)) {
            return m96b("secp256r1", bArr);
        }
        return null;
    }

    public java.security.interfaces.ECPublicKey verifyPinnedAuthKey(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws java.lang.Exception {
        if (java.util.Arrays.equals(bArr4, hmacWithPin(com.ucarhu.demo.util.binary.CryptoByteUtils.concat(bArr, bArr2), bArr3))) {
            return m96b("secp256r1", bArr);
        }
        return null;
    }

    public void m106f(byte[] bArr) {
        f138j.peerDao().delete(new java.lang.String(bArr, java.nio.charset.StandardCharsets.UTF_8));
    }

    public boolean deriveAndStoreSessionKeys(java.security.Key key, byte[] bArr) throws java.lang.Exception {
        if (this.f141c == null || key == null) {
            return false;
        }
        javax.crypto.KeyAgreement keyAgreement = javax.crypto.KeyAgreement.getInstance("ECDH");
        keyAgreement.init(this.f141c.getPrivate());
        keyAgreement.doPhase(key, true);
        byte[] bArrM27243b = com.ucarhu.demo.protocol.crypto.Hkdf.deriveKey("HMACSHA256", keyAgreement.generateSecret(), java.security.MessageDigest.getInstance("SHA-256").digest(bArr), utf8Bytes(f134f), 16);
        com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto.putSessionKey(bArrM27243b, f134f);
        com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto.putSessionKey(bArrM27243b, f135g);
        return true;
    }

    public byte[] generateRandomBytes(int i) throws java.lang.Exception {
        java.security.SecureRandom secureRandom = new java.security.SecureRandom();
        byte[] bArr = new byte[i];
        this.f140b = bArr;
        secureRandom.nextBytes(bArr);
        return this.f140b;
    }

    public byte[] base64Decode(java.lang.String str) {
        return java.util.Base64.getDecoder().decode(str);
    }

    public byte[] signAgreementKey(java.lang.String str, byte[] bArr, byte[] bArr2) throws java.lang.Exception {
        java.security.PrivateKey privateKey;
        java.security.KeyStore keyStore = java.security.KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (android.os.Build.VERSION.SDK_INT <= 28) {
            privateKey = (java.security.PrivateKey) keyStore.getKey(str, null);
        } else {
            java.security.KeyStore.Entry entry = keyStore.getEntry(str, null);
            if (!(entry instanceof java.security.KeyStore.PrivateKeyEntry)) {
                throw new java.lang.RuntimeException("sign key not exist or invalid : " + str);
            }
            privateKey = ((java.security.KeyStore.PrivateKeyEntry) entry).getPrivateKey();
        }
        java.security.Signature signature = java.security.Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(com.ucarhu.demo.util.binary.CryptoByteUtils.concat(bArr, bArr2));
        return signature.sign();
    }

    public byte[] encodeEcPublicKey(java.security.interfaces.ECPublicKey eCPublicKey) {
        int iBitLength = eCPublicKey.getParams().getOrder().bitLength() / 8;
        byte[] bArr = new byte[iBitLength * 2];
        byte[] byteArray = eCPublicKey.getW().getAffineX().toByteArray();
        if (byteArray.length <= iBitLength) {
            java.lang.System.arraycopy(byteArray, 0, bArr, iBitLength - byteArray.length, byteArray.length);
        } else {
            if (byteArray.length != iBitLength + 1 || byteArray[0] != 0) {
                throw new java.lang.RuntimeException("x coordinate with wrong size: len=" + byteArray.length);
            }
            java.lang.System.arraycopy(byteArray, 1, bArr, 0, iBitLength);
        }
        byte[] byteArray2 = eCPublicKey.getW().getAffineY().toByteArray();
        if (byteArray2.length <= iBitLength) {
            java.lang.System.arraycopy(byteArray2, 0, bArr, (iBitLength + iBitLength) - byteArray2.length, byteArray2.length);
        } else {
            if (byteArray2.length != iBitLength + 1 || byteArray2[0] != 0) {
                throw new java.lang.RuntimeException("y coordinate with wrong size: len=" + byteArray2.length);
            }
            java.lang.System.arraycopy(byteArray2, 1, bArr, iBitLength, iBitLength);
        }
        return bArr;
    }

    public byte[] hmacWithPin(byte[] bArr, byte[] bArr2) throws java.lang.Exception {
        byte[] bArr3 = this.f139a;
        if (bArr3 == null || bArr3.length < 6) {
            throw new java.lang.Exception("pin is empty or too short when requested");
        }
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        mac.init(new javax.crypto.spec.SecretKeySpec(java.security.MessageDigest.getInstance("SHA-256").digest(com.ucarhu.demo.util.binary.CryptoByteUtils.concat(bArr2, this.f139a)), "HmacSHA256"));
        return mac.doFinal(bArr);
    }

    public java.lang.String base64Encode(byte[] bArr) {
        return java.util.Base64.getEncoder().encodeToString(bArr);
    }

    public boolean savePeerAuthKey(byte[] bArr, byte[] bArr2) {
        com.ucarhu.demo.sharelink.auth.PeerEntity c0019c = new com.ucarhu.demo.sharelink.auth.PeerEntity();
        c0019c.f146a = new java.lang.String(bArr, java.nio.charset.StandardCharsets.UTF_8);
        c0019c.f147b = base64Encode(bArr2);
        c0019c.f148c = java.lang.System.currentTimeMillis() / 1000;
        f138j.peerDao().insert(c0019c);
        return true;
    }

    public com.ucarhu.demo.sharelink.auth.PeerEntity getPeer(byte[] bArr) {
        return f138j.peerDao().get(new java.lang.String(bArr, java.nio.charset.StandardCharsets.UTF_8));
    }

    public java.security.interfaces.ECPublicKey getEphemeralPublicKey() {
        return (java.security.interfaces.ECPublicKey) this.f141c.getPublic();
    }

    public java.security.interfaces.ECPublicKey generateKeyStoreAuthPublicKey(java.lang.String str) throws java.lang.Exception {
        java.security.KeyPairGenerator keyPairGenerator = java.security.KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
        keyPairGenerator.initialize(new android.security.keystore.KeyGenParameterSpec.Builder(str, 12).setAlgorithmParameterSpec(new java.security.spec.ECGenParameterSpec("secp256r1")).setDigests("SHA-256").build());
        return (java.security.interfaces.ECPublicKey) keyPairGenerator.generateKeyPair().getPublic();
    }

    public com.ucarhu.demo.sharelink.auth.PeerEntity getLastPeer() {
        return f138j.peerDao().getLast();
    }

    public java.security.interfaces.ECPublicKey getKeyStoreAuthPublicKey(java.lang.String str) throws java.lang.Exception {
        java.security.KeyStore keyStore = java.security.KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        java.security.KeyStore.Entry entry = keyStore.getEntry(str, null);
        if (entry instanceof java.security.KeyStore.PrivateKeyEntry) {
            return (java.security.interfaces.ECPublicKey) ((java.security.KeyStore.PrivateKeyEntry) entry).getCertificate().getPublicKey();
        }
        throw new java.lang.RuntimeException("sign key not exist or invalid : " + str);
    }

    public java.security.interfaces.ECPublicKey decodeEcPublicKey(byte[] bArr) throws java.lang.Exception {
        return m96b("secp256r1", bArr);
    }

    public void setPinCode(byte[] bArr) {
        this.f139a = bArr;
    }

    public byte[] getRandomBytes() {
        return this.f140b;
    }

    public byte[] utf8Bytes(java.lang.String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }
}
