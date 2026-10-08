package com.ucarhu.demo.sharelink.bluetooth;

import com.ucarhu.demo.logging.EasyLogger;
import com.ucarhu.demo.sharelink.util.ByteArrayUtils;
import com.ucarhu.demo.util.binary.HexCodec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class BleAdvertiseDataBuilder {

    private static final String TAG = "ScanResponseBuilder";
    private static final String VERSION_SEPARATOR_REGEX = "\\.";
    private static final int DEVICE_NAME_BYTES = 16;
    private static final byte[] ADVERTISE_SERIAL = new byte[1];
    private static final String SERIAL_PREFS = "serial_num_prefs";
    private static final String LAST_SERIAL_NUM = "last_serial_num";

    static {
        new Random().nextBytes(ADVERTISE_SERIAL);
    }

    private BleAdvertiseDataBuilder() {
    }

    public static android.bluetooth.le.AdvertiseData buildDeviceNameScanResponse(String deviceName) {
        if (deviceName == null) {
            throw new IllegalArgumentException();
        }
        byte[] deviceNameBytes = toFixedUtf8NameBytes(deviceName);
        EasyLogger.info(TAG, "BLE scan response ADVERTISE_DEVICE_NAME uuid: " + BleProtocolConstants.Uuids.ADVERTISE_DEVICE_NAME);
        EasyLogger.info(TAG, "BLE scan response deviceName text: " + deviceName);
        EasyLogger.info(TAG, "BLE scan response deviceName serviceData hex: " + BleDebugUtils.bytesToHexString(deviceNameBytes));
        return new android.bluetooth.le.AdvertiseData.Builder()
                .addServiceData(BleProtocolConstants.Uuids.ADVERTISE_DEVICE_NAME, deviceNameBytes)
                .build();
    }

    public static android.bluetooth.le.AdvertiseData buildShareLinkAdvertiseData(String protocolVersion, String deviceId, String productId, String carId, boolean reconnect, android.content.Context context) {
        if (protocolVersion == null || deviceId == null || productId == null || carId == null) {
            throw new IllegalArgumentException();
        }
        byte[] versionBytes = encodeProtocolVersion(protocolVersion);
        byte[] serialBytes = nextAdvertiseSerial(reconnect, context);
        byte[] deviceIdBytes = decodePrefix(deviceId, 6);
        byte[] productIdBytes = decodePrefix(productId, 4);
        byte[] carIdBytes = decodePrefix(carId, 2);
        byte[] advertisePayload = ByteArrayUtils.concat(
                versionBytes,
                serialBytes,
                deviceIdBytes,
                productIdBytes,
                carIdBytes);
        EasyLogger.info(TAG, "BLE advertise ADVERTISE_SERVICE uuid: " + BleProtocolConstants.Uuids.ADVERTISE_SERVICE);
        EasyLogger.info(TAG, "BLE advertise ADVERTISE_DEVICE_ID uuid: " + BleProtocolConstants.Uuids.ADVERTISE_DEVICE_ID);
        EasyLogger.info(TAG, "BLE advertise protocolVersion text: " + protocolVersion + ", hex: " + BleDebugUtils.bytesToHexString(versionBytes));
        EasyLogger.info(TAG, "BLE advertise serial hex: " + BleDebugUtils.bytesToHexString(serialBytes) + ", reconnect: " + reconnect);
        EasyLogger.info(TAG, "BLE advertise deviceId text: " + deviceId + ", serviceData part hex: " + BleDebugUtils.bytesToHexString(deviceIdBytes));
        EasyLogger.info(TAG, "BLE advertise productId text: " + productId + ", serviceData part hex: " + BleDebugUtils.bytesToHexString(productIdBytes));
        EasyLogger.info(TAG, "BLE advertise carId text: " + carId + ", serviceData part hex: " + BleDebugUtils.bytesToHexString(carIdBytes));
        EasyLogger.info(TAG, "BLE advertise ADVERTISE_DEVICE_ID serviceData full hex: " + BleDebugUtils.bytesToHexString(advertisePayload));
        return new android.bluetooth.le.AdvertiseData.Builder()
                .addServiceUuid(BleProtocolConstants.Uuids.ADVERTISE_SERVICE)
                .addServiceData(BleProtocolConstants.Uuids.ADVERTISE_DEVICE_ID, advertisePayload)
                .build();
    }

    private static byte[] nextAdvertiseSerial(boolean reconnect, android.content.Context context) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SERIAL_PREFS, 0);
        if (!reconnect) {
            int lastSerial = sharedPreferences.getInt(LAST_SERIAL_NUM, 0);
            while (lastSerial == ADVERTISE_SERIAL[0]) {
                new Random().nextBytes(ADVERTISE_SERIAL);
            }
            EasyLogger.debug(TAG, "advertising new serial number");
            ADVERTISE_SERIAL[0] = (byte) (ADVERTISE_SERIAL[0] + 1);
        }
        EasyLogger.debug(TAG, "advertising serial number is :" + ((int) ADVERTISE_SERIAL[0]));
        sharedPreferences.edit().putInt(LAST_SERIAL_NUM, ADVERTISE_SERIAL[0]).apply();
        return ADVERTISE_SERIAL;
    }

    private static byte[] decodePrefix(String hex, int length) {
        return Arrays.copyOfRange(HexCodec.decode(hex.toLowerCase()), 0, length);
    }

    private static byte[] toFixedUtf8NameBytes(String deviceName) {
        byte[] bytes = deviceName.getBytes(StandardCharsets.UTF_8);
        while (bytes.length > DEVICE_NAME_BYTES && !deviceName.isEmpty()) {
            deviceName = deviceName.substring(0, deviceName.length() - 1);
            bytes = BleDebugUtils.utf8Bytes(deviceName + "…");
        }
        byte[] deviceNameBytes = bytes.length < DEVICE_NAME_BYTES ? Arrays.copyOfRange(bytes, 0, DEVICE_NAME_BYTES) : bytes;
        EasyLogger.debug(TAG, "advertise device name bytes: " + BleDebugUtils.bytesToHexString(deviceNameBytes));
        return deviceNameBytes;
    }

    private static byte[] encodeProtocolVersion(String protocolVersion) {
        String[] versionParts = protocolVersion.split(VERSION_SEPARATOR_REGEX);
        if (versionParts.length < 2) {
            throw new IllegalArgumentException();
        }
        byte[] version = new byte[2];
        for (int i = 0; i < version.length; i++) {
            version[i] = (byte) Integer.parseInt(versionParts[i]);
        }
        return version;
    }
}
