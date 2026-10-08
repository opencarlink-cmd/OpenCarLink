package com.share.connect.ble;

import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.share.connect.Device;
import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.bluetooth.BleDebugUtils;
import com.ucarhu.demo.sharelink.bluetooth.BleProtocolConstants;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DeviceCache {

    private static final String TAG = "DeviceCache";

    private DeviceCacheListener listener;

    private final LinkedHashMap<String, BtDevice> devicesByVin = new LinkedHashMap<>();

    public class BtDevice extends Device {
        private int signal;
        private String mac;

        public BtDevice() {
        }

        public String getMac() {
            return this.mac;
        }

        public int getSignal() {
            return this.signal;
        }

        public BtDevice setMac(String mac) {
            this.mac = mac;
            return this;
        }

        public BtDevice setSignal(int signal) {
            this.signal = signal;
            return this;
        }
    }

    /** 扫描缓存变化监听器，负责把新增、丢失的车机蓝牙设备通知给服务层。 */
    public interface DeviceCacheListener {
        void onDeviceAdded(BtDevice btDevice);

        void onDeviceRemoved(BtDevice btDevice);
    }

    private void addOrUpdateDevice(BtDevice btDevice) {
        String macAddress = btDevice.mac;
        String vin = btDevice.getVin();
        String productName = btDevice.getPName();
        boolean hasVin = containsVin(vin);
        boolean hasMacAddress = containsMacAddress(macAddress);
        if (!hasVin) {
            this.devicesByVin.put(vin, btDevice);
        } else {
            if (hasMacAddress) {
                return;
            }
            BtDevice oldDevice = this.devicesByVin.get(vin);
            this.devicesByVin.put(vin, btDevice);
            if (oldDevice == null || TextUtils.equals(oldDevice.getPName(), productName)) {
                return;
            }
        }
        this.listener.onDeviceAdded(btDevice);
    }

    private boolean isValidScanResult(ScanResult scanResult) {
        String errorMessage;
        ScanRecord scanRecord = scanResult.getScanRecord();
        if (scanRecord == null) {
            errorMessage = "ScanRecord is null.";
        } else if (scanResult.getDevice() == null) {
            errorMessage = "BluetoothDevice is null.";
        } else {
            if ((scanRecord.getServiceUuids() != null ? scanRecord.getServiceUuids().get(0) : null) != null) {
                return true;
            }
            errorMessage = "Service UUID is null, it shouldn't happen.";
        }
        EasyLogger.error(TAG, errorMessage);
        return false;
    }

    private boolean containsMacAddress(String macAddress) {
        Iterator<BtDevice> iterator = this.devicesByVin.values().iterator();
        while (iterator.hasNext()) {
            if (TextUtils.equals(macAddress, iterator.next().mac)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    private BtDevice findDeviceByMac(String macAddress) {
        for (BtDevice btDevice : this.devicesByVin.values()) {
            if (TextUtils.equals(macAddress, btDevice.mac)) {
                return btDevice;
            }
        }
        return null;
    }

    @Nullable
    private BtDevice parseBleScanResult(ScanResult scanResult) {
        String deviceId = BleDebugUtils.utf8String(scanResult.getScanRecord().getServiceData(BleProtocolConstants.Uuids.ADVERTISE_DEVICE_ID));
        String deviceName = BleDebugUtils.utf8String(scanResult.getScanRecord().getServiceData(BleProtocolConstants.Uuids.ADVERTISE_DEVICE_NAME));
        if (TextUtils.isEmpty(deviceId) || TextUtils.isEmpty(deviceName)) {
            EasyLogger.warn(TAG, "Package data missing, illegal content:\n" + BleDebugUtils.bytesToHexString(scanResult.getScanRecord().getBytes()));
            return null;
        }
        Log.d(TAG, String.format("parse: id=%s, name=%s", deviceId, deviceName));
        BtDevice btDevice = new BtDevice();
        btDevice.setVin(deviceId).setPName(deviceName);
        btDevice.setSignal(scanResult.getRssi());
        btDevice.setMac(scanResult.getDevice().getAddress());
        return btDevice;
    }

    private void removeDevicesByMac(String macAddress) {
        do {
            BtDevice btDevice = findDeviceByMac(macAddress);
            if (btDevice != null) {
                this.devicesByVin.remove(btDevice.getVin());
                this.listener.onDeviceRemoved(btDevice);
            }
        } while (findDeviceByMac(macAddress) != null);
    }

    public LinkedHashMap<String, Integer> getSignalByVin() {
        LinkedHashMap<String, Integer> signalByVin = new LinkedHashMap<>();
        for (BtDevice btDevice : this.devicesByVin.values()) {
            signalByVin.put(btDevice.getVin(), Integer.valueOf(btDevice.getSignal()));
        }
        return signalByVin;
    }

    public void setDeviceCacheListener(DeviceCacheListener listener) {
        this.listener = listener;
    }

    public boolean containsVin(String vin) {
        return this.devicesByVin.containsKey(vin);
    }

    public void handleDeviceLostScan(ScanResult scanResult) {
        if (isValidScanResult(scanResult)) {
            removeDevicesByMac(scanResult.getDevice().getAddress());
        }
    }

    public void handleDeviceFoundScan(ScanResult scanResult) {
        BtDevice btDevice;
        if (isValidScanResult(scanResult)) {
            try {
                if (!BleProtocolConstants.Uuids.ADVERTISE_SERVICE.equals(scanResult.getScanRecord().getServiceUuids().get(0)) || (btDevice = parseBleScanResult(scanResult)) == null) {
                    return;
                }
                addOrUpdateDevice(btDevice);
            } catch (Exception e2) {
                EasyLogger.error(TAG, "Parse data error: " + e2.getMessage() + ", ignored.");
            }
        }
    }

    public String getMacByVin(String vin) {
        BtDevice btDevice = this.devicesByVin.get(vin);
        if (btDevice != null) {
            return btDevice.mac;
        }
        EasyLogger.warn(TAG, "Try to get mac with an id which is not exist.");
        return "";
    }
}
