package com.ucarhu.demo.vehicle.uibc;

/**
 * 车辆控制通道管理器，处理心跳、按键、UIBC、相机、音频、蓝牙、导航和手机状态等控制消息。
 * 入站控制消息统一在工作线程中解析，再通过回调分发给 UCarAdapter。
 */


public class VehicleControlManager {

    private static final java.lang.String f748l = "ControlManager";

    private static final int f749m = 500;

    private static final int f750n = 268435456;

    private static final int f751o = 536870912;

    private com.ucarhu.demo.sharelink.channel.ShareLinkChannel f752a;

    private android.os.HandlerThread f753b;

    private android.os.Handler f754c;

    private boolean f755d;

    private java.lang.Thread f756e;

    private boolean f757f;

    private com.ucar.vehiclesdk.UCarCommon.PhoneStateInfo f759h;

    private com.ucar.vehiclesdk.UCarCommon.MusicInfo f761j;

    private com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o f762k;

    private final java.lang.Object f758g = new java.lang.Object();

    private java.lang.String f760i = null;

    public class a implements com.ucarhu.demo.protocol.channel.SendCallback {
        public a() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to add camera");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "add camera succeeded");
        }
    }

    public class b implements com.ucarhu.demo.protocol.channel.SendCallback {
        public b() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to remove camera");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "remove camera succeeded");
        }
    }

    public class c implements com.ucarhu.demo.protocol.channel.SendCallback {
        public c() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to notify camera state");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "notify camera state succeeded");
        }
    }

    public class d implements com.ucarhu.demo.protocol.channel.SendCallback {
        public d() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendGetUCarConfigResponse");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendGetUCarConfigResponse succeed");
        }
    }

    public class e implements com.ucarhu.demo.protocol.channel.SendCallback {
        public e() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendDisconnect", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendDisconnect succeed");
        }
    }

    public class f implements com.ucarhu.demo.protocol.channel.SendCallback {
        public f() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendGotoForeground", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendGotoForeground succeed");
        }
    }

    public class g implements com.ucarhu.demo.protocol.channel.SendCallback {
        public g() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendGotoBackground");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendGotoBackground succeed");
        }
    }

    public class h implements com.ucarhu.demo.protocol.channel.SendCallback {
        public h() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendKeyEventToPhone");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendKeyEventToPhone succeed");
        }
    }

    public class i implements com.ucarhu.demo.protocol.channel.SendCallback {
        public i() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendVRCmdToPhone");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendVRCmdToPhone succeed");
        }
    }

    public class j implements com.ucarhu.demo.protocol.channel.SendCallback {
        public j() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendCallHungUp");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendCallHungUp succeed");
        }
    }

    public class k implements com.ucarhu.demo.protocol.channel.SendCallback {
        public k() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendSwitchDayOrNight");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendSwitchDayOrNight succeed");
        }
    }

    public class l implements com.ucarhu.demo.protocol.channel.SendCallback {
        public l() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendAwakenVoiceAssistant");
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendAwakenVoiceAssistant succeed");
        }
    }

    public class m implements com.ucarhu.demo.protocol.channel.SendCallback {

        public final com.ucarhu.demo.vehicle.uibc.VehicleControlManager.p f775a;

        public final com.ucar.databus.proto.UCarProto.Heartbeat f776b;

        public m(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.p pVar, com.ucar.databus.proto.UCarProto.Heartbeat heartbeat) {
            this.f775a = pVar;
            this.f776b = heartbeat;
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "failed to sendHeartBeat");
            com.ucarhu.demo.vehicle.uibc.VehicleControlManager.p pVar = this.f775a;
            if (pVar != null) {
                pVar.onPhoneControlReady();
            }
            com.ucarhu.demo.vehicle.uibc.VehicleControlManager.this.m909L();
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "sendHeartBeat to phone succeeded," + this.f776b.getTimestamp());
        }
    }

    public class n implements java.lang.Runnable {

        public final com.ucarhu.demo.vehicle.uibc.VehicleControlManager.p f778b;

        public n(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.p pVar) {
            this.f778b = pVar;
        }

        @Override
        public void run() {
            while (com.ucarhu.demo.vehicle.uibc.VehicleControlManager.this.f757f) {
                try {
                    com.ucarhu.demo.vehicle.uibc.VehicleControlManager.this.m896i(this.f778b);
                    java.lang.Thread.sleep(2000L);
                } catch (java.lang.Exception e2) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f748l, "startSendHeartBeatThread Exception", e2);
                }
            }
        }
    }

    public interface o {
        void onOpenSystemSettings();

        void onHeartbeat(long j);

        void onCustomControlMessage(com.ucarhu.demo.protocol.UCarMessage c0102w);

        void onAudioPlayerControl(com.ucarhu.demo.vehicle.audio.UCarAudioManager.b bVar);

        void onAudioPlayerStateChanged(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVar);

        void onBluetoothMacInfo(com.ucar.vehiclesdk.UCarCommon.BluetoothMacInfo bluetoothMacInfo);

        void onCameraAction(com.ucar.vehiclesdk.UCarCommon.CameraAction cameraAction, com.ucar.vehiclesdk.UCarCommon.CameraActionArgs cameraActionArgs);

        void onMusicInfo(com.ucar.vehiclesdk.UCarCommon.MusicInfo musicInfo);

        void onNavigationInfo(com.ucar.vehiclesdk.UCarCommon.NavigationInfo navigationInfo);

        void onPhoneStateInfo(com.ucar.vehiclesdk.UCarCommon.PhoneStateInfo phoneStateInfo);

        void onMicRecordRequest(boolean z, com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, boolean z2);

        void onHomePressed();

        void onBackPressed();
    }

    public interface p {
        void onPhoneControlReady();
    }

    public VehicleControlManager() {
        this.f755d = false;
        if (!this.f755d) {
            com.ucarhu.demo.logging.EasyLogger.info(f748l, "start worker thread");
            android.os.HandlerThread handlerThread = new android.os.HandlerThread("ControlManager_WorkerThread");
            this.f753b = handlerThread;
            handlerThread.start();
            android.os.Looper looper = this.f753b.getLooper();
            if (looper != null) {
                this.f754c = new android.os.Handler(looper);
                this.f755d = true;
            } else {
                com.ucarhu.demo.logging.EasyLogger.error(f748l, "failed to get valid worker thread looper!");
                this.f754c = null;
                this.f755d = false;
            }
        }
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a = new com.ucarhu.demo.sharelink.channel.ShareLinkChannel(com.ucarhu.demo.protocol.channel.ChannelType.CONTROL, false, true);
        this.f752a = c0016a;
        c0016a.m422x0(new com.ucarhu.demo.protocol.channel.NetChannel.a() {
            @Override
            public final void mo94a(com.ucarhu.demo.protocol.UCarMessage c0102w) throws java.lang.InterruptedException {
                VehicleControlManager.this.m888Y(c0102w);
            }
        });
        this.f759h = new com.ucar.vehiclesdk.UCarCommon.PhoneStateInfo();
    }

    private com.ucar.databus.proto.UCarProto.SampleRate m870A(int i2) {
        return com.ucar.databus.proto.UCarProto.SampleRate.forNumber(i2);
    }

    private void m871B(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleBluetoothMacInfo");
        com.ucar.databus.proto.UCarProto.BluetoothMacInfo bluetoothMacInfoM622N = com.ucarhu.demo.protocol.ControlMessages.m622N(c0102w);
        if (bluetoothMacInfoM622N == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "camera state cannot be null");
            return;
        }
        com.ucar.vehiclesdk.UCarCommon.BluetoothMacInfo bluetoothMacInfo = new com.ucar.vehiclesdk.UCarCommon.BluetoothMacInfo(com.ucar.vehiclesdk.UCarCommon.OPType.fromType(bluetoothMacInfoM622N.getOpTypeValue()), bluetoothMacInfoM622N.getBluetoothMac());
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onBluetoothMacInfo(bluetoothMacInfo);
        }
    }

    private void m872D(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleCameraState");
        com.ucar.databus.proto.UCarProto.SetCameraState setCameraStateM647k0 = com.ucarhu.demo.protocol.ControlMessages.m647k0(c0102w);
        if (setCameraStateM647k0 == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "camera state cannot be null");
            return;
        }
        com.ucar.vehiclesdk.UCarCommon.CameraActionArgs cameraActionArgs = new com.ucar.vehiclesdk.UCarCommon.CameraActionArgs(setCameraStateM647k0.getCameraId(), new android.util.Range(java.lang.Integer.valueOf(setCameraStateM647k0.getFpsRange().getMin()), java.lang.Integer.valueOf(setCameraStateM647k0.getFpsRange().getMax())), new android.util.Size(setCameraStateM647k0.getPictureSize().getWidth(), setCameraStateM647k0.getPictureSize().getHeight()));
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onCameraAction(com.ucar.vehiclesdk.UCarCommon.CameraAction.fromInt(setCameraStateM647k0.getActionValue()), cameraActionArgs);
        }
    }

    private void m873F(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onCustomControlMessage(c0102w);
        }
    }

    private void m874H(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleMicroPhoneState");
        com.ucar.databus.proto.UCarProto.NotifyMicrophoneState notifyMicrophoneStateM638d0 = com.ucarhu.demo.protocol.ControlMessages.m638d0(c0102w);
        if (notifyMicrophoneStateM638d0 == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "micro phone state cannot be null");
            return;
        }
        int iM889a = m889a(notifyMicrophoneStateM638d0.getChannelMask());
        int iM890b = m890b(notifyMicrophoneStateM638d0.getEncodingFormat());
        int iM891c = m891c(notifyMicrophoneStateM638d0.getSampleRate());
        boolean state = notifyMicrophoneStateM638d0.getState();
        com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat = new com.ucar.vehiclesdk.UCarCommon.AudioFormat("audio/pcm", iM890b, iM891c, iM889a);
        boolean z = com.ucar.vehiclesdk.UCarCommon.ModemCallState.RINGING.equals(this.f759h.getModemCallState()) || com.ucar.vehiclesdk.UCarCommon.ModemCallState.OFFHOOK.equals(this.f759h.getModemCallState()) || this.f759h.isVoipCall();
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onMicRecordRequest(state, audioFormat, z);
        }
        this.f759h.setUseMicrophone(state);
        m879P();
    }

    private void m875K(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucar.databus.proto.UCarProto.NotifyMirrorState notifyMirrorStateM639e0 = com.ucarhu.demo.protocol.ControlMessages.m639e0(c0102w);
        if (notifyMirrorStateM639e0 == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "mirror state cannot be null");
            return;
        }
        if (this.f762k != null) {
            int mirrorStateValue = notifyMirrorStateM639e0.getMirrorStateValue();
            com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleMirrorState" + mirrorStateValue);
            if (mirrorStateValue == 0) {
                this.f762k.onHomePressed();
            } else if (mirrorStateValue == 1) {
                this.f762k.onOpenSystemSettings();
            } else if (mirrorStateValue == 2) {
                this.f762k.onBackPressed();
            }
        }
    }

    private void m876M(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleMusicInfo");
        com.ucar.databus.proto.UCarProto.NotifyMusicInfo notifyMusicInfoM640f0 = com.ucarhu.demo.protocol.ControlMessages.m640f0(c0102w);
        if (notifyMusicInfoM640f0 == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "music info cannot be null");
            return;
        }
        com.ucar.vehiclesdk.UCarCommon.MusicInfo musicInfoM894f = m894f(notifyMusicInfoM640f0);
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onMusicInfo(musicInfoM894f);
        }
    }

    private void m877N() {
        android.os.HandlerThread handlerThread = this.f753b;
        if (handlerThread == null || !this.f755d) {
            return;
        }
        handlerThread.quitSafely();
        this.f753b = null;
        this.f754c = null;
        this.f755d = false;
    }

    private void m878O(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleNavigationInfo");
        com.ucar.databus.proto.UCarProto.NotifyNavigationInfo notifyNavigationInfoM641g0 = com.ucarhu.demo.protocol.ControlMessages.m641g0(c0102w);
        if (notifyNavigationInfoM641g0 == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "navigation cannot be null");
            return;
        }
        com.ucar.vehiclesdk.UCarCommon.NavigationInfo navigationInfo = new com.ucar.vehiclesdk.UCarCommon.NavigationInfo(notifyNavigationInfoM641g0.getIsNavigating(), notifyNavigationInfoM641g0.getDirectionIcon().toByteArray(), notifyNavigationInfoM641g0.getDistance(), notifyNavigationInfoM641g0.getDistanceUnit(), notifyNavigationInfoM641g0.getOperation(), notifyNavigationInfoM641g0.getWhere(), notifyNavigationInfoM641g0.getTitle1(), notifyNavigationInfoM641g0.getTitle2());
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "navigation info, isNavigating:" + navigationInfo.isNavigating() + ", Dist" + navigationInfo.getDistance() + ", Unit" + navigationInfo.getDistanceUnit() + ", Operation" + navigationInfo.getOperation());
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onNavigationInfo(navigationInfo);
        }
    }

    private void m879P() {
        if (this.f762k != null) {
            com.ucarhu.demo.logging.EasyLogger.info(f748l, "update phone state:" + this.f759h);
            this.f762k.onPhoneStateInfo(this.f759h);
        }
    }

    private void m880Q(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handlePhoneHeartbeat");
        com.ucar.databus.proto.UCarProto.Heartbeat heartbeatM631W = com.ucarhu.demo.protocol.ControlMessages.m631W(c0102w);
        if (heartbeatM631W == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "heart beat cannot be null");
            return;
        }
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onHeartbeat(heartbeatM631W.getTimestamp());
        }
    }

    private void m881R(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handlePhoneState");
        com.ucar.databus.proto.UCarProto.NotifyPhoneState notifyPhoneStateM642h0 = com.ucarhu.demo.protocol.ControlMessages.m642h0(c0102w);
        if (notifyPhoneStateM642h0 == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "phone state cannot be null");
            return;
        }
        this.f759h.setModemCallState(com.ucar.vehiclesdk.UCarCommon.ModemCallState.fromInt(notifyPhoneStateM642h0.getCsValue()));
        this.f759h.setScreenLocked(notifyPhoneStateM642h0.getIsScreenLocked());
        this.f759h.setVoipCall(notifyPhoneStateM642h0.getIsWechatQqCall());
        this.f759h.setVoiceAssistantActive(notifyPhoneStateM642h0.getIsVoiceAssistantActive());
        m879P();
    }

    public void m887X(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        if (!m903z()) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "control socket channel not ready, please check!");
        }
        int iM697y = c0102w.getMethodId();
        if (iM697y == 1) {
            m880Q(c0102w);
            return;
        }
        if (iM697y == 17) {
            m895g(c0102w);
            return;
        }
        if (iM697y == 20) {
            m872D(c0102w);
            return;
        }
        if (iM697y == 22) {
            m871B(c0102w);
            return;
        }
        if (iM697y == 24) {
            m873F(c0102w);
            return;
        }
        switch (iM697y) {
            case 6:
                m874H(c0102w);
                break;
            case 7:
                m875K(c0102w);
                break;
            case 8:
                m902x(c0102w);
                break;
            case 9:
                m881R(c0102w);
                break;
            case 10:
                m876M(c0102w);
                break;
            case 11:
                m878O(c0102w);
                break;
            default:
                com.ucarhu.demo.logging.EasyLogger.error(f748l, "unknown message method type: " + iM697y);
                break;
        }
    }

    public void m888Y(final com.ucarhu.demo.protocol.UCarMessage c0102w) throws java.lang.InterruptedException {
        java.lang.Runnable runnable;
        if (c0102w == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "received message cannot be null");
            return;
        }
        android.os.Handler handler = this.f754c;
        if (handler != null) {
            runnable = new java.lang.Runnable() {
                @Override
                public final void run() {
                    VehicleControlManager.this.m887X(c0102w);
                }
            };
        } else {
            handler = new android.os.Handler(android.os.Looper.getMainLooper());
            runnable = new java.lang.Runnable() {
                @Override
                public final void run() {
                    VehicleControlManager.this.m887X(c0102w);
                }
            };
        }
        handler.post(runnable);
    }

    private int m889a(com.ucar.databus.proto.UCarProto.ChannelMask channelMask) {
        if (channelMask.equals(com.ucar.databus.proto.UCarProto.ChannelMask.CHANNEL_MONO)) {
            return 16;
        }
        return channelMask.equals(com.ucar.databus.proto.UCarProto.ChannelMask.CHANNEL_STEREO) ? 12 : -1;
    }

    private int m890b(com.ucar.databus.proto.UCarProto.EncodingFormat encodingFormat) {
        if (encodingFormat.equals(com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_8BIT)) {
            return 3;
        }
        if (encodingFormat.equals(com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_16BIT)) {
            return 2;
        }
        return (encodingFormat.equals(com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_24BIT_PACKED) || encodingFormat.equals(com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_32BIT) || !encodingFormat.equals(com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_FLOAT)) ? -1 : 4;
    }

    private int m891c(com.ucar.databus.proto.UCarProto.SampleRate sampleRate) {
        return sampleRate.getNumber();
    }

    private long m892d() {
        return java.lang.System.currentTimeMillis();
    }

    private com.ucar.databus.proto.UCarProto.ChannelMask m893e(int i2) {
        if (i2 != 4) {
            if (i2 == 12) {
                return com.ucar.databus.proto.UCarProto.ChannelMask.CHANNEL_STEREO;
            }
            if (i2 != 16) {
                return com.ucar.databus.proto.UCarProto.ChannelMask.UNKNOWN_CHANNEL;
            }
        }
        return com.ucar.databus.proto.UCarProto.ChannelMask.CHANNEL_MONO;
    }

    private com.ucar.vehiclesdk.UCarCommon.MusicInfo m894f(com.ucar.databus.proto.UCarProto.NotifyMusicInfo notifyMusicInfo) {
        com.ucar.vehiclesdk.UCarCommon.MusicInfo musicInfo;
        java.lang.String strM901w = m901w(notifyMusicInfo);
        if (android.text.TextUtils.equals(strM901w, this.f760i) && (musicInfo = this.f761j) != null) {
            musicInfo.setPlaying(notifyMusicInfo.getIsPlaying());
            this.f761j.setPlayingCurrentTimeMs(notifyMusicInfo.getPlayingCurrentTimeMs());
            this.f761j.setLyrics(notifyMusicInfo.getLyrics());
            return this.f761j;
        }
        byte[] bArrM30188A0 = null;
        if (notifyMusicInfo.hasCoverArtBitmap() && notifyMusicInfo.getCoverArtBitmap() != null) {
            bArrM30188A0 = notifyMusicInfo.getCoverArtBitmap().toByteArray();
        }
        com.ucar.vehiclesdk.UCarCommon.MusicInfo musicInfo2 = new com.ucar.vehiclesdk.UCarCommon.MusicInfo(notifyMusicInfo.getArtistName(), notifyMusicInfo.getAlbumName(), notifyMusicInfo.getCoverArt(), notifyMusicInfo.getLyrics(), notifyMusicInfo.getPlayingTimesMs(), notifyMusicInfo.getTitle(), notifyMusicInfo.getAuthorName(), notifyMusicInfo.getWriterName(), notifyMusicInfo.getComposerName(), notifyMusicInfo.getPlayingCurrentTimeMs(), notifyMusicInfo.getIsFavorite(), notifyMusicInfo.getIsPlaying(), bArrM30188A0);
        this.f760i = strM901w;
        this.f761j = musicInfo2;
        return musicInfo2;
    }

    private void m895g(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleAudioPlayerControl");
        if (this.f762k == null) {
            com.ucarhu.demo.logging.EasyLogger.debug(f748l, "mPhoneCmdListener is null, ignore AudioPlayerControl");
            return;
        }
        com.ucar.databus.proto.UCarProto.AudioPlayerControl audioPlayerControlM619K = com.ucarhu.demo.protocol.ControlMessages.m619K(c0102w);
        com.ucar.vehiclesdk.UCarCommon.AudioType audioTypeFromInt = com.ucar.vehiclesdk.UCarCommon.AudioType.fromInt(audioPlayerControlM619K.getAudioType().getNumber());
        com.ucarhu.demo.vehicle.audio.UCarAudioManager.b bVar = new com.ucarhu.demo.vehicle.audio.UCarAudioManager.b();
        bVar.setAudioType(audioTypeFromInt);
        bVar.setBufferingCount(audioPlayerControlM619K.getBufferingCount());
        bVar.setSpeedAdjustStep(audioPlayerControlM619K.getSpeedAdjustStep());
        this.f762k.onAudioPlayerControl(bVar);
    }

    public void m896i(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.p pVar) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "sendHeartBeat");
        if (m903z()) {
            com.ucarhu.demo.protocol.UCarMessage c0102wM616H = com.ucarhu.demo.protocol.ControlMessages.m616H();
            this.f752a.mo355c(c0102wM616H, new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.m(pVar, com.ucarhu.demo.protocol.ControlMessages.m631W(c0102wM616H)));
        }
    }

    private int m899u(com.ucar.databus.proto.UCarProto.ChannelMask channelMask) {
        if (channelMask.equals(com.ucar.databus.proto.UCarProto.ChannelMask.CHANNEL_MONO)) {
            return 4;
        }
        return channelMask.equals(com.ucar.databus.proto.UCarProto.ChannelMask.CHANNEL_STEREO) ? 12 : -1;
    }

    private com.ucar.databus.proto.UCarProto.EncodingFormat m900v(int i2) {
        return i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != f750n ? i2 != f751o ? com.ucar.databus.proto.UCarProto.EncodingFormat.UNKNOWN_FORMAT : com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_32BIT : com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_24BIT_PACKED : com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_FLOAT : com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_8BIT : com.ucar.databus.proto.UCarProto.EncodingFormat.ENCODING_PCM_16BIT;
    }

    private static java.lang.String m901w(com.ucar.databus.proto.UCarProto.NotifyMusicInfo notifyMusicInfo) {
        return notifyMusicInfo.getTitle() + notifyMusicInfo.getAlbumName() + notifyMusicInfo.getArtistName() + notifyMusicInfo.getPlayingTimesMs();
    }

    private void m902x(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "handleAudioPlayerState");
        com.ucar.databus.proto.UCarProto.NotifyAudioPlayerState notifyAudioPlayerStateM633Y = com.ucarhu.demo.protocol.ControlMessages.m633Y(c0102w);
        if (notifyAudioPlayerStateM633Y == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "audio player cannot be null");
            return;
        }
        int iM899u = m899u(notifyAudioPlayerStateM633Y.getChannelMask());
        int iM890b = m890b(notifyAudioPlayerStateM633Y.getEncodingFormat());
        int iM891c = m891c(notifyAudioPlayerStateM633Y.getSampleRate());
        com.ucar.vehiclesdk.UCarCommon.AudioType audioTypeFromInt = com.ucar.vehiclesdk.UCarCommon.AudioType.fromInt(notifyAudioPlayerStateM633Y.getTypeValue());
        com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat = new com.ucar.vehiclesdk.UCarCommon.AudioFormat("audio/pcm", iM890b, iM891c, iM899u);
        com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVarM796b = com.ucarhu.demo.vehicle.audio.UCarAudioManager.d.fromValue(notifyAudioPlayerStateM633Y.getStateValue());
        com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar = this.f762k;
        if (oVar != null) {
            oVar.onAudioPlayerStateChanged(audioTypeFromInt, audioFormat, dVarM796b);
        }
    }

    private boolean m903z() {
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a = this.f752a;
        return c0016a != null && c0016a.mo353b();
    }

    public boolean m904C() {
        if (!m903z()) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, " sendCallHungUp() Channel not ready");
            return false;
        }
        this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m657u(com.ucar.databus.proto.UCarProto.NotifyCallHungUp.newBuilder().setTimestamp(m892d()).build()), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.j());
        return true;
    }

    public boolean m905E() {
        java.lang.String str;
        com.ucar.databus.proto.UCarProto.Disconnect disconnectBuild = com.ucar.databus.proto.UCarProto.Disconnect.newBuilder().build();
        if (m903z()) {
            java.lang.Exception e;
            try {
                return this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m650n(disconnectBuild), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.e()).get(500L, java.util.concurrent.TimeUnit.MILLISECONDS).booleanValue();
            } catch (java.lang.InterruptedException e2) {
                e = e2;
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "sendDisconnect exception:", e);
                return false;
            } catch (java.util.concurrent.ExecutionException e3) {
                e = e3;
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "sendDisconnect exception:", e);
                return false;
            } catch (java.util.concurrent.TimeoutException unused) {
                str = "sendDisconnect get ack time out";
            }
        } else {
            str = "sendDisconnect Channel not ready";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f748l, str);
        return false;
    }

    public boolean m906G() {
        java.lang.String str;
        if (m903z()) {
            java.lang.Exception e;
            try {
                return this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m618J(), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.g()).get(500L, java.util.concurrent.TimeUnit.MILLISECONDS).booleanValue();
            } catch (java.lang.InterruptedException e2) {
                e = e2;
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "sendGotoBackground exception:", e);
                return false;
            } catch (java.util.concurrent.ExecutionException e3) {
                e = e3;
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "sendGotoBackground exception:", e);
                return false;
            } catch (java.util.concurrent.TimeoutException unused) {
                str = "sendGotoBackground get ack time out";
            }
        } else {
            str = " sendGotoBackground Channel not ready";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f748l, str);
        return false;
    }

    public boolean m907I() {
        java.lang.String str;
        if (m903z()) {
            java.lang.Exception e;
            try {
                return this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m620L(), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.f()).get(500L, java.util.concurrent.TimeUnit.MILLISECONDS).booleanValue();
            } catch (java.lang.InterruptedException e2) {
                e = e2;
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "sendGotoForeground exception:", e);
                return false;
            } catch (java.util.concurrent.ExecutionException e3) {
                e = e3;
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "sendGotoForeground exception:", e);
                return false;
            } catch (java.util.concurrent.TimeoutException unused) {
                str = "sendGotoForeground get ack time out";
            }
        } else {
            str = " sendGotoForeground() Channel not ready";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f748l, str);
        return false;
    }

    public void m908J() {
        synchronized (this.f758g) {
            m909L();
            m877N();
            if (this.f752a != null) {
                com.ucarhu.demo.logging.EasyLogger.debug(f748l, "close control channel");
                this.f752a.mo359q0();
            }
        }
    }

    public void m909L() {
        com.ucarhu.demo.logging.EasyLogger.debug(f748l, "stopSendHeartBeatThread");
        this.f757f = false;
        java.lang.Thread thread = this.f756e;
        if (thread != null) {
            try {
                thread.interrupt();
                this.f756e.join();
                this.f756e = null;
            } catch (java.lang.InterruptedException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "stopSendHeartBeatThread Exception", e2);
            }
        }
    }

    public void m910h(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.o oVar) {
        this.f762k = oVar;
    }

    public void m911k(java.lang.String str) {
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a = this.f752a;
        if (c0016a == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "control socket channel init failed");
            return;
        }
        if (c0016a.mo353b()) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, "control socket channel has opened");
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f748l, "start control channel");
        try {
            java.util.HashMap map = new java.util.HashMap();
            map.put(com.ucarhu.demo.protocol.channel.socket.SocketChannel.f485u, 1);
            map.put(com.ucarhu.demo.protocol.channel.socket.SocketChannel.f486v, 1);
            map.put(com.ucarhu.demo.protocol.channel.socket.SocketChannel.f487w, 1);
            map.put(com.ucarhu.demo.protocol.channel.socket.SocketChannel.f488x, 0);
            map.put(com.ucarhu.demo.protocol.channel.socket.SocketChannel.f489y, java.lang.Integer.valueOf(com.ucarhu.demo.protocol.channel.socket.SocketChannel.f490z));
            this.f752a.m419g0(0, str, map);
        } catch (java.io.IOException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "Start control channel error.", e2);
        }
    }

    public boolean m912m(com.ucar.databus.proto.UCarProto.NotifyAddCamera notifyAddCamera) {
        com.ucarhu.demo.logging.EasyLogger.debug(f748l, "add camera");
        if (!m903z()) {
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f748l, "add camera start");
        try {
            this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m655s(notifyAddCamera), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.a()).get(50L, java.util.concurrent.TimeUnit.MILLISECONDS);
            return true;
        } catch (java.lang.Exception e) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f748l, "notifyAddCamera exception:", e);
            return true;
        }
    }

    public boolean m913n(com.ucar.databus.proto.UCarProto.NotifyRemoveCamera notifyRemoveCamera) {
        com.ucarhu.demo.logging.EasyLogger.debug(f748l, "remove camera");
        if (m903z()) {
            this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m610B(notifyRemoveCamera), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.b());
            return true;
        }
        com.ucarhu.demo.logging.EasyLogger.error(f748l, " notifyRemoveCamera() Channel not ready");
        return false;
    }

    public boolean m914o(com.ucar.vehiclesdk.UCarCommon.DayNightMode dayNightMode) {
        java.lang.String str;
        if (dayNightMode == null || dayNightMode == com.ucar.vehiclesdk.UCarCommon.DayNightMode.UNKNOWN_MODE) {
            str = " sendSwitchDayOrNight() args error";
        } else {
            if (m903z()) {
                this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m611C(com.ucar.databus.proto.UCarProto.NotifySwitchDayOrNight.newBuilder().setModeValue(dayNightMode.getValue()).build()), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.k());
                return true;
            }
            str = " sendSwitchDayOrNight Channel not ready";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f748l, str);
        return false;
    }

    public boolean m915p(com.ucar.vehiclesdk.UCarCommon.KeyEventActionType keyEventActionType, com.ucar.vehiclesdk.UCarCommon.KeyCodeType keyCodeType, int i2) {
        java.lang.String str;
        if (keyEventActionType == null || keyCodeType == null || keyEventActionType == com.ucar.vehiclesdk.UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_UNDEFINED || keyCodeType == com.ucar.vehiclesdk.UCarCommon.KeyCodeType.KEY_CODE_UNDEFINED) {
            str = " sendKeyEventToPhone() args error";
        } else {
            if (m903z()) {
                this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m649m(com.ucar.databus.proto.UCarProto.CustomKeyEvent.newBuilder().setTimestamp(m892d()).setAction(keyEventActionType.getValue()).setKeycodeValue(keyCodeType.getValue()).setMetaState(i2).build()), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.h());
                return true;
            }
            str = " sendKeyEventToPhone() Channel not ready";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f748l, str);
        return false;
    }

    public boolean m916q(com.ucar.vehiclesdk.UCarCommon.VRCmdType vRCmdType, java.lang.String str) {
        java.lang.String str2;
        if (vRCmdType == null || str == null) {
            str2 = "sendVRCmdToPhone() args error";
        } else {
            if (m903z()) {
                this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m613E(com.ucar.databus.proto.UCarProto.VRCmdToPhone.newBuilder().setCmdValue(vRCmdType.getValue()).setSource(str).build()), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.i());
                return true;
            }
            str2 = " sendVRCmdToPhone() Channel not ready";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f748l, str2);
        return false;
    }

    public boolean m917r(com.ucar.vehiclesdk.UCarConfig uCarConfig, com.ucarhu.demo.protocol.UCarMessage c0102w) {
        if (!m903z()) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, " sendGetUCarConfigResponse() Channel not ready");
            return false;
        }
        this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m654r(com.ucar.databus.proto.UCarProto.GetUCarConfigResponse.newBuilder().setCarBrMac(com.google.protobuf.ByteString.copyFrom(uCarConfig.getCarBrMac())).setScreenWidth(uCarConfig.getScreenWidth()).setScreenHeight(uCarConfig.getScreenHeight()).setDpi(uCarConfig.getDpi()).setVideoDisplayWidth(uCarConfig.getVideoDisplayWidth()).setVideoDisplayHeight(uCarConfig.getVideoDisplayHeight()).setFps(uCarConfig.getFps()).setIsSupportP2P(uCarConfig.isSupportP2P()).setIsSupportSoftAP(uCarConfig.isSupportSoftAP()).setIsDataTransMode(uCarConfig.isDataTransMode()).setDefault5GChannel(uCarConfig.getDefault5gChannel()).setIsSupportCamera(uCarConfig.isSupportCamera()).setIsSupportMic(uCarConfig.isSupportMic()).setIsSupportLowLatencyDecodingMode(uCarConfig.isSupportLowLatencyDecodingMode()).setIsSupportVoiceWaken(uCarConfig.isSupportVoiceWaken()).setCarCustomField(com.google.protobuf.ByteString.copyFrom(uCarConfig.getCarCustomField())).setSdkVersion(com.ucar.sdk.BuildConfig.SDK_VERSION).build(), c0102w.getSequenceId()), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.d());
        return true;
    }

    public boolean m918s(java.lang.String str, com.ucar.vehiclesdk.UCarCommon.CameraState cameraState) {
        com.ucarhu.demo.logging.EasyLogger.debug(f748l, "onCameraStateChanged, state = " + cameraState);
        if (!m903z()) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, " notifyCameraStateChanged() Channel not ready");
            return false;
        }
        this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m658v(com.ucar.databus.proto.UCarProto.NotifyCameraStateChanged.newBuilder().setCameraId(str).setStateValue(cameraState.getValue()).build()), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.c());
        return true;
    }

    public boolean m919t(byte[] bArr, com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, java.lang.String str) {
        if (!m903z()) {
            com.ucarhu.demo.logging.EasyLogger.error(f748l, " sendAwakenVoiceAssistant() Channel not ready");
            return false;
        }
        com.ucar.databus.proto.UCarProto.SampleRate sampleRateM870A = m870A(audioFormat.getSampleRate());
        com.ucar.databus.proto.UCarProto.ChannelMask channelMaskM893e = m893e(audioFormat.getChannelConfig());
        this.f752a.mo355c(com.ucarhu.demo.protocol.ControlMessages.m648l(com.ucar.databus.proto.UCarProto.AwakenVoiceAssistant.newBuilder().setPcmData(com.google.protobuf.ByteString.copyFrom(bArr)).setSampleRate(sampleRateM870A).setChannelMask(channelMaskM893e).setEncodingFormat(m900v(audioFormat.getEncodingFormat())).setTimestamp(m892d()).setSource(str).build()), new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.l());
        return true;
    }

    public void m920y(com.ucarhu.demo.vehicle.uibc.VehicleControlManager.p pVar) {
        com.ucarhu.demo.logging.EasyLogger.info(f748l, "startSendHeartBeatThread");
        this.f757f = true;
        java.lang.Thread thread = new java.lang.Thread(new com.ucarhu.demo.vehicle.uibc.VehicleControlManager.n(pVar));
        this.f756e = thread;
        thread.start();
    }
}
