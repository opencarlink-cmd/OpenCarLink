package com.ucar.vehiclesdk.connect;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.share.connect.Device;
import com.share.connect.IShareLinkManager;
import com.share.connect.ShareLinkObserver;
import com.share.connect.ShareLinkService;
import com.ucar.sdk.BuildConfig;
import com.ucar.vehiclesdk.MDevice;
import com.ucar.vehiclesdk.UCarConfig;
import com.ucar.vehiclesdk.UCarConnectState;
import java.util.Timer;
import java.util.TimerTask;
import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.core.ConnectionStateHolder;
import com.ucarhu.demo.sharelink.util.CarVerifier;
import com.ucarhu.demo.sharelink.util.RandomStringUtils;
import com.ucarhu.demo.vehicle.connection.VehicleConnectionCallback;
import com.ucarhu.demo.util.binary.HexCodec;

public class UCarConnectProxy {

    private static final String f9572r = "UCarConnectProxy";

    private static final int f9573s = 6;

    public static final int f9574t = 2;

    public static final int f9575u = 6;

    public static final String f9576v = "0000";

    public static final int f9577w = 120000;

    private Context f9578a;

    private UCarConfig f9579b;

    private IShareLinkManager f9581d;

    private VehicleConnectionCallback f9582e;

    private String f9583f;

    private String f9584g;

    private String f9585h;

    private String f9586i;

    private String f9587j;

    private String f9588k;

    private MDevice f9589l;

    private Timer f9590m;

    private boolean f9580c = false;

    private int f9591n = 0;

    private volatile boolean f9592o = true;

    private ServiceConnection f9593p = new ServiceConnectionC1137a();

    private ShareLinkObserver.Stub f9594q = new ShareLinkObserver.Stub() {
        @Override
        public void onAuthenticationOk() throws RemoteException {
            if (UCarConnectProxy.this.f9582e != null) {
                UCarConnectProxy.this.f9582e.onConnecting(UCarConnectProxy.this.f9589l.getId());
            }
        }

        @Override
        public void onConnectFailed(int i) throws RemoteException {
            UCarConnectProxy.this.m10105a(i);
        }

        @Override
        public void onConnected() throws RemoteException {
            if (UCarConnectProxy.this.f9582e != null) {
                UCarConnectProxy.this.f9582e.onConnected();
            }
        }

        @Override
        public void onDeviceDiscover(boolean z, Device device) throws RemoteException {
        }

        @Override
        public void onDisconnected() throws RemoteException {
            EasyLogger.debug(UCarConnectProxy.f9572r, "onDisconnected");
            if (UCarConnectProxy.this.f9582e != null) {
                UCarConnectProxy.this.f9582e.onConnectionStarted(UCarConnectProxy.this.f9589l.getId());
            }
        }

        @Override
        public void onOpenResult(boolean z) throws RemoteException {
            if (z) {
                return;
            }
            UCarConnectProxy.this.m10105a(1);
        }

        @Override
        public void onProgress(int i) {
            UCarConnectProxy.this.m10118c(i);
        }

        @Override
        public void onScanResult(boolean z) throws RemoteException {
        }

        @Override
        public void onUserInterventionNeeded(boolean z) {
            UCarConnectProxy.this.m10114b(z);
        }

        @Override
        public void receivedClientAddress(String str) throws RemoteException {
            UCarConnectProxy.this.f9589l.setAddress(str);
            EasyLogger.info(UCarConnectProxy.f9572r, "receivedClientAddress");
            if (UCarConnectProxy.this.f9582e != null) {
                UCarConnectProxy.this.f9582e.onClientAddressReceived(UCarConnectProxy.this.f9589l.getId(), str);
            }
            if (UCarConnectProxy.this.f9581d != null) {
                UCarConnectProxy.this.f9581d.stopAdvertise();
            }
        }

        @Override
        public void receivedClientHello(String deviceId, String model, String transport) throws RemoteException {
            EasyLogger.info(UCarConnectProxy.f9572r, "receivedClientHello, deviceId=" + deviceId + ", model=" + model + ", transport=" + transport);
            UCarConnectProxy.this.f9589l.setId(isEmpty(deviceId) ? "-1" : deviceId);
            UCarConnectProxy.this.f9589l.setModel(isEmpty(model) ? "GENERIC PHONE" : model);
            UCarConnectProxy.this.f9589l.setName(isEmpty(model) ? "UNKNOWN" : model);
            if (ConnectionStateHolder.TRANSPORT_USB.equals(transport)) {
                UCarConnectProxy.this.f9589l.setConnectType(MDevice.CONNECT_TYPE_USB);
            } else if (ConnectionStateHolder.TRANSPORT_WIFI_AP.equals(transport)) {
                UCarConnectProxy.this.f9589l.setConnectType(MDevice.CONNECT_TYPE_SOFTAP);
            } else {
                UCarConnectProxy.this.f9589l.setConnectType(MDevice.CONNECT_TYPE_WIFIP2P);
            }
        }

        @Override
        public void receivedClientInfo(String str) throws RemoteException {
            EasyLogger.info(UCarConnectProxy.f9572r, "received client info from: " + str);
            UCarConnectProxy.this.f9589l.setModel(str);
        }
    };

    public class ServiceConnectionC1137a implements ServiceConnection {
        public ServiceConnectionC1137a() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            EasyLogger.info(UCarConnectProxy.f9572r, "share link service connected.");
            UCarConnectProxy.this.f9581d = IShareLinkManager.Stub.asInterface(iBinder);
            try {
                if (!UCarConnectProxy.this.f9580c) {
                    UCarConnectProxy.this.f9581d.registerLinkObserver(UCarConnectProxy.this.f9594q);
                    UCarConnectProxy.this.f9580c = true;
                }
                if (UCarConnectProxy.this.f9582e != null) {
                    EasyLogger.info(UCarConnectProxy.f9572r, "notify share link service connected");
                    UCarConnectProxy.this.f9582e.onConnectionRejected();
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            EasyLogger.warn(UCarConnectProxy.f9572r, "share link service disconnected.");
            UCarConnectProxy.this.f9580c = false;
            if (UCarConnectProxy.this.f9581d != null) {
                if (UCarConnectProxy.this.f9582e != null) {
                    EasyLogger.info(UCarConnectProxy.f9572r, "notify share link service disconnected");
                    UCarConnectProxy.this.f9582e.onConnectionClosed();
                }
                try {
                    UCarConnectProxy.this.f9581d.unregisterLinkObserver(UCarConnectProxy.this.f9594q);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public class PinCodeRefreshTask extends TimerTask {
        public PinCodeRefreshTask() {
        }

        @Override
        public void run() {
            UCarConnectProxy.this.f9583f = RandomStringUtils.randomDigits(6);
            try {
                UCarConnectProxy.this.f9581d.updatePinCode(UCarConnectProxy.this.f9583f);
                if (UCarConnectProxy.this.f9582e == null || UCarConnectProxy.this.f9586i == null) {
                    return;
                }
                EasyLogger.debug(UCarConnectProxy.f9572r, "notify pin code to car app");
                UCarConnectProxy.this.f9582e.onPinCode(UCarConnectProxy.this.f9583f, UCarConnectProxy.this.f9586i);
            } catch (RemoteException e2) {
                UCarConnectProxy.this.m10105a(7);
                EasyLogger.errorWithThrowable(UCarConnectProxy.f9572r, "updatePinCode failed", e2);
            }
        }
    }

    public UCarConnectProxy(@NonNull Context context, @NonNull UCarConfig uCarConfig) {
        this.f9578a = context;
        this.f9579b = uCarConfig;
    }

    private static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public void m10105a(int i) {
        int i2;
        EasyLogger.debug(f9572r, "handleConnectFailed, reason: " + i);
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
                i2 = UCarConnectState.ErrorCode.ERROR_WIRELESS_CONNECT_FAILED;
                break;
            case 5:
                i2 = UCarConnectState.ErrorCode.ERROR_USB_CONNECT_FAILED;
                break;
            case 6:
                i2 = UCarConnectState.ErrorCode.ERROR_CONNECT_AUTH_ERROR;
                break;
            case 7:
                i2 = UCarConnectState.ErrorCode.ERROR_CONNECT_INNER_FAILED;
                break;
            default:
                return;
        }
        m10111b(i2);
    }

    private void m10107a(byte[] bArr) {
        this.f9585h = HexCodec.encodeLower(bArr);
        EasyLogger.debug(f9572r, "get car id: " + this.f9585h);
    }

    private boolean m10108a() {
        EasyLogger.debug(f9572r, "bind connect service");
        if (this.f9580c) {
            return true;
        }
        try {
            Intent intent = new Intent(this.f9578a, (Class<?>) ShareLinkService.class);
            intent.putExtra("car_id", this.f9585h);
            intent.putExtra("mod_id", this.f9584g);
            intent.putExtra("car_short_name", this.f9587j);
            intent.putExtra("car_name", this.f9586i);
            intent.putExtra("protocol_version", BuildConfig.PROTOCOL_VERSION);
            intent.putExtra("car_vendor_custom_data", this.f9588k);
            this.f9578a.bindService(intent, this.f9593p, 1);
            return true;
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9572r, "bind connect service error.", e2);
            return false;
        }
    }

    private boolean m10110a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 6) {
            EasyLogger.error(f9572r, "Invalid car id");
            return false;
        }
        m10107a(bArr);
        m10122f();
        m10124g();
        m10115b(bArr2);
        this.f9589l = new MDevice();
        return true;
    }

    private void m10111b(int i) {
        VehicleConnectionCallback interfaceC0119a = this.f9582e;
        if (interfaceC0119a != null) {
            interfaceC0119a.onConnectionProgress(this.f9589l.getId(), i);
        }
    }

    public void m10114b(boolean z) {
        VehicleConnectionCallback interfaceC0119a = this.f9582e;
        if (interfaceC0119a != null) {
            interfaceC0119a.onUserInterventionNeeded(z);
        }
    }

    private void m10115b(byte[] bArr) {
        String strM14138l;
        if (bArr == null || bArr.length != 2) {
            EasyLogger.warn(f9572r, "Invalid custom Data.");
            strM14138l = f9576v;
        } else {
            strM14138l = HexCodec.encodeLower(bArr);
        }
        this.f9588k = strM14138l;
        EasyLogger.debug(f9572r, "get Custom Data: " + this.f9588k);
    }

    public void m10118c(int i) {
        VehicleConnectionCallback interfaceC0119a = this.f9582e;
        if (interfaceC0119a != null) {
            interfaceC0119a.onConnectionFailed(this.f9589l.getId(), i);
        }
    }

    private void m10122f() {
        Device deviceM155f = new CarVerifier(this.f9578a, this.f9579b.getCcdFilePath()).readCarDevice();
        if (deviceM155f == null) {
            deviceM155f = Device.UNKNOWN_CAR;
        }
        this.f9584g = (deviceM155f.getVid() + deviceM155f.getPid()).toLowerCase();
    }

    private void m10124g() {
        Device deviceM155f = new CarVerifier(this.f9578a, this.f9579b.getCcdFilePath()).readCarDevice();
        if (deviceM155f == null) {
            deviceM155f = Device.UNKNOWN_CAR;
        }
        this.f9586i = deviceM155f.getPName();
        this.f9587j = deviceM155f.getShortName();
    }

    private void m10125h() {
        m10126j();
        Timer timer = new Timer();
        this.f9590m = timer;
        timer.schedule(new PinCodeRefreshTask(), 0L, 120000L);
    }

    private void m10126j() {
        Timer timer = this.f9590m;
        if (timer != null) {
            timer.cancel();
            this.f9590m = null;
        }
    }

    public MDevice m10127a(String str) {
        EasyLogger.info(f9572r, "getMDevice, mMDevice: " + this.f9589l);
        return this.f9589l;
    }

    public void m10128a(VehicleConnectionCallback interfaceC0119a) {
        this.f9582e = interfaceC0119a;
    }

    public void m10129a(boolean z) {
        IShareLinkManager iShareLinkManager = this.f9581d;
        if (iShareLinkManager != null) {
            try {
                iShareLinkManager.enableUsbDeviceScanning(z);
            } catch (RemoteException e2) {
                EasyLogger.errorWithThrowable(f9572r, "enableUsbDeviceScanning failed", e2);
            }
        }
    }

    public boolean m10130a(boolean z, UCarConfig uCarConfig) {
        int i = this.f9591n;
        if (this.f9581d == null) {
            EasyLogger.error(f9572r, "start advertise failed, mShareLinkManager is null");
            return false;
        }
        EasyLogger.info(f9572r, "start advertise is reconnect:" + z);
        if (!this.f9592o) {
            EasyLogger.warn(f9572r, "already in advertised, need to stop first");
            m10137i();
        }
        EasyLogger.info(f9572r, "start advertise running");
        m10125h();
        boolean zIsSupportSoftAP = uCarConfig.isSupportSoftAP();
        boolean zIsSupportP2P = uCarConfig.isSupportP2P();
        int default5gChannel = uCarConfig.getDefault5gChannel();
        boolean zIsSupportRealWifiAddress = uCarConfig.isSupportRealWifiAddress();
        try {
            if (zIsSupportP2P && zIsSupportSoftAP) {
                i = 1003;
            } else {
                if (!zIsSupportP2P) {
                    if (zIsSupportSoftAP) {
                        i = 1002;
                    }
                    this.f9591n = i;
                    this.f9581d.open(z, this.f9591n, default5gChannel, zIsSupportRealWifiAddress);
                    this.f9592o = false;
                    return true;
                }
                i = 1001;
            }
            this.f9591n = i;
            this.f9581d.open(z, this.f9591n, default5gChannel, zIsSupportRealWifiAddress);
            this.f9592o = false;
            return true;
        } catch (RemoteException e2) {
            m10105a(7);
            EasyLogger.errorWithThrowable(f9572r, "startAdvertise failed", e2);
            return false;
        }
    }

    public void m10131b() {
        IShareLinkManager iShareLinkManager = this.f9581d;
        if (iShareLinkManager != null) {
            try {
                iShareLinkManager.close();
            } catch (RemoteException e2) {
                EasyLogger.errorWithThrowable(f9572r, "close RemoteException", e2);
            }
        }
    }

    public void m10132b(VehicleConnectionCallback interfaceC0119a) {
        if (this.f9582e == interfaceC0119a) {
            this.f9582e = null;
        }
    }

    public boolean m10133b(byte[] bArr, byte[] bArr2) {
        if (m10110a(bArr, bArr2)) {
            return m10108a();
        }
        return false;
    }

    public void m10134c() {
        IShareLinkManager iShareLinkManager = this.f9581d;
        if (iShareLinkManager != null) {
            try {
                iShareLinkManager.disconnect();
            } catch (RemoteException e2) {
                EasyLogger.errorWithThrowable(f9572r, "disconnect Exception", e2);
            }
        }
    }

    public void m10135d() {
        if (this.f9580c) {
            try {
                this.f9578a.unbindService(this.f9593p);
                this.f9593p = null;
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9572r, "disconnectService Exception", e2);
            }
        }
    }

    public String m10136e() {
        return this.f9585h;
    }

    public boolean m10137i() {
        if (this.f9581d == null) {
            EasyLogger.error(f9572r, "stopAdvertise failed, mShareLinkManager is null");
            return false;
        }
        EasyLogger.info(f9572r, "stopAdvertise");
        m10126j();
        try {
            this.f9581d.stopAdvertise();
            this.f9592o = true;
            return true;
        } catch (RemoteException e2) {
            EasyLogger.errorWithThrowable(f9572r, "stopAdvertise failed", e2);
            return false;
        }
    }
}
