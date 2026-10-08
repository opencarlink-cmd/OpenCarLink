package com.share.connect.wifiap;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.share.connect.wifiap.IWifiAp;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.core.ConnectionStateHolder;
import com.ucarhu.demo.sharelink.util.WorkThreadExecutor;
import com.ucarhu.demo.sharelink.wifi.WifiChannelUtils;

public class WifiApService extends Service {

    private static final int f9328A = 0;

    private static final int f9329B = 11;

    private static final int f9330C = 12;

    private static final int f9331D = 13;

    private static final int f9332E = 14;

    private static final int f9333F = 2;

    private static final int f9334G = 1;

    private static final int f9335H = 4;

    private static final int f9336I = 30000;

    private static final int f9337J = 2000;

    private static final int f9338K = 165;

    private static final int f9339L = 36;

    private static final String f9340s = "WifiApService";

    private static final String f9341t = "DIRECT-ICCOA-";

    private static final String f9342u = "CN";

    private static final String f9343v = "android.net.wifi.WIFI_AP_STATE_CHANGED";

    private static final String f9344w = "android.net.conn.TETHER_STATE_CHANGED";

    private static final String f9345x = "wifi_state";

    private static final String f9346y = "tetherArray";

    private static final String f9347z = "tethering";

    private WifiManager f9349b;

    private ConnectivityManager f9350c;

    private Runnable f9352e;

    private Object f9353f;

    private final WifiApServiceBinder f9348a = new WifiApServiceBinder(this, null);

    private Handler f9351d = null;

    private String f9354g = null;

    private String f9355h = null;

    private String f9356i = null;

    private String f9357j = null;

    private int f9358k = 36;

    private boolean f9359l = false;

    private boolean f9360m = false;

    private CountDownLatch f9361n = null;

    private CountDownLatch f9362o = null;

    private final List<WifiApObserver> f9363p = new ArrayList();

    private final BroadcastReceiver f9364q = new WifiApStateReceiver();

    private final Runnable f9365r = new Runnable() {
        @Override
        public final void run() {
            WifiApService.this.m9796c();
        }
    };

    public class WifiApStateReceiver extends BroadcastReceiver {
        public WifiApStateReceiver() {
        }

        public void m9832a() {
            WifiApService wifiApService = WifiApService.this;
            wifiApService.m9785a(wifiApService.f9354g, WifiApService.this.f9355h, WifiApService.this.f9357j, WifiApService.this.f9356i, WifiChannelUtils.channelToFrequency(WifiApService.this.f9358k));
            WifiApService.this.f9359l = true;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            CountDownLatch countDownLatch;
            String action = intent.getAction();
            if (WifiApService.f9343v.equals(action)) {
                int intExtra = intent.getIntExtra(WifiApService.f9345x, 14);
                EasyLogger.info(WifiApService.f9340s, "receiver WIFI_AP_STATE_CHANGED_ACTION,  state = " + intExtra + " connect state = " + ConnectionStateHolder.getInstance().getState());
                if (intExtra != 11) {
                    return;
                }
                WifiApService.this.f9359l = false;
                WifiApService.this.f9360m = false;
                WifiApService.this.f9352e = null;
                WifiApService.this.f9361n = null;
                WifiApService.this.f9354g = null;
                if (WifiApService.this.f9362o == null) {
                    return;
                }
                EasyLogger.info(WifiApService.f9340s, "tethering has been disabled !");
                countDownLatch = WifiApService.this.f9362o;
            } else {
                if (!WifiApService.f9344w.equals(action)) {
                    return;
                }
                EasyLogger.info(WifiApService.f9340s, "ACTION_TETHER_STATE_CHANGED !");
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(WifiApService.f9346y);
                if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
                    return;
                }
                WifiApService.this.m9793b(stringArrayListExtra.get(0));
                int i = Build.VERSION.SDK_INT;
                if (i < 30 && i >= 28) {
                    try {
                        WifiConfiguration wifiConfiguration = (WifiConfiguration) WifiManager.class.getMethod("getWifiApConfiguration", new Class[0]).invoke(WifiApService.this.f9349b, new Object[0]);
                        int iIntValue = ((Integer) WifiConfiguration.class.getDeclaredField("apChannel").get(wifiConfiguration)).intValue();
                        EasyLogger.info(WifiApService.f9340s, "WifiConfiguration = " + wifiConfiguration);
                        if (!wifiConfiguration.SSID.contains(WifiApService.f9341t) || WifiApService.this.f9359l || iIntValue == 0) {
                            return;
                        }
                        if (WifiApService.this.f9354g == null) {
                            WifiApService.this.m9819n();
                            WifiApService.this.f9354g = wifiConfiguration.SSID;
                        }
                        WifiApService.this.f9358k = iIntValue;
                        WifiApService.this.f9355h = wifiConfiguration.preSharedKey;
                        EasyLogger.info(WifiApService.f9340s, "ap config check pass, channel = " + WifiApService.this.f9358k);
                        WifiApService.this.f9352e = new Runnable() {
                            @Override
                            public final void run() {
                                WifiApStateReceiver.this.m9832a();
                            }
                        };
                        WifiApService.this.f9352e.run();
                        return;
                    } catch (Exception e2) {
                        EasyLogger.errorWithThrowable(WifiApService.f9340s, "onReceive ACTION_TETHER_STATE_CHANGED error: ", e2);
                        return;
                    }
                }
                if (i < 30) {
                    EasyLogger.debug(WifiApService.f9340s, "the version less than Android P don't support");
                    return;
                }
                EasyLogger.debug(WifiApService.f9340s, "the Android R and above process in callback");
                if (WifiApService.this.f9361n == null) {
                    return;
                } else {
                    countDownLatch = WifiApService.this.f9361n;
                }
            }
            countDownLatch.countDown();
        }
    }

    public final class WifiManagerProxyHandler implements InvocationHandler {

        private static final int f9367b = 2;

        private static final int f9368c = 3;

        private WifiManagerProxyHandler() {
        }

        public WifiManagerProxyHandler(WifiApService wifiApService, WifiApStateReceiver c1066a) {
            this();
        }

        public void m9834a(SoftApConfiguration softApConfiguration, int i) {
            WifiApService.this.m9785a(softApConfiguration.getSsid(), softApConfiguration.getPassphrase(), WifiApService.this.f9357j, WifiApService.this.f9356i, i);
            WifiApService.this.f9359l = true;
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] objArr) {
            Object objInvoke;
            Boolean bool = Boolean.FALSE;
            if (Build.VERSION.SDK_INT >= 30) {
                if (TextUtils.equals("onConnectedClientsChanged", method.getName())) {
                    EasyLogger.info(WifiApService.f9340s, "onConnectedClientsChanged");
                    List list = (List) (objArr.length == 2 ? objArr[1] : objArr[0]);
                    if (list.size() > 0) {
                        WifiApService.this.m9786a(false);
                        if (list.get(0) != null && !WifiApService.this.f9360m) {
                            EasyLogger.info(WifiApService.f9340s, "onConnectedClientsChanged WifiClient = " + list.get(0));
                            WifiApService.this.m9809i();
                            WifiApService.this.f9360m = true;
                        }
                    } else if (WifiApService.this.f9360m && !WifiApService.this.m9794b()) {
                        WifiApService.this.f9360m = false;
                        WifiApService.this.m9811j();
                    }
                    return null;
                }
                if (TextUtils.equals("onInfoChanged", method.getName())) {
                    try {
                        Method method2 = Class.forName("android.net.wifi.SoftApInfo").getMethod("getFrequency", new Class[0]);
                        if (!(objArr[0] instanceof List)) {
                            objInvoke = method2.invoke(objArr[0], new Object[0]);
                        } else {
                            if (((List) objArr[0]).size() <= 0) {
                                EasyLogger.error(WifiApService.f9340s, "onInfoChanged SoftApInfo list size is 0");
                                return null;
                            }
                            objInvoke = method2.invoke(((List) objArr[0]).get(0), new Object[0]);
                        }
                        final int iIntValue = ((Integer) objInvoke).intValue();
                        final SoftApConfiguration softApConfiguration = (SoftApConfiguration) WifiManager.class.getMethod("getSoftApConfiguration", new Class[0]).invoke(WifiApService.this.f9349b, new Object[0]);
                        EasyLogger.info(WifiApService.f9340s, "SoftApInfo freq = " + iIntValue + " ssid = " + softApConfiguration.getSsid());
                        if (!WifiApService.this.f9359l && softApConfiguration.getSsid().contains(WifiApService.f9341t) && iIntValue != 0) {
                            if (WifiApService.this.f9361n == null || !WifiApService.this.f9361n.await(3L, TimeUnit.SECONDS)) {
                                EasyLogger.error(WifiApService.f9340s, "soft Ap create fail !");
                                WifiApService.this.m9807h();
                            } else {
                                WifiApService.this.f9352e = new Runnable() {
                                    @Override
                                    public final void run() {
                                        WifiManagerProxyHandler.this.m9834a(softApConfiguration, iIntValue);
                                    }
                                };
                                WifiApService.this.f9352e.run();
                            }
                        }
                        return null;
                    } catch (Exception e2) {
                        EasyLogger.errorWithThrowable(WifiApService.f9340s, "onInfoChanged reflect error: ", e2);
                        return null;
                    }
                }
            } else {
                if (TextUtils.equals("onStateChanged", method.getName())) {
                    EasyLogger.info(WifiApService.f9340s, "onStateChanged state = " + objArr[0]);
                    return null;
                }
                if (TextUtils.equals("onNumClientsChanged", method.getName())) {
                    EasyLogger.info(WifiApService.f9340s, "onNumClientsChanged WifiClient size = " + objArr[0]);
                    if (WifiApService.this.m9777a() == 13 && ((Integer) objArr[0]).intValue() > 0) {
                        WifiApService.this.m9786a(false);
                        if (!WifiApService.this.f9360m) {
                            WifiApService.this.m9809i();
                            WifiApService.this.f9360m = true;
                        }
                    } else if (WifiApService.this.f9360m && ((Integer) objArr[0]).intValue() == 0 && !WifiApService.this.m9794b()) {
                        WifiApService.this.f9360m = false;
                        WifiApService.this.m9811j();
                    }
                    return null;
                }
            }
            if (String.class == method.getReturnType()) {
                return "";
            }
            if (Integer.class == method.getReturnType() || Integer.TYPE == method.getReturnType()) {
                return 0;
            }
            if (Boolean.class == method.getReturnType() || Boolean.TYPE == method.getReturnType()) {
                return bool;
            }
            return null;
        }
    }

    public class WifiApServiceBinder extends IWifiAp.Stub {
        private WifiApServiceBinder() {
        }

        public WifiApServiceBinder(WifiApService wifiApService, WifiApStateReceiver c1066a) {
            this();
        }

        @Override
        public void cancelConnect() {
            WifiApService.this.m9831o();
        }

        @Override
        public void close() {
            WifiApService.this.m9831o();
        }

        @Override
        public void createSoftAp(int i) {
            if (WifiApService.this.f9352e == null || WifiApService.this.m9777a() != 13) {
                WifiApService.this.m9827a(i);
                return;
            }
            EasyLogger.debug(WifiApService.f9340s, "reuse existing Wifi softAp");
            WifiApService.this.m9786a(true);
            WifiApService.this.f9352e.run();
        }

        @Override
        public void open() {
            WifiApService.this.m9830m();
        }

        @Override
        public void registerWifiApObserver(WifiApObserver wifiApObserver) {
            WifiApService.this.m9828a(wifiApObserver);
        }

        @Override
        public void unregisterWifiApObserver(WifiApObserver wifiApObserver) {
            WifiApService.this.m9829b(wifiApObserver);
        }
    }

    public final class WifiApCallbackProxyHandler implements InvocationHandler {
        private WifiApCallbackProxyHandler() {
        }

        public WifiApCallbackProxyHandler(WifiApService wifiApService, WifiApStateReceiver c1066a) {
            this();
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (TextUtils.equals("onTetheringStarted", method.getName())) {
                EasyLogger.info(WifiApService.f9340s, "WifiApService startTethering Success");
                return null;
            }
            if (!TextUtils.equals("onTetheringFailed", method.getName())) {
                return null;
            }
            EasyLogger.info(WifiApService.f9340s, "WifiApService startTethering failed, error num: " + objArr[0]);
            WifiApService.this.m9807h();
            return null;
        }
    }

    public int m9777a() {
        try {
            return ((Integer) WifiManager.class.getMethod("getWifiApState", new Class[0]).invoke(this.f9349b, new Object[0])).intValue();
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9340s, "call getWifiApState method error: ", e2);
            return 0;
        }
    }

    private String m9781a(byte[] bArr) {
        if (bArr == null) {
            EasyLogger.error(f9340s, "MacAddress params is null");
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 0xFF);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
            if (i < length - 1) {
                stringBuffer.append(":");
            }
        }
        return String.valueOf(stringBuffer);
    }

    private void m9784a(String str, String str2) {
        this.f9359l = false;
        this.f9354g = str;
        this.f9355h = str2;
        WifiManager wifiManager = this.f9349b;
        if (wifiManager != null && (wifiManager.getWifiState() == 3 || this.f9349b.getWifiState() == 2)) {
            EasyLogger.info(f9340s, "disable wifi, before start startTethering");
            this.f9349b.setWifiEnabled(false);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            EasyLogger.info(f9340s, "call new function for startTethering");
            m9803f();
        } else {
            EasyLogger.info(f9340s, "call old function for startTethering");
            m9816l();
        }
    }

    public void m9785a(String str, String str2, String str3, String str4, int i) {
        synchronized (this.f9363p) {
            Iterator<WifiApObserver> it = this.f9363p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onApCreated(str3, str, str2, str4, i);
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9340s, "Observer.notifyOnApCreated(...) failed.", e2);
                }
            }
        }
    }

    public void m9786a(boolean z) {
        EasyLogger.debug(f9340s, "setAcceptTimeout: " + z);
        this.f9351d.removeCallbacks(this.f9365r);
        if (z) {
            this.f9351d.postDelayed(this.f9365r, 30000L);
        }
    }

    private boolean m9789a(String str) {
        try {
            return InetAddress.getByName(str) instanceof Inet4Address;
        } catch (UnknownHostException unused) {
            return false;
        }
    }

    public void m9793b(String str) {
        try {
            NetworkInterface byName = NetworkInterface.getByName(str);
            if (byName != null) {
                this.f9356i = m9781a(byName.getHardwareAddress());
                Enumeration<InetAddress> inetAddresses = byName.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && m9789a(inetAddressNextElement.getHostAddress())) {
                        this.f9357j = inetAddressNextElement.getHostAddress();
                    }
                }
            }
            EasyLogger.info(f9340s, "IpAddress = " + this.f9357j + " mac = " + this.f9356i);
        } catch (SocketException e2) {
            EasyLogger.errorWithThrowable(f9340s, "setApAddress error: ", e2);
        }
    }

    public boolean m9794b() {
        return this.f9351d.hasCallbacks(this.f9365r);
    }

    public void m9796c() {
        if (this.f9360m) {
            return;
        }
        EasyLogger.warn(f9340s, "No client join. notifyOnAcceptFailed");
        m9831o();
        m9805g();
    }

    public void m9800d() {
        m9785a(this.f9354g, this.f9355h, this.f9357j, this.f9356i, WifiChannelUtils.channelToFrequency(this.f9358k));
    }

    public void m9802e() {
        m9831o();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.f9362o = countDownLatch;
        try {
            if (countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                m9784a(f9341t + UUID.randomUUID().toString().substring(0, 3).toUpperCase(), UUID.randomUUID().toString().substring(0, 8));
            } else {
                m9807h();
            }
        } catch (InterruptedException e2) {
            EasyLogger.errorWithThrowable(f9340s, "tetherReset thread error: ", e2);
            m9807h();
        }
        this.f9362o = null;
    }

    @RequiresApi(api = 30)
    private void m9803f() {
        this.f9361n = new CountDownLatch(1);
        if (this.f9350c != null) {
            try {
                ConnectivityManager.class.getDeclaredField("mService").setAccessible(true);
                Class<?> cls = Class.forName("android.net.wifi.SoftApConfiguration$Builder");
                Method method = cls.getMethod("setSsid", String.class);
                Class<?> cls2 = Integer.TYPE;
                Method method2 = cls.getMethod("setPassphrase", String.class, cls2);
                Method method3 = cls.getMethod("setChannel", cls2, cls2);
                Method method4 = cls.getMethod("build", new Class[0]);
                Object objNewInstance = cls.newInstance();
                method.invoke(objNewInstance, this.f9354g);
                method2.invoke(objNewInstance, this.f9355h, 1);
                method3.invoke(objNewInstance, Integer.valueOf(this.f9358k), 2);
                SoftApConfiguration softApConfiguration = (SoftApConfiguration) method4.invoke(objNewInstance, new Object[0]);
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(ConnectionStateHolder.TRANSPORT_WIFI);
                wifiManager.getClass().getMethod("setSoftApConfiguration", SoftApConfiguration.class).invoke(wifiManager, softApConfiguration);
                m9819n();
                Class<?> cls3 = Class.forName("android.net.TetheringManager$TetheringRequest$Builder");
                Object objNewInstance2 = cls3.getConstructor(cls2).newInstance(0);
                Method method5 = cls3.getMethod("setShouldShowEntitlementUi", Boolean.TYPE);
                Method method6 = cls3.getMethod("build", new Class[0]);
                method5.invoke(objNewInstance2, Boolean.TRUE);
                Object objInvoke = method6.invoke(objNewInstance2, new Object[0]);
                Class<?> cls4 = Class.forName("android.net.TetheringManager$StartTetheringCallback");
                Class.forName("android.net.TetheringManager").getMethod("startTethering", Class.forName("android.net.TetheringManager$TetheringRequest"), Executor.class, cls4).invoke(getSystemService(f9347z), objInvoke, Executors.newSingleThreadExecutor(), Proxy.newProxyInstance(cls4.getClassLoader(), new Class[]{cls4}, new WifiApCallbackProxyHandler(this, null)));
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9340s, "start new startTethering method error: ", e2);
            }
        }
    }

    private void m9805g() {
        synchronized (this.f9363p) {
            Iterator<WifiApObserver> it = this.f9363p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onAcceptFailed();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9340s, "Observer.onAcceptFailed() failed.", e2);
                }
            }
        }
    }

    public void m9807h() {
        synchronized (this.f9363p) {
            Iterator<WifiApObserver> it = this.f9363p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onApCreateFailed();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9340s, "Observer.notifyOnApCreateFailed() failed.", e2);
                }
            }
        }
    }

    public void m9809i() {
        synchronized (this.f9363p) {
            Iterator<WifiApObserver> it = this.f9363p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onConnected();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9340s, "Observer.notifyOnConnected() failed.", e2);
                }
            }
        }
    }

    public void m9811j() {
        synchronized (this.f9363p) {
            Iterator<WifiApObserver> it = this.f9363p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onDisconnected();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9340s, "Observer.onDisconnected() failed.", e2);
                }
            }
        }
    }

    private void m9814k() {
        synchronized (this.f9363p) {
            Iterator<WifiApObserver> it = this.f9363p.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onOpenSuccess();
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9340s, "Observer.onOpenSuccess error: ", e2);
                }
            }
        }
    }

    @RequiresApi(api = 28)
    private void m9816l() {
        if (this.f9350c != null) {
            try {
                Class<?> cls = Class.forName("android.net.wifi.WifiConfiguration");
                WifiConfiguration wifiConfiguration = (WifiConfiguration) cls.newInstance();
                Field declaredField = cls.getDeclaredField("apBand");
                Field declaredField2 = cls.getDeclaredField("apChannel");
                declaredField.set(wifiConfiguration, 1);
                declaredField2.set(wifiConfiguration, Integer.valueOf(this.f9358k));
                wifiConfiguration.SSID = this.f9354g;
                wifiConfiguration.preSharedKey = this.f9355h;
                wifiConfiguration.allowedKeyManagement.set(4);
                Method method = WifiManager.class.getMethod("setCountryCode", String.class);
                if (((String) WifiManager.class.getMethod("getCountryCode", new Class[0]).invoke(this.f9349b, new Object[0])) == null) {
                    EasyLogger.error(f9340s, "getCountryCode is null, reset it");
                    method.invoke(this.f9349b, f9342u);
                }
                WifiManager.class.getMethod("setWifiApConfiguration", WifiConfiguration.class).invoke(this.f9349b, wifiConfiguration);
                m9819n();
                Field declaredField3 = ConnectivityManager.class.getDeclaredField("mService");
                declaredField3.setAccessible(true);
                Class.forName("android.net.IConnectivityManager").getDeclaredMethod("startTethering", Integer.TYPE, ResultReceiver.class, Boolean.TYPE, String.class).invoke(declaredField3.get(this.f9350c), 0, new ResultReceiver(null) {
                    @Override
                    public void onReceiveResult(int i, Bundle bundle) {
                        super.onReceiveResult(i, bundle);
                    }
                }, Boolean.TRUE, getPackageName());
            } catch (Exception e2) {
                EasyLogger.errorWithThrowable(f9340s, "start old startTethering method error: ", e2);
            }
        }
    }

    public void m9819n() {
        if (this.f9353f == null) {
            EasyLogger.info(f9340s, "register SoftAp Callback");
            WifiApStateReceiver c1066a = null;
            try {
                Class<?> cls = Class.forName("android.net.wifi.WifiManager$SoftApCallback");
                this.f9353f = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new WifiManagerProxyHandler(this, c1066a));
                if (Build.VERSION.SDK_INT >= 30) {
                    WifiManager.class.getMethod("registerSoftApCallback", Executor.class, cls).invoke(this.f9349b, Executors.newSingleThreadExecutor(), this.f9353f);
                } else {
                    WifiManager.class.getMethod("registerSoftApCallback", cls, Handler.class).invoke(this.f9349b, this.f9353f, new Handler(Looper.getMainLooper()));
                }
            } catch (Exception e2) {
                this.f9353f = null;
                EasyLogger.errorWithThrowable(f9340s, "registerSoftApCallback reflect error!", e2);
            }
        }
    }

    private void m9822p() {
        try {
            String str = this.f9354g;
            if (str != null && str.contains(f9341t) && this.f9359l) {
                EasyLogger.info(f9340s, "use exist ICCOA softAp");
                m9819n();
                Runnable runnable = new Runnable() {
                    @Override
                    public final void run() {
                        WifiApService.this.m9800d();
                    }
                };
                this.f9352e = runnable;
                runnable.run();
            } else {
                String str2 = this.f9354g;
                if (str2 == null || !str2.contains(f9341t)) {
                    EasyLogger.debug(f9340s, "ssid check error, restart wifi tether");
                    WorkThreadExecutor.submit(new Runnable() {
                        @Override
                        public final void run() {
                            WifiApService.this.m9802e();
                        }
                    }, "reCreate soft ap");
                }
            }
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9340s, "useExistSoftAp error: ", e2);
        }
    }

    public void m9827a(int i) {
        if (i > f9338K || i < 36) {
            EasyLogger.error(f9340s, "channel out of 5G range");
            return;
        }
        EasyLogger.debug(f9340s, "set 5G channel = " + i);
        this.f9358k = i;
        if (m9777a() == 12 || m9777a() == 13) {
            m9786a(true);
            EasyLogger.debug(f9340s, "soft ap is enabling or enabled, don't create it again");
            m9822p();
        } else {
            m9784a(f9341t + UUID.randomUUID().toString().substring(0, 3).toUpperCase(), UUID.randomUUID().toString().substring(0, 8));
        }
    }

    public void m9828a(WifiApObserver wifiApObserver) {
        synchronized (this.f9363p) {
            if (!this.f9363p.contains(wifiApObserver)) {
                this.f9363p.add(wifiApObserver);
            }
        }
    }

    public void m9829b(WifiApObserver wifiApObserver) {
        synchronized (this.f9363p) {
            this.f9363p.remove(wifiApObserver);
        }
    }

    public void m9830m() {
        EasyLogger.debug(f9340s, "WifiApService open");
        m9814k();
    }

    public void m9831o() {
        String str;
        Exception e;
        if (this.f9350c != null) {
            if (this.f9353f != null) {
                try {
                    WifiManager.class.getMethod("unregisterSoftApCallback", Class.forName("android.net.wifi.WifiManager$SoftApCallback")).invoke(this.f9349b, this.f9353f);
                    this.f9353f = null;
                } catch (Exception e2) {
                    EasyLogger.errorWithThrowable(f9340s, "call unregisterSoftApCallback error", e2);
                }
            }
            if (m9777a() == 13 || m9777a() == 12 || m9777a() == 14) {
                EasyLogger.debug(f9340s, "wifi ap state = " + m9777a() + ", stopTethering");
                if (Build.VERSION.SDK_INT >= 30) {
                    try {
                        Class.forName("android.net.TetheringManager").getMethod("stopTethering", Integer.TYPE).invoke(getSystemService(f9347z), 0);
                        return;
                    } catch (Exception e3) {
                        e = e3;
                        str = "call stopTetheringMethod error";
                    }
                } else {
                    try {
                        Field declaredField = ConnectivityManager.class.getDeclaredField("mService");
                        declaredField.setAccessible(true);
                        Class.forName("android.net.IConnectivityManager").getDeclaredMethod("stopTethering", Integer.TYPE, String.class).invoke(declaredField.get(this.f9350c), 0, getPackageName());
                        return;
                    } catch (Exception e4) {
                        e = e4;
                        str = "call old function for stopTethering error";
                    }
                }
                EasyLogger.errorWithThrowable(f9340s, str, e);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.f9348a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.f9349b = (WifiManager) getSystemService(ConnectionStateHolder.TRANSPORT_WIFI);
        this.f9350c = (ConnectivityManager) getSystemService("connectivity");
        this.f9351d = new Handler(Looper.getMainLooper());
        IntentFilter intentFilter = new IntentFilter(f9343v);
        intentFilter.addAction(f9344w);
        registerReceiver(this.f9364q, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        m9831o();
        unregisterReceiver(this.f9364q);
    }
}
