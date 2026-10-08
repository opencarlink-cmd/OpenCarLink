package com.share.connect.wifip2p;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.share.connect.wifip2p.IWifiP2p;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.core.ConnectionStateHolder;
import com.ucarhu.demo.sharelink.util.DebugTimer;
import com.ucarhu.demo.sharelink.util.WorkThreadExecutor;
import com.ucarhu.demo.sharelink.wifi.CountdownActionListener;
import com.ucarhu.demo.sharelink.wifi.LoggingActionListener;
import com.ucarhu.demo.sharelink.wifi.NetworkInterfaceUtils;
import com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver;
import com.ucarhu.demo.sharelink.wifi.WifiChannelUtils;
import com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pGroupInfo;
import com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pConfigBuilderProxy;
import com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy;

public class WifiP2pService extends Service {

    private static final String f9401q = "WifiP2pService";

    private static final int f9402r = 5000;

    private static final int f9403s = 20000;

    private static final int f9404t = 30000;

    private static final int f9405u = 12;

    private static final int f9406v = 13;

    private static final int f9407w = 14;

    private static final int f9408x = 50;

    private WifiP2pManager f9410b;

    private WifiP2pManager.Channel f9411c;

    private WifiManager f9412d;

    private Runnable f9413e;

    private Handler f9414f;

    private CreateWifiP2pGroupTask f9417i;

    private Thread f9418j;

    private BroadcastReceiver f9422n;

    private String f9423o;

    private final WifiP2pServiceBinder f9409a = new WifiP2pServiceBinder(this, null);

    private volatile boolean f9415g = false;

    private boolean f9416h = false;

    private final Runnable f9419k = new Runnable() {
        @Override
        public final void run() {
            WifiP2pService.this.m9877e();
        }
    };

    private final Runnable f9420l = new Runnable() {
        @Override
        public final void run() {
            WifiP2pService.this.m9881f();
        }
    };

    private final WifiP2pStateReceiver f9421m = new WifiP2pStateReceiver();

    private final List<WifiP2pObserver> f9424p = new ArrayList();

    public class OpenWifiP2pTask implements Runnable {

        public final Runnable f9425a;

        public OpenWifiP2pTask(Runnable runnable) {
            this.f9425a = runnable;
        }

        @Override
        public void run() {
            if (WifiP2pService.this.f9411c != null) {
                TimeUnit timeUnit = TimeUnit.SECONDS;
                try {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    WifiP2pService.this.m9849a(new CountdownActionListener(WifiP2pService.f9401q, "Stop peer discovery", countDownLatch));
                    if (!countDownLatch.await(5L, timeUnit)) {
                        EasyLogger.warn(WifiP2pService.f9401q, "Stop peer discovery timeout.");
                    }
                    EasyLogger.info(WifiP2pService.f9401q, "Removing group...");
                    CountDownLatch countDownLatch2 = new CountDownLatch(1);
                    WifiP2pService.this.f9410b.removeGroup(WifiP2pService.this.f9411c, new CountdownActionListener(WifiP2pService.f9401q, "Remove group", countDownLatch2));
                    if (!countDownLatch2.await(5L, timeUnit)) {
                        EasyLogger.warn(WifiP2pService.f9401q, "Remove group timeout.");
                    }
                    EasyLogger.info(WifiP2pService.f9401q, "Reset p2p channel...");
                    CountDownLatch countDownLatch3 = new CountDownLatch(1);
                    if (WifiP2pManagerProxy.setWifiP2pChannel(WifiP2pService.this.f9410b, WifiP2pService.this.f9411c, 0, new CountdownActionListener(WifiP2pService.f9401q, "Reset channel", countDownLatch3)) && !countDownLatch3.await(5L, timeUnit)) {
                        EasyLogger.warn(WifiP2pService.f9401q, "Reset p2p channel timeout.");
                    }
                    EasyLogger.info(WifiP2pService.f9401q, "Cancel invited requests...");
                    CountDownLatch countDownLatch4 = new CountDownLatch(1);
                    WifiP2pService.this.f9410b.cancelConnect(WifiP2pService.this.f9411c, new CountdownActionListener(WifiP2pService.f9401q, "Cancel invited requests", countDownLatch4));
                    if (!countDownLatch4.await(5L, timeUnit)) {
                        EasyLogger.warn(WifiP2pService.f9401q, "Cancel invited requests timeout.");
                    }
                    WifiP2pManagerProxy.deleteAllPersistentGroups(WifiP2pService.this.f9410b, WifiP2pService.this.f9411c);
                } catch (Exception e2) {
                    EasyLogger.errorWithThrowable(WifiP2pService.f9401q, "Latch await failed.", e2);
                }
            } else {
                EasyLogger.warn(WifiP2pService.f9401q, "Channel is null, resetP2pNetworks is not executable");
            }
            if (this.f9425a != null) {
                WifiP2pService.this.f9414f.post(this.f9425a);
            }
        }
    }

    public class WifiP2pInitialConnectionListener implements WifiP2pStateReceiver.a {

        public final WifiP2pGroupInfo f9427a;

        public class a implements WifiP2pManager.GroupInfoListener {
            public a() {
            }

            public void m9918a(WifiP2pGroup wifiP2pGroup, WifiP2pGroupInfo c0038a) {
                WifiP2pService.this.m9848a(wifiP2pGroup, c0038a.getFrequency());
            }

            @Override
            public void onGroupInfoAvailable(final WifiP2pGroup wifiP2pGroup) {
                EasyLogger.info(WifiP2pService.f9401q, "Actually group: " + wifiP2pGroup);
                if (wifiP2pGroup == null) {
                    EasyLogger.warn(WifiP2pService.f9401q, "Group in broadcast isn't null but request return null.");
                    return;
                }
                DebugTimer.endEvent("P2p-createGroup");
                WifiP2pInitialConnectionListener c1080b = WifiP2pInitialConnectionListener.this;
                WifiP2pService wifiP2pService = WifiP2pService.this;
                final WifiP2pGroupInfo c0038a = c1080b.f9427a;
                wifiP2pService.f9413e = new Runnable() {
                    @Override
                    public final void run() {
                        a.this.m9918a(wifiP2pGroup, c0038a);
                    }
                };
                WifiP2pService.this.f9413e.run();
            }
        }

        public class b implements WifiP2pStateReceiver.a {
            public b() {
            }

            @Override
            public void onConnectionStateChanged(WifiP2pInfo wifiP2pInfo, WifiP2pGroup wifiP2pGroup, NetworkInfo networkInfo) {
                boolean zM9865a;
                if (wifiP2pGroup != null) {
                    zM9865a = WifiP2pService.this.m9865a(wifiP2pGroup.getClientList());
                    EasyLogger.info(WifiP2pService.f9401q, "hasValidClient=" + zM9865a + ", clientConnected=" + WifiP2pService.this.f9415g);
                } else {
                    zM9865a = false;
                }
                if (zM9865a) {
                    WifiP2pService.this.m9867b(false);
                    if (WifiP2pService.this.f9415g || wifiP2pInfo == null) {
                        return;
                    }
                    WifiP2pService.this.f9415g = true;
                    DebugTimer.endEvent("P2p-clientJoin");
                    WifiP2pService.this.m9913j();
                    return;
                }
                if (WifiP2pService.this.m9873c()) {
                    EasyLogger.info(WifiP2pService.f9401q, "ignored acceptance timeout");
                } else if (WifiP2pService.this.f9415g) {
                    WifiP2pService.this.f9415g = false;
                    WifiP2pService.this.m9889k();
                }
            }
        }

        public WifiP2pInitialConnectionListener(WifiP2pGroupInfo c0038a) {
            this.f9427a = c0038a;
        }

        public void m9916a(WifiP2pGroup wifiP2pGroup, WifiP2pGroupInfo c0038a) {
            WifiP2pService.this.m9848a(wifiP2pGroup, c0038a.getFrequency());
        }

        @Override
        public void onConnectionStateChanged(WifiP2pInfo wifiP2pInfo, final WifiP2pGroup wifiP2pGroup, NetworkInfo networkInfo) {
            if (wifiP2pInfo == null || !wifiP2pInfo.groupFormed || !wifiP2pInfo.isGroupOwner || wifiP2pGroup == null) {
                return;
            }
            if (WifiP2pService.this.m9876d()) {
                WifiP2pService.this.f9410b.requestGroupInfo(WifiP2pService.this.f9411c, new a());
            } else {
                DebugTimer.endEvent("P2p-createGroup");
                WifiP2pService wifiP2pService = WifiP2pService.this;
                final WifiP2pGroupInfo c0038a = this.f9427a;
                wifiP2pService.f9413e = new Runnable() {
                    @Override
                    public final void run() {
                        WifiP2pInitialConnectionListener.this.m9916a(wifiP2pGroup, c0038a);
                    }
                };
                WifiP2pService.this.f9413e.run();
            }
            DebugTimer.startEvent("P2p-clientJoin");
            WifiP2pService.this.f9421m.setConnectionStateListener(new b());
        }
    }

    public class WifiP2pReconnectListener implements WifiP2pStateReceiver.a {

        private boolean f9431a = false;

        public WifiP2pReconnectListener() {
        }

        @Override
        public void onConnectionStateChanged(WifiP2pInfo wifiP2pInfo, WifiP2pGroup wifiP2pGroup, NetworkInfo networkInfo) {
            String str;
            if (!this.f9431a && wifiP2pInfo != null && wifiP2pInfo.groupFormed && !wifiP2pInfo.isGroupOwner && wifiP2pGroup != null && TextUtils.equals(wifiP2pGroup.getOwner().deviceAddress, WifiP2pService.this.f9423o)) {
                this.f9431a = true;
                WifiP2pService.this.m9899q();
                WifiP2pService.this.m9872c(false);
                WifiP2pService.this.m9862a(wifiP2pInfo.groupOwnerAddress, wifiP2pGroup.getInterface(), wifiP2pInfo.isGroupOwner);
            }
            if (wifiP2pInfo != null && this.f9431a && !wifiP2pInfo.groupFormed) {
                this.f9431a = false;
                WifiP2pService.this.m9894n();
                WifiP2pService.this.m9889k();
            }
            if (networkInfo != null) {
                String typeName = networkInfo.getTypeName();
                if (TextUtils.isEmpty(typeName) || !typeName.toUpperCase().contains("P2P") || networkInfo.getState() != NetworkInfo.State.DISCONNECTED || networkInfo.getDetailedState() != NetworkInfo.DetailedState.FAILED) {
                    return;
                }
                if (this.f9431a) {
                    this.f9431a = false;
                    WifiP2pService.this.m9894n();
                    WifiP2pService.this.m9889k();
                    return;
                }
                str = "NetworkInfo.getDetailedState(): FAILED";
            } else {
                str = "NetworkInfo is null FAILED";
            }
            EasyLogger.warn(WifiP2pService.f9401q, str);
            WifiP2pService.this.m9894n();
            WifiP2pService.this.m9886i();
        }
    }

    public class RemoveWifiP2pGroupTask implements Runnable {

        public final InetAddress f9433a;

        public final String f9434b;

        public RemoveWifiP2pGroupTask(InetAddress inetAddress, String str) {
            this.f9433a = inetAddress;
            this.f9434b = str;
        }

        @Override
        public void run() {
            try {
                EasyLogger.info(WifiP2pService.f9401q, "Group owner address: " + this.f9433a.getHostAddress() + ", local address: " + NetworkInterfaceUtils.getHostAddressByInterface(NetworkInterface.getByName(this.f9434b)));
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= 10) {
                        break;
                    }
                    if (this.f9433a.isReachable(200)) {
                        EasyLogger.info(WifiP2pService.f9401q, "isReachable=true, ready to notifyOnConnected.");
                        DebugTimer.endEvent("P2p-connectGroup");
                        WifiP2pService.this.m9913j();
                        z = true;
                        break;
                    }
                    EasyLogger.warn(WifiP2pService.f9401q, "isReachable=false, waiting...");
                    Thread.sleep(200L);
                    i++;
                }
                if (z) {
                    return;
                }
                EasyLogger.error(WifiP2pService.f9401q, "Network can't reach.");
                WifiP2pService.this.m9886i();
            } catch (IOException | InterruptedException e2) {
                EasyLogger.errorWithThrowable(WifiP2pService.f9401q, "Checking isReachable exception", e2);
                WifiP2pService.this.m9886i();
            }
        }
    }

    public class WifiP2pScreenStateReceiver extends BroadcastReceiver {
        public WifiP2pScreenStateReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("wifi_state", 4) == 3) {
                WifiP2pService.this.unregisterWifiStateReceiver();
                WifiP2pService.this.m9866b();
            }
        }
    }

    public class WifiP2pDeviceInfoListener implements WifiP2pManager.DeviceInfoListener {
        public WifiP2pDeviceInfoListener() {
        }

        @Override
        public void onDeviceInfoAvailable(WifiP2pDevice wifiP2pDevice) {
            if (wifiP2pDevice != null) {
                WifiP2pService wifiP2pService = WifiP2pService.this;
                wifiP2pService.m9861a(wifiP2pDevice.deviceAddress, WifiChannelUtils.is5GHzSupported(wifiP2pService.f9412d) ? 1 : 0);
            } else {
                EasyLogger.error(WifiP2pService.f9401q, "Device info is null.");
                WifiP2pService.this.m9891l();
            }
        }
    }

    public class WifiP2pThisDeviceListener implements WifiP2pStateReceiver.c {

        public class a implements WifiP2pManager.DeviceInfoListener {
            public a() {
            }

            @Override
            public void onDeviceInfoAvailable(WifiP2pDevice wifiP2pDevice) {
                if (wifiP2pDevice == null) {
                    EasyLogger.error(WifiP2pService.f9401q, "Device info is null.");
                    return;
                }
                EasyLogger.debug(WifiP2pService.f9401q, "Device changed: " + wifiP2pDevice);
                WifiP2pService.this.m9860a(wifiP2pDevice.deviceAddress);
            }
        }

        public WifiP2pThisDeviceListener() {
        }

        @Override
        public void onThisDeviceChanged(WifiP2pDevice wifiP2pDevice) {
            EasyLogger.info(WifiP2pService.f9401q, "Requesting device info...");
            WifiP2pService.this.f9410b.requestDeviceInfo(WifiP2pService.this.f9411c, new a());
        }
    }

    public class WifiP2pOwnerDeviceListener implements WifiP2pStateReceiver.c {
        public WifiP2pOwnerDeviceListener() {
        }

        @Override
        public void onThisDeviceChanged(WifiP2pDevice wifiP2pDevice) {
            WifiP2pService.this.f9421m.setThisDeviceChangedListener(null);
            WifiP2pService wifiP2pService = WifiP2pService.this;
            wifiP2pService.m9861a(wifiP2pDevice.deviceAddress, WifiChannelUtils.is5GHzSupported(wifiP2pService.f9412d) ? 1 : 0);
        }
    }

    public class ConnectWifiP2pTask implements Runnable {

        public final WifiP2pGroupInfo f9441a;

        public final WifiP2pConfig f9442b;

        public final int f9443c;

        public class a extends LoggingActionListener {
            public a(String str, String str2) {
                super(str, str2);
            }

            @Override
            public void onFailure(int i) {
                super.onFailure(i);
                WifiP2pService.this.m9915o();
                WifiP2pService.this.m9883g();
            }
        }

        public class b extends LoggingActionListener {

            public final WifiP2pManager.ActionListener f9446c;

            public b(String str, String str2, WifiP2pManager.ActionListener actionListener) {
                super(str, str2);
                this.f9446c = actionListener;
            }

            @Override
            public void onFailure(int i) {
                super.onFailure(i);
                ConnectWifiP2pTask.this.f9441a.setFrequency(0);
                WifiP2pService.this.f9410b.createGroup(WifiP2pService.this.f9411c, this.f9446c);
            }

            @Override
            public void onSuccess() {
                super.onSuccess();
                WifiP2pService.this.f9410b.createGroup(WifiP2pService.this.f9411c, this.f9446c);
            }
        }

        public ConnectWifiP2pTask(WifiP2pGroupInfo c0038a, WifiP2pConfig wifiP2pConfig, int i) {
            this.f9441a = c0038a;
            this.f9442b = wifiP2pConfig;
            this.f9443c = i;
        }

        @Override
        public void run() {
            DebugTimer.startEvent("P2p-createGroup");
            WifiP2pService.this.m9845a(this.f9441a);
            a aVar = new a(WifiP2pService.f9401q, "Create group");
            if (WifiP2pService.this.m9876d() && this.f9442b != null) {
                EasyLogger.debug(WifiP2pService.f9401q, "Method selected: Q");
                WifiP2pService.this.f9410b.createGroup(WifiP2pService.this.f9411c, this.f9442b, aVar);
                return;
            }
            EasyLogger.debug(WifiP2pService.f9401q, "Method selected: Classic");
            WifiP2pManagerProxy.setWifiP2pChannel(WifiP2pService.this.f9410b, WifiP2pService.this.f9411c, this.f9443c, new b(WifiP2pService.f9401q, "Set channel to " + this.f9443c, aVar));
        }
    }

    public class StopWifiP2pTask implements Runnable {

        public final WifiP2pConfig f9448a;

        public class a extends LoggingActionListener {
            public a(String str, String str2) {
                super(str, str2);
            }

            @Override
            public void onFailure(int i) {
                super.onFailure(i);
                WifiP2pService.this.m9894n();
                WifiP2pService.this.m9886i();
            }
        }

        public StopWifiP2pTask(WifiP2pConfig wifiP2pConfig) {
            this.f9448a = wifiP2pConfig;
        }

        @Override
        public void run() {
            DebugTimer.startEvent("P2p-connectGroup");
            WifiP2pService.this.m9897p();
            WifiP2pService.this.f9410b.connect(WifiP2pService.this.f9411c, this.f9448a, new a(WifiP2pService.f9401q, "Connect group on Q"));
        }
    }

    public class StartWifiP2pDiscoveryTask implements Runnable {
        public StartWifiP2pDiscoveryTask() {
        }

        @Override
        public void run() {
            DebugTimer.startEvent("P2p-discoverPeer");
            WifiP2pService.this.m9863a(false);
        }
    }

    public class RefreshWifiP2pPeersTask implements Runnable {

        public class a implements WifiP2pStateReceiver.b {
            public a() {
            }

            @Override
            public void onPeersChanged(WifiP2pDeviceList wifiP2pDeviceList) {
                WifiP2pDevice wifiP2pDevice = wifiP2pDeviceList.get(WifiP2pService.this.f9423o);
                if (wifiP2pDevice != null) {
                    WifiP2pService.this.f9414f.removeCallbacks(WifiP2pService.this.f9417i);
                    WifiP2pService.this.f9421m.setPeersChangedListener(null);
                    DebugTimer.endEvent("P2p-discoverPeer");
                    WifiP2pService.this.m9846a(wifiP2pDevice);
                }
            }
        }

        public class b extends LoggingActionListener {
            public b(String str, String str2) {
                super(str, str2);
            }

            @Override
            public void onFailure(int i) {
                super.onFailure(i);
                WifiP2pService.this.f9414f.removeCallbacks(WifiP2pService.this.f9417i);
                WifiP2pService.this.m9894n();
                WifiP2pService.this.m9886i();
            }
        }

        public RefreshWifiP2pPeersTask() {
        }

        @Override
        public void run() {
            WifiP2pService.this.f9421m.setPeersChangedListener(new a());
            WifiP2pService.this.f9410b.discoverPeers(WifiP2pService.this.f9411c, new b(WifiP2pService.f9401q, "Discover peers"));
        }
    }

    public class RemoveGroupActionListener extends LoggingActionListener {
        public RemoveGroupActionListener(String str, String str2) {
            super(str, str2);
        }

        @Override
        public void onFailure(int i) {
            super.onFailure(i);
            WifiP2pService.this.m9894n();
            WifiP2pService.this.m9886i();
        }
    }

    public class CreateWifiP2pGroupTask implements Runnable {
        private CreateWifiP2pGroupTask() {
        }

        public CreateWifiP2pGroupTask(WifiP2pService wifiP2pService, WifiP2pScreenStateReceiver c1083e) {
            this();
        }

        @Override
        public void run() {
            EasyLogger.warn(WifiP2pService.f9401q, "Haven't found p2p device in a single scan duration, rescanning.");
            WifiP2pService.this.m9863a(true);
        }
    }

    public class WifiP2pServiceBinder extends IWifiP2p.Stub {
        private WifiP2pServiceBinder() {
        }

        public WifiP2pServiceBinder(WifiP2pService wifiP2pService, WifiP2pScreenStateReceiver c1083e) {
            this();
        }

        @Override
        public void cancelConnect() {
            WifiP2pService.this.m9872c(false);
            WifiP2pService.this.m9867b(false);
            WifiP2pService.this.m9915o();
        }

        @Override
        public void close() {
            WifiP2pService.this.m9908a();
        }

        @Override
        public void connectGroupOwner(String str, String str2, String str3, int i) {
            WifiP2pService.this.m9911a(str, str2, str3, i);
        }

        @Override
        public void createGroupForClient(int i, String str, int i2) {
            EasyLogger.debug(WifiP2pService.f9401q, "Creating group for client: " + i + ", " + str);
            WifiP2pService.this.m9867b(true);
            if (WifiP2pService.this.f9413e != null) {
                EasyLogger.debug(WifiP2pService.f9401q, "reuse existing Wifi p2p group");
                WifiP2pService.this.f9413e.run();
            } else if (WifiP2pService.this.f9416h) {
                EasyLogger.debug(WifiP2pService.f9401q, "createWifiP2pGroup func has been called, but Don't receive p2p broadcast!");
            } else {
                WifiP2pService.this.f9416h = true;
                WifiP2pService.this.m9909a(i, i2);
            }
        }

        @Override
        public void open() {
            WifiP2pService.this.m9914m();
        }

        @Override
        public void registerWifiP2pObserver(WifiP2pObserver wifiP2pObserver) {
            WifiP2pService.this.m9910a(wifiP2pObserver);
        }

        @Override
        public void unregisterWifiP2pObserver(WifiP2pObserver wifiP2pObserver) {
            WifiP2pService.this.m9912b(wifiP2pObserver);
        }
    }

    public void m9845a(WifiP2pGroupInfo c0038a) {
        this.f9415g = false;
        this.f9421m.setConnectionStateListener(new WifiP2pInitialConnectionListener(c0038a));
    }

    public void m9846a(WifiP2pDevice wifiP2pDevice) {
        EasyLogger.debug(f9401q, "Found target device: " + wifiP2pDevice.deviceAddress);
        DebugTimer.startEvent("P2p-connectGroup");
        WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
        wifiP2pConfig.deviceAddress = wifiP2pDevice.deviceAddress;
        m9897p();
        this.f9410b.connect(this.f9411c, wifiP2pConfig, new RemoveGroupActionListener(f9401q, "Connect device: " + wifiP2pConfig.deviceAddress));
    }

    public void m9847a(WifiP2pDeviceList wifiP2pDeviceList) {
        if (m9865a(wifiP2pDeviceList.getDeviceList())) {
            return;
        }
        EasyLogger.warn(f9401q, "No client join. notifyOnDisconnected");
        this.f9415g = false;
        m9894n();
        m9889k();
    }

    public void m9848a(WifiP2pGroup wifiP2pGroup, int i) {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onGroupCreated(wifiP2pGroup, i);
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onGroupCreated(...) failed.", e2);
                }
            }
        }
    }

    public void m9849a(@Nullable WifiP2pManager.ActionListener actionListener) {
        EasyLogger.debug(f9401q, "stopPeerDiscovery...");
        this.f9421m.setPeersChangedListener(null);
        if (this.f9411c != null) {
            if (actionListener == null) {
                actionListener = new LoggingActionListener(f9401q, "Stop peer discovery");
            }
            this.f9410b.stopPeerDiscovery(this.f9411c, actionListener);
        }
    }

    private void m9859a(@Nullable Runnable runnable) {
        EasyLogger.debug(f9401q, "resetP2pNetworks...");
        if (this.f9411c == null) {
            EasyLogger.debug(f9401q, "Skip resetP2pNetworks because P2P channel is not initialized.");
            return;
        }
        Thread thread = this.f9418j;
        if (thread != null) {
            thread.interrupt();
            try {
                this.f9418j.join(50L);
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9401q, "Wait last reset thread error.", e2);
            }
            this.f9418j = null;
        }
        Thread thread2 = new Thread(new OpenWifiP2pTask(runnable), "Reset networks");
        this.f9418j = thread2;
        thread2.start();
        this.f9413e = null;
    }

    public void m9860a(String str) {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onDeviceChanged(str);
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onDeviceChanged(" + str + ") failed.", e2);
                }
            }
        }
    }

    public void m9861a(String str, int i) {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onOpenSuccess(str, i);
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onOpenSuccess(" + str + ", " + i + ") failed.", e2);
                }
            }
        }
    }

    public void m9862a(InetAddress inetAddress, String str, boolean z) {
        WorkThreadExecutor.submit(new RemoveWifiP2pGroupTask(inetAddress, str), "Check address reachable");
    }

    public void m9863a(boolean z) {
        m9899q();
        this.f9414f.postDelayed(new RefreshWifiP2pPeersTask(), z ? 500L : 0L);
    }

    public boolean m9865a(Collection<WifiP2pDevice> collection) {
        return !collection.isEmpty();
    }

    public void m9866b() {
        WifiP2pManager.Channel channel = this.f9411c;
        if (channel != null) {
            try {
                channel.close();
                this.f9411c = null;
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9401q, "Channel close failed.", e2);
            }
        }
        WifiP2pManager.Channel channelInitialize = this.f9410b.initialize(this, getMainLooper(), null);
        this.f9411c = channelInitialize;
        if (channelInitialize == null) {
            EasyLogger.error(f9401q, "P2pChannel is null");
            m9891l();
        } else if (!m9876d()) {
            this.f9421m.setThisDeviceChangedListener(new WifiP2pOwnerDeviceListener());
            this.f9421m.setRegistered(this, true);
        } else {
            this.f9410b.requestDeviceInfo(this.f9411c, new WifiP2pDeviceInfoListener());
            this.f9421m.setRegistered(this, true);
            this.f9421m.setThisDeviceChangedListener(new WifiP2pThisDeviceListener());
        }
    }

    public void m9867b(boolean z) {
        EasyLogger.debug(f9401q, "setAcceptTimeout: " + z);
        if (m9873c()) {
            this.f9414f.removeCallbacks(this.f9420l);
        }
        if (z) {
            this.f9414f.postDelayed(this.f9420l, 30000L);
        }
    }

    public void m9872c(boolean z) {
        EasyLogger.debug(f9401q, "setJoinTimeout: " + z);
        if (z) {
            this.f9414f.postDelayed(this.f9419k, 20000L);
        } else {
            this.f9414f.removeCallbacks(this.f9419k);
        }
    }

    public boolean m9873c() {
        return this.f9414f.hasCallbacks(this.f9420l);
    }

    public boolean m9876d() {
        StringBuilder sb = new StringBuilder();
        sb.append("Build.VERSION.SDK_INT=");
        int i = Build.VERSION.SDK_INT;
        sb.append(i);
        EasyLogger.debug(f9401q, sb.toString());
        return i > 28;
    }

    public void m9877e() {
        EasyLogger.warn(f9401q, "Haven't found p2p device, cancel peer discovery");
        m9899q();
        m9886i();
    }

    public void m9881f() {
        if (this.f9415g) {
            this.f9410b.requestPeers(this.f9411c, new WifiP2pManager.PeerListListener() {
                @Override
                public final void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
                    WifiP2pService.this.m9847a(wifiP2pDeviceList);
                }
            });
            return;
        }
        EasyLogger.warn(f9401q, "No client join. notifyOnAcceptFailed");
        m9915o();
        m9885h();
    }

    public void m9883g() {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onCreateGroupFailed();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onAcceptFailed() failed.", e2);
                }
            }
        }
    }

    private void m9885h() {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onAcceptFailed();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onAcceptFailed() failed.", e2);
                }
            }
        }
    }

    public void m9886i() {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onConnectFailed();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onConnectFailed() failed.", e2);
                }
            }
        }
    }

    public void m9889k() {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onDisconnected();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onDisconnected() failed.", e2);
                }
            }
        }
    }

    public void m9891l() {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onOpenFailed();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onOpenFailed() failed.", e2);
                }
            }
        }
    }

    public void m9894n() {
        EasyLogger.debug(f9401q, "reset...");
        this.f9421m.setPeersChangedListener(null);
        this.f9421m.setConnectionStateListener(null);
        if (!m9876d()) {
            this.f9421m.setThisDeviceChangedListener(null);
        }
        this.f9423o = null;
        m9872c(false);
        m9867b(false);
        m9915o();
    }

    public void m9897p() {
        this.f9421m.setConnectionStateListener(new WifiP2pReconnectListener());
    }

    public void m9899q() {
        m9849a((WifiP2pManager.ActionListener) null);
    }

    private void m9902r() {
        String str;
        try {
            int iIntValue = ((Integer) WifiManager.class.getMethod("getWifiApState", new Class[0]).invoke(this.f9412d, new Object[0])).intValue();
            if (iIntValue == 13 || iIntValue == 12 || iIntValue == 14) {
                Exception e;
                EasyLogger.debug(f9401q, "wifi ap state = " + iIntValue + ", stopTethering");
                if (Build.VERSION.SDK_INT >= 30) {
                    try {
                        Class.forName("android.net.TetheringManager").getMethod("stopTethering", Integer.TYPE).invoke(getSystemService("tethering"), 0);
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        str = "call stopTetheringMethod error";
                    }
                } else {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
                        Field declaredField = ConnectivityManager.class.getDeclaredField("mService");
                        declaredField.setAccessible(true);
                        Class.forName("android.net.IConnectivityManager").getDeclaredMethod("stopTethering", Integer.TYPE, String.class).invoke(declaredField.get(connectivityManager), 0, getPackageName());
                        return;
                    } catch (Exception e3) {
                        e = e3;
                        str = "call old function for stopTethering error";
                    }
                }
                EasyLogger.errorWithThrowable(f9401q, str, e);
            }
        } catch (Exception e4) {
            EasyLogger.errorWithThrowable(f9401q, "call getWifiApState method error: ", e4);
        }
    }

    public void m9908a() {
        unregisterWifiStateReceiver();
        this.f9421m.setThisDeviceChangedListener(null);
        this.f9421m.setRegistered(this, false);
        m9894n();
    }

    private void registerWifiStateReceiver() {
        unregisterWifiStateReceiver();
        this.f9422n = new WifiP2pScreenStateReceiver();
        registerReceiver(this.f9422n, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
    }

    private void unregisterWifiStateReceiver() {
        BroadcastReceiver broadcastReceiver = this.f9422n;
        if (broadcastReceiver == null) {
            return;
        }
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e2) {
            EasyLogger.warn(f9401q, "Wifi state receiver was already unregistered: " + e2.getMessage());
        } finally {
            this.f9422n = null;
        }
    }

    private boolean openWifiSettingsPanel() {
        // Android 10 及以上禁止普通应用直接开关 Wi-Fi，只能引导用户在系统面板中开启。
        if (Build.VERSION.SDK_INT >= 29 && startActivitySafely(new Intent(Settings.Panel.ACTION_WIFI))) {
            EasyLogger.warn(f9401q, "Wi-Fi is disabled; opened system Wi-Fi panel for user confirmation.");
            return true;
        }
        if (startActivitySafely(new Intent(Settings.ACTION_WIFI_SETTINGS))) {
            EasyLogger.warn(f9401q, "Wi-Fi is disabled; opened Wi-Fi settings for user confirmation.");
            return true;
        }
        return false;
    }

    private boolean startActivitySafely(Intent intent) {
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        } catch (ActivityNotFoundException | SecurityException e2) {
            EasyLogger.warn(f9401q, "Unable to open Wi-Fi settings: " + e2.getMessage());
            return false;
        }
    }

    public void m9909a(int i, int i2) {
        int iM187b = WifiChannelUtils.selectOperatingChannel(i, i2, this.f9412d);
        int iM190e = WifiChannelUtils.channelToFrequency(iM187b);
        WifiP2pGroupInfo c0038a = new WifiP2pGroupInfo();
        c0038a.setNetworkName("DIRECT-vs-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        c0038a.setPassphrase(UUID.randomUUID().toString().substring(0, 8));
        c0038a.setFrequency(iM190e);
        c0038a.setGroupOwner(true);
        m9859a(new ConnectWifiP2pTask(c0038a, WifiP2pConfigBuilderProxy.buildConfig(c0038a), iM187b));
    }

    public void m9910a(WifiP2pObserver wifiP2pObserver) {
        synchronized (this.f9424p) {
            if (!this.f9424p.contains(wifiP2pObserver)) {
                this.f9424p.add(wifiP2pObserver);
            }
        }
    }

    public void m9911a(String str, String str2, String str3, int i) {
        EasyLogger.debug(f9401q, "Connecting GO: " + str + ", mac=" + str3 + ", frequency=" + i);
        if (TextUtils.isEmpty(str3)) {
            EasyLogger.error(f9401q, "Empty GO mac address.");
            m9886i();
            return;
        }
        m9872c(true);
        this.f9423o = str3;
        boolean z = (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || i == 0) ? false : true;
        WifiP2pGroupInfo c0038a = new WifiP2pGroupInfo();
        c0038a.setNetworkName(str).setPassphrase(str2).setGroupOwner(false).setGroupOwnerMac(str3).setFrequency(i);
        WifiP2pConfig wifiP2pConfigM204a = WifiP2pConfigBuilderProxy.buildConfig(c0038a);
        if (z && m9876d() && wifiP2pConfigM204a != null) {
            EasyLogger.debug(f9401q, "Method selected: Q");
            m9859a(new StopWifiP2pTask(wifiP2pConfigM204a));
            return;
        }
        EasyLogger.debug(f9401q, "Method selected: Classic");
        CreateWifiP2pGroupTask runnableC1092n = new CreateWifiP2pGroupTask(this, null);
        this.f9417i = runnableC1092n;
        this.f9414f.postDelayed(runnableC1092n, 5000L);
        m9859a(new StartWifiP2pDiscoveryTask());
    }

    public void m9912b(WifiP2pObserver wifiP2pObserver) {
        synchronized (this.f9424p) {
            this.f9424p.remove(wifiP2pObserver);
        }
    }

    public void m9913j() {
        synchronized (this.f9424p) {
            Iterator<WifiP2pObserver> it = this.f9424p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onConnected();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9401q, "Observer.onConnect() failed.", e2);
                }
            }
        }
    }

    public void m9914m() {
        m9902r();
        if (this.f9412d == null) {
            EasyLogger.error(f9401q, "WifiManager is null, cannot open Wi-Fi P2P.");
            m9891l();
            m9908a();
            return;
        }
        if (this.f9412d.isWifiEnabled()) {
            m9866b();
            return;
        }
        registerWifiStateReceiver();
        if (Build.VERSION.SDK_INT >= 29) {
            if (openWifiSettingsPanel()) {
                return;
            }
            EasyLogger.error(f9401q, "Wi-Fi is disabled and system Wi-Fi settings could not be opened.");
            m9891l();
            m9908a();
            return;
        }
        if (this.f9412d.setWifiEnabled(true)) {
            return;
        }
        EasyLogger.error(f9401q, "setWifiEnabled(true) failed, were hotspot or airplane mode activated?");
        m9891l();
        m9908a();
    }

    public void m9915o() {
        this.f9416h = false;
        m9859a((Runnable) null);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.f9409a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.f9414f = new Handler();
        this.f9412d = (WifiManager) getSystemService(ConnectionStateHolder.TRANSPORT_WIFI);
        this.f9410b = (WifiP2pManager) getSystemService("wifip2p");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        m9908a();
    }
}
