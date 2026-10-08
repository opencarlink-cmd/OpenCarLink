package com.ucarhu.demo.sharelink.auth;

public class ServerKeyNegotiator extends com.ucarhu.demo.sharelink.auth.KeyNegotiator {

    private static final java.lang.String f149k = "ServerKeyNegotiator";

    private static final java.lang.String f150l = "server_auth_key_for_client_";

    private static final int f151m = 32;

    private static com.ucar.databus.proto.UCarProto.AuthResponse f152n;

    public static class b {

        public byte[] f153a;

        public int f154b;

        public byte[] f155c;

        public byte[] f156d;

        public byte[] f157e;

        public byte[] f158f;

        public byte[] f159g;

        public boolean f160h;

        private b() {
        }
    }

    static {
        try {
            com.ucar.databus.proto.UCarProto.AuthResponse.Builder version = com.ucar.databus.proto.UCarProto.AuthResponse.newBuilder().setVersion(com.ucarhu.demo.sharelink.auth.KeyNegotiator.getProtocolVersion());
            com.google.protobuf.ByteString abstractC2534u = com.google.protobuf.ByteString.EMPTY;
            f152n = version.setAuthPk(abstractC2534u).setAuthPkHmac(abstractC2534u).setAgreementPk(abstractC2534u).setAgreementPkSig(abstractC2534u).setRandom(abstractC2534u).setResult(3).build();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f149k, "build AuthResponse Exception", e2);
        }
    }

    public ServerKeyNegotiator(android.content.Context context) {
        super(context);
    }

    public static void deletePhoneById(android.content.Context context, java.lang.String str) {
        if (android.text.TextUtils.isEmpty(str)) {
            com.ucarhu.demo.logging.EasyLogger.warn(f149k, "deletePhoneById received empty deviceId");
            return;
        }
        try {
            byte[] bytes = str.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            com.ucarhu.demo.sharelink.auth.KeyNegotiator.initPeerDatabase(context);
            com.ucarhu.demo.sharelink.auth.KeyNegotiator.removePeer(bytes, m126G(bytes));
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f149k, "removePeer Exception", e2);
        }
    }

    private com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator.b m125F(com.ucar.databus.proto.UCarProto.AuthRequest authRequest) {
        com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator.b bVar = new com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator.b();
        bVar.f153a = authRequest.getId().toByteArray();
        bVar.f154b = authRequest.getVersion();
        bVar.f155c = authRequest.getAuthPk().toByteArray();
        bVar.f156d = authRequest.getAuthPkHmac().toByteArray();
        bVar.f157e = authRequest.getAgreementPk().toByteArray();
        bVar.f158f = authRequest.getAgreementPkSig().toByteArray();
        bVar.f159g = authRequest.getRandom().toByteArray();
        bVar.f160h = authRequest.getUserConfirmed();
        return bVar;
    }

    private static java.lang.String m126G(byte[] bArr) {
        return f150l + new java.lang.String(bArr, java.nio.charset.StandardCharsets.UTF_8);
    }

    public com.ucar.databus.proto.UCarProto.AuthResponse generateAuthResponse(com.ucar.databus.proto.UCarProto.AuthRequest authRequest) {
        com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator.b bVarM125F = null;
        com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator.b bVar = null;
        if (getPinCode() == null || getPinCode().length < 6) {
            return null;
        }
        try {
            bVarM125F = m125F(authRequest);
        } catch (java.lang.Exception e2) {
            java.lang.Exception e = e2;
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f149k, "parse auth request Exception", e);
            return f152n;
        }
        try {
            if (bVarM125F.f157e.length != 0 && bVarM125F.f159g.length != 0 && bVarM125F.f153a.length != 0) {
                byte[] bArrM111l = encodeEcPublicKey(generateEphemeralEcPublicKey());
                byte[] bArrM108i = generateRandomBytes(32);
                if (bVarM125F.f155c.length != 0 && bVarM125F.f156d.length != 0) {
                    com.ucarhu.demo.logging.EasyLogger.debug(f149k, "normal connection initiated by the phone");
                    if (deriveAndStoreSessionKeys(verifySignedAgreementKey(bVarM125F.f157e, bVarM125F.f159g, bVarM125F.f158f, verifyPinnedAuthKey(bVarM125F.f155c, new byte[0], bVarM125F.f159g, bVarM125F.f156d)), com.ucarhu.demo.sharelink.util.ByteArrayUtils.concat(bVarM125F.f159g, getRandomBytes())) && savePeerAuthKey(bVarM125F.f153a, bVarM125F.f155c)) {
                        byte[] bArrM111l2 = encodeEcPublicKey(generateKeyStoreAuthPublicKey(m126G(bVarM125F.f153a)));
                        com.ucarhu.demo.logging.EasyLogger.debug(f149k, "normal connection succeeded");
                        return com.ucar.databus.proto.UCarProto.AuthResponse.newBuilder().setVersion(com.ucarhu.demo.sharelink.auth.KeyNegotiator.getProtocolVersion()).setAuthPk(com.google.protobuf.ByteString.copyFrom(bArrM111l2)).setAuthPkHmac(com.google.protobuf.ByteString.copyFrom(hmacWithPin(com.ucarhu.demo.sharelink.util.ByteArrayUtils.concat(bArrM111l2, bVarM125F.f155c), com.ucarhu.demo.sharelink.util.ByteArrayUtils.concat(bArrM108i, bVarM125F.f159g)))).setAgreementPk(com.google.protobuf.ByteString.copyFrom(bArrM111l)).setAgreementPkSig(com.google.protobuf.ByteString.copyFrom(signAgreementKey(m126G(bVarM125F.f153a), bArrM111l, com.ucarhu.demo.sharelink.util.ByteArrayUtils.concat(bVarM125F.f157e, bArrM108i, bVarM125F.f159g)))).setRandom(com.google.protobuf.ByteString.copyFrom(bArrM108i)).setResult(0).build();
                    }
                    com.ucarhu.demo.logging.EasyLogger.error(f149k, "normal connection failed");
                    byte[] bArr = bVarM125F.f153a;
                    com.ucarhu.demo.sharelink.auth.KeyNegotiator.removePeer(bArr, m126G(bArr));
                    return f152n;
                }
                com.ucarhu.demo.sharelink.auth.PeerEntity c0019cM115r = getPeer(bVarM125F.f153a);
                if (c0019cM115r == null) {
                    com.ucarhu.demo.logging.EasyLogger.debug(f149k, "quick connection rejected, need normal connection");
                    com.ucar.databus.proto.UCarProto.AuthResponse.Builder version = com.ucar.databus.proto.UCarProto.AuthResponse.newBuilder().setVersion(com.ucarhu.demo.sharelink.auth.KeyNegotiator.getProtocolVersion());
                    com.google.protobuf.ByteString abstractC2534u = com.google.protobuf.ByteString.EMPTY;
                    return version.setAuthPk(abstractC2534u).setAuthPkHmac(abstractC2534u).setAgreementPk(abstractC2534u).setAgreementPkSig(abstractC2534u).setRandom(abstractC2534u).setResult(1).build();
                }
                com.ucarhu.demo.sharelink.auth.PeerEntity c0019cM118u = getLastPeer();
                if (c0019cM118u != null && !c0019cM118u.f146a.equals(c0019cM115r.f146a) && !bVarM125F.f160h) {
                    com.ucarhu.demo.logging.EasyLogger.debug(f149k, "the incoming phone wants quick connection but is not the last connected one, the phone needs to disconnect and ask for user confirmation");
                    com.ucar.databus.proto.UCarProto.AuthResponse.Builder version2 = com.ucar.databus.proto.UCarProto.AuthResponse.newBuilder().setVersion(com.ucarhu.demo.sharelink.auth.KeyNegotiator.getProtocolVersion());
                    com.google.protobuf.ByteString abstractC2534u2 = com.google.protobuf.ByteString.EMPTY;
                    return version2.setAuthPk(abstractC2534u2).setAuthPkHmac(abstractC2534u2).setAgreementPk(abstractC2534u2).setAgreementPkSig(abstractC2534u2).setRandom(abstractC2534u2).setResult(2).build();
                }
                if (!deriveAndStoreSessionKeys(verifySignedAgreementKey(bVarM125F.f157e, bVarM125F.f159g, bVarM125F.f158f, decodeEcPublicKey(base64Decode(c0019cM115r.f147b))), com.ucarhu.demo.sharelink.util.ByteArrayUtils.concat(bVarM125F.f159g, getRandomBytes()))) {
                    com.ucarhu.demo.logging.EasyLogger.error(f149k, "quick connection failed");
                    byte[] bArr2 = bVarM125F.f153a;
                    com.ucarhu.demo.sharelink.auth.KeyNegotiator.removePeer(bArr2, m126G(bArr2));
                    return f152n;
                }
                com.ucarhu.demo.logging.EasyLogger.debug(f149k, "quick connection succeeded");
                updatePeerLastConnection(bVarM125F.f153a);
                com.ucar.databus.proto.UCarProto.AuthResponse.Builder version3 = com.ucar.databus.proto.UCarProto.AuthResponse.newBuilder().setVersion(com.ucarhu.demo.sharelink.auth.KeyNegotiator.getProtocolVersion());
                com.google.protobuf.ByteString abstractC2534u3 = com.google.protobuf.ByteString.EMPTY;
                return version3.setAuthPk(abstractC2534u3).setAuthPkHmac(abstractC2534u3).setAgreementPk(com.google.protobuf.ByteString.copyFrom(bArrM111l)).setAgreementPkSig(com.google.protobuf.ByteString.copyFrom(signAgreementKey(m126G(bVarM125F.f153a), bArrM111l, com.ucarhu.demo.sharelink.util.ByteArrayUtils.concat(bVarM125F.f157e, bArrM108i, bVarM125F.f159g)))).setRandom(com.google.protobuf.ByteString.copyFrom(bArrM108i)).setResult(0).build();
            }
            return null;
        } catch (java.lang.Exception e3) {
            java.lang.Exception e = e3;
            bVar = bVarM125F;
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f149k, "generateKeyNegotiationInfo Exception", e);
            if (bVar != null) {
                byte[] bArr3 = bVar.f153a;
                com.ucarhu.demo.sharelink.auth.KeyNegotiator.removePeer(bArr3, m126G(bArr3));
            }
            return f152n;
        }
    }
}
