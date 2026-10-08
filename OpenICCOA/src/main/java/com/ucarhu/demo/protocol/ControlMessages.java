package com.ucarhu.demo.protocol;

public class ControlMessages extends com.ucarhu.demo.protocol.ProtocolMessageRegistry {

    public static class a extends java.util.HashMap<java.lang.Integer, java.lang.String> {
        public a() {
            put(1, "heartbeat");
            put(2, "get_port_request");
            put(3, "get_port_response");
            put(4, "notify_car_to_foreground");
            put(5, "notify_car_to_background");
            put(6, "notify_microphone_state");
            put(7, "notify_mirror_state");
            put(8, "notify_audio_player_state");
            put(9, "notify_phone_state");
            put(10, "notify_music_info");
            put(11, "notify_navigation_info");
            put(12, "custom_key_event");
            put(13, "vr_cmd_to_phone");
            put(14, "notify_call_hung_up");
            put(15, "notify_switch_day_or_night");
            put(16, "awaken_voice_assistant");
            put(17, "audio_player_control");
            put(18, "notify_add_camera");
            put(19, "notify_remove_camera");
            put(20, "set_camera_state");
            put(21, "camera_state");
            put(22, "bluetooth_mac_info");
            put(23, "disconnect");
            put(24, "get_ucar_config_request");
            put(25, "get_ucar_config_response");
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage m609A(com.ucar.databus.proto.UCarProto.NotifyPhoneState notifyPhoneState) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(9).buildProtobuf(notifyPhoneState));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m610B(com.ucar.databus.proto.UCarProto.NotifyRemoveCamera notifyRemoveCamera) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(19).buildProtobuf(notifyRemoveCamera));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m611C(com.ucar.databus.proto.UCarProto.NotifySwitchDayOrNight notifySwitchDayOrNight) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(15).buildProtobuf(notifySwitchDayOrNight));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m612D(com.ucar.databus.proto.UCarProto.SetCameraState setCameraState) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(20).buildProtobuf(setCameraState));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m613E(com.ucar.databus.proto.UCarProto.VRCmdToPhone vRCmdToPhone) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(13).buildProtobuf(vRCmdToPhone));
    }

    private static void m614F(int i, com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessage(com.ucarhu.demo.protocol.DataFormat.PB3, com.ucarhu.demo.protocol.CommandCategory.CONTROL, i, c0102w);
    }

    private static void m615G(int i, com.ucarhu.demo.protocol.UCarMessage c0102w, com.ucarhu.demo.protocol.MessageType enumC0093n) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.checkMessageType(com.ucarhu.demo.protocol.DataFormat.PB3, com.ucarhu.demo.protocol.CommandCategory.CONTROL, i, c0102w, enumC0093n);
    }

    public static com.ucarhu.demo.protocol.UCarMessage m616H() {
        return m624P().sourceDevice(com.ucarhu.demo.protocol.ProtocolMessageRegistry.getLocalDevice()).methodId(1).buildProtobuf(com.ucar.databus.proto.UCarProto.Heartbeat.newBuilder().setTimestamp(java.lang.System.currentTimeMillis()).build());
    }

    public static boolean m617I(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w.isProtobuf() && c0102w.getCategory() == com.ucarhu.demo.protocol.CommandCategory.CONTROL && c0102w.getMethodId() == 1;
    }

    public static com.ucarhu.demo.protocol.UCarMessage m618J() {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(5).buildProtobuf(com.ucar.databus.proto.UCarProto.NotifyCarToBackground.newBuilder().setTimestamp(java.lang.System.currentTimeMillis()).build()));
    }

    public static com.ucar.databus.proto.UCarProto.AudioPlayerControl m619K(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(17, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.AudioPlayerControl.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseAudioPlayerControl error: " + e2.getMessage());
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage m620L() {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(4).buildProtobuf(com.ucar.databus.proto.UCarProto.NotifyCarToForeground.newBuilder().setTimestamp(java.lang.System.currentTimeMillis()).build()));
    }

    public static com.ucar.databus.proto.UCarProto.AwakenVoiceAssistant m621M(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(16, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.AwakenVoiceAssistant.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseAwakenVoiceAssistantMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.BluetoothMacInfo m622N(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(22, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.BluetoothMacInfo.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseBluetoothMacInfo error: " + e2.getMessage());
        }
    }

    public static void m623O() {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.registerMethodNames(com.ucarhu.demo.protocol.CommandCategory.CONTROL, new com.ucarhu.demo.protocol.ControlMessages.a());
    }

    private static com.ucarhu.demo.protocol.UCarMessageBuilder m624P() {
        return com.ucarhu.demo.protocol.UCarMessage.newBuilder().category(com.ucarhu.demo.protocol.CommandCategory.CONTROL);
    }

    public static com.ucar.databus.proto.UCarProto.CustomKeyEvent m625Q(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(12, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.CustomKeyEvent.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseCustomKeyEventMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.Disconnect m626R(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(23, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.Disconnect.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseDisconnect error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.GetPortRequest m627S(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(2, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.GetPortRequest.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseGetPortRequestMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.GetPortResponse m628T(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(3, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.GetPortResponse.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseGetPortResponseMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.GetUCarConfigRequest m629U(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m615G(24, c0102w, com.ucarhu.demo.protocol.MessageType.REQ);
        try {
            return com.ucar.databus.proto.UCarProto.GetUCarConfigRequest.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseGetUCarConfigRequestMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.GetUCarConfigResponse m630V(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m615G(25, c0102w, com.ucarhu.demo.protocol.MessageType.RES);
        try {
            return com.ucar.databus.proto.UCarProto.GetUCarConfigResponse.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseGetUCarConfigResponseMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.Heartbeat m631W(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(1, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.Heartbeat.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseHeartbeatMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyAddCamera m632X(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(18, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyAddCamera.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyAddCameraMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyAudioPlayerState m633Y(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(8, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyAudioPlayerState.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyAudioPlayerStateMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyCallHungUp m634Z(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(14, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyCallHungUp.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyCallHungUpMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyCameraStateChanged m635a0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(21, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyCameraStateChanged.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyMicrophoneStateMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyCarToBackground m636b0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(5, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyCarToBackground.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyHomeToBackgroundMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyCarToForeground m637c0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(4, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyCarToForeground.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyHomeToForegroundMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyMicrophoneState m638d0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(6, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyMicrophoneState.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyMicrophoneStateMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyMirrorState m639e0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(7, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyMirrorState.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyMirrorStateMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyMusicInfo m640f0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(10, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyMusicInfo.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyMusicInfoMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyNavigationInfo m641g0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(11, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyNavigationInfo.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyNavigationInfoMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyPhoneState m642h0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(9, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyPhoneState.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyPhoneStateMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifyRemoveCamera m643i0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(19, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifyRemoveCamera.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifyRemoveCameraMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.VRCmdToPhone m644j(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(13, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.VRCmdToPhone.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseVRCmdToPhoneMessage error: " + e2.getMessage());
        }
    }

    public static com.ucar.databus.proto.UCarProto.NotifySwitchDayOrNight m645j0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(15, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.NotifySwitchDayOrNight.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseNotifySwitchDayOrNightMessage error: " + e2.getMessage());
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage m646k(com.ucar.databus.proto.UCarProto.AudioPlayerControl audioPlayerControl) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(17).buildProtobuf(audioPlayerControl));
    }

    public static com.ucar.databus.proto.UCarProto.SetCameraState m647k0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        m614F(20, c0102w);
        try {
            return com.ucar.databus.proto.UCarProto.SetCameraState.parseFrom(c0102w.getBodyBuffer());
        } catch (com.google.protobuf.InvalidProtocolBufferException e2) {
            throw new com.ucarhu.demo.protocol.ProtocolException("parseSetCameraStateMessage error: " + e2.getMessage());
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessage m648l(com.ucar.databus.proto.UCarProto.AwakenVoiceAssistant awakenVoiceAssistant) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(16).buildProtobuf(awakenVoiceAssistant));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m649m(com.ucar.databus.proto.UCarProto.CustomKeyEvent customKeyEvent) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(12).buildProtobuf(customKeyEvent));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m650n(com.ucar.databus.proto.UCarProto.Disconnect disconnect) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(23).buildProtobuf(disconnect));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m651o(com.ucar.databus.proto.UCarProto.GetPortRequest getPortRequest) {
        return m624P().sourceDevice(com.ucarhu.demo.protocol.ProtocolMessageRegistry.getLocalDevice()).methodId(2).buildProtobuf(getPortRequest);
    }

    public static com.ucarhu.demo.protocol.UCarMessage m652p(com.ucar.databus.proto.UCarProto.GetPortResponse getPortResponse) {
        return m624P().sourceDevice(com.ucarhu.demo.protocol.ProtocolMessageRegistry.getLocalDevice()).methodId(3).buildProtobuf(getPortResponse);
    }

    public static com.ucarhu.demo.protocol.UCarMessage m653q(com.ucar.databus.proto.UCarProto.GetUCarConfigRequest getUCarConfigRequest) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).messageType(com.ucarhu.demo.protocol.MessageType.REQ).methodId(24).buildProtobuf(getUCarConfigRequest));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m654r(com.ucar.databus.proto.UCarProto.GetUCarConfigResponse getUCarConfigResponse, int i) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).messageType(com.ucarhu.demo.protocol.MessageType.RES).methodId(25).buildProtobuf(getUCarConfigResponse).updateHeaderLength(i));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m655s(com.ucar.databus.proto.UCarProto.NotifyAddCamera notifyAddCamera) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(18).buildProtobuf(notifyAddCamera));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m656t(com.ucar.databus.proto.UCarProto.NotifyAudioPlayerState notifyAudioPlayerState) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(8).buildProtobuf(notifyAudioPlayerState));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m657u(com.ucar.databus.proto.UCarProto.NotifyCallHungUp notifyCallHungUp) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(14).buildProtobuf(notifyCallHungUp));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m658v(com.ucar.databus.proto.UCarProto.NotifyCameraStateChanged notifyCameraStateChanged) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.CAR).methodId(21).buildProtobuf(notifyCameraStateChanged));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m659w(com.ucar.databus.proto.UCarProto.NotifyMicrophoneState notifyMicrophoneState) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(6).buildProtobuf(notifyMicrophoneState));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m660x(com.ucar.databus.proto.UCarProto.NotifyMirrorState notifyMirrorState) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(7).buildProtobuf(notifyMirrorState));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m661y(com.ucar.databus.proto.UCarProto.NotifyMusicInfo notifyMusicInfo) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(10).buildProtobuf(notifyMusicInfo));
    }

    public static com.ucarhu.demo.protocol.UCarMessage m662z(com.ucar.databus.proto.UCarProto.NotifyNavigationInfo notifyNavigationInfo) {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.requireLocalDeviceMessage(m624P().sourceDevice(com.ucarhu.demo.protocol.SourceDevice.PHONE).methodId(11).buildProtobuf(notifyNavigationInfo));
    }
}
