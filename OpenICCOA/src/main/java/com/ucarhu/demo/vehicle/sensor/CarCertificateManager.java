package com.ucarhu.demo.vehicle.sensor;

public class CarCertificateManager {

    private static final java.lang.String f800c = "CarCertManager";

    private final android.content.Context f801a;

    private final com.ucarhu.demo.sharelink.channel.ShareLinkChannel f802b;

    /**
     * 车辆证书专用通道，连接建立后读取本地证书数据并发送给手机端。
     */
    public class CertificateChannel extends com.ucarhu.demo.sharelink.channel.ShareLinkChannel {

        public final java.lang.String f803F;

        /** 证书消息发送完成后的回调，成功后关闭一次性证书通道。 */
        public class CertificateSendCallback implements com.ucarhu.demo.protocol.channel.SendCallback {
            public CertificateSendCallback() {
            }

            @Override
            public void onFailure(java.lang.Exception exc) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.CarCertificateManager.f800c, "sending car certificate error: " + exc.getMessage(), exc);
            }

            @Override
            public void onSuccess(java.lang.Boolean bool) {
                com.ucarhu.demo.vehicle.sensor.CarCertificateManager.this.f802b.mo359q0();
            }
        }

        public CertificateChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z, boolean z2, java.lang.String str) {
            super(enumC0057b, z, z2);
            this.f803F = str;
        }

        @Override
        public void mo349Z() {
            java.lang.String strM156h = new com.ucarhu.demo.sharelink.util.CarVerifier(com.ucarhu.demo.vehicle.sensor.CarCertificateManager.this.f801a, this.f803F).readRawCarData();
            if (android.text.TextUtils.isEmpty(strM156h)) {
                com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.sensor.CarCertificateManager.f800c, "failed to get car cert data");
            } else {
                com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.CarCertificateManager.f800c, "sending car certificate to phone");
                mo355c(com.ucarhu.demo.protocol.CertificateMessages.m605j(com.ucar.databus.proto.UCarProto.CarCertificate.newBuilder().setContent(com.google.protobuf.ByteString.copyFrom(strM156h.getBytes(java.nio.charset.StandardCharsets.UTF_8))).build()), new com.ucarhu.demo.vehicle.sensor.CarCertificateManager.CertificateChannel.CertificateSendCallback());
            }
        }
    }

    public CarCertificateManager(android.content.Context context, java.lang.String str) {
        this.f801a = context;
        this.f802b = new com.ucarhu.demo.vehicle.sensor.CarCertificateManager.CertificateChannel(com.ucarhu.demo.protocol.channel.ChannelType.CERT, false, true, str);
    }

    public void stopCertificateChannel() throws java.io.IOException {
        com.ucarhu.demo.logging.EasyLogger.info(f800c, "stopCertChannel");
        this.f802b.mo359q0();
    }

    public void startCertificateChannel(java.lang.String str) {
        try {
            this.f802b.m417a0(0, str);
        } catch (java.io.IOException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f800c, "Start car certificate error: ", e2);
        }
    }
}
