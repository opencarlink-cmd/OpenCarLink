package com.share.connect.ble;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.AdvertisingSet;
import android.bluetooth.le.AdvertisingSetCallback;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.RequiresPermission;
import com.share.connect.ble.DeviceCache;
import com.share.connect.ble.IBluetoothLe;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.bluetooth.BleAdvertiseDataBuilder;
import com.ucarhu.demo.sharelink.bluetooth.BleDebugUtils;
import com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner;
import com.ucarhu.demo.sharelink.bluetooth.BleProtocolConstants;
import com.ucarhu.demo.sharelink.util.DebugTimer;
import java.util.LinkedHashMap;

public class BluetoothLeService extends Service {

    private static final String f9213E = "BLEService";

    public static final int f9214F = 30000;

    public static final int f9215G = 48;

    private String f9216A;

    private final BluetoothLeServiceBinder f9217B;

    private BluetoothManager f9220a;

    private BluetoothAdapter f9221b;

    private CompatLeScanner f9222c;

    private BluetoothLeAdvertiser f9223d;

    private BluetoothGattServer f9224e;

    private final ServerGattCallback f9225f;

    private BluetoothGatt f9226g;

    private ClientGattCallback f9227h;

    private HandlerC1048h f9228i;

    private Handler f9229j;

    private AdvertisingSetCallback f9231l;

    private CompatLeScanner.c f9232m;

    private DeviceCache f9233n;

    private BroadcastReceiver f9234o;

    private String f9236q;

    private String f9237r;

    private String f9238s;

    private String f9239t;

    private String f9240u;

    private String f9241v;

    private String f9242w;

    private String f9243x;

    private int f9245z;

    private final Runnable f9230k = new BleConnectTimeoutTask();

    private final AtomicBoolean f9235p = new AtomicBoolean(false);

    private final AtomicInteger f9244y = new AtomicInteger(0);

    private final List<BluetoothLeObserver> f9218C = new ArrayList();

    private boolean f9219D = false;

    public class BleConnectTimeoutTask implements Runnable {
        public BleConnectTimeoutTask() {
        }

        @Override
        public void run() {
            EasyLogger.warn(BluetoothLeService.f9213E, "Connect timeout, take this as failure.");
            BluetoothLeService.this.f9217B.connectDone();
            BluetoothLeService.this.m9732g();
            BluetoothLeService.this.m9676b(3, 1);
        }
    }

    public class BluetoothStateReceiver extends BroadcastReceiver {

        public final int f9247a;

        public BluetoothStateReceiver(int i) {
            this.f9247a = i;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
            EasyLogger.debug(BluetoothLeService.f9213E, "Received bluetooth state: " + intExtra);
            if (intExtra == 12) {
                try {
                    BluetoothLeService bluetoothLeService = BluetoothLeService.this;
                    bluetoothLeService.unregisterReceiver(bluetoothLeService.f9234o);
                } catch (RuntimeException unused) {
                }
                if (BluetoothLeService.this.m9713l()) {
                    return;
                }
                int i = this.f9247a;
                if (i > 0) {
                    BluetoothLeService.this.m9659a(i - 1);
                } else {
                    BluetoothLeService.this.m9676b(1, 2);
                }
            }
        }
    }

    public class DeviceCacheCallback implements DeviceCache.DeviceCacheListener {
        public DeviceCacheCallback() {
        }

        @Override
        public void onDeviceAdded(DeviceCache.BtDevice btDevice) {
            BluetoothLeService.this.m9665a(true, btDevice);
        }

        @Override
        public void onDeviceRemoved(DeviceCache.BtDevice btDevice) {
            BluetoothLeService.this.m9665a(false, btDevice);
        }
    }

    public class LeScanCallback implements CompatLeScanner.c {
        public LeScanCallback() {
        }

        @Override
        public void onScanFailed(int i) {
            EasyLogger.error(BluetoothLeService.f9213E, "Start scanning failed with error code: " + i);
            BluetoothLeService.this.m9676b(2, 1);
        }

        @Override
        public void onScanResult(int i, ScanResult scanResult) {
            if (BluetoothLeService.this.f9233n == null) {
                EasyLogger.warn(BluetoothLeService.f9213E, "onScanResult invoked when mDeviceCache is null.");
            } else if (i == 1) {
                BluetoothLeService.this.f9233n.handleDeviceFoundScan(scanResult);
            } else if (i == 0) {
                BluetoothLeService.this.f9233n.handleDeviceLostScan(scanResult);
            }
        }
    }

    public class DeferredConnectCallback implements PendingBleAction {

        public final String f9251a;

        public DeferredConnectCallback(String str) {
            this.f9251a = str;
        }

        @Override
        public void mo9739a() {
            BluetoothLeService.this.m9673b(this.f9251a).run();
        }
    }

    public class BleConnectTask implements Runnable {

        public final String f9253a;

        public BleConnectTask(String str) {
            this.f9253a = str;
        }

        @Override
        public void run() {
            try {
                BluetoothDevice bluetoothDeviceM9695f = BluetoothLeService.this.m9695f(this.f9253a);
                BluetoothLeService.this.f9227h = new ClientGattCallback(BluetoothLeService.this, null);
                BluetoothLeService.this.f9227h.m9744a(true);
                DebugTimer.startEvent("Ble-connect");
                BluetoothLeService bluetoothLeService = BluetoothLeService.this;
                bluetoothLeService.f9226g = bluetoothDeviceM9695f.connectGatt(bluetoothLeService, false, bluetoothLeService.f9227h, 2);
                BluetoothLeService.this.f9236q = this.f9253a;
                BluetoothLeService.this.m9678b(true);
            } catch (IllegalArgumentException e2) {
                EasyLogger.errorWithThrowable(BluetoothLeService.f9213E, "Connect failed because of illegal argument.", e2);
                BluetoothLeService.this.m9676b(3, 1);
            }
        }
    }

    public class LegacyAdvertiseStopCallback extends AdvertisingSetCallback {
        public LegacyAdvertiseStopCallback() {
        }

        @Override
        public void onAdvertisingEnabled(AdvertisingSet advertisingSet, boolean z, int i) {
            if (z) {
                EasyLogger.error(BluetoothLeService.f9213E, "Legacy advertiser should be only disabled on timeout, but was enabled!");
                return;
            }
            AdvertisingSetCallback advertisingSetCallback = BluetoothLeService.this.f9231l;
            if (advertisingSetCallback != null) {
                BluetoothLeService.this.f9223d.stopAdvertisingSet(advertisingSetCallback);
            }
        }

        @Override
        public void onAdvertisingSetStarted(AdvertisingSet advertisingSet, int i, int i2) {
            EasyLogger.debug(BluetoothLeService.f9213E, "onAdvertisingSetStarted status " + i2 + " advertisingSet: " + advertisingSet + " txPower " + i);
            BluetoothLeService bluetoothLeService = BluetoothLeService.this;
            if (i2 == 0) {
                bluetoothLeService.m9675b(1);
            } else {
                bluetoothLeService.m9676b(1, 3);
            }
        }
    }

    public class HandlerC1048h extends Handler {

        private static final String f9256b = "BLEService-Delayer";

        private static final int f9257c = 200;

        private static final int f9258d = 1;

        private static final int f9259e = 2;

        public class a implements Runnable {

            public final String f9261a;

            public final CountDownLatch f9262b;

            public a(String str, CountDownLatch countDownLatch) {
                this.f9261a = str;
                this.f9262b = countDownLatch;
            }

            @Override
            public void run() {
                BluetoothLeService.this.m9729a(this.f9261a);
                this.f9262b.countDown();
            }
        }

        public class b implements Runnable {

            public final CountDownLatch f9264a;

            public b(CountDownLatch countDownLatch) {
                this.f9264a = countDownLatch;
            }

            @Override
            public void run() {
                BluetoothLeService.this.m9732g();
                this.f9264a.countDown();
            }
        }

        public HandlerC1048h(Looper looper) {
            super(looper);
        }

        public synchronized void m9740a() {
            EasyLogger.info(f9256b, "Post disconnectMsg");
            Message messageObtainMessage = obtainMessage();
            messageObtainMessage.what = 2;
            sendMessage(messageObtainMessage);
        }

        public synchronized void m9741a(String str) {
            EasyLogger.info(f9256b, "Post connectMsg");
            Message messageObtainMessage = obtainMessage();
            messageObtainMessage.what = 1;
            messageObtainMessage.obj = str;
            sendMessage(messageObtainMessage);
        }

        @Override
        public void handleMessage(Message message) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            int i = message.what;
            if (i == 1) {
                String str = (String) message.obj;
                if (BluetoothLeService.this.f9229j != null) {
                    BluetoothLeService.this.f9229j.post(new a(str, countDownLatch));
                } else {
                    EasyLogger.warn(f9256b, "Receive connect message but handler is null.");
                    BluetoothLeService.this.m9676b(3, 1);
                }
            } else {
                if (i != 2) {
                    EasyLogger.info(f9256b, "Unable handle msg.what=" + message.what);
                    return;
                }
                if (BluetoothLeService.this.f9229j != null) {
                    BluetoothLeService.this.f9229j.post(new b(countDownLatch));
                }
            }
            try {
                countDownLatch.await();
                Thread.sleep(200L);
            } catch (Exception unused) {
            }
        }
    }

    public class BluetoothLeServiceBinder extends IBluetoothLe.Stub {

        private LinkedHashMap<String, Integer> f9266s;

        private BluetoothLeServiceBinder() {
            this.f9266s = new LinkedHashMap<>();
        }

        public BluetoothLeServiceBinder(BluetoothLeService bluetoothLeService, BleConnectTimeoutTask runnableC1041a) {
            this();
        }

        @Override
        public void allowProcessNewConnection() {
            BluetoothLeService.this.f9225f.m9748a();
        }

        @Override
        public void close() {
            BluetoothLeService.this.m9731f();
        }

        @Override
        public boolean connect(String str) {
            if (BluetoothLeService.this.f9233n == null) {
                EasyLogger.warn(BluetoothLeService.f9213E, "Cache is null, do not connect when scan has stopped.");
                return false;
            }
            String strM9764d = BluetoothLeService.this.f9233n.getMacByVin(str);
            if (TextUtils.isEmpty(strM9764d) || BluetoothLeService.this.f9228i == null) {
                EasyLogger.warn(BluetoothLeService.f9213E, BluetoothLeService.this.f9228i == null ? "Internal error." : "Can't find mac in scan cache.");
                return false;
            }
            BluetoothLeService.this.f9228i.m9741a(strM9764d);
            return true;
        }

        @Override
        public void connectDone() {
            EasyLogger.info(BluetoothLeService.f9213E, "connectDone...");
            if (BluetoothLeService.this.f9227h != null) {
                BluetoothLeService.this.f9227h.m9743a();
            }
            if (BluetoothLeService.this.f9225f != null) {
                BluetoothLeService.this.f9225f.m9751b();
            }
            BluetoothLeService.this.m9678b(false);
        }

        @Override
        public void disconnect() {
            if (BluetoothLeService.this.f9228i != null) {
                BluetoothLeService.this.f9228i.m9740a();
            }
        }

        @Override
        public void disconnectWithoutState() {
            EasyLogger.error(BluetoothLeService.f9213E, "TEST-ONLY METHOD INVOKED!!");
            if (BluetoothLeService.this.f9226g != null) {
                EasyLogger.info(BluetoothLeService.f9213E, "disconnect gatt client");
                BluetoothLeService.this.f9226g.close();
                BluetoothLeService.this.f9226g = null;
            }
            if (BluetoothLeService.this.f9224e == null || TextUtils.isEmpty(BluetoothLeService.this.f9237r)) {
                return;
            }
            EasyLogger.info(BluetoothLeService.f9213E, "cancelConnection for " + BluetoothLeService.this.f9237r);
            try {
                BluetoothLeService.this.f9224e.cancelConnection(BluetoothLeService.this.f9221b.getRemoteDevice(BluetoothLeService.this.f9237r));
            } catch (IllegalArgumentException e2) {
                EasyLogger.errorWithThrowable(BluetoothLeService.f9213E, "cancelConnection failed because of illegal argument.", e2);
            }
        }

        @Override
        public Map getDevicesSignal(int i, int i2) {
            if (BluetoothLeService.this.f9233n == null) {
                EasyLogger.warn(BluetoothLeService.f9213E, "Call signal list while scanning is stopped.");
                return Collections.emptyMap();
            }
            if (i == 0 || this.f9266s.isEmpty()) {
                this.f9266s.clear();
                this.f9266s = BluetoothLeService.this.f9233n.getSignalByVin();
            }
            if (this.f9266s.size() <= i) {
                return Collections.emptyMap();
            }
            LinkedHashMap<String, Integer> c0715a = new LinkedHashMap<>();
            ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList<>(this.f9266s.entrySet());
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                int i4 = i3 + i;
                if (i4 >= this.f9266s.size()) {
                    this.f9266s.clear();
                    break;
                }
                Map.Entry<String, Integer> entry = arrayList.get(i4);
                c0715a.put(entry.getKey(), entry.getValue());
                i3++;
            }
            return c0715a;
        }

        @Override
        public boolean isDeviceInMatch(String str) {
            if (BluetoothLeService.this.f9233n != null) {
                return BluetoothLeService.this.f9233n.containsVin(str);
            }
            return false;
        }

        @Override
        public void notifyServerInfo(String str, String str2, String str3, int i, int i2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ready to notify server info: m:");
            sb.append(!str3.equalsIgnoreCase("02:00:00:00:00:00"));
            sb.append(", f:");
            sb.append(i);
            EasyLogger.debug(BluetoothLeService.f9213E, sb.toString());
            DebugTimer.startEvent("Ble-notifyCharacteristic");
            BluetoothLeService bluetoothLeService = BluetoothLeService.this;
            byte[] bArrM62f = BleDebugUtils.utf8Bytes(bluetoothLeService.m9658a(str, str2, str3, i, bluetoothLeService.f9241v, i2).toString());
            if (bArrM62f == null || BluetoothLeService.this.f9237r == null) {
                BluetoothLeService.this.m9676b(6, 1);
                return;
            }
            BluetoothLeService.this.m9677b(BleProtocolConstants.Uuids.SERVER_CONNECTION_INFO_CHARACTERISTIC.getUuid(), bArrM62f);
            BluetoothLeService.this.m9705i();
            connectDone();
            BluetoothLeService.this.m9666a(1, 0);
        }

        @Override
        public void open(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
            BluetoothLeService.this.f9238s = str;
            BluetoothLeService.this.f9239t = str2;
            BluetoothLeService bluetoothLeService = BluetoothLeService.this;
            if (str3 == null) {
                str3 = "";
            }
            bluetoothLeService.f9240u = str3;
            BluetoothLeService bluetoothLeService2 = BluetoothLeService.this;
            if (str4 == null) {
                str4 = "";
            }
            bluetoothLeService2.f9241v = str4;
            BluetoothLeService.this.f9242w = str5;
            BluetoothLeService.this.f9243x = str6;
            BluetoothLeService.this.f9219D = z;
            BluetoothLeService.this.m9733j();
        }

        @Override
        public void registerBluetoothLeObserver(BluetoothLeObserver bluetoothLeObserver) {
            BluetoothLeService.this.m9728a(bluetoothLeObserver);
        }

        @Override
        public void setBandSupported(int i) {
            BluetoothLeService.this.f9245z = i;
        }

        @Override
        public void setP2pDeviceMac(String str) {
            if (!TextUtils.isEmpty(BluetoothLeService.this.f9216A) && !TextUtils.equals(BluetoothLeService.this.f9216A, str)) {
                EasyLogger.error(BluetoothLeService.f9213E, "Warning!!!! Mac address changed from " + BluetoothLeService.this.f9216A + " to " + str);
            }
            BluetoothLeService.this.f9216A = str;
        }

        @Override
        public void startScan() {
            BluetoothLeService.this.m9735p();
        }

        @Override
        public void stopAdvertise() throws RemoteException {
            BluetoothLeService.this.m9723q();
        }

        @Override
        public void stopScan() {
            BluetoothLeService.this.m9736r();
        }

        @Override
        public void unregisterBluetoothLeObserver(BluetoothLeObserver bluetoothLeObserver) {
            BluetoothLeService.this.m9730b(bluetoothLeObserver);
        }
    }

    public class ClientGattCallback extends BluetoothGattCallback {

        private static final String f9268f = "GattClientCallback";

        private int f9269a;

        private boolean f9270b;

        private boolean f9271c;

        private AtomicBoolean f9272d;

        public class a implements Runnable {
            public a() {
            }

            @Override
            public void run() {
                try {
                    BluetoothDevice remoteDevice = BluetoothLeService.this.f9221b.getRemoteDevice(BluetoothLeService.this.f9236q);
                    BluetoothLeService bluetoothLeService = BluetoothLeService.this;
                    bluetoothLeService.f9226g = remoteDevice.connectGatt(bluetoothLeService, false, bluetoothLeService.f9227h);
                } catch (IllegalArgumentException e2) {
                    EasyLogger.errorWithThrowable(ClientGattCallback.f9268f, "Reconnect failed because of illegal argument.", e2);
                    BluetoothLeService.this.m9676b(3, 1);
                }
            }
        }

        private ClientGattCallback() {
            this.f9269a = 2;
            this.f9270b = false;
            this.f9271c = false;
            this.f9272d = new AtomicBoolean(false);
        }

        public ClientGattCallback(BluetoothLeService bluetoothLeService, BleConnectTimeoutTask runnableC1041a) {
            this();
        }

        private void m9742a(BluetoothGatt bluetoothGatt) {
            if (bluetoothGatt.discoverServices()) {
                EasyLogger.info(f9268f, "Discover service action success.");
            } else {
                EasyLogger.error(f9268f, "Discover service action failed.");
                BluetoothLeService.this.m9676b(3, 1);
            }
        }

        public void m9743a() {
            EasyLogger.info(f9268f, "Connect done.");
            this.f9270b = true;
            if (!this.f9271c || BluetoothLeService.this.f9226g == null) {
                return;
            }
            BluetoothLeService.this.f9226g.close();
        }

        public void m9744a(boolean z) {
            EasyLogger.debug(f9268f, "Set positive: " + z);
            this.f9271c = z;
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            EasyLogger.info(f9268f, "onCharacteristicChanged: connectDone=" + this.f9270b + ", device=" + bluetoothGatt.getDevice() + ", targetDevice=" + BluetoothLeService.this.f9236q + ", characteristic=" + BleProtocolConstants.getCharacteristicName(bluetoothGattCharacteristic.getUuid().toString()) + ", value=" + BleDebugUtils.bytesToHexString(bluetoothGattCharacteristic.getValue()));
            boolean zEquals = TextUtils.equals(BluetoothLeService.this.f9236q, Objects.toString(bluetoothGatt.getDevice()));
            if (!this.f9270b && zEquals && BleProtocolConstants.Uuids.SERVER_CONNECTION_INFO_CHARACTERISTIC.getUuid().equals(bluetoothGattCharacteristic.getUuid())) {
                BluetoothLeService.this.m9690d(BleDebugUtils.utf8String(BleDebugUtils.trimTrailingZeroBytes(bluetoothGattCharacteristic.getValue())));
            }
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            int i3;
            EasyLogger.debug(f9268f, "onConnectionStateChange: isConnectDone=" + this.f9270b + ", device=" + bluetoothGatt.getDevice() + ", targetDevice=" + BluetoothLeService.this.f9236q + ", status=" + i + ", newState=" + i2);
            boolean zEquals = TextUtils.equals(BluetoothLeService.this.f9236q, Objects.toString(bluetoothGatt.getDevice()));
            if (i != 0) {
                EasyLogger.warn(f9268f, "Gatt client status: " + i);
                if (this.f9270b || !zEquals) {
                    bluetoothGatt.close();
                } else if (i != 133 || (i3 = this.f9269a) <= 0) {
                    BluetoothLeService.this.m9676b(3, 1);
                } else {
                    this.f9269a = i3 - 1;
                    EasyLogger.warn(f9268f, "Gatt reconnect...");
                    bluetoothGatt.close();
                    if (BluetoothLeService.this.f9229j != null) {
                        BluetoothLeService.this.f9229j.postDelayed(new a(), 300L);
                    } else {
                        EasyLogger.warn(f9268f, "Handler is null, cancel reconnect.");
                        BluetoothLeService.this.m9676b(3, 1);
                    }
                }
                if (i == 257) {
                    EasyLogger.error(f9268f, "Receives GATT_FAILURE !!");
                    return;
                }
                return;
            }
            if (i2 == 0) {
                EasyLogger.warn(f9268f, "Gatt disconnected");
                if (this.f9270b || !zEquals) {
                    bluetoothGatt.close();
                    return;
                } else {
                    BluetoothLeService.this.m9732g();
                    return;
                }
            }
            if (i2 == 2 && !this.f9270b && zEquals) {
                BluetoothLeService.this.f9235p.set(true);
                if (!this.f9272d.compareAndSet(false, true)) {
                    EasyLogger.warn(f9268f, "Twice in onConnectionStateChange, ignored.");
                    return;
                }
                DebugTimer.endEvent("Ble-connect");
                DebugTimer.startEvent("Ble-discoverServices");
                if (bluetoothGatt.requestMtu(512)) {
                    return;
                }
                m9742a(bluetoothGatt);
            }
        }

        @Override
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            EasyLogger.info(f9268f, "onMtuChanged: mtu=" + i + ", status=" + i2);
            EasyLogger.info(f9268f, "Connected, Trying discoverService...");
            m9742a(bluetoothGatt);
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            EasyLogger.info(f9268f, "onServicesDiscovered: connectDone=" + this.f9270b + ", device=" + bluetoothGatt.getDevice() + ", targetDevice=" + BluetoothLeService.this.f9236q + ", status=" + i);
            boolean zEquals = TextUtils.equals(BluetoothLeService.this.f9236q, Objects.toString(bluetoothGatt.getDevice()));
            if (i != 0) {
                EasyLogger.warn(f9268f, "ServicesDiscover failed with status: " + i);
                if (this.f9270b || !zEquals) {
                    bluetoothGatt.close();
                    return;
                } else {
                    BluetoothLeService.this.m9676b(3, 1);
                    return;
                }
            }
            if (this.f9270b || !zEquals) {
                return;
            }
            EasyLogger.info(f9268f, "Connected and services discovered ! Hooyaaah!");
            DebugTimer.endEvent("Ble-discoverServices");
            DebugTimer.startEvent("Ble-writeCharacteristics");
            if (bluetoothGatt.getService(BleProtocolConstants.Uuids.SHARELINK_SERVICE.getUuid()) != null) {
                String strSubstring = UUID.randomUUID().toString().substring(0, 6);
                if (!BluetoothLeService.this.m9669a(BleProtocolConstants.Uuids.CLIENT_INFO_CHARACTERISTIC.getUuid(), BleDebugUtils.utf8Bytes(BluetoothLeService.this.m9694e(strSubstring).toString()))) {
                    BluetoothLeService.this.m9676b(6, 1);
                    return;
                }
                BluetoothLeService.this.m9668a(BleProtocolConstants.Uuids.SERVER_CONNECTION_INFO_CHARACTERISTIC.getUuid());
                BluetoothLeService.this.m9678b(false);
                synchronized (BluetoothLeService.this.f9218C) {
                    Iterator it = BluetoothLeService.this.f9218C.iterator();
                    while (it.hasNext()) {
                        try {
                            ((BluetoothLeObserver) it.next()).onPinAvailable(strSubstring);
                        } catch (RemoteException e2) {
                            EasyLogger.errorWithThrowable(f9268f, "Error when invoke onPinAvailable.", e2);
                        }
                    }
                }
            }
        }
    }

    public class ServerGattCallback extends BluetoothGattServerCallback {

        private static final String f9275j = "GattServerCallback";

        private boolean f9276a;

        private boolean f9277b;

        private Map<String, byte[]> f9278c;

        private Map<String, UUID> f9279d;

        private List<Pair<BluetoothDevice, String>> f9280e;

        private boolean f9281f;

        private String f9282g;

        private PendingBleAction f9283h;

        private ServerGattCallback() {
            this.f9276a = true;
            this.f9277b = true;
            this.f9278c = new LinkedHashMap<>();
            this.f9279d = new LinkedHashMap<>();
            this.f9280e = new LinkedList();
            this.f9281f = true;
        }

        public ServerGattCallback(BluetoothLeService bluetoothLeService, BleConnectTimeoutTask runnableC1041a) {
            this();
        }

        private void m9745a(BluetoothDevice bluetoothDevice, byte[] bArr) {
            if (this.f9281f || bluetoothDevice.getAddress().equals(BluetoothLeService.this.f9237r)) {
                m9747c();
                BluetoothLeService.this.f9237r = bluetoothDevice.getAddress();
                BluetoothLeService.this.m9686c(BleDebugUtils.utf8String(bArr));
                return;
            }
            EasyLogger.debug(f9275j, "There is already a phone connecting, caching client info. phone address:" + bluetoothDevice.getAddress());
            this.f9280e.add(new Pair<>(bluetoothDevice, BleDebugUtils.utf8String(bArr)));
        }

        private byte[] m9746a(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, int i, byte[] bArr) {
            if (!z) {
                return bArr;
            }
            this.f9279d.put(bluetoothDevice.getAddress(), bluetoothGattCharacteristic.getUuid());
            byte[] bArr2 = this.f9278c.get(bluetoothDevice.getAddress());
            if (bArr2 == null) {
                bArr2 = new byte[1024];
                this.f9278c.put(bluetoothDevice.getAddress(), bArr2);
            }
            if (i < 0 || bArr.length + i > bArr2.length) {
                return null;
            }
            System.arraycopy(bArr, 0, bArr2, i, bArr.length);
            return null;
        }

        private void m9747c() {
            this.f9276a = false;
            this.f9281f = false;
        }

        public void m9748a() {
            if (this.f9280e.isEmpty()) {
                this.f9281f = true;
                return;
            }
            Pair<BluetoothDevice, String> pair = this.f9280e.get(0);
            BluetoothDevice bluetoothDevice = (BluetoothDevice) pair.first;
            BluetoothLeService.this.f9237r = bluetoothDevice.getAddress();
            EasyLogger.debug(f9275j, "process cached client info. phone address:" + bluetoothDevice.getAddress());
            m9747c();
            BluetoothLeService.this.m9686c((String) pair.second);
            this.f9280e.remove(0);
        }

        public void m9749a(String str, PendingBleAction interfaceC1052l) {
            this.f9282g = str;
            this.f9283h = interfaceC1052l;
        }

        public void m9750a(boolean z) {
            EasyLogger.debug(f9275j, "Set positive: " + z);
            this.f9277b = z;
        }

        public void m9751b() {
            try {
                this.f9276a = true;
                if (this.f9277b) {
                    BluetoothDevice remoteDevice = BluetoothLeService.this.f9221b.getRemoteDevice(BluetoothLeService.this.f9237r);
                    EasyLogger.debug(f9275j, "connectDone for " + BluetoothLeService.this.f9237r);
                    BluetoothLeService.this.f9224e.cancelConnection(remoteDevice);
                }
            } catch (IllegalArgumentException e2) {
                EasyLogger.warn(f9275j, "cancelConnection for " + BluetoothLeService.this.f9237r + " failed: " + e2.getMessage());
            }
        }

        public void m9752d() {
            this.f9280e.clear();
            this.f9281f = true;
        }

        @Override
        public void onCharacteristicWriteRequest(BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
            byte[] bArrM9746a;
            EasyLogger.info(f9275j, "onCharacteristicWriteRequest: device=" + bluetoothDevice + ", requestId=" + i + ", offset=" + i2 + ", preparedWrite=" + z + ", responseNeeded=" + z2 + ", characteristic={" + bluetoothGattCharacteristic.getUuid() + ", " + BleDebugUtils.bytesToHexString(bArr) + "}");
            if (BluetoothLeService.this.f9224e != null && z2) {
                BluetoothLeService.this.f9224e.sendResponse(bluetoothDevice, i, 0, i2, bArr);
            }
            if (!BleProtocolConstants.Uuids.CLIENT_INFO_CHARACTERISTIC.getUuid().equals(bluetoothGattCharacteristic.getUuid()) || (bArrM9746a = m9746a(bluetoothDevice, bluetoothGattCharacteristic, z, i2, bArr)) == null) {
                return;
            }
            m9745a(bluetoothDevice, bArrM9746a);
        }

        @Override
        public void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i, int i2) {
            if (bluetoothDevice == null) {
                EasyLogger.error(f9275j, "bluetooth device cannot be null.");
                return;
            }
            EasyLogger.debug(f9275j, "onConnectionStateChange: device=" + bluetoothDevice + ", targetMac=" + BluetoothLeService.this.f9237r + ", status=" + i + ", newState=" + i2);
            if (TextUtils.equals(bluetoothDevice.getAddress(), BluetoothLeService.this.f9236q) && i2 == 0) {
                EasyLogger.debug(f9275j, "Client(self) disconnected with GATT server.");
                BluetoothLeService.this.f9235p.set(false);
            }
            if (TextUtils.equals(bluetoothDevice.getAddress(), this.f9282g)) {
                EasyLogger.error(f9275j, "Listen device disconnected.");
                PendingBleAction interfaceC1052l = this.f9283h;
                if (interfaceC1052l != null) {
                    interfaceC1052l.mo9739a();
                }
                this.f9282g = null;
                this.f9283h = null;
            }
            boolean zEquals = TextUtils.equals(BluetoothLeService.this.f9237r, bluetoothDevice.getAddress());
            if (this.f9276a || !zEquals) {
                return;
            }
            if (i != 0 || i2 == 0) {
                m9751b();
                m9752d();
                BluetoothLeService.this.m9676b(4, 1);
            }
        }

        @Override
        public void onExecuteWrite(BluetoothDevice bluetoothDevice, int i, boolean z) {
            String str;
            EasyLogger.info(f9275j, "onExecuteWrite: device=" + bluetoothDevice + ", requestId=" + i + ", execute=" + z);
            byte[] bArrRemove = this.f9278c.remove(bluetoothDevice.getAddress());
            UUID uuidRemove = this.f9279d.remove(bluetoothDevice.getAddress());
            if (uuidRemove == null) {
                str = "Characteristic Uuid is null.";
            } else {
                if (bArrRemove != null) {
                    EasyLogger.info(f9275j, "Cache byte: " + BleDebugUtils.bytesToHexString(bArrRemove));
                    if (BluetoothLeService.this.f9224e != null) {
                        BluetoothLeService.this.f9224e.sendResponse(bluetoothDevice, i, 0, 0, null);
                    }
                    if (BleProtocolConstants.Uuids.CLIENT_INFO_CHARACTERISTIC.getUuid().equals(uuidRemove)) {
                        m9745a(bluetoothDevice, bArrRemove);
                        return;
                    }
                    return;
                }
                str = "Cache byte[] is null.";
            }
            EasyLogger.warn(f9275j, str);
        }

        @Override
        public void onMtuChanged(BluetoothDevice bluetoothDevice, int i) {
            EasyLogger.info(f9275j, "onMtuChanged: device=" + bluetoothDevice.getAddress() + ", mtu=" + i);
        }

        @Override
        public void onServiceAdded(int i, BluetoothGattService bluetoothGattService) {
            EasyLogger.debug(f9275j, "onServiceAdded: " + BleProtocolConstants.getServiceName(bluetoothGattService.getUuid().toString()));
        }
    }

    public interface PendingBleAction {
        void mo9739a();
    }

    public BluetoothLeService() {
        BleConnectTimeoutTask runnableC1041a = null;
        this.f9225f = new ServerGattCallback(this, runnableC1041a);
        this.f9217B = new BluetoothLeServiceBinder(this, runnableC1041a);
    }

    private AdvertiseSettings m9652a(boolean z) {
        AdvertiseSettings.Builder builder = new AdvertiseSettings.Builder();
        builder.setAdvertiseMode(2);
        builder.setTxPowerLevel(3);
        builder.setTimeout(0);
        return builder.build();
    }

    private String m9655a(String str, int i) {
        while (BleDebugUtils.utf8Bytes(str).length > i) {
            str = str.substring(0, str.length() - 1);
        }
        EasyLogger.info(f9213E, "Trim string: " + str);
        return str;
    }

    private List<ScanFilter> m9656a() {
        ArrayList arrayList = new ArrayList();
        ScanFilter.Builder builder = new ScanFilter.Builder();
        builder.setServiceUuid(BleProtocolConstants.Uuids.ADVERTISE_SERVICE);
        arrayList.add(builder.build());
        return arrayList;
    }

    public JSONObject m9658a(String str, String str2, String str3, int i, String str4, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(BleProtocolConstants.JsonFields.DEVICE_NAME, str4);
            jSONObject.put(BleProtocolConstants.JsonFields.WIFI_SSID, str);
            jSONObject.put(BleProtocolConstants.JsonFields.WIFI_PASSWORD, str2);
            jSONObject.put(BleProtocolConstants.JsonFields.MAC_ADDRESS, str3);
            jSONObject.put(BleProtocolConstants.JsonFields.WIFI_FREQUENCY, i);
            jSONObject.put(BleProtocolConstants.JsonFields.SERVER_PORT, 0);
            jSONObject.put(BleProtocolConstants.JsonFields.CONNECTION_TYPE, i2);
            return jSONObject;
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9213E, "Generate server info failed.", e2);
            return null;
        }
    }

    public void m9659a(int i) {
        BluetoothAdapter bluetoothAdapter = this.f9221b;
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
                BluetoothStateReceiver c1042b = new BluetoothStateReceiver(i);
                this.f9234o = c1042b;
                registerReceiver(c1042b, intentFilter);
                boolean zEnable = this.f9221b.enable();
                EasyLogger.debug(f9213E, "Enabling Bluetooth Action: " + zEnable);
                if (zEnable) {
                    return;
                } else {
                    try {
                        unregisterReceiver(this.f9234o);
                    } catch (RuntimeException unused) {
                    }
                }
            } else {
                if (m9713l()) {
                    return;
                }
                if (i > 0) {
                    m9659a(i - 1);
                    return;
                }
            }
            m9676b(1, 2);
        }
    }

    public void m9665a(boolean z, DeviceCache.BtDevice btDevice) {
        synchronized (this.f9218C) {
            for (BluetoothLeObserver bluetoothLeObserver : this.f9218C) {
                if (z) {
                    try {
                        bluetoothLeObserver.onDeviceMatch(btDevice);
                    } catch (RemoteException e2) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Observer.");
                        sb.append(z ? "onDeviceMatch" : "onDeviceLost");
                        sb.append(" failed");
                        EasyLogger.errorWithThrowable(f9213E, sb.toString(), e2);
                    }
                } else {
                    try {
                        bluetoothLeObserver.onDeviceLost(btDevice);
                    } catch (RemoteException e2) {
                        EasyLogger.errorWithThrowable(f9213E, "Observer.onDeviceLost failed", e2);
                    }
                }
            }
        }
    }

    public boolean m9666a(int i, int i2) {
        StringBuilder sb;
        EasyLogger.debug(f9213E, "Change state to " + i2 + " if current state is " + i);
        boolean zCompareAndSet = this.f9244y.compareAndSet(i, i2);
        if (zCompareAndSet) {
            sb = new StringBuilder();
            sb.append("Change state to ");
            sb.append(i2);
            sb.append(" success");
            EasyLogger.debug(f9213E, sb.toString());
        } else {
            sb = new StringBuilder();
            sb.append("Change state to ");
            sb.append(i2);
            sb.append(" failed");
            EasyLogger.warn(f9213E, sb.toString());
        }
        return zCompareAndSet;
    }

    public boolean m9668a(UUID uuid) {
        String str;
        BluetoothGatt bluetoothGatt = this.f9226g;
        if (bluetoothGatt != null) {
            List<BluetoothGattService> services = bluetoothGatt.getServices();
            if (services != null && services.size() > 0) {
                Iterator<BluetoothGattService> it = services.iterator();
                while (it.hasNext()) {
                    BluetoothGattCharacteristic characteristic = it.next().getCharacteristic(uuid);
                    if (characteristic != null) {
                        return this.f9226g.setCharacteristicNotification(characteristic, true);
                    }
                }
            }
            str = "writeCharacteristic: Characteristic(" + BleProtocolConstants.getCharacteristicName(uuid.toString()) + ") not found.";
        } else {
            str = "subscribeCharacteristic: mBluetoothGatt is null, already disconnected ?";
        }
        EasyLogger.warn(f9213E, str);
        return false;
    }

    public boolean m9669a(UUID uuid, byte[] bArr) {
        String str;
        EasyLogger.info(f9213E, "Trying to send write characteristic(" + BleProtocolConstants.getCharacteristicName(uuid.toString()) + ") request to remote gatt server, value=" + BleDebugUtils.bytesToHexString(bArr));
        BluetoothGatt bluetoothGatt = this.f9226g;
        if (bluetoothGatt != null) {
            List<BluetoothGattService> services = bluetoothGatt.getServices();
            if (services != null && services.size() > 0) {
                Iterator<BluetoothGattService> it = services.iterator();
                while (it.hasNext()) {
                    BluetoothGattCharacteristic characteristic = it.next().getCharacteristic(uuid);
                    if (characteristic != null) {
                        characteristic.setValue(bArr);
                        return this.f9226g.writeCharacteristic(characteristic);
                    }
                }
            }
            str = "writeCharacteristic: Characteristic(" + BleProtocolConstants.getCharacteristicName(uuid.toString()) + ") not found.";
        } else {
            str = "writeCharacteristic: mBluetoothGatt is null, already disconnected ?";
        }
        EasyLogger.warn(f9213E, str);
        return false;
    }

    private ScanSettings m9671b() {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setScanMode(2);
        return builder.build();
    }

    public Runnable m9673b(String str) {
        return new BleConnectTask(str);
    }

    public void m9675b(int i) {
        synchronized (this.f9218C) {
            Iterator<BluetoothLeObserver> it = this.f9218C.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onSuccess(i);
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9213E, "Observer.onSuccess(" + i + ") failed.", e2);
                }
            }
        }
    }

    public void m9676b(int i, int i2) {
        synchronized (this.f9218C) {
            Iterator<BluetoothLeObserver> it = this.f9218C.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onFailure(i, i2);
                } catch (RemoteException e2) {
                    EasyLogger.errorWithThrowable(f9213E, "Observer.onFailure(" + i + ", " + i2 + ") failed.", e2);
                }
            }
        }
    }

    public void m9677b(UUID uuid, byte[] bArr) {
        String str;
        BluetoothGattCharacteristic characteristic = this.f9224e.getService(BleProtocolConstants.Uuids.SHARELINK_SERVICE.getUuid()).getCharacteristic(uuid);
        if (characteristic == null) {
            str = "notify server info failed. characteristic is null ";
        } else {
            if (characteristic.setValue(bArr)) {
                this.f9224e.notifyCharacteristicChanged(this.f9221b.getRemoteDevice(this.f9237r), characteristic, true);
                return;
            }
            str = "notify server info failed. setting characteristic not successful";
        }
        EasyLogger.error(f9213E, str);
        m9676b(3, 1);
    }

    public void m9678b(boolean z) {
        EasyLogger.debug(f9213E, "setConnectTimeout: " + z);
        if (!z) {
            Handler handler = this.f9229j;
            if (handler != null) {
                handler.removeCallbacks(this.f9230k);
                return;
            }
            return;
        }
        Handler handler2 = this.f9229j;
        if (handler2 != null) {
            handler2.postDelayed(this.f9230k, 30000L);
        } else {
            EasyLogger.warn(f9213E, "Enable timeout but handler is null, closed?");
            m9676b(3, 1);
        }
    }

    private AdvertiseData m9683c() {
        return BleAdvertiseDataBuilder.buildShareLinkAdvertiseData(this.f9242w, this.f9238s, this.f9239t, this.f9243x, this.f9219D, getApplicationContext());
    }

    public void m9686c(String str) {
        List<BluetoothLeObserver> list;
        EasyLogger.debug(f9213E, "Client info: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(BleProtocolConstants.JsonFields.DEVICE_ID);
            String string2 = jSONObject.getString(BleProtocolConstants.JsonFields.DEVICE_NAME);
            String string3 = jSONObject.getString(BleProtocolConstants.JsonFields.DEVICE_MODEL);
            int i = jSONObject.getInt(BleProtocolConstants.JsonFields.WIFI_BAND);
            String string4 = jSONObject.getString(BleProtocolConstants.JsonFields.MAC_ADDRESS);
            int iOptInt = jSONObject.optInt(BleProtocolConstants.JsonFields.CONNECTION_TYPE, 1001);
            List<BluetoothLeObserver> list2 = this.f9218C;
            try {
                synchronized (list2) {
                    try {
                        Iterator<BluetoothLeObserver> it = this.f9218C.iterator();
                        while (it.hasNext()) {
                            list = list2;
                            try {
                                it.next().onClientInfoReceived(string, string2, string3, i, string4, iOptInt);
                            } catch (RemoteException e2) {
                                EasyLogger.errorWithThrowable(f9213E, "Observer.onClientInfoReceived(...) failed.", e2);
                                m9676b(4, 1);
                            }
                            list2 = list;
                        }
                    } catch (Throwable th) {
                        th = th;
                        list = list2;
                        throw new RuntimeException(th);
                    }
                }
            } catch (Throwable th2) {
            }
        } catch (Exception e3) {
            EasyLogger.errorWithThrowable(f9213E, "Parsing client info failed.", e3);
            m9676b(4, 1);
        }
    }

    private AdvertiseData m9687d() {
        return BleAdvertiseDataBuilder.buildDeviceNameScanResponse(this.f9241v);
    }

    public void m9690d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(BleProtocolConstants.JsonFields.WIFI_SSID);
            String string2 = jSONObject.getString(BleProtocolConstants.JsonFields.WIFI_PASSWORD);
            String string3 = jSONObject.getString(BleProtocolConstants.JsonFields.MAC_ADDRESS);
            int i = jSONObject.getInt(BleProtocolConstants.JsonFields.WIFI_FREQUENCY);
            synchronized (this.f9218C) {
                Iterator<BluetoothLeObserver> it = this.f9218C.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onServerInfoReceived(string, string2, string3, i);
                    } catch (RemoteException e2) {
                        EasyLogger.errorWithThrowable(f9213E, "Observer.onServerInfoReceived(...) failed.", e2);
                        m9676b(3, 1);
                    }
                }
            }
        } catch (Exception e3) {
            EasyLogger.errorWithThrowable(f9213E, "Parsing server info failed.", e3);
            m9676b(3, 1);
        }
    }

    private BluetoothGattService m9691e() {
        BluetoothGattService bluetoothGattService = new BluetoothGattService(BleProtocolConstants.Uuids.SHARELINK_SERVICE.getUuid(), 0);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BleProtocolConstants.Uuids.CLIENT_INFO_CHARACTERISTIC.getUuid(), 8, 16);
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(BleProtocolConstants.Uuids.SERVER_CONNECTION_INFO_CHARACTERISTIC.getUuid(), 16, 1);
        bluetoothGattCharacteristic2.addDescriptor(new BluetoothGattDescriptor(BleProtocolConstants.Uuids.CLIENT_CHARACTERISTIC_CONFIG.getUuid(), 16));
        bluetoothGattService.addCharacteristic(bluetoothGattCharacteristic);
        bluetoothGattService.addCharacteristic(bluetoothGattCharacteristic2);
        return bluetoothGattService;
    }

    public JSONObject m9694e(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(BleProtocolConstants.JsonFields.DEVICE_ID, this.f9238s);
            jSONObject.put(BleProtocolConstants.JsonFields.DEVICE_NAME, this.f9240u);
            jSONObject.put(BleProtocolConstants.JsonFields.PIN_CODE_OR_AUTHENTICATION, str);
            jSONObject.put(BleProtocolConstants.JsonFields.WIFI_BAND, this.f9245z);
            jSONObject.put(BleProtocolConstants.JsonFields.MAC_ADDRESS, this.f9216A);
            EasyLogger.info(f9213E, "Client info: " + jSONObject.toString());
            return jSONObject;
        } catch (Exception e2) {
            EasyLogger.errorWithThrowable(f9213E, "Generate receiver info failed.", e2);
            return null;
        }
    }

    public BluetoothDevice m9695f(String str) {
        BluetoothDevice remoteDevice = this.f9221b.getRemoteDevice(str);
        EasyLogger.debug(f9213E, "Bond state = " + remoteDevice.getBondState());
        return remoteDevice;
    }

    private boolean m9702h() {
        this.f9223d = this.f9221b.getBluetoothLeAdvertiser();
        this.f9222c = CompatLeScanner.create(this.f9221b.getBluetoothLeScanner());
        StringBuilder sb = new StringBuilder();
        sb.append("initialize: advertiser=");
        sb.append(this.f9223d != null);
        sb.append(", scanner=");
        sb.append(this.f9222c != null);
        EasyLogger.debug(f9213E, sb.toString());
        return (this.f9223d == null || this.f9222c == null) ? false : true;
    }

    public void m9705i() {
        synchronized (this.f9218C) {
            Iterator<BluetoothLeObserver> it = this.f9218C.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onServerAddressSent();
                } catch (Exception e2) {
                    EasyLogger.errorWithThrowable(f9213E, "notifyServerAddressSent Exception", e2);
                }
            }
        }
    }

    private void m9709k() {
        EasyLogger.debug(f9213E, "Reset state.");
        this.f9244y.set(0);
    }

    public boolean m9713l() {
        try {
            if (!m9702h()) {
                return false;
            }
            m9734n();
            return true;
        } catch (IllegalStateException e2) {
            EasyLogger.warnWithThrowable(f9213E, "Setup Bt failed because of illegal state.", e2);
            return false;
        }
    }

    private void m9715m() throws IllegalStateException {
        int i;
        EasyLogger.debug(f9213E, "Starting advertiser.");
        AdvertisingSetParameters.Builder txPowerLevel = new AdvertisingSetParameters.Builder().setConnectable(true).setScannable(true).setLegacyMode(true).setPrimaryPhy(1).setTxPowerLevel(1);
        try {
            Field declaredField = AdvertisingSetParameters.Builder.class.getDeclaredField("mInterval");
            declaredField.setAccessible(true);
            declaredField.set(txPowerLevel, 48);
        } catch (NoSuchFieldException e2) {
            // Android 新版本隐藏字段名称可能变化，系统默认广播间隔仍可正常启动。
            EasyLogger.warn(f9213E, "Advertising interval field is unavailable, use platform default interval.");
        } catch (Exception e2) {
            EasyLogger.warnWithThrowable(f9213E, "Set advertising interval failed, use platform default interval.", e2);
        }
        AdvertisingSetParameters advertisingSetParametersBuild = txPowerLevel.build();
        AdvertiseSettings advertiseSettingsM9652a = m9652a(true);
        AdvertiseData advertiseDataM9683c = m9683c();
        AdvertiseData advertiseDataM9687d = m9687d();
        int timeout = advertiseSettingsM9652a.getTimeout();
        if (timeout > 0) {
            i = timeout >= 10 ? timeout / 10 : 1;
        } else {
            i = 0;
        }
        BluetoothLeAdvertiser bluetoothLeAdvertiser = this.f9223d;
        if (bluetoothLeAdvertiser != null) {
            LegacyAdvertiseStopCallback c1047g = new LegacyAdvertiseStopCallback();
            this.f9231l = c1047g;
            bluetoothLeAdvertiser.startAdvertisingSet(advertisingSetParametersBuild, advertiseDataM9683c, advertiseDataM9687d, null, null, i, 0, c1047g);
        }
    }

    private boolean m9720o() {
        EasyLogger.debug(f9213E, "Starting Gatt Server");
        if (this.f9224e != null) {
            EasyLogger.warn(f9213E, "startGattServer while mBluetoothGattServer is not null");
            this.f9224e.close();
            this.f9224e = null;
        }
        this.f9225f.m9752d();
        this.f9225f.m9750a(false);
        BluetoothGattServer bluetoothGattServerOpenGattServer = this.f9220a.openGattServer(this, this.f9225f);
        this.f9224e = bluetoothGattServerOpenGattServer;
        if (bluetoothGattServerOpenGattServer != null) {
            bluetoothGattServerOpenGattServer.addService(m9691e());
        } else {
            EasyLogger.error(f9213E, "Opening gatt server failed.");
            m9676b(1, 3);
        }
        return this.f9224e != null;
    }

    public void m9723q() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser;
        AdvertisingSetCallback advertisingSetCallback;
        EasyLogger.debug(f9213E, "Stopping advertisers.");
        if (this.f9221b.isEnabled() && (bluetoothLeAdvertiser = this.f9223d) != null && (advertisingSetCallback = this.f9231l) != null) {
            bluetoothLeAdvertiser.stopAdvertisingSet(advertisingSetCallback);
        }
        this.f9225f.m9752d();
        this.f9231l = null;
    }

    public void m9728a(BluetoothLeObserver bluetoothLeObserver) {
        synchronized (this.f9218C) {
            if (!this.f9218C.contains(bluetoothLeObserver)) {
                this.f9218C.add(bluetoothLeObserver);
            }
        }
    }

    public synchronized void m9729a(String str) {
        EasyLogger.debug(f9213E, "Connecting device=" + str);
        if (this.f9221b == null) {
            EasyLogger.error(f9213E, "BluetoothAdapter not initialized.");
            m9676b(3, 2);
            return;
        }
        if (str == null) {
            EasyLogger.error(f9213E, "Invalid address.");
            m9676b(3, 4);
            return;
        }
        if (!m9666a(0, 1)) {
            EasyLogger.warn(f9213E, "Conflicted. state is busy now.");
            m9676b(3, 6);
            return;
        }
        if (this.f9226g != null) {
            EasyLogger.warn(f9213E, "Disconnecting existing different bluetoothGatt connection.");
            this.f9227h.m9743a();
            this.f9225f.m9749a(this.f9236q, new DeferredConnectCallback(str));
            this.f9226g.close();
            this.f9226g = null;
            Handler handler = this.f9229j;
            if (handler != null) {
                handler.postDelayed(m9673b(str), 500L);
            } else {
                EasyLogger.warn(f9213E, "Handler is null, share service may already closed.");
                m9676b(3, 1);
            }
        } else {
            m9673b(str).run();
        }
    }

    public void m9730b(BluetoothLeObserver bluetoothLeObserver) {
        synchronized (this.f9218C) {
            this.f9218C.remove(bluetoothLeObserver);
        }
    }

    public void m9731f() {
        EasyLogger.debug(f9213E, "Closing...");
        Handler handler = this.f9229j;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f9229j = null;
        }
        HandlerC1048h handlerC1048h = this.f9228i;
        if (handlerC1048h != null) {
            handlerC1048h.removeCallbacksAndMessages(null);
            Looper looper = handlerC1048h.getLooper();
            if (looper != null) {
                looper.quitSafely();
            }
            this.f9228i = null;
        }
        try {
            unregisterReceiver(this.f9234o);
        } catch (Exception unused) {
        }
        if (this.f9221b.isEnabled()) {
            BluetoothGatt bluetoothGatt = this.f9226g;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
            }
            BluetoothGattServer bluetoothGattServer = this.f9224e;
            if (bluetoothGattServer != null) {
                bluetoothGattServer.close();
            }
            CompatLeScanner c0013g = this.f9222c;
            if (c0013g != null && this.f9232m != null) {
                c0013g.stopScan();
            }
        }
        m9709k();
        m9723q();
        m9732g();
        this.f9223d = null;
        this.f9226g = null;
        this.f9224e = null;
        this.f9222c = null;
        this.f9232m = null;
        EasyLogger.debug(f9213E, "Closed.");
    }

    public synchronized void m9732g() {
        EasyLogger.debug(f9213E, "disconnect...");
        m9709k();
        if (this.f9226g != null) {
            EasyLogger.debug(f9213E, "close exist bluetoothGatt connection");
            this.f9226g.close();
            this.f9226g = null;
        }
        if (this.f9224e == null || TextUtils.isEmpty(this.f9237r)) {
            m9678b(false);
            this.f9237r = null;
            this.f9236q = null;
            this.f9235p.set(false);
        } else {
            try {
                BluetoothDevice remoteDevice = this.f9221b.getRemoteDevice(this.f9237r);
                EasyLogger.debug(f9213E, "cancelConnection for " + this.f9237r);
                this.f9224e.cancelConnection(remoteDevice);
            } catch (IllegalArgumentException e2) {
                EasyLogger.errorWithThrowable(f9213E, "cancelConnection failed because of illegal argument.", e2);
            }
            m9678b(false);
            this.f9237r = null;
            this.f9236q = null;
            this.f9235p.set(false);
        }
    }

    public void m9733j() {
        EasyLogger.debug(f9213E, "Opening...");
        this.f9229j = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread("ConnectDelayer");
        handlerThread.start();
        if (handlerThread.getLooper() != null) {
            this.f9228i = new HandlerC1048h(handlerThread.getLooper());
        }
        m9659a(1);
    }

    public void m9734n() {
        if (m9720o()) {
            try {
                m9715m();
            } catch (Exception e2) {
                EasyLogger.debug(f9213E, "startAdvertising() e:" + e2);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.f9217B;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        this.f9220a = bluetoothManager;
        if (bluetoothManager != null) {
            this.f9221b = bluetoothManager.getAdapter();
        }
        if (this.f9220a == null || this.f9221b == null) {
            EasyLogger.error(f9213E, "mBluetoothManager=" + this.f9220a + ", mBluetoothAdapter=" + this.f9221b);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        m9731f();
    }

    public void m9735p() {
        EasyLogger.debug(f9213E, "Starting scan...");
        DeviceCache deviceCache = new DeviceCache();
        this.f9233n = deviceCache;
        deviceCache.setDeviceCacheListener(new DeviceCacheCallback());
        CompatLeScanner c0013g = this.f9222c;
        List<ScanFilter> listM9656a = m9656a();
        ScanSettings scanSettingsM9671b = m9671b();
        LeScanCallback c1044d = new LeScanCallback();
        this.f9232m = c1044d;
        c0013g.startScan(listM9656a, scanSettingsM9671b, c1044d);
    }

    public void m9736r() {
        EasyLogger.debug(f9213E, "Stopping scan...");
        this.f9233n = null;
        if (!this.f9221b.isEnabled()) {
            EasyLogger.warn(f9213E, "Stop scan after bluetooth down, do nothing.");
            return;
        }
        CompatLeScanner c0013g = this.f9222c;
        if (c0013g == null || this.f9232m == null) {
            return;
        }
        c0013g.stopScan();
        this.f9232m = null;
        EasyLogger.debug(f9213E, "Stop scan success");
    }
}
