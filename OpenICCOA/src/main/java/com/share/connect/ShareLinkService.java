package com.share.connect;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pGroup;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import com.share.connect.IShareLinkManager;
import com.share.connect.ble.BluetoothLeObserver;
import com.share.connect.ble.BluetoothLeService;
import com.share.connect.ble.IBluetoothLe;
import com.share.connect.wifiap.IWifiAp;
import com.share.connect.wifiap.WifiApObserver;
import com.share.connect.wifiap.WifiApService;
import com.share.connect.wifip2p.IWifiP2p;
import com.share.connect.wifip2p.WifiP2pObserver;
import com.share.connect.wifip2p.WifiP2pService;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.core.ConnectSession;
import com.ucarhu.demo.sharelink.core.ConnectionStateHolder;
import com.ucarhu.demo.sharelink.core.ShareLinkObserverDispatcher;
import com.ucarhu.demo.sharelink.auth.UCarAuthService;
import com.ucarhu.demo.sharelink.util.WakeLockHolder;
import com.ucarhu.demo.sharelink.util.WorkThreadExecutor;
import com.ucarhu.demo.connection.aoa.AoaConnectionManager;
import com.ucarhu.demo.util.binary.HexCodec;

public class ShareLinkService extends Service {

    private static final String f9144J = "ShareLink";

    private static final int f9145K = 6;

    private static final int f9146L = 10000;

    private static final int f9147M = 20000;

    private static final int f9148N = 15000;

    private static final String f9149O = "02:00:00:00:00:00";

    private static final long P2P_ACCEPT_RETRY_DELAY_MS = 1500L;

    private String f9159a;

    private String f9160b;

    private String f9161c;

    private String f9162d;

    private String f9163e;

    private String f9164f;

    private String f9165g;

    private String f9166h;

    private IBluetoothLe f9167i;

    private IWifiP2p f9168j;

    private IWifiAp f9169k;

    private UCarAuthService f9170l;

    private boolean f9171m;

    private int f9176r;

    private int f9177s;

    private int f9178t;

    private boolean f9172n = false;

    private boolean f9173o = false;

    private boolean f9174p = false;

    private boolean f9175q = false;

    private final ConnectSession f9179u = new ConnectSession();

    private final ConnectionStateHolder f9180v = ConnectionStateHolder.getInstance();

    private final CountDownLatch f9181w = new CountDownLatch(3);

    private final ShareLinkObserverDispatcher f9182x = new ShareLinkObserverDispatcher();

    private final ShareLinkStub f9183y = new ShareLinkStub(this, null);

    private final Handler f9184z = new Handler(Looper.getMainLooper());

    private final Runnable f9150A = new Runnable() {
        @Override
        public final void run() {
            ShareLinkService.this.handleOpenTimeout();
        }
    };

    private final Runnable f9151B = new Runnable() {
        @Override
        public final void run() {
            ShareLinkService.this.handleAuthTimeout();
        }
    };

    private final Runnable f9152C = new Runnable() {
        @Override
        public final void run() {
            ShareLinkService.this.handleUserConfirmTimeout();
        }
    };

    private final Runnable restartP2pAcceptWindowTask = new Runnable() {
        @Override
        public void run() {
            ShareLinkService.this.restartP2pAcceptWindow();
        }
    };

    private final ServiceConnection f9153D = new BluetoothServiceConnection();

    private final ServiceConnection f9154E = new WifiP2pServiceConnection();

    private final ServiceConnection f9155F = new WifiApServiceConnection();

    private BluetoothLeObserver f9156G = new BluetoothLeObserverBridge();

    private WifiP2pObserver f9157H = new WifiP2pObserverBridge();

    private WifiApObserver f9158I = new WifiApObserverBridge();

    public class ShareLinkStub extends IShareLinkManager.Stub {
        private ShareLinkStub() {
        }

        public ShareLinkStub(ShareLinkService shareLinkService, BluetoothServiceConnection serviceConnectionC1028b) {
            this();
        }

        @Override
        public void close() {
            ShareLinkService.this.closeShareLink();
        }

        @Override
        public void connect(String str) {
            EasyLogger.warn(ShareLinkService.f9144J, "unSupport method call");
        }

        @Override
        public void disconnect() {
            ShareLinkService.this.disconnectShareLink();
        }

        @Override
        public void disconnectBle() throws RemoteException {
            ShareLinkService.this.f9167i.disconnectWithoutState();
        }

        @Override
        public void enableUsbDeviceScanning(boolean z) throws RemoteException {
            ShareLinkService.this.f9175q = z;
            ShareLinkService.this.setUsbDeviceScanningEnabled(z);
        }

        @Override
        public Map getDevicesSignal(int i, int i2) throws RemoteException {
            if (ShareLinkService.this.f9167i != null) {
                return ShareLinkService.this.f9167i.getDevicesSignal(i, i2);
            }
            EasyLogger.warn(ShareLinkService.f9144J, "getDevicesSignal when bluetooth haven't initialized");
            return Collections.emptyMap();
        }

        @Override
        public boolean isDeviceInMatch(String str) throws RemoteException {
            if (ShareLinkService.this.f9167i != null) {
                return ShareLinkService.this.f9167i.isDeviceInMatch(str);
            }
            EasyLogger.warn(ShareLinkService.f9144J, "Bluetooth not initialize");
            return false;
        }

        @Override
        public void open(boolean z, int i, int i2, boolean z2) {
            ShareLinkService.this.f9172n = z;
            ShareLinkService.this.f9174p = z2;
            ShareLinkService.this.openWirelessConnection(i, i2);
        }

        @Override
        public void registerLinkObserver(ShareLinkObserver shareLinkObserver) {
            ShareLinkService.this.f9182x.addObserver(shareLinkObserver);
        }

        @Override
        public void startScan() {
            ShareLinkService.this.startBluetoothScan();
        }

        @Override
        public void stopAdvertise() throws RemoteException {
            ShareLinkService.this.setOpenTimeoutEnabled(false);
            ShareLinkService.this.closeBluetoothLeService();
            if (ShareLinkService.this.f9180v.isWifiConnecting()) {
                ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_IDLE);
                ShareLinkService.this.setAuthTimeoutEnabled(false);
            }
        }

        @Override
        public void stopScan() {
            ShareLinkService.this.stopBluetoothScan();
        }

        @Override
        public void unregisterLinkObserver(ShareLinkObserver shareLinkObserver) {
            ShareLinkService.this.f9182x.removeObserver(shareLinkObserver);
        }

        @Override
        public void updatePinCode(String str) {
            ShareLinkService.this.f9161c = str;
        }
    }

    public class ReadBluetoothAddressTask implements Runnable {
        public final long[] f9185a;

        public ReadBluetoothAddressTask(long[] jArr) {
            this.f9185a = jArr;
        }

        @Override
        public void run() {
            this.f9185a[0] = ShareLinkService.this.getNetworkTimeMillis();
        }
    }

    public class BluetoothServiceConnection implements ServiceConnection {
        public BluetoothServiceConnection() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            EasyLogger.debug(ShareLinkService.f9144J, "Bluetooth service connected");
            ShareLinkService.this.f9167i = IBluetoothLe.Stub.asInterface(iBinder);
            ShareLinkService.this.f9181w.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            EasyLogger.error(ShareLinkService.f9144J, "Bluetooth service disconnected");
            ShareLinkService.this.f9167i = null;
        }
    }

    public class WifiP2pServiceConnection implements ServiceConnection {
        public WifiP2pServiceConnection() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            EasyLogger.debug(ShareLinkService.f9144J, "WifiP2p service connected");
            ShareLinkService.this.f9168j = IWifiP2p.Stub.asInterface(iBinder);
            ShareLinkService.this.f9181w.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            EasyLogger.error(ShareLinkService.f9144J, "WifiP2p service disconnected");
            ShareLinkService.this.f9168j = null;
        }
    }

    public class WifiApServiceConnection implements ServiceConnection {
        public WifiApServiceConnection() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            EasyLogger.debug(ShareLinkService.f9144J, "WifiAp service connected");
            ShareLinkService.this.f9169k = IWifiAp.Stub.asInterface(iBinder);
            ShareLinkService.this.f9181w.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            EasyLogger.error(ShareLinkService.f9144J, "WifiAp service disconnected");
            ShareLinkService.this.f9169k = null;
        }
    }

    public class AuthCallback extends UCarAuthService.c {

        private String f9190a = ConnectionStateHolder.STATE_IDLE;

        public AuthCallback() {
        }

        @Override
        public void mo139a() {
            ShareLinkService.this.f9182x.notifyAuthenticationSucceeded();
            ShareLinkService.this.f9182x.notifyProgress(8);
        }

        @Override
        public void mo140a(String str) {
            EasyLogger.debug(ShareLinkService.f9144J, "mo140a "+str);
            try {
                ShareLinkService.this.f9170l.stop();
            } catch (java.io.IOException e) {
                EasyLogger.errorWithThrowable(ShareLinkService.f9144J, "stop auth service error", e);
            }
            ShareLinkService.this.f9170l = null;
            ShareLinkService.this.setAuthTimeoutEnabled(false);
            EasyLogger.info(ShareLinkService.f9144J, "authentication confirmed, skip expired fixed-date gate.");
            ShareLinkService.this.f9180v.setState(this.f9190a);
            ShareLinkService.this.f9182x.notifyProgress(9);
            ShareLinkService.this.f9182x.notifyClientAddress(str);
        }

        @Override
        public void mo141a(String str, String str2) {
            UCarAuthService c0021e;
            String str3;
            if (ShareLinkService.this.f9180v.isUsbConnected()) {
                ShareLinkService.this.f9182x.notifyClientHello(str, str2, ConnectionStateHolder.TRANSPORT_USB);
                c0021e = ShareLinkService.this.f9170l;
                str3 = ShareLinkService.this.f9162d;
            } else {
                int iM15d = ShareLinkService.this.f9179u.getConnectType();
                EasyLogger.info(ShareLinkService.f9144J, "onClientKeyNegotiationReceived connectType = " + iM15d);
                ShareLinkService.this.f9182x.notifyClientHello(str, str2, 1002 == iM15d ? ConnectionStateHolder.TRANSPORT_WIFI_AP : ConnectionStateHolder.TRANSPORT_WIFI_P2P);
                c0021e = ShareLinkService.this.f9170l;
                str3 = ShareLinkService.this.f9161c;
            }
            c0021e.setPinCode(str3);
            ShareLinkService.this.setAuthTimeoutEnabled(true);
            ShareLinkService.this.f9182x.notifyProgress(7);
            this.f9190a = ShareLinkService.this.f9180v.getState();
        }

        @Override
        public void mo142b() {
            ShareLinkService.this.setAuthTimeoutEnabled(false);
            ShareLinkService.this.f9182x.notifyUserInterventionNeeded(false);
            if (this.f9190a.contains(ConnectionStateHolder.TRANSPORT_WIFI)) {
                ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_IDLE);
                if (ShareLinkService.this.f9167i != null) {
                    try {
                        ShareLinkService.this.f9167i.allowProcessNewConnection();
                    } catch (RemoteException e2) {
                        EasyLogger.errorWithThrowable(ShareLinkService.f9144J, "call failed.", e2);
                    }
                }
                ShareLinkService shareLinkService = ShareLinkService.this;
                shareLinkService.setUsbDeviceScanningEnabled(shareLinkService.f9175q);
            }
        }
    }

    public class OpenWifiConnectionTask implements Runnable {
        public OpenWifiConnectionTask() {
        }

        @Override
        public void run() {
            ShareLinkService shareLinkService;
            try {
                EasyLogger.debug(ShareLinkService.f9144J, "Open: Waiting for services bound, start at: " + SystemClock.elapsedRealtime());
                ShareLinkService.this.f9181w.await(10L, TimeUnit.SECONDS);
            } catch (InterruptedException e2) {
                EasyLogger.errorWithThrowable(ShareLinkService.f9144J, "READY.await() failed", e2);
            }
            EasyLogger.debug(ShareLinkService.f9144J, "Open: services bound at: " + SystemClock.elapsedRealtime());
            try {
                if (ShareLinkService.this.f9176r == 1001 && ShareLinkService.this.f9168j != null) {
                    ShareLinkService.this.setOpenTimeoutEnabled(true);
                    ShareLinkService.this.f9179u.setConnectType(1001);
                    ShareLinkService.this.f9168j.registerWifiP2pObserver(ShareLinkService.this.f9157H);
                    shareLinkService = ShareLinkService.this;
                } else {
                    if (ShareLinkService.this.f9176r == 1002 && ShareLinkService.this.f9169k != null) {
                        ShareLinkService.this.setOpenTimeoutEnabled(true);
                        ShareLinkService.this.f9179u.setConnectType(1002);
                        ShareLinkService.this.f9169k.registerWifiApObserver(ShareLinkService.this.f9158I);
                        ShareLinkService.this.f9169k.open();
                        return;
                    }
                    if (ShareLinkService.this.f9176r != 1003 || ShareLinkService.this.f9169k == null || ShareLinkService.this.f9168j == null) {
                        EasyLogger.error(ShareLinkService.f9144J, "Open failed: wifi Services binds failed");
                        ShareLinkService.this.setOpenTimeoutEnabled(false);
                        ShareLinkService.this.f9182x.notifyOpenResult(false);
                        return;
                    } else {
                        if (ShareLinkService.this.openWifiApFirstWhenP2pUnavailable()) {
                            return;
                        }
                        EasyLogger.info(ShareLinkService.f9144J, "support wifi p2p & ap connect type, default open p2p");
                        ShareLinkService.this.setOpenTimeoutEnabled(true);
                        ShareLinkService.this.f9179u.setConnectType(1001);
                        ShareLinkService.this.f9168j.registerWifiP2pObserver(ShareLinkService.this.f9157H);
                        shareLinkService = ShareLinkService.this;
                    }
                }
                shareLinkService.f9168j.open();
            } catch (Exception e3) {
                EasyLogger.errorWithThrowable(ShareLinkService.f9144J, "Open failed.", e3);
                ShareLinkService.this.setOpenTimeoutEnabled(false);
                ShareLinkService.this.f9182x.notifyOpenResult(false);
            }
        }
    }

    public class AoaConnectionCallback implements AoaConnectionManager.b {
        public AoaConnectionCallback() {
        }

        @Override
        public void mo245a() {
            EasyLogger.info(ShareLinkService.f9144J, "aoa disconnected: ");
            ShareLinkService.this.setUserConfirmTimeoutEnabled(false);
            if (ShareLinkService.this.f9180v.isUsbConnected()) {
                ShareLinkService.this.setAuthTimeoutEnabled(false);
                ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_USB_DISCONNECTED);
                ShareLinkService.this.f9182x.notifyDisconnected();
            } else if (!ShareLinkService.this.f9180v.isUsbPreparingOrConnecting()) {
                return;
            } else {
                ShareLinkService.this.f9182x.notifyConnectFailed(5);
            }
            ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_IDLE);
        }

        @Override
        public void mo246a(String str) {
            EasyLogger.info(ShareLinkService.f9144J, "usbPlugged: ");
            ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_USB_CONNECTING);
            ShareLinkService.this.f9182x.notifyClientInfo(str);
        }

        @Override
        public void mo247b() {
            EasyLogger.info(ShareLinkService.f9144J, "usbConnected: ");
            ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_USB_PREPARING);
            ShareLinkService.this.f9182x.notifyProgress(3);
            ShareLinkService.this.setUserConfirmTimeoutEnabled(true);
        }

        @Override
        public void mo248c() {
            EasyLogger.info(ShareLinkService.f9144J, "aoa NetworkReady: ");
            ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_USB_CONNECTED);
            ShareLinkService.this.setUserConfirmTimeoutEnabled(false);
            ShareLinkService.this.setAuthTimeoutEnabled(true);
            ShareLinkService.this.f9182x.notifyConnected();
            ShareLinkService.this.f9182x.notifyProgress(6);
            ShareLinkService.this.startAuthService("127.0.0.1");
        }
    }

    public class BluetoothLeObserverBridge extends BluetoothLeObserver.Stub {

        private static final String f9194k = "ShareLink-BleObserver";

        public BluetoothLeObserverBridge() {
        }

        private void failWifiConnectionIfConnecting() {
            if (ConnectionStateHolder.getInstance().isWifiConnecting()) {
                ShareLinkService.this.cancelActiveConnection();
                ShareLinkService.this.f9182x.notifyConnectFailed(4);
            }
        }

        @Override
        public void onClientInfoReceived(String str, String str2, String str3, int i, String str4, int i2) {
            ShareLinkService.this.setUserConfirmTimeoutEnabled(false);
            if (ShareLinkService.this.f9180v.isConnected() && (!ShareLinkService.this.f9179u.getDeviceId().equals(str) || ShareLinkService.this.f9180v.isUsbConnected())) {
                EasyLogger.info(f9194k, "new other client info received, need skip, because current state is " + ShareLinkService.this.f9180v.getState());
                return;
            }
            ShareLinkService.this.f9179u.reset();
            ShareLinkService.this.f9179u.setDeviceId(str).setDeviceName(str2).setBand(i).setP2pMac(str4);
            ShareLinkService.this.setAuthTimeoutEnabled(true);
            ShareLinkService.this.deinitializeAoaConnection();
            ShareLinkService.this.setUsbDeviceScanningEnabled(false);
            ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_WIFI_CONNECTING);
            ShareLinkService.this.f9171m = false;
            ShareLinkService.this.f9182x.notifyClientInfo(str3);
            ShareLinkService.this.f9182x.notifyProgress(1);
            ShareLinkService.this.createExpectedWifiConnection(i2);
        }

        @Override
        public void onDeviceLost(Device device) {
            ShareLinkService.this.f9182x.notifyDeviceLost(device);
        }

        @Override
        public void onDeviceMatch(Device device) {
            ShareLinkService.this.f9182x.notifyDeviceDiscovered(device);
        }

        @Override
        public void onFailure(int i, int i2) {
            EasyLogger.info(f9194k, "onFailure: " + i + ", " + i2);
            if (i == 1) {
                ShareLinkService.this.closeShareLink();
                ShareLinkService.this.setOpenTimeoutEnabled(false);
                ShareLinkService.this.f9182x.notifyOpenResult(false);
            } else {
                if (i == 2) {
                    ShareLinkService.this.stopBluetoothScan();
                    return;
                }
                if (i == 3 || i == 5 || i == 6) {
                    if (i2 == 6) {
                        return;
                    }
                } else if (i != 4) {
                    return;
                }
                failWifiConnectionIfConnecting();
            }
        }

        @Override
        public void onPinAvailable(String str) {
        }

        @Override
        public void onServerAddressSent() {
            ShareLinkService.this.f9182x.notifyProgress(4);
        }

        @Override
        public void onServerInfoReceived(String str, String str2, String str3, int i) {
            try {
                EasyLogger.info(f9194k, "onServerInfoReceived");
                ShareLinkService.this.f9168j.connectGroupOwner(str, str2, str3, i);
            } catch (RemoteException e2) {
                EasyLogger.errorWithThrowable(f9194k, "Connect group owner failed.", e2);
                ShareLinkService.this.f9182x.notifyConnectFailed(7);
            }
        }

        @Override
        public void onSuccess(int i) {
            EasyLogger.info(f9194k, "onSuccess: " + i);
            if (i == 1) {
                ShareLinkService.this.setOpenTimeoutEnabled(false);
                ShareLinkService.this.f9182x.notifyOpenResult(true);
            }
            ShareLinkService.this.f9173o = true;
        }
    }

    public class WifiP2pObserverBridge extends WifiP2pObserver.Stub {

        private static final String f9196l = "ShareLink-P2pObserver";

        public WifiP2pObserverBridge() {
        }

        @Override
        public void onAcceptFailed() {
            EasyLogger.warn(f9196l, "wifi onAcceptFailed, state=" + ShareLinkService.this.f9180v.getState());
            if (ShareLinkService.this.retryP2pAcceptWindowIfWaitingForPhone()) {
                return;
            }
            ShareLinkService.this.notifyWifiConnectFailedIfActive(3);
        }

        @Override
        public void onConnectFailed() {
            EasyLogger.warn(f9196l, "wifi onConnectFailed, state=" + ShareLinkService.this.f9180v.getState());
            ShareLinkService.this.notifyWifiConnectFailedIfActive(3);
        }

        @Override
        public void onConnected() throws RemoteException {
            WakeLockHolder.acquire(ShareLinkService.this);
            ShareLinkService.this.f9182x.notifyConnected();
            ShareLinkService.this.f9182x.notifyProgress(5);
            ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_WIFI_CONNECTED);
        }

        @Override
        public void onCreateGroupFailed() {
            EasyLogger.warn(f9196l, "wifi onCreateGroupFailed, state=" + ShareLinkService.this.f9180v.getState());
            ShareLinkService.this.notifyWifiConnectFailedIfActive(2);
        }

        @Override
        public void onDeviceChanged(String str) throws RemoteException {
            EasyLogger.info(f9196l, "onDeviceChanged: " + str);
            ShareLinkService.this.f9167i.setP2pDeviceMac(str);
        }

        @Override
        public void onDisconnected() {
            ShareLinkService.this.handleWifiDisconnected();
        }

        @Override
        public void onGroupCreated(WifiP2pGroup wifiP2pGroup, int i) {
            String hostAddress;
            EasyLogger.info(f9196l, "onGroupCreated: Group owner mac: " + wifiP2pGroup.getOwner().deviceAddress + ", frequency: " + i);
            if ((ShareLinkService.this.getApplicationInfo().flags & 2) != 0) {
                EasyLogger.debug(f9196l, "ssid:" + wifiP2pGroup.getNetworkName() + ", pw:" + wifiP2pGroup.getPassphrase());
            }
            if (!ShareLinkService.this.f9180v.isWifiConnecting() || ShareLinkService.this.f9171m) {
                return;
            }
            ShareLinkService.this.f9171m = true;
            try {
                EasyLogger.info(f9196l, "start UCarAuthService...");
                ShareLinkService.this.f9182x.notifyProgress(2);
                NetworkInterface byName = NetworkInterface.getByName(wifiP2pGroup.getInterface());
                if (byName != null) {
                    for (InetAddress inetAddress : Collections.list(byName.getInetAddresses())) {
                        if (inetAddress instanceof Inet4Address) {
                            hostAddress = inetAddress.getHostAddress();
                            break;
                        }
                    }
                    hostAddress = "0.0.0.0";
                    ShareLinkService.this.startAuthService(hostAddress);
                } else {
                    hostAddress = "0.0.0.0";
                    ShareLinkService.this.startAuthService(hostAddress);
                }
            } catch (IOException e2) {
                EasyLogger.error(f9196l, "NetworkInterface.getByName cause exception" + e2.toString());
            }
            try {
                ShareLinkService.this.f9167i.notifyServerInfo(wifiP2pGroup.getNetworkName(), wifiP2pGroup.getPassphrase(), ShareLinkService.this.f9174p ? wifiP2pGroup.getOwner().deviceAddress : ShareLinkService.f9149O, i, 1001);
            } catch (RemoteException e3) {
                EasyLogger.errorWithThrowable(f9196l, "notifyServerInfo failed.", e3);
                ShareLinkService.this.f9182x.notifyConnectFailed(7);
            }
        }

        @Override
        public void onOpenFailed() {
            EasyLogger.info(f9196l, "onOpenFailed");
            if (ShareLinkService.this.fallbackToWifiApAfterP2pOpenFailed()) {
                return;
            }
            ShareLinkService.this.closeShareLink();
            ShareLinkService.this.setOpenTimeoutEnabled(false);
            ShareLinkService.this.f9182x.notifyOpenResult(false);
        }

        @Override
        public void onOpenSuccess(String str, int i) throws RemoteException {
            EasyLogger.info(f9196l, "onOpenSuccess: mac=" + str + ", bandSupported=" + i);
            if (ShareLinkService.this.f9168j != null && ShareLinkService.this.f9179u.getConnectType() == 1001) {
                ShareLinkService.this.f9168j.createGroupForClient(ShareLinkService.this.f9179u.getBand(), ShareLinkService.this.f9179u.getP2pMac(), ShareLinkService.this.f9178t);
                ShareLinkService.this.f9177s = 1001;
            }
            if (ShareLinkService.this.f9167i == null || ShareLinkService.this.f9173o) {
                return;
            }
            ShareLinkService.this.f9167i.registerBluetoothLeObserver(ShareLinkService.this.f9156G);
            ShareLinkService.this.f9167i.setBandSupported(i);
            ShareLinkService.this.f9167i.setP2pDeviceMac(str);
            ShareLinkService.this.f9167i.open(ShareLinkService.this.f9159a, ShareLinkService.this.f9160b, ShareLinkService.this.f9163e, ShareLinkService.this.f9164f, ShareLinkService.this.f9165g, ShareLinkService.this.f9166h, ShareLinkService.this.f9172n);
        }
    }

    public class WifiApObserverBridge extends WifiApObserver.Stub {

        private static final String f9198j = "ShareLink-ApObserver";

        public WifiApObserverBridge() {
        }

        @Override
        public void onAcceptFailed() {
            EasyLogger.warn(f9198j, "wifi onAcceptFailed, state=" + ShareLinkService.this.f9180v.getState());
            ShareLinkService.this.notifyWifiConnectFailedIfActive(3);
        }

        @Override
        public void onApCreateFailed() {
            EasyLogger.warn(f9198j, "softAp is created failed, state=" + ShareLinkService.this.f9180v.getState());
            ShareLinkService.this.notifyWifiConnectFailedIfActive(8);
        }

        @Override
        public void onApCreated(String str, String str2, String str3, String str4, int i) {
            if (!ShareLinkService.this.f9180v.isWifiConnecting() || ShareLinkService.this.f9171m) {
                EasyLogger.info(f9198j, "soft ap is created, but the ble is not coming");
                return;
            }
            EasyLogger.info(f9198j, "onApCreated start, ssid:" + str2 + " password:" + str3 + " macAddress:" + str4 + " frequency:" + i + " localAddress:" + str);
            ShareLinkService.this.f9171m = true;
            ShareLinkService.this.f9182x.notifyProgress(2);
            ShareLinkService.this.startAuthService(str);
            try {
                if (!ShareLinkService.this.f9174p) {
                    str4 = ShareLinkService.f9149O;
                }
                ShareLinkService.this.f9167i.notifyServerInfo(str2, str3, str4, i, 1002);
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9198j, "notifyServerInfo failed.", e2);
                ShareLinkService.this.f9182x.notifyConnectFailed(7);
            }
        }

        @Override
        public void onConnected() throws RemoteException {
            WakeLockHolder.acquire(ShareLinkService.this);
            ShareLinkService.this.f9182x.notifyConnected();
            ShareLinkService.this.f9182x.notifyProgress(5);
            ShareLinkService.this.f9180v.setState(ConnectionStateHolder.STATE_WIFI_CONNECTED);
        }

        @Override
        public void onDisconnected() {
            ShareLinkService.this.handleWifiDisconnected();
        }

        @Override
        public void onOpenFailed() {
            EasyLogger.info(f9198j, "onOpenFailed");
            ShareLinkService.this.closeShareLink();
            ShareLinkService.this.setOpenTimeoutEnabled(false);
            ShareLinkService.this.f9182x.notifyOpenResult(false);
        }

        @Override
        public void onOpenSuccess() throws RemoteException {
            EasyLogger.info(f9198j, "WifiApService onOpenSuccess");
            if (ShareLinkService.this.f9169k != null && ShareLinkService.this.f9179u.getConnectType() == 1002) {
                ShareLinkService.this.f9169k.createSoftAp(ShareLinkService.this.f9178t);
            }
            ShareLinkService.this.f9177s = 1002;
            if (ShareLinkService.this.f9167i == null || ShareLinkService.this.f9173o) {
                return;
            }
            ShareLinkService.this.f9167i.registerBluetoothLeObserver(ShareLinkService.this.f9156G);
            ShareLinkService.this.f9167i.open(ShareLinkService.this.f9159a, ShareLinkService.this.f9160b, ShareLinkService.this.f9163e, ShareLinkService.this.f9164f, ShareLinkService.this.f9165g, ShareLinkService.this.f9166h, ShareLinkService.this.f9172n);
        }
    }

    public void createExpectedWifiConnection(int i) {
        IWifiP2p iWifiP2p;
        int iM12a;
        String strM21j;
        try {
            EasyLogger.debug(f9144J, "expectConnectType = " + i);
            if (i == 1002) {
                int i2 = this.f9176r;
                if (i2 != 1001 || this.f9168j == null) {
                    if (i2 == 1002 && this.f9169k != null) {
                        EasyLogger.debug(f9144J, "phone expect ap connection, car only supports ap connection,direct createSoftAp.");
                        this.f9169k.createSoftAp(this.f9178t);
                        return;
                    }
                    if (i2 != 1003 || this.f9169k == null) {
                        return;
                    }
                    EasyLogger.debug(f9144J, "phone expect ap connection, car supports ap&P2P connection, need close possible p2p connection and open ap");
                    this.f9179u.setConnectType(1002);
                    IWifiP2p iWifiP2p2 = this.f9168j;
                    if (iWifiP2p2 != null) {
                        iWifiP2p2.unregisterWifiP2pObserver(this.f9157H);
                        this.f9168j.close();
                    }
                    IWifiAp iWifiAp = this.f9169k;
                    if (iWifiAp != null) {
                        iWifiAp.registerWifiApObserver(this.f9158I);
                        this.f9169k.open();
                        return;
                    }
                    return;
                }
                EasyLogger.debug(f9144J, "phone expect ap connection, but car only supports p2p connection,use p2p instead, createGroupForClient.");
                iWifiP2p = this.f9168j;
                iM12a = this.f9179u.getBand();
                strM21j = this.f9179u.getP2pMac();
            } else {
                int i3 = this.f9176r;
                if (i3 != 1001 || this.f9168j == null) {
                    if (i3 != 1003) {
                        EasyLogger.debug(f9144J, "the phone don't support wireless connection. please plugin USB to connect");
                        cancelActiveConnection();
                        this.f9182x.notifyConnectFailed(3);
                        return;
                    }
                    EasyLogger.debug(f9144J, "phone expect p2p connection, car supports ap&P2P connection,need close possible ap connection and open p2p");
                    this.f9179u.setConnectType(1001);
                    IWifiAp iWifiAp2 = this.f9169k;
                    if (iWifiAp2 != null) {
                        iWifiAp2.unregisterWifiApObserver(this.f9158I);
                        this.f9169k.close();
                    }
                    IWifiP2p iWifiP2p3 = this.f9168j;
                    if (iWifiP2p3 != null) {
                        iWifiP2p3.registerWifiP2pObserver(this.f9157H);
                        this.f9168j.open();
                        return;
                    }
                    return;
                }
                EasyLogger.info(f9144J, "phone expect p2p connection, car only supports p2p connection, direct createGroupForClient.");
                iWifiP2p = this.f9168j;
                iM12a = this.f9179u.getBand();
                strM21j = this.f9179u.getP2pMac();
            }
            iWifiP2p.createGroupForClient(iM12a, strM21j, this.f9178t);
        } catch (Exception e2) {
            EasyLogger.error(f9144J, "create wifi connection failed." + e2.toString());
            cancelActiveConnection();
        }
    }

    private boolean fallbackToWifiApAfterP2pOpenFailed() {
        if (this.f9176r != 1003 || this.f9169k == null || this.f9179u.getConnectType() != 1001) {
            return false;
        }
        try {
            EasyLogger.warn(f9144J, "P2P open failed in auto mode, fallback to Wi-Fi AP.");
            this.f9179u.setConnectType(1002);
            IWifiP2p iWifiP2p = this.f9168j;
            if (iWifiP2p != null) {
                iWifiP2p.unregisterWifiP2pObserver(this.f9157H);
                this.f9168j.close();
            }
            this.f9169k.registerWifiApObserver(this.f9158I);
            this.f9169k.open();
            return true;
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9144J, "Fallback to Wi-Fi AP failed.", e2);
            return false;
        }
    }

    private boolean openWifiApFirstWhenP2pUnavailable() throws RemoteException {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(ConnectionStateHolder.TRANSPORT_WIFI);
        if (wifiManager == null || wifiManager.isWifiEnabled()) {
            return false;
        }
        EasyLogger.warn(f9144J, "Wi-Fi is disabled in auto mode, open Wi-Fi AP before P2P.");
        setOpenTimeoutEnabled(true);
        this.f9179u.setConnectType(1002);
        this.f9169k.registerWifiApObserver(this.f9158I);
        this.f9169k.open();
        return true;
    }

    private boolean retryP2pAcceptWindowIfWaitingForPhone() {
        if (this.f9177s != ConnectionStateHolder.CONNECT_TYPE_WIFI_P2P || this.f9168j == null || !this.f9173o || !this.f9180v.isIdleOrDisconnected()) {
            return false;
        }
        EasyLogger.info(f9144J, "No phone joined P2P group, recreate group to keep discovery window alive.");
        this.f9184z.removeCallbacks(this.restartP2pAcceptWindowTask);
        this.f9184z.postDelayed(this.restartP2pAcceptWindowTask, P2P_ACCEPT_RETRY_DELAY_MS);
        return true;
    }

    private void restartP2pAcceptWindow() {
        if (this.f9177s != ConnectionStateHolder.CONNECT_TYPE_WIFI_P2P || this.f9168j == null || !this.f9173o || !this.f9180v.isIdleOrDisconnected()) {
            EasyLogger.info(f9144J, "Skip P2P accept window restart, state=" + this.f9180v.getState());
            return;
        }
        try {
            EasyLogger.info(f9144J, "Restart P2P accept window for phone discovery.");
            this.f9168j.createGroupForClient(this.f9179u.getBand(), this.f9179u.getP2pMac(), this.f9178t);
        } catch (RemoteException e2) {
            EasyLogger.errorWithThrowable(f9144J, "Restart P2P accept window failed.", e2);
        }
    }

    public void setAuthTimeoutEnabled(boolean z) {
        EasyLogger.debug(f9144J, "enableAuthTimeout: " + z);
        this.f9184z.removeCallbacks(this.f9151B);
        if (z) {
            this.f9184z.postDelayed(this.f9151B, 20000L);
        }
    }

    private String formatTimestampMillis(String str) {
        try {
            return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault(Locale.Category.FORMAT)).parse(str).getTime());
        } catch (ParseException e2) {
            EasyLogger.errorWithThrowable(f9144J, "get time failed", e2);
            return null;
        }
    }

    public boolean isNetworkTimeBeforeAuthBaseline() {
        long[] jArr = new long[1];
        Thread thread = new Thread(new ReadBluetoothAddressTask(jArr));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e2) {
            EasyLogger.info(f9144J, String.format("getTimeThread error" + e2.toString(), new Object[0]));
        }
        return jArr[0] < Long.parseLong(formatTimestampMillis("2023-4-3 00:00:00"));
    }

    public void notifyWifiConnectFailedIfActive(int i) {
        if (!this.f9180v.isWifiConnecting() && !this.f9180v.isWifiConnected()) {
            EasyLogger.info(f9144J, "ignored wifi connect event");
        } else {
            cancelActiveConnection();
            this.f9182x.notifyConnectFailed(i);
        }
    }

    public void startAuthService(String str) {
        if (this.f9170l != null) {
            EasyLogger.info(f9144J, "stop existing auth service");
            try {
                this.f9170l.stop();
            } catch (java.io.IOException e) {
                EasyLogger.errorWithThrowable(f9144J, "stop auth service error", e);
            }
        }
        UCarAuthService c0021e = new UCarAuthService(this, new AuthCallback());
        this.f9170l = c0021e;
        c0021e.start(str);
    }

    public void setOpenTimeoutEnabled(boolean z) {
        EasyLogger.debug(f9144J, "enableOpenTimeout: " + z);
        if (z) {
            this.f9184z.postDelayed(this.f9150A, 10000L);
        } else {
            this.f9184z.removeCallbacks(this.f9150A);
        }
    }

    public void closeBluetoothLeService() {
        try {
            IBluetoothLe iBluetoothLe = this.f9167i;
            if (iBluetoothLe != null) {
                iBluetoothLe.unregisterBluetoothLeObserver(this.f9156G);
                this.f9167i.close();
            }
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9144J, "closeBluetooth failed", e2);
        }
    }

    public void setUsbDeviceScanningEnabled(boolean z) {
        EasyLogger.info(f9144J, "set usb device scanning: " + z);
        if (!z) {
            AoaConnectionManager.getInstance().m229E();
            return;
        }
        if (this.f9180v.isIdleOrDisconnected()) {
            AoaConnectionManager.getInstance().registerReceiverAndStartConnect();
            return;
        }
        EasyLogger.debug(f9144J, "Can't enable usb device scanning, current state is " + ConnectionStateHolder.getInstance().getState());
    }

    public void setUserConfirmTimeoutEnabled(boolean z) {
        EasyLogger.debug(f9144J, "enableUserConfirmTimeout: " + z);
        this.f9184z.removeCallbacks(this.f9152C);
        if (z) {
            this.f9184z.postDelayed(this.f9152C, 15000L);
        }
    }

    public void deinitializeAoaConnection() {
        AoaConnectionManager.getInstance().deinitialize();
    }

    public void cancelActiveConnection() {
        try {
            IBluetoothLe iBluetoothLe = this.f9167i;
            if (iBluetoothLe != null) {
                iBluetoothLe.disconnect();
            }
            IWifiP2p iWifiP2p = this.f9168j;
            if (iWifiP2p != null && this.f9177s == 1001) {
                iWifiP2p.cancelConnect();
            }
            IWifiAp iWifiAp = this.f9169k;
            if (iWifiAp == null || this.f9177s != 1002) {
                return;
            }
            iWifiAp.cancelConnect();
        } catch (Exception e2) {
            EasyLogger.warnWithThrowable(f9144J, "Disconnect failed.", e2);
        }
    }

    public void handleOpenTimeout() {
        EasyLogger.warn(f9144J, "No response in 10000current state:" + this.f9180v.getState());
        if (this.f9180v.isIdleOrDisconnected()) {
            EasyLogger.warn(f9144J, "take this as open failure.");
            closeShareLink();
            this.f9182x.notifyOpenResult(false);
        }
    }

    public void handleAuthTimeout() {
        EasyLogger.warn(f9144J, "key negotiation not finished in 20000");
        resetConnectionResources();
        this.f9182x.notifyConnectFailed(6);
    }

    public long getNetworkTimeMillis() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            URLConnection uRLConnectionOpenConnection = new URL("https://www.baidu.com").openConnection();
            uRLConnectionOpenConnection.connect();
            return uRLConnectionOpenConnection.getDate();
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9144J, "get net data exception", e2);
            return jCurrentTimeMillis;
        }
    }

    public void handleUserConfirmTimeout() {
        EasyLogger.warn(f9144J, "user confirm not finished in 15000");
        this.f9182x.notifyUserInterventionNeeded(true);
    }

    public void handleWifiDisconnected() {
        EasyLogger.warn(f9144J, "wifi onDisconnected, state=" + this.f9180v.getState());
        if (!this.f9180v.isWifiConnecting() && !this.f9180v.isWifiConnected()) {
            EasyLogger.info(f9144J, "ignored wifi p2p event");
            return;
        }
        WakeLockHolder.release();
        setAuthTimeoutEnabled(false);
        cancelActiveConnection();
        if (this.f9170l != null) {
            EasyLogger.warn(f9144J, "key negotiation ongoing");
            try {
                this.f9170l.stop();
            } catch (java.io.IOException e) {
                EasyLogger.errorWithThrowable(f9144J, "stop auth service error", e);
            }
            this.f9182x.notifyConnectFailed(6);
        } else {
            this.f9182x.notifyDisconnected();
        }
        this.f9180v.setState(ConnectionStateHolder.STATE_IDLE);
    }

    private void resetConnectionResources() {
        cancelActiveConnection();
        deinitializeAoaConnection();
        this.f9180v.setState(ConnectionStateHolder.STATE_IDLE);
        this.f9179u.reset();
    }

    private void initializeAoaConnection() {
        if (this.f9159a != null) {
            this.f9162d = HexCodec.encodeBase64NoPadding(Arrays.copyOfRange(HexCodec.decode(this.f9159a.toLowerCase()), 0, 6));
        }
        AoaConnectionManager.getInstance().initialize(getApplicationContext(), this.f9160b, this.f9159a, this.f9164f, this.f9162d, this.f9165g, this.f9166h);
        AoaConnectionManager.getInstance().m233h(new AoaConnectionCallback());
    }

    private void bindBluetoothLeService() {
        EasyLogger.debug(f9144J, "start BluetoothLe service");
        Intent intent = new Intent();
        intent.setClass(this, BluetoothLeService.class);
        bindService(intent, this.f9153D, 1);
    }

    private void bindTransportServices() {
        bindBluetoothLeService();
        bindWifiServices();
        initializeAoaConnection();
    }

    private void bindWifiServices() {
        EasyLogger.debug(f9144J, "start Wifi service");
        bindService(new Intent().setClass(this, WifiP2pService.class), this.f9154E, 1);
        bindService(new Intent().setClass(this, WifiApService.class), this.f9155F, 1);
    }

    public void openWirelessConnection(int i, int i2) {
        this.f9176r = i;
        this.f9178t = i2;
        this.f9173o = false;
        this.f9184z.removeCallbacks(this.restartP2pAcceptWindowTask);
        if (i != 1001 && i != 1002 && i != 1003) {
            EasyLogger.error(f9144J, "wifi connectType error !!!");
        } else {
            this.f9180v.setState(ConnectionStateHolder.STATE_IDLE);
            WorkThreadExecutor.submit(new OpenWifiConnectionTask(), "Open() waiting services");
        }
    }

    public void closeShareLink() {
        EasyLogger.debug(f9144J, "Closing...");
        WakeLockHolder.release();
        this.f9184z.removeCallbacks(this.restartP2pAcceptWindowTask);
        try {
            this.f9175q = false;
            IWifiP2p iWifiP2p = this.f9168j;
            if (iWifiP2p != null) {
                iWifiP2p.unregisterWifiP2pObserver(this.f9157H);
                this.f9168j.close();
            }
            IWifiAp iWifiAp = this.f9169k;
            if (iWifiAp != null) {
                iWifiAp.unregisterWifiApObserver(this.f9158I);
                this.f9169k.close();
            }
            closeBluetoothLeService();
            UCarAuthService c0021e = this.f9170l;
            if (c0021e != null) {
                c0021e.stop();
            }
            AoaConnectionManager.getInstance().deinitialize();
            this.f9180v.setState(ConnectionStateHolder.STATE_IDLE);
            this.f9179u.reset();
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9144J, "close failed", e2);
        }
    }

    public void disconnectShareLink() {
        EasyLogger.info(f9144J, "Disconnect.");
        this.f9184z.removeCallbacks(this.restartP2pAcceptWindowTask);
        setOpenTimeoutEnabled(false);
        resetConnectionResources();
        this.f9182x.notifyDisconnected();
    }

    public void startBluetoothScan() {
        IBluetoothLe iBluetoothLe = this.f9167i;
        if (iBluetoothLe != null) {
            try {
                iBluetoothLe.startScan();
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9144J, "Scan devices failed.", e2);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        this.f9159a = intent.getStringExtra("car_id");
        this.f9160b = intent.getStringExtra("mod_id");
        this.f9163e = intent.getStringExtra("car_name");
        this.f9164f = intent.getStringExtra("car_short_name");
        this.f9165g = intent.getStringExtra("protocol_version");
        this.f9166h = intent.getStringExtra("car_vendor_custom_data");
        StringBuilder sb = new StringBuilder();
        sb.append("onBind called id = ");
        sb.append(this.f9159a != null);
        sb.append(" mod = ");
        sb.append(this.f9160b != null);
        sb.append(" name = ");
        sb.append(this.f9164f != null);
        sb.append(" custom data = ");
        sb.append(this.f9166h != null);
        EasyLogger.debug(f9144J, sb.toString());
        bindTransportServices();
        return this.f9183y;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeShareLink();
        this.f9182x.clearObservers();
        try {
            unbindService(this.f9153D);
        } catch (Exception unused) {
        }
        try {
            unbindService(this.f9154E);
        } catch (Exception unused2) {
        }
        try {
            unbindService(this.f9155F);
        } catch (Exception unused3) {
        }
    }

    public void stopBluetoothScan() {
        IBluetoothLe iBluetoothLe = this.f9167i;
        if (iBluetoothLe != null) {
            try {
                iBluetoothLe.stopScan();
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9144J, "Stop scan failed.", e2);
            }
        }
    }
}
