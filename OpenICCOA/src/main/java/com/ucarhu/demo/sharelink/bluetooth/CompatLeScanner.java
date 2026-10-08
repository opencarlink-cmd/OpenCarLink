package com.ucarhu.demo.sharelink.bluetooth;

@androidx.annotation.RequiresApi(api = 21)
public class CompatLeScanner {

    private static final java.lang.String f95g = "CompatLeScanner";

    private android.bluetooth.le.BluetoothLeScanner f96a;

    private com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.c f97b;

    private java.util.LinkedHashMap<android.bluetooth.BluetoothDevice, android.bluetooth.le.ScanResult> f98c = new java.util.LinkedHashMap<>();

    private boolean f99d = false;

    private android.bluetooth.le.ScanCallback f100e = new com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.a();

    private android.bluetooth.le.ScanCallback f101f = new com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.b();

    public class a extends android.bluetooth.le.ScanCallback {
        public a() {
        }

        @Override
        public void onScanFailed(int i) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.f95g, "MatchCallback: onScanFailed with reason: " + i);
            com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.this.dispatchScanFailedOnce(i);
        }

        @Override
        public void onScanResult(int i, android.bluetooth.le.ScanResult scanResult) {
            com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.this.dispatchDeviceFound(scanResult);
        }
    }

    public class b extends android.bluetooth.le.ScanCallback {
        public b() {
        }

        @Override
        public void onScanFailed(int i) {
            com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.f95g, "LostCallback: onScanFailed with reason: " + i);
            com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.this.dispatchScanFailedOnce(i);
        }

        @Override
        public void onScanResult(int i, android.bluetooth.le.ScanResult scanResult) {
            android.bluetooth.BluetoothDevice device = scanResult.getDevice();
            if (i == 4 && com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.this.f98c.containsKey(device)) {
                com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.this.dispatchDeviceLost(scanResult);
            }
        }
    }

    public interface c {

        public static final int f104a = 1;

        public static final int f105b = 0;

        void onScanFailed(int i);

        void onScanResult(int i, android.bluetooth.le.ScanResult scanResult);
    }

    @androidx.annotation.Nullable
    public static com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner create(android.bluetooth.le.BluetoothLeScanner bluetoothLeScanner) {
        if (bluetoothLeScanner == null) {
            return null;
        }
        com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner c0013g = new com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner();
        c0013g.f96a = bluetoothLeScanner;
        return c0013g;
    }

    public synchronized void dispatchScanFailedOnce(int i) {
        if (!this.f99d) {
            com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.c cVar = this.f97b;
            if (cVar != null) {
                cVar.onScanFailed(i);
            }
            this.f99d = true;
        }
    }

    public synchronized void dispatchDeviceLost(android.bluetooth.le.ScanResult scanResult) {
        android.bluetooth.le.ScanResult scanResultRemove = this.f98c.remove(scanResult.getDevice());
        if (scanResultRemove == null) {
            com.ucarhu.demo.logging.EasyLogger.info(f95g, "CompatLeScanner doLost can not find Map key, result is null");
            return;
        }
        com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.c cVar = this.f97b;
        if (cVar != null) {
            cVar.onScanResult(0, scanResultRemove);
        }
    }

    public synchronized void dispatchDeviceFound(android.bluetooth.le.ScanResult scanResult) {
        this.f98c.put(scanResult.getDevice(), scanResult);
        com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.c cVar = this.f97b;
        if (cVar != null) {
            cVar.onScanResult(1, scanResult);
        }
    }

    public void stopScan() {
        com.ucarhu.demo.logging.EasyLogger.info(f95g, "stopScan...");
        try {
            this.f96a.stopScan(this.f100e);
            this.f96a.stopScan(this.f101f);
        } catch (java.lang.IllegalStateException e2) {
            com.ucarhu.demo.logging.EasyLogger.warnWithThrowable(f95g, "Scan stop failed because of illegal state, not a problem.", e2);
        }
        synchronized (this) {
            this.f97b = null;
        }
        this.f98c.clear();
    }

    public void startScan(java.util.List<android.bluetooth.le.ScanFilter> list, android.bluetooth.le.ScanSettings scanSettings, com.ucarhu.demo.sharelink.bluetooth.CompatLeScanner.c cVar) {
        com.ucarhu.demo.logging.EasyLogger.info(f95g, "startScan...");
        synchronized (this) {
            this.f97b = cVar;
            this.f99d = false;
        }
        android.bluetooth.le.ScanSettings scanSettingsBuild = new android.bluetooth.le.ScanSettings.Builder().setScanMode(scanSettings.getScanMode()).setCallbackType(1).build();
        android.bluetooth.le.ScanSettings scanSettingsBuild2 = new android.bluetooth.le.ScanSettings.Builder().setScanMode(scanSettings.getScanMode()).setCallbackType(4).build();
        try {
            com.ucarhu.demo.logging.EasyLogger.info(f95g, "Start match scanner...");
            this.f96a.startScan(list, scanSettingsBuild, this.f100e);
            com.ucarhu.demo.logging.EasyLogger.info(f95g, "Start lost scanner...");
            this.f96a.startScan(list, scanSettingsBuild2, this.f101f);
        } catch (java.lang.IllegalStateException e2) {
            com.ucarhu.demo.logging.EasyLogger.warnWithThrowable(f95g, "Scan start failed because of illegal state.", e2);
        }
    }
}
