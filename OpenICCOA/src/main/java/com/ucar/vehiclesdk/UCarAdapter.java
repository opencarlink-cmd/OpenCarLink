package com.ucar.vehiclesdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import androidx.annotation.NonNull;
import com.ucar.vehiclesdk.UCarCommon;
import com.ucar.vehiclesdk.UCarConnectState;
import com.ucar.vehiclesdk.connect.UCarConnectProxy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.bluetooth.CarBluetoothMacRecord;
import com.ucarhu.demo.sharelink.auth.ServerKeyNegotiator;
import com.ucarhu.demo.protocol.ProtocolConfig;
import com.ucarhu.demo.protocol.UCarMessage;
import com.ucarhu.demo.protocol.SourceDevice;
import com.ucarhu.demo.protocol.logging.AndroidProtocolLogger;
import com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer;
import com.ucarhu.demo.vehicle.audio.UCarAudioManager;
import com.ucarhu.demo.vehicle.camera.VehicleCameraManager;
import com.ucarhu.demo.vehicle.cast.NativeCastManager;
import com.ucarhu.demo.vehicle.cast.CastEventListener;
import com.ucarhu.demo.vehicle.connection.VehicleConnectionCallback;
import com.ucarhu.demo.vehicle.uibc.VehicleControlManager;
import com.ucarhu.demo.vehicle.uibc.UibcEventManager;
import com.ucarhu.demo.vehicle.sensor.CarCertificateManager;
import com.ucarhu.demo.vehicle.sensor.VehicleSensorManager;
import com.ucarhu.demo.vehicle.sdk.RandomTouchTestThread;
import com.ucarhu.demo.vehicle.sdk.TestModeProperties;
import com.ucarhu.demo.vehicle.sdk.VehicleSdkDebugServer;

public class UCarAdapter {

    private static final String f9474P = "UCarAdapter";

    private static final int f9475Q = 500;

    private static final int f9476R = 500;

    private static final int f9477S = 200;

    public static final int f9478T = 0;

    public static final int f9479U = 10;

    public static final int f9480V = 20;

    public static final int f9481W = 30;

    public static final int f9482X = 40;

    public static final int f9483Y = 50;

    public static final int f9484Z = 60;

    public static final int f9485a0 = 70;

    public static final int f9486b0 = 80;

    public static final int f9487c0 = 90;

    public static final int f9488d0 = 100;

    private static final Map<Integer, Integer> f9489e0 = new ProgressPercentMap();

    private static volatile UCarAdapter f9490f0;

    private int f9491A;

    private int f9492B;

    private String f9500J;

    private ConnectionHeartbeatTimer f9501K;

    private HandlerThread f9502L;

    private Handler f9503M;

    private RandomTouchTestThread f9504N;

    private VehicleSdkDebugServer f9505O;

    private UCarConfig f9506a;

    private Handler f9509d;

    private HandlerThread f9510e;

    private Handler f9511f;

    private boolean f9515j;

    private String f9516k;

    private Context f9517l;

    private UCarConnectProxy f9521p;

    private VehicleConnectionCallback f9522q;

    private NativeCastManager f9523r;

    private VehicleControlManager f9524s;

    private UCarAudioManager f9525t;

    private UibcEventManager f9526u;

    private VehicleSensorManager f9527v;

    private CarCertificateManager f9528w;

    private VehicleCameraManager f9529x;

    private int f9530y;

    private int f9531z;

    private final ReentrantLock f9507b = new ReentrantLock();

    private String f9508c = "-1";

    private boolean f9512g = false;

    private boolean f9513h = false;

    private volatile boolean f9514i = true;

    private final List<ICarConnectListener> f9518m = new ArrayList();

    private final List<IPhoneDataListener> f9519n = new ArrayList();

    private ICarInitCallback f9520o = null;

    private final byte[] f9493C = new byte[2];

    private final byte[] f9494D = new byte[6];

    private int f9495E = 0;

    private boolean f9496F = false;

    private boolean f9497G = false;

    private boolean f9498H = false;

    private AtomicBoolean f9499I = new AtomicBoolean(false);

    public static class ProgressPercentMap extends HashMap {
        public ProgressPercentMap() {
            put(0, 0);
            put(1, 10);
            put(2, 20);
            put(3, 20);
            put(4, 30);
            put(5, 40);
            put(6, 40);
            put(7, 50);
            put(8, 60);
            put(9, 70);
            put(10, 80);
            put(11, 90);
            put(12, 100);
        }
    }

    public class AudioFocusForwarder implements UCarAudioManager.c {
        public AudioFocusForwarder() {
        }

        @Override
        public void onRequestAudioFocus(UCarCommon.AudioType audioType, int i) {
            for (IPhoneDataListener iPhoneDataListener : UCarAdapter.this.f9519n) {
                if (iPhoneDataListener != null) {
                    iPhoneDataListener.onRequestAudioFocus(audioType, i);
                }
            }
        }
    }

    public class ConnectionTimeoutCallback implements ConnectionHeartbeatTimer.TimeoutListener {
        public ConnectionTimeoutCallback() {
        }

        public void handleConnectTimeoutOnMainThread() {
            String str;
            if (UCarAdapter.this.f9521p != null) {
                EasyLogger.info(UCarAdapter.f9474P, "time out, try stop advertise");
                UCarAdapter.this.f9521p.m10137i();
                UCarAdapter.this.f9521p.m10131b();
                UCarAdapter.this.f9507b.lock();
                UCarAdapter uCarAdapter = UCarAdapter.this;
                MDevice mDeviceM9945a = uCarAdapter.m9945a(uCarAdapter.f9508c);
                if (mDeviceM9945a.isWireless()) {
                    if (UCarAdapter.this.f9495E >= 20) {
                        str = "time out in cast phase (wireless), try disconnect link";
                        EasyLogger.info(UCarAdapter.f9474P, str);
                        UCarAdapter.this.f9521p.m10134c();
                    }
                } else if (mDeviceM9945a.isWired() && UCarAdapter.this.f9495E >= 50) {
                    str = "time out in cast phase (usb), try disconnect link";
                    EasyLogger.info(UCarAdapter.f9474P, str);
                    UCarAdapter.this.f9521p.m10134c();
                }
                UCarAdapter.this.f9507b.unlock();
            }
        }

        public void handleHeartbeatTimeoutOnMainThread() {
            if (UCarAdapter.this.f9521p != null) {
                EasyLogger.info(UCarAdapter.f9474P, "heart beat time out, try disconnect link");
                UCarAdapter.this.f9521p.m10134c();
            }
        }

        @Override
        public void onHeartbeatTimeout() {
            UCarAdapter.this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    ConnectionTimeoutCallback.this.handleHeartbeatTimeoutOnMainThread();
                }
            });
        }

        @Override
        public void onConnectTimeout() {
            EasyLogger.info(UCarAdapter.f9474P, "connect time out");
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9969a(uCarAdapter.f9508c, UCarConnectState.ErrorCode.ERROR_CONNECT_TIMEOUT);
            UCarAdapter.this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    ConnectionTimeoutCallback.this.handleConnectTimeoutOnMainThread();
                }
            });
        }
    }

    public class HeartbeatErrorCallback implements VehicleControlManager.p {
        public HeartbeatErrorCallback() {
        }

        public void m10057b() {
            if (UCarAdapter.this.f9521p != null) {
                EasyLogger.debug(UCarAdapter.f9474P, "send heart beat error, try disconnect link");
                UCarAdapter.this.f9521p.m10134c();
            }
        }

        @Override
        public void onPhoneControlReady() {
            EasyLogger.error(UCarAdapter.f9474P, "onSendHeartBeatError");
            if (UCarAdapter.this.f9511f != null) {
                UCarAdapter.this.f9511f.post(new Runnable() {
                    @Override
                    public final void run() {
                        HeartbeatErrorCallback.this.m10057b();
                    }
                });
            }
        }
    }

    public class ControlEventCallback implements VehicleControlManager.o {
        public ControlEventCallback() {
        }

        public void m10059d() {
            UCarAdapter.this.f9521p.m10134c();
        }

        public void m10060e() {
            if (!UCarAdapter.this.m10022i()) {
                UCarAdapter.this.m10045s();
            }
            UCarAdapter.this.f9501K.cancelHeartbeatTimer();
            UCarAdapter.this.f9501K.scheduleHeartbeatTimeout();
        }

        @Override
        public void onOpenSystemSettings() {
            UCarAdapter.this.f9514i = true;
            UCarAdapter.this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    ControlEventCallback.this.m10059d();
                }
            });
        }

        @Override
        public void onHeartbeat(long j) {
            EasyLogger.info(UCarAdapter.f9474P, "onHeartbeat, timeStamp = " + j);
            UCarAdapter.this.f9503M.post(new Runnable() {
                @Override
                public final void run() {
                    ControlEventCallback.this.m10060e();
                }
            });
        }

        @Override
        public void onCustomControlMessage(UCarMessage c0102w) {
            UCarAdapter.this.f9524s.m917r(UCarAdapter.this.f9506a, c0102w);
        }

        @Override
        public void onAudioPlayerControl(UCarAudioManager.b bVar) {
            UCarAdapter.this.m9952a(bVar);
        }

        @Override
        public void onAudioPlayerStateChanged(UCarCommon.AudioType audioType, UCarCommon.AudioFormat audioFormat, UCarAudioManager.d dVar) {
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9972a(uCarAdapter.f9508c, audioType, audioFormat, dVar);
        }

        @Override
        public void onBluetoothMacInfo(UCarCommon.BluetoothMacInfo bluetoothMacInfo) {
            UCarAdapter.this.m9968a(bluetoothMacInfo);
        }

        @Override
        public void onCameraAction(UCarCommon.CameraAction cameraAction, UCarCommon.CameraActionArgs cameraActionArgs) {
            if (UCarAdapter.this.f9529x != null && !UCarAdapter.this.f9529x.m813i() && cameraAction == UCarCommon.CameraAction.CAMERA_OPEN) {
                EasyLogger.info(UCarAdapter.f9474P, "CameraManager.start with address:" + UCarAdapter.this.f9500J);
                UCarAdapter.this.f9529x.startCameraChannel(UCarAdapter.this.f9500J);
                UCarAdapter.this.f9499I.set(true);
            }
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9973a(uCarAdapter.f9508c, cameraAction, cameraActionArgs);
        }

        @Override
        public void onMusicInfo(UCarCommon.MusicInfo musicInfo) {
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9991b(uCarAdapter.f9508c, musicInfo);
        }

        @Override
        public void onNavigationInfo(UCarCommon.NavigationInfo navigationInfo) {
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9992b(uCarAdapter.f9508c, navigationInfo);
        }

        @Override
        public void onPhoneStateInfo(UCarCommon.PhoneStateInfo phoneStateInfo) {
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9993b(uCarAdapter.f9508c, phoneStateInfo);
        }

        @Override
        public void onMicRecordRequest(boolean z, UCarCommon.AudioFormat audioFormat, boolean z2) {
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9971a(uCarAdapter.f9508c, audioFormat, z, z2);
        }

        @Override
        public void onHomePressed() {
            EasyLogger.info(UCarAdapter.f9474P, "stop mirror, not used yet.");
        }

        @Override
        public void onBackPressed() {
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9990b(uCarAdapter.f9508c, 6, -1);
        }
    }

    public class CastCallback implements CastEventListener {
        public CastCallback() {
        }

        public void m10063a() {
            if (UCarAdapter.this.f9521p != null) {
                EasyLogger.debug(UCarAdapter.f9474P, "onWfdSinkStopped");
                if (UCarAdapter.this.m10019h()) {
                    UCarAdapter.this.f9521p.m10134c();
                }
                UCarAdapter uCarAdapter = UCarAdapter.this;
                uCarAdapter.m9990b(uCarAdapter.f9508c, 5, -1);
            }
        }

        public void m10064a(String str, boolean z, int i, int i2) {
            UCarAdapter.this.f9516k = str;
            if (UCarAdapter.this.f9526u != null) {
                UCarAdapter.this.f9526u.setSessionId(str);
                UCarAdapter.this.f9526u.configureEncryption(z);
                UCarAdapter.this.m9950a(i, i2);
            }
            UCarAdapter.this.m9985b(11);
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9990b(uCarAdapter.f9508c, 4, -1);
        }

        @Override
        public void onAudioFormatChanged(UCarCommon.AudioFormat audioFormat) {
            UCarAdapter uCarAdapter = UCarAdapter.this;
            uCarAdapter.m9972a(uCarAdapter.f9508c, UCarCommon.AudioType.STREAM_CAST_MUSIC, audioFormat, UCarAudioManager.d.START_PLAYER);
        }

        @Override
        public void onCastStopped(String str) {
            UCarAdapter.this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    CastCallback.this.m10063a();
                }
            });
        }

        @Override
        public void onVideoSizeChanged(final String str, final int i, final int i2, final boolean z) {
            UCarAdapter.this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    CastCallback.this.m10064a(str, z, i, i2);
                }
            });
        }
    }

    public class VehicleConnectionForwarder implements VehicleConnectionCallback {
        public VehicleConnectionForwarder() {
        }

        public void m10067b(String str, String str2) {
            for (ICarConnectListener iCarConnectListener : UCarAdapter.this.f9518m) {
                if (iCarConnectListener != null) {
                    iCarConnectListener.onPinCode(str, str2);
                }
            }
        }

        public void m10068c() {
            UCarAdapter.this.f9501K.cancelConnectTimer();
            UCarAdapter.this.f9501K.scheduleConnectTimeout();
        }

        public void m10069c(String str, int i) {
            UCarAdapter.this.f9503M.post(new Runnable() {
                @Override
                public final void run() {
                    VehicleConnectionForwarder.this.m10071d();
                }
            });
            if (UCarAdapter.this.m10019h()) {
                return;
            }
            UCarAdapter.this.m9969a(str, i);
        }

        public void m10070c(String str, String str2) {
            EasyLogger.info(UCarAdapter.f9474P, "onReceivedClientAddress: device id = " + str + ", ip = " + str2);
            UCarAdapter.this.f9508c = str;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                EasyLogger.error(UCarAdapter.f9474P, "client address is invalid, skip native cast startup");
                UCarAdapter.this.m9969a(str, UCarConnectState.ErrorCode.ERROR_CONNECT_INNER_FAILED);
                return;
            }
            if (UCarAdapter.this.f9523r == null) {
                EasyLogger.error(UCarAdapter.f9474P, "native cast manager is null, skip native cast startup");
                UCarAdapter.this.m9969a(str, UCarConnectState.ErrorCode.ERROR_CONNECT_INNER_FAILED);
                return;
            }
            UCarAdapter.this.m10009d(true);
            UCarAdapter.this.m9988b(str2);
            if (!UCarAdapter.this.f9506a.isDataTransMode()) {
                UCarAdapter.this.f9523r.m850d(UCarAdapter.this.m9945a(str));
                UCarAdapter.this.f9523r.startNativeCast(UCarAdapter.this.f9530y, UCarAdapter.this.f9531z, UCarAdapter.this.f9491A, UCarAdapter.this.f9492B);
                UCarAdapter.this.m10014f(true);
            }
            UCarAdapter.this.m9984b();
            UCarAdapter.this.stopAdvertise();
        }

        public void m10071d() {
            UCarAdapter.this.f9501K.cancelConnectTimer();
        }

        public void m10072e() {
            UCarAdapter.this.m9999c();
        }

        public void m10073f() {
            if (UCarAdapter.this.f9520o != null) {
                EasyLogger.debug(UCarAdapter.f9474P, "UCarAdapter initialized");
                UCarAdapter.this.f9520o.onInitSuccess();
            }
        }

        public void m10074g() {
            UCarAdapter.this.m9999c();
        }

        @Override
        public void onConnectionClosed() {
            EasyLogger.info(UCarAdapter.f9474P, "onShareLinkServiceDisconnected");
            UCarAdapter.this.f9513h = false;
            UCarAdapter.this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    VehicleConnectionForwarder.this.m10074g();
                }
            });
        }

        @Override
        public void onConnectionStarted(String str) {
            EasyLogger.info(UCarAdapter.f9474P, "onDisconnected from share link service");
            if (UCarAdapter.this.f9520o != null) {
                UCarAdapter.this.f9511f.post(new Runnable() {
                    @Override
                    public final void run() {
                        VehicleConnectionForwarder.this.m10072e();
                    }
                });
            } else {
                EasyLogger.debug(UCarAdapter.f9474P, "mCarInitCallback is null, deInit method has called");
            }
        }

        @Override
        public void onConnectionFailed(String str, int i) {
            UCarAdapter.this.m9985b(i);
        }

        @Override
        public void onClientAddressReceived(final String str, final String str2) {
            EasyLogger.info(UCarAdapter.f9474P, "logical connection succeeded, device id = " + str);
            UCarAdapter.this.f9508c = str;
            UCarAdapter.this.f9514i = false;
            if (UCarAdapter.this.f9523r != null) {
                UCarAdapter.this.f9511f.post(new Runnable() {
                    @Override
                    public final void run() {
                        VehicleConnectionForwarder.this.m10070c(str, str2);
                    }
                });
            }
        }

        @Override
        public void onConnectionRejected() {
            EasyLogger.info(UCarAdapter.f9474P, "onShareLinkServiceConnected");
            UCarAdapter.this.f9507b.lock();
            if (UCarAdapter.this.f9512g) {
                UCarAdapter.this.f9513h = true;
                UCarAdapter.this.f9509d.post(new Runnable() {
                    @Override
                    public final void run() {
                        VehicleConnectionForwarder.this.m10073f();
                    }
                });
            }
            UCarAdapter.this.f9507b.unlock();
        }

        @Override
        public void onConnecting(String str) {
            UCarAdapter.this.f9503M.post(new Runnable() {
                @Override
                public final void run() {
                    VehicleConnectionForwarder.this.m10068c();
                }
            });
        }

        @Override
        public void onConnectionProgress(final String str, final int i) {
            EasyLogger.warn(UCarAdapter.f9474P, "onConnectFailed, errorCode:" + i);
            UCarAdapter.this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    VehicleConnectionForwarder.this.m10069c(str, i);
                }
            });
        }

        @Override
        public void onDeviceDisconnected(String str) {
            UCarAdapter.this.f9508c = str;
        }

        @Override
        public void onConnected() {
            EasyLogger.info(UCarAdapter.f9474P, "physical connection succeeded");
        }

        @Override
        public void onPinCode(final String str, final String str2) {
            UCarAdapter.this.f9509d.post(new Runnable() {
                @Override
                public final void run() {
                    VehicleConnectionForwarder.this.m10067b(str, str2);
                }
            });
        }

        @Override
        public void onUserInterventionNeeded(boolean z) {
            if (!z) {
                UCarAdapter.this.m9949a(0);
            } else {
                UCarAdapter uCarAdapter = UCarAdapter.this;
                uCarAdapter.m9990b(uCarAdapter.f9508c, 3, UCarConnectState.ErrorCode.ERROR_USER_INTERVENTION_TIMEOUT);
            }
        }
    }

    static {
        EasyLogger.setTagPrefix("UCar_");
        ProtocolConfig.setLogger(new AndroidProtocolLogger());
        ProtocolConfig.setLocalDevice(SourceDevice.CAR);
    }

    private UCarAdapter() {
    }

    public MDevice m9945a(String str) {
        UCarConnectProxy uCarConnectProxy = this.f9521p;
        return uCarConnectProxy != null ? uCarConnectProxy.m10127a(str) : new MDevice();
    }

    private void m9948a() {
        RandomTouchTestThread c0142c = this.f9504N;
        if (c0142c != null) {
            c0142c.interrupt();
        }
        VehicleSdkDebugServer c0144e = this.f9505O;
        if (c0144e != null) {
            c0144e.closeServer();
        }
    }

    public void m9949a(int i) {
        EasyLogger.info(f9474P, "setConnectPhase to: " + i);
        this.f9507b.lock();
        this.f9495E = i;
        this.f9507b.unlock();
        m10008d(this.f9508c, i);
    }

    public void m9950a(int i, int i2) {
        this.f9526u.setSourceWidth(i);
        this.f9526u.setSourceHeight(i2);
    }

    public void m9951a(int i, int i2, Surface surface) {
        this.f9526u.setDisplayWidth(i);
        this.f9526u.setDisplayHeight(i2);
        if (this.f9523r.startVideoRender(surface, i, i2, this.f9506a.isSupportLowLatencyDecodingMode())) {
            return;
        }
        m9969a(this.f9508c, UCarConnectState.ErrorCode.ERROR_START_CAST_FAILED);
    }

    public void m9952a(final UCarAudioManager.b bVar) {
        this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9986b(bVar);
            }
        });
    }

    public void m9953a(UCarAudioManager.d dVar, UCarCommon.AudioType audioType, UCarCommon.AudioFormat audioFormat) {
        UCarCommon.AudioAttributes audioAttributesByType;
        UCarCommon.AudioAttributes audioAttributes = null;
        if (dVar == UCarAudioManager.d.START_PLAYER) {
            for (IPhoneDataListener iPhoneDataListener : this.f9519n) {
                if (iPhoneDataListener != null && (audioAttributesByType = iPhoneDataListener.getAudioAttributesByType(audioType)) != null) {
                    audioAttributes = audioAttributesByType;
                }
            }
        }
        this.f9525t.handleAudioPlayerStateChanged(audioType, audioFormat, dVar, audioAttributes);
    }

    public void m9954a(ICarInitCallback iCarInitCallback, UCarConfig uCarConfig, Context context) {
        boolean zM9980a;
        EasyLogger.info(f9474P, "init, sdk version : v1.2.10-202303291849-c5620286");
        this.f9520o = iCarInitCallback;
        byte[] carBrMac = uCarConfig.getCarBrMac();
        if (carBrMac == null || carBrMac.length != 6) {
            EasyLogger.error(f9474P, "carId is invalid, the length must be 6 size!");
            this.f9520o.onInitFailed(UCarConnectState.ErrorCode.ERROR_INVALID_PARAMETER);
        }
        this.f9506a = uCarConfig;
        System.arraycopy(carBrMac, 0, this.f9494D, 0, 6);
        this.f9531z = this.f9506a.getVideoDisplayHeight();
        this.f9530y = this.f9506a.getVideoDisplayWidth();
        this.f9491A = this.f9506a.getDpi();
        this.f9492B = this.f9506a.getFps();
        byte[] carCustomField = uCarConfig.getCarCustomField();
        if (carCustomField != null && carCustomField.length == 2) {
            System.arraycopy(carCustomField, 0, this.f9493C, 0, 2);
        }
        if (this.f9517l == null) {
            EasyLogger.info(f9474P, "init adapter");
            this.f9507b.lock();
            zM9980a = m9980a(context);
            this.f9507b.unlock();
        } else {
            zM9980a = true;
        }
        m9950a(this.f9530y, this.f9531z);
        this.f9512g = true;
        if (!zM9980a) {
            this.f9520o.onInitFailed(UCarConnectState.ErrorCode.ERROR_INIT_FAILED);
        } else if (this.f9513h) {
            this.f9520o.onInitSuccess();
        }
    }

    public void m9967a(UCarCommon.AudioFormat audioFormat, boolean z, boolean z2) {
        this.f9525t.handleMicRecordRequest(audioFormat, z, z2);
    }

    public void m9968a(UCarCommon.BluetoothMacInfo bluetoothMacInfo) {
        CarBluetoothMacRecord c0012f = new CarBluetoothMacRecord(bluetoothMacInfo.getBluetoothMac(), this.f9517l);
        if (bluetoothMacInfo.getOpType() == UCarCommon.OPType.OP_ADD) {
            c0012f.save();
        } else if (bluetoothMacInfo.getOpType() == UCarCommon.OPType.OP_DELETE) {
            c0012f.delete();
        }
    }

    public void m9969a(String str, int i) {
        if (this.f9495E > 0) {
            m9985b(0);
        }
        m10002c(str, i);
    }

    public void m9970a(String str, int i, int i2) {
        for (ICarConnectListener iCarConnectListener : this.f9518m) {
            if (iCarConnectListener != null) {
                iCarConnectListener.onConnectStateChanged(str, i, i2);
            }
        }
    }

    public void m9971a(String str, final UCarCommon.AudioFormat audioFormat, final boolean z, final boolean z2) {
        this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9967a(audioFormat, z, z2);
            }
        });
    }

    public void m9972a(String str, final UCarCommon.AudioType audioType, final UCarCommon.AudioFormat audioFormat, final UCarAudioManager.d dVar) {
        this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9953a(dVar, audioType, audioFormat);
            }
        });
    }

    public void m9973a(String str, UCarCommon.CameraAction cameraAction, UCarCommon.CameraActionArgs cameraActionArgs) {
        VehicleCameraManager c0113b = this.f9529x;
        if (c0113b != null) {
            c0113b.m812g(str, cameraAction, cameraActionArgs);
        }
    }

    public void m9974a(String str, UCarCommon.MusicInfo musicInfo) {
        for (IPhoneDataListener iPhoneDataListener : this.f9519n) {
            if (iPhoneDataListener != null) {
                iPhoneDataListener.onMusicInfoReceived(str, musicInfo);
            }
        }
    }

    public void m9975a(String str, UCarCommon.NavigationInfo navigationInfo) {
        for (IPhoneDataListener iPhoneDataListener : this.f9519n) {
            if (iPhoneDataListener != null) {
                iPhoneDataListener.onNavigationInfoReceived(str, navigationInfo);
            }
        }
    }

    public void m9976a(String str, UCarCommon.PhoneStateInfo phoneStateInfo) {
        for (IPhoneDataListener iPhoneDataListener : this.f9519n) {
            if (iPhoneDataListener != null) {
                iPhoneDataListener.onPhoneStateInfoReceived(str, phoneStateInfo);
            }
        }
    }

    public void m9977a(String str, boolean z) {
        if (this.f9495E >= 90) {
            this.f9521p.m10131b();
        }
        m9990b(str, 1, z ? 11 : -1);
        VehicleCameraManager c0113b = this.f9529x;
        if (c0113b != null) {
            c0113b.m809d(1);
        }
    }

    public void m9978a(CountDownLatch countDownLatch) {
        this.f9512g = false;
        this.f9520o = null;
        this.f9514i = true;
        stopAdvertise();
        UCarConnectProxy uCarConnectProxy = this.f9521p;
        if (uCarConnectProxy != null) {
            uCarConnectProxy.m10129a(false);
            this.f9521p.m10134c();
        }
        m9999c();
        this.f9519n.clear();
        this.f9518m.clear();
        VehicleCameraManager c0113b = this.f9529x;
        if (c0113b != null) {
            c0113b.m816l(null);
        }
        countDownLatch.countDown();
    }

    public void m9979a(boolean z) {
        this.f9521p.m10129a(z);
    }

    private boolean m9980a(@NonNull Context context) {
        this.f9515j = false;
        this.f9517l = context;
        m10006d();
        UCarConfig uCarConfig = this.f9506a;
        if (uCarConfig != null && uCarConfig.isSupportCamera()) {
            this.f9529x = new VehicleCameraManager(this.f9517l);
        }
        this.f9526u = new UibcEventManager(this.f9517l);
        this.f9527v = new VehicleSensorManager(this.f9517l);
        this.f9528w = new CarCertificateManager(this.f9517l, this.f9506a.getCcdFilePath());
        m10017g();
        m10011e();
        this.f9512g = true;
        return m10015f();
    }

    public void m9984b() {
        m9985b(10);
        this.f9503M.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m10025k();
            }
        });
        if (this.f9524s != null) {
            EasyLogger.info(f9474P, "onCastVideoInitialized(), startSendHeartBeatThread");
            this.f9524s.m920y(new HeartbeatErrorCallback());
        }
        int connectType = getConnectType(this.f9508c);
        UCarAudioManager.b bVar = new UCarAudioManager.b();
        bVar.setAudioType(UCarCommon.AudioType.STREAM_CAST_MUSIC);
        if (connectType == 2) {
            bVar.setBufferingCount(100);
            bVar.setSpeedAdjustStep(10);
        } else {
            bVar.setBufferingCount(UCarAudioManager.b.f659e);
            bVar.setSpeedAdjustStep(100);
        }
        m9952a(bVar);
    }

    public void m9985b(int i) {
        Integer num = f9489e0.get(Integer.valueOf(i));
        if (num != null) {
            m9949a(num.intValue());
        }
    }

    public void m9986b(UCarAudioManager.b bVar) {
        this.f9525t.applyAudioPlayerControl(bVar);
    }

    public void m9988b(String str) {
        this.f9500J = str;
        if (this.f9524s == null) {
            EasyLogger.debug(f9474P, "onControlChannelStart: peerAddress = " + str);
            VehicleControlManager c0123d = new VehicleControlManager();
            this.f9524s = c0123d;
            c0123d.m910h(new ControlEventCallback());
        }
        VehicleControlManager c0123d2 = this.f9524s;
        if (c0123d2 != null) {
            c0123d2.m911k(str);
        }
        VehicleSensorManager c0128d = this.f9527v;
        if (c0128d != null) {
            c0128d.startSensorChannel(str);
            m10012e(true);
        }
        CarCertificateManager c0126b = this.f9528w;
        if (c0126b != null) {
            c0126b.startCertificateChannel(str);
        }
        UCarAudioManager c0111c = this.f9525t;
        if (c0111c != null) {
            c0111c.startAudioChannel(str);
            m10003c(true);
        }
    }

    public void m9989b(String str, int i) {
        for (ICarConnectListener iCarConnectListener : this.f9518m) {
            if (iCarConnectListener != null) {
                iCarConnectListener.onConnectingProgress(str, getInstance().m9945a(str) != null ? getInstance().m9945a(str).getModel() : "", i);
            }
        }
    }

    public void m9990b(final String str, final int i, final int i2) {
        this.f9509d.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9970a(str, i, i2);
            }
        });
    }

    public void m9991b(final String str, final UCarCommon.MusicInfo musicInfo) {
        this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9974a(str, musicInfo);
            }
        });
    }

    public void m9992b(final String str, final UCarCommon.NavigationInfo navigationInfo) {
        if (navigationInfo == null) {
            return;
        }
        this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9975a(str, navigationInfo);
            }
        });
    }

    public void m9993b(final String str, final UCarCommon.PhoneStateInfo phoneStateInfo) {
        this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9976a(str, phoneStateInfo);
            }
        });
    }

    private void m9994b(final String str, final boolean z) {
        EasyLogger.warn(f9474P, "notifyDisconnected, byUser : " + z);
        this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9977a(str, z);
            }
        });
    }

    public void m9995b(CountDownLatch countDownLatch) {
        this.f9523r.stopNativeCast(this.f9516k);
        countDownLatch.countDown();
    }

    public void m9996b(boolean z) {
        this.f9521p.m10130a(z, this.f9506a);
    }

    public void m9999c() {
        EasyLogger.info(f9474P, "handleDisconnectRequest, isConnected :" + m10019h());
        if (this.f9495E > 0) {
            m9985b(0);
        }
        if (m10019h()) {
            m10009d(false);
            m10042r();
            m9994b(this.f9508c, this.f9514i);
        }
    }

    private void m10002c(String str, int i) {
        EasyLogger.warn(f9474P, "connection failed, errorCode" + i);
        m9990b(str, 3, i);
    }

    private void m10003c(boolean z) {
        this.f9507b.lock();
        this.f9496F = z;
        this.f9507b.unlock();
    }

    private void m10006d() {
        this.f9525t = new UCarAudioManager(this.f9517l, this.f9506a.isSupportStereoRecord(), new AudioFocusForwarder());
    }

    private void m10008d(final String str, final int i) {
        this.f9509d.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9989b(str, i);
            }
        });
    }

    public void m10009d(boolean z) {
        EasyLogger.info(f9474P, "setConnectState to: " + z);
        this.f9507b.lock();
        this.f9515j = z;
        this.f9507b.unlock();
    }

    public static void deletePhoneById(@NonNull Context context, @NonNull String str) {
        EasyLogger.info(f9474P, "deletePhoneById, deviceId:" + str);
        ServerKeyNegotiator.deletePhoneById(context, str);
    }

    private void m10011e() {
        this.f9523r = new NativeCastManager(this.f9525t, this.f9492B, new CastCallback());
    }

    private void m10012e(boolean z) {
        this.f9507b.lock();
        this.f9498H = z;
        this.f9507b.unlock();
    }

    public static boolean existedCarBluetoothMac(String str, Context context) {
        return CarBluetoothMacRecord.containsMac(str, context);
    }

    public void m10014f(boolean z) {
        this.f9507b.lock();
        this.f9497G = z;
        this.f9507b.unlock();
    }

    private boolean m10015f() {
        this.f9521p = new UCarConnectProxy(this.f9517l, this.f9506a);
        VehicleConnectionForwarder c1128g = new VehicleConnectionForwarder();
        this.f9522q = c1128g;
        this.f9521p.m10128a(c1128g);
        return this.f9521p.m10133b(this.f9494D, this.f9493C);
    }

    private void m10017g() {
        ConnectionHeartbeatTimer c0108a = new ConnectionHeartbeatTimer();
        this.f9501K = c0108a;
        c0108a.setTimeoutListener(new ConnectionTimeoutCallback());
    }

    private boolean m10018g(final boolean z) {
        Handler handler;
        if (this.f9521p == null || (handler = this.f9511f) == null) {
            return false;
        }
        return handler.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9996b(z);
            }
        });
    }

    public static UCarAdapter getInstance() {
        if (f9490f0 == null) {
            synchronized (UCarAdapter.class) {
                if (f9490f0 == null) {
                    f9490f0 = new UCarAdapter();
                }
            }
        }
        return f9490f0;
    }

    public boolean m10019h() {
        return this.f9515j;
    }

    public boolean m10022i() {
        return this.f9495E == 100;
    }

    public void m10024j() {
        UCarConnectProxy uCarConnectProxy = this.f9521p;
        if (uCarConnectProxy != null) {
            uCarConnectProxy.m10134c();
        }
    }

    public void m10025k() {
        this.f9501K.cancelHeartbeatTimer();
        this.f9501K.scheduleHeartbeatTimeout();
    }

    public void m10028l() {
        this.f9524s.m906G();
    }

    public void m10030m() {
        this.f9521p.m10137i();
    }

    public void m10033n() {
        this.f9501K.cancelHeartbeatTimer();
        this.f9501K.cancelConnectTimer();
    }

    private void m10036o() {
        EasyLogger.info(f9474P, "Test : start send touch event.");
        if (this.f9504N == null) {
            this.f9504N = new RandomTouchTestThread(this.f9530y, this.f9531z);
        }
        this.f9504N.start();
    }

    private void m10039p() {
        EasyLogger.info(f9474P, "start UCarAdapter api Test");
        if (this.f9505O == null) {
            this.f9505O = new VehicleSdkDebugServer(this.f9517l);
        }
        if (this.f9505O.isAlive()) {
            return;
        }
        this.f9505O.start();
    }

    private boolean m10040q() {
        if (this.f9509d == null) {
            this.f9509d = new Handler(Looper.getMainLooper());
        }
        if (this.f9510e == null) {
            EasyLogger.info(f9474P, "start worker thread");
            HandlerThread handlerThread = new HandlerThread("WorkerThread");
            this.f9510e = handlerThread;
            handlerThread.start();
            Looper looper = this.f9510e.getLooper();
            if (looper == null) {
                EasyLogger.error(f9474P, "failed to get valid worker thread looper!");
                this.f9511f = null;
                return false;
            }
            this.f9511f = new Handler(looper);
        }
        if (this.f9502L != null) {
            return true;
        }
        EasyLogger.info(f9474P, "start timer thread");
        HandlerThread handlerThread2 = new HandlerThread("UCarAdapterTimerThread");
        this.f9502L = handlerThread2;
        handlerThread2.start();
        Looper looper2 = this.f9502L.getLooper();
        if (looper2 != null) {
            this.f9503M = new Handler(looper2);
            return true;
        }
        EasyLogger.error(f9474P, "failed to get valid timer thread looper!");
        this.f9503M = null;
        return false;
    }

    private void m10042r() {
        boolean z;
        EasyLogger.debug(f9474P, "stopMiracastAndUCarChannel.");
        this.f9503M.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m10033n();
            }
        });
        this.f9507b.lock();
        try {
            try {
                UCarAudioManager c0111c = this.f9525t;
                if (c0111c != null && this.f9496F) {
                    this.f9496F = false;
                    c0111c.stopAudioChannel();
                }
                if (this.f9529x != null && this.f9499I.get()) {
                    this.f9499I.set(false);
                    this.f9529x.stopCameraChannel();
                }
                VehicleSensorManager c0128d = this.f9527v;
                if (c0128d != null && this.f9498H) {
                    this.f9498H = false;
                    c0128d.closeSensorChannel();
                }
                VehicleControlManager c0123d = this.f9524s;
                if (c0123d != null) {
                    c0123d.m908J();
                }
                CarCertificateManager c0126b = this.f9528w;
                if (c0126b != null) {
                    c0126b.stopCertificateChannel();
                }
                z = true;
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                if (this.f9523r == null || !this.f9497G) {
                    z = false;
                } else {
                    this.f9497G = false;
                    new Thread(new Runnable() {
                        @Override
                        public final void run() {
                            UCarAdapter.this.m9995b(countDownLatch);
                        }
                    }).start();
                    if (countDownLatch.await(500L, TimeUnit.MILLISECONDS)) {
                        EasyLogger.info(f9474P, "stop sink tasks have been done.");
                        z = false;
                    } else {
                        EasyLogger.error(f9474P, "stop sink not be fully executed, we need exit process.");
                    }
                }
            } catch (Exception e3) {
                EasyLogger.errorWithThrowable(f9474P, "stopMiracastAndUCarChannel Exception", e3);
                this.f9507b.unlock();
                z = false;
            }
            EasyLogger.info(f9474P, "stopMiracastAndUCarChannel done~");
            if (z) {
                EasyLogger.error(f9474P, "exit process while stop the sink failed");
                System.exit(0);
            }
        } finally {
            this.f9507b.unlock();
        }
    }

    public void m10045s() {
        this.f9507b.lock();
        // 手机心跳到达时说明控制通道已经可用；无线投屏的视频初始化可能先把阶段推进到 90。
        // 这种时序下仍然需要进入 100 并取消连接超时，否则会在画面已出来后被误判为 10006。
        if (!this.f9506a.isDataTransMode() ? this.f9495E < 100 : this.f9495E < 80) {
            m9985b(12);
            m9990b(this.f9508c, 2, -1);
            VehicleCameraManager c0113b = this.f9529x;
            if (c0113b != null) {
                c0113b.m809d(2);
            }
            this.f9501K.cancelConnectTimer();
            if (TestModeProperties.isTestActionEnabled()) {
                m10036o();
            }
            if (TestModeProperties.isTestModeEnabled()) {
                m10039p();
            }
        }
        this.f9507b.unlock();
    }

    public boolean abandonAudioFocus() {
        EasyLogger.info(f9474P, "abandonAudioFocus by user");
        return this.f9525t.abandonAudioFocus();
    }

    public void addCamera(UCarCommon.CameraInfo cameraInfo) {
        VehicleControlManager c0123d = this.f9524s;
        if (c0123d == null || this.f9529x == null) {
            return;
        }
        try {
            c0123d.m912m(VehicleCameraManager.m804a(cameraInfo));
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9474P, "addCamera failed", e2);
        }
    }

    public void allowGainAudioFocus() {
        EasyLogger.info(f9474P, "allowGainAudioFocus by user");
        this.f9525t.pauseCurrentAudio();
    }

    public boolean awakenVoiceAssistant(byte[] bArr, UCarCommon.AudioFormat audioFormat, String str) {
        VehicleControlManager c0123d = this.f9524s;
        if (c0123d != null) {
            return c0123d.m919t(bArr, audioFormat, str);
        }
        return false;
    }

    public void deInit() {
        EasyLogger.info(f9474P, "deInit start");
        if (this.f9511f == null) {
            EasyLogger.error(f9474P, "stop cast must be called after successful initialization");
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        if (this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9978a(countDownLatch);
            }
        })) {
            try {
                EasyLogger.info(f9474P, countDownLatch.await(500L, TimeUnit.MILLISECONDS) ? "deInit tasks have been done." : "deInit may not be fully executed.");
            } catch (InterruptedException e2) {
                EasyLogger.errorWithThrowable(f9474P, "catch InterruptedException", e2);
            }
        } else {
            EasyLogger.warn(f9474P, "add deInit tasks failed.");
        }
        m9948a();
    }

    public boolean disconnect() {
        boolean zM905E;
        EasyLogger.info(f9474P, "disconnect by user");
        if (this.f9524s != null) {
            this.f9514i = true;
            zM905E = this.f9524s.m905E();
        } else {
            zM905E = false;
        }
        if (zM905E) {
            this.f9511f.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    UCarAdapter.this.m10024j();
                }
            }, 200L);
        }
        EasyLogger.info(f9474P, "disconnect, result = " + zM905E);
        return zM905E;
    }

    public void enableUsbDeviceDetection(final boolean z) {
        Handler handler;
        EasyLogger.info(f9474P, "enableUsbDeviceDetection by user: " + z);
        if (this.f9521p == null || (handler = this.f9511f) == null) {
            return;
        }
        handler.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9979a(z);
            }
        });
    }

    public int getConnectType(String str) {
        MDevice mDeviceM10127a;
        EasyLogger.info(f9474P, "getConnectType, deviceId: " + str);
        UCarConnectProxy uCarConnectProxy = this.f9521p;
        if (uCarConnectProxy == null || (mDeviceM10127a = uCarConnectProxy.m10127a(str)) == null) {
            return 1;
        }
        return mDeviceM10127a.getConnectType();
    }

    public boolean init(@NonNull final Context context, @NonNull final UCarConfig uCarConfig, @NonNull final ICarInitCallback iCarInitCallback) {
        EasyLogger.info(f9474P, "init SDK by user");
        if (!m10040q() || !this.f9512g) {
            return this.f9511f.post(new Runnable() {
                @Override
                public final void run() {
                    UCarAdapter.this.m9954a(iCarInitCallback, uCarConfig, context);
                }
            });
        }
        iCarInitCallback.onInitFailed(UCarConnectState.ErrorCode.ERROR_INIT_FAILED);
        return false;
    }

    public void notifyCameraStateChanged(String str, UCarCommon.CameraState cameraState) {
        VehicleControlManager c0123d = this.f9524s;
        if (c0123d != null) {
            c0123d.m918s(str, cameraState);
        }
    }

    public boolean notifyHungUpCall() {
        VehicleControlManager c0123d = this.f9524s;
        if (c0123d != null) {
            return c0123d.m904C();
        }
        return false;
    }

    public boolean notifySwitchDayOrNight(UCarCommon.DayNightMode dayNightMode) {
        VehicleControlManager c0123d = this.f9524s;
        if (c0123d != null) {
            return c0123d.m914o(dayNightMode);
        }
        return false;
    }

    public boolean pauseCast() {
        EasyLogger.info(f9474P, "pauseCast");
        if (this.f9511f == null || !this.f9512g) {
            EasyLogger.error(f9474P, "start cast must be called after successful initialization");
            return false;
        }
        this.f9523r.pauseVideoRender();
        return this.f9511f.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m10028l();
            }
        });
    }

    public void registerCameraInfoListener(ICameraInfoListener iCameraInfoListener) {
        EasyLogger.info(f9474P, "registerCameraInfoListener: " + iCameraInfoListener);
        VehicleCameraManager c0113b = this.f9529x;
        if (c0113b != null) {
            c0113b.m810e(iCameraInfoListener);
        }
    }

    public void registerCarConnectListener(ICarConnectListener iCarConnectListener) {
        EasyLogger.info(f9474P, "registerCarConnectListener: " + iCarConnectListener);
        if (this.f9518m.contains(iCarConnectListener)) {
            return;
        }
        this.f9518m.add(iCarConnectListener);
    }

    public void registerPhoneDataListener(IPhoneDataListener iPhoneDataListener) {
        EasyLogger.info(f9474P, "registerPhoneDataListener: " + iPhoneDataListener);
        if (this.f9519n.contains(iPhoneDataListener)) {
            return;
        }
        this.f9519n.add(iPhoneDataListener);
    }

    public void removeCamera(String[] strArr) {
        VehicleControlManager c0123d = this.f9524s;
        if (c0123d == null || this.f9529x == null) {
            return;
        }
        c0123d.m913n(VehicleCameraManager.m805b(strArr));
    }

    public boolean sendAccelerationInfo(UCarCommon.AccelerationInfo accelerationInfo) {
        if (this.f9527v == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendAccelerationInfo");
        return this.f9527v.sendAccelerationInfo(accelerationInfo);
    }

    public boolean sendBatteryInfo(UCarCommon.BatteryInfo batteryInfo) {
        return false;
    }

    public void sendCameraData(UCarCommon.VideoType videoType, ByteBuffer byteBuffer, short s) {
        VehicleCameraManager c0113b = this.f9529x;
        if (c0113b != null) {
            c0113b.sendCameraFrame(videoType, byteBuffer, s);
        }
    }

    public boolean sendGPSInfo(UCarCommon.GPSInfo gPSInfo) {
        if (this.f9527v == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendGPSInfo");
        return this.f9527v.sendGpsInfo(gPSInfo);
    }

    public boolean sendGearStateInfo(UCarCommon.GearStateInfo gearStateInfo) {
        if (this.f9527v == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendGearStateInfo");
        return this.f9527v.sendGearStateInfo(gearStateInfo);
    }

    public boolean sendGyroscopeInfo(UCarCommon.GyroscopeInfo gyroscopeInfo) {
        if (this.f9527v == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendGyroscopeInfo");
        return this.f9527v.sendGyroscopeInfo(gyroscopeInfo);
    }

    public boolean sendKeyEvent(UCarCommon.KeyEventActionType keyEventActionType, UCarCommon.KeyCodeType keyCodeType, int i) {
        if (keyCodeType == null || this.f9524s == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendKeyEvent action:" + keyEventActionType + ",keycode:" + keyCodeType + ",metaState:" + i);
        return this.f9524s.m915p(keyEventActionType, keyCodeType, i);
    }

    public boolean sendLightSensorInfo(UCarCommon.LightSensorInfo lightSensorInfo) {
        if (this.f9527v == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendLightSensorInfo");
        return this.f9527v.sendLightSensorInfo(lightSensorInfo);
    }

    public boolean sendLightsInfo(UCarCommon.LightsInfo lightsInfo) {
        if (this.f9527v == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendLightsInfo");
        return this.f9527v.sendLightsInfo(lightsInfo);
    }

    public boolean sendMicRecordData(int i, short[] sArr, long j) {
        UCarAudioManager c0111c = this.f9525t;
        if (c0111c != null) {
            return c0111c.sendMicRecordData(this.f9516k, i, sArr, j);
        }
        return false;
    }

    public boolean sendOilInfo(UCarCommon.OilInfo oilInfo) {
        if (this.f9527v == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendOilInfo");
        return this.f9527v.sendOilInfo(oilInfo);
    }

    public boolean sendTouchEvent(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        UibcEventManager c0124e = this.f9526u;
        if (c0124e != null) {
            return c0124e.sendTouchEvent(i, i2, iArr, iArr2, iArr3);
        }
        return false;
    }

    public boolean sendVRCMD(UCarCommon.VRCmdType vRCmdType, String str) {
        if (this.f9524s == null) {
            return false;
        }
        EasyLogger.debug(f9474P, "sendVRCMD type:" + vRCmdType + ",source:" + str);
        return this.f9524s.m916q(vRCmdType, str);
    }

    public boolean startAdvertise() {
        EasyLogger.info(f9474P, "startAdvertise by user");
        this.f9507b.lock();
        boolean z = true;
        if (this.f9514i) {
            z = false;
        } else {
            this.f9514i = true;
        }
        this.f9507b.unlock();
        return m10018g(z);
    }

    public boolean startCast(final Surface surface, final int i, final int i2) {
        EasyLogger.info(f9474P, "start cast, width:" + i + ", height:" + i2);
        Handler handler = this.f9511f;
        if (handler == null || !this.f9512g) {
            EasyLogger.error(f9474P, "start cast must be called after successful initialization");
            return false;
        }
        if (this.f9524s == null) {
            EasyLogger.error(f9474P, "start cast failed because control channel is not ready");
            return false;
        }
        if (surface == null || !surface.isValid() || i <= 0 || i2 <= 0) {
            EasyLogger.error(f9474P, "start cast failed because surface or size is invalid");
            return false;
        }
        handler.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m9951a(i, i2, surface);
            }
        });
        return this.f9524s.m907I();
    }

    public boolean stopAdvertise() {
        Handler handler;
        EasyLogger.info(f9474P, "stopAdvertise by user");
        if (this.f9521p == null || (handler = this.f9511f) == null) {
            return false;
        }
        return handler.post(new Runnable() {
            @Override
            public final void run() {
                UCarAdapter.this.m10030m();
            }
        });
    }

    public void unregisterCameraInfoListener(ICameraInfoListener iCameraInfoListener) {
        EasyLogger.info(f9474P, "unregisterCameraInfoListener: " + iCameraInfoListener);
        VehicleCameraManager c0113b = this.f9529x;
        if (c0113b != null) {
            c0113b.m816l(iCameraInfoListener);
        }
    }

    public void unregisterCarConnectListener(ICarConnectListener iCarConnectListener) {
        EasyLogger.info(f9474P, "unregisterCarConnectListener: " + iCarConnectListener);
        this.f9518m.remove(iCarConnectListener);
    }

    public void unregisterPhoneDataListener(IPhoneDataListener iPhoneDataListener) {
        EasyLogger.info(f9474P, "unregisterPhoneDataListener: " + iPhoneDataListener);
        this.f9519n.remove(iPhoneDataListener);
    }
}
