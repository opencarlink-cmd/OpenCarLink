package com.ucarhu.demo.sharelink.auth;

/**
 * UCar 认证通道服务，监听 AUTH 通道消息并输出认证结果。
 * 它负责启动/停止认证通道、处理认证请求和确认消息，并通过回调通知上层连接流程。
 */


public class UCarAuthService {

    private static final java.lang.String f161f = "UCarAuthService";

    private final com.ucarhu.demo.sharelink.channel.ShareLinkChannel f163b;

    private final com.ucarhu.demo.sharelink.auth.UCarAuthService.c f164c;

    private final com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator f166e;

    private boolean f162a = false;

    private final com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto f165d = new com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto();

    public class a extends com.ucarhu.demo.sharelink.channel.ShareLinkChannel {
        public a(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z, boolean z2) {
            super(enumC0057b, z, z2);
        }

        @Override
        public void onChannelBound() {
            super.onChannelBound();
            com.ucarhu.demo.sharelink.auth.UCarAuthService.this.f162a = true;
        }

        @Override
        public void mo135O() {
            com.ucarhu.demo.sharelink.auth.UCarAuthService.this.f162a = false;
        }
    }

    public class b implements com.ucarhu.demo.protocol.channel.SendCallback {

        public final com.ucar.databus.proto.UCarProto.AuthResponse f168a;

        public b(com.ucar.databus.proto.UCarProto.AuthResponse authResponse) {
            this.f168a = authResponse;
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.sharelink.auth.UCarAuthService.f161f, "Send auth response error.", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            if (bool.booleanValue()) {
                if (this.f168a.getResult() == 0) {
                    com.ucarhu.demo.sharelink.auth.UCarAuthService.this.f164c.mo139a();
                } else if (this.f168a.getResult() == 2 || this.f168a.getResult() == 1 || this.f168a.getResult() == 3) {
                    com.ucarhu.demo.sharelink.auth.UCarAuthService.this.f164c.mo142b();
                } else {
                    android.util.Log.e(com.ucarhu.demo.sharelink.auth.UCarAuthService.f161f, "wrong negotiation protocol version received");
                }
            }
        }
    }

    public static class c {
        public void mo139a() {
        }

        public void mo140a(java.lang.String str) {
        }

        public void mo141a(java.lang.String str, java.lang.String str2) {
        }

        public void mo142b() {
        }
    }

    public UCarAuthService(android.content.Context context, com.ucarhu.demo.sharelink.auth.UCarAuthService.c cVar) {
        this.f166e = new com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator(context);
        if (cVar != null) {
            this.f164c = cVar;
        } else {
            this.f164c = new com.ucarhu.demo.sharelink.auth.UCarAuthService.c();
        }
        com.ucarhu.demo.sharelink.auth.UCarAuthService.a aVar = new com.ucarhu.demo.sharelink.auth.UCarAuthService.a(com.ucarhu.demo.protocol.channel.ChannelType.AUTH, true, false);
        this.f163b = aVar;
        aVar.m422x0(new com.ucarhu.demo.protocol.channel.NetChannel.a() {
            @Override
            public final void mo94a(com.ucarhu.demo.protocol.UCarMessage c0102w) throws java.lang.InterruptedException {
                UCarAuthService.this.handleAuthChannelMessage(c0102w);
            }
        });
    }

    public void handleAuthChannelMessage(com.ucarhu.demo.protocol.UCarMessage c0102w) throws java.lang.InterruptedException {
        if (com.ucarhu.demo.protocol.AuthMessages.m600q(c0102w)) {
            com.ucar.databus.proto.UCarProto.AuthRequest authRequestM603t = com.ucarhu.demo.protocol.AuthMessages.m603t(c0102w);
            this.f164c.mo141a(new java.lang.String(authRequestM603t.getId().toByteArray(), java.nio.charset.StandardCharsets.UTF_8), authRequestM603t.getModel());
            com.ucar.databus.proto.UCarProto.AuthResponse authResponseM127D = this.f166e.generateAuthResponse(authRequestM603t);
            this.f163b.mo355c(com.ucarhu.demo.protocol.AuthMessages.m595l(authResponseM127D, c0102w.getSequenceId()), new com.ucarhu.demo.sharelink.auth.UCarAuthService.b(authResponseM127D));
            return;
        }
        if (com.ucarhu.demo.protocol.AuthMessages.m598o(c0102w)) {
            java.lang.String str = new java.lang.String(this.f165d.decryptBytes(com.ucarhu.demo.protocol.AuthMessages.m602s(c0102w).getCipher().toByteArray()), java.nio.charset.StandardCharsets.UTF_8);
            java.lang.String[] strArrSplit = str.split(":");
            if (strArrSplit.length > 0) {
                this.f164c.mo140a(strArrSplit[0]);
                return;
            }
            android.util.Log.e(f161f, "phone's authentication confirmation message is ill-formatted, msg=" + str);
        }
    }

    public void stop() throws java.io.IOException {
        com.ucarhu.demo.logging.EasyLogger.info(f161f, "stop auth service");
        this.f163b.mo359q0();
        this.f162a = false;
    }

    public void setPinCode(java.lang.String str) {
        this.f166e.setPinCode(str.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    public void start(java.lang.String str) {
        try {
            com.ucarhu.demo.logging.EasyLogger.info(f161f, "start auth service, binding=" + this.f162a);
            if (this.f162a) {
                return;
            }
            this.f163b.m417a0(0, str);
        } catch (java.io.IOException e2) {
            android.util.Log.e(f161f, "Start auth channel error.", e2);
        }
    }
}
