package com.ucarhu.demo.sharelink.bluetooth;

public class BleProtocolConstants {

    public static final int ROLE_CLIENT = 1;

    public static final int ROLE_SERVER = 2;

    private static final java.util.Map<java.lang.String, java.lang.String> CHARACTERISTIC_NAMES = new java.util.HashMap();

    private static final java.util.Map<java.lang.String, java.lang.String> SERVICE_NAMES = new java.util.HashMap();

    /**
     * BLE 交换的 JSON 字段名，字段值保持协议兼容，不能随意修改字符串内容。
     */
    public static class JsonFields {

        public static final java.lang.String DEVICE_ID = "id";

        public static final java.lang.String DEVICE_NAME = "name";

        public static final java.lang.String WIFI_BAND = "band";

        public static final java.lang.String PIN_CODE_OR_AUTHENTICATION = "pinCodeOrAuthentication";

        public static final java.lang.String MAC_ADDRESS = "mac";

        public static final java.lang.String DEVICE_MODEL = "model";

        public static final java.lang.String WIFI_SSID = "ssid";

        public static final java.lang.String WIFI_PASSWORD = "psk";

        public static final java.lang.String WIFI_FREQUENCY = "freq";

        public static final java.lang.String SERVER_PORT = "port";

        public static final java.lang.String CONNECTION_TYPE = "type";

        public static final java.lang.String AUTHENTICATION = "authentication";
    }

    /**
     * ShareLink BLE 服务与特征 UUID。
     */
    public static class Uuids {

        public static final android.os.ParcelUuid CLIENT_CHARACTERISTIC_CONFIG = android.os.ParcelUuid.fromString("00002902-0000-1000-8000-00805F9B34FB");

        public static final android.os.ParcelUuid ADVERTISE_SERVICE = android.os.ParcelUuid.fromString("0000FCFB-0000-1000-8000-00805F9B34FB");

        public static final android.os.ParcelUuid ADVERTISE_DEVICE_ID = android.os.ParcelUuid.fromString("00000001-0000-1000-8000-00805F9B34FB");

        public static final android.os.ParcelUuid ADVERTISE_DEVICE_NAME = android.os.ParcelUuid.fromString("00000002-0000-1000-8000-00805F9B34FB");

        public static final android.os.ParcelUuid ADVERTISE_RESERVED = android.os.ParcelUuid.fromString("00000003-0000-1000-8000-00805F9B34FB");

        public static final android.os.ParcelUuid SHARELINK_SERVICE = android.os.ParcelUuid.fromString("2abcc850-9935-4f8a-ba84-123456789100");

        public static final android.os.ParcelUuid CLIENT_INFO_CHARACTERISTIC = android.os.ParcelUuid.fromString("2abcc850-9935-4f8a-ba84-123456789101");

        public static final android.os.ParcelUuid SERVER_CONNECTION_INFO_CHARACTERISTIC = android.os.ParcelUuid.fromString("2abcc850-9935-4f8a-ba84-123456789102");
    }

    static {
        SERVICE_NAMES.put("00001801-0000-1000-8000-00805f9b34fb", "Generic Attribute");
        SERVICE_NAMES.put("00001800-0000-1000-8000-00805f9b34fb", "Generic Access");
        CHARACTERISTIC_NAMES.put("00002a05-0000-1000-8000-00805f9b34fb", "Service Changed");
        CHARACTERISTIC_NAMES.put("00002a00-0000-1000-8000-00805f9b34fb", "Device Name");
        CHARACTERISTIC_NAMES.put("00002a01-0000-1000-8000-00805f9b34fb", "Appearance");
        CHARACTERISTIC_NAMES.put("00002aa6-0000-1000-8000-00805f9b34fb", "Central Address Resolution");
        SERVICE_NAMES.put(com.ucarhu.demo.sharelink.bluetooth.BleProtocolConstants.Uuids.SHARELINK_SERVICE.toString(), "UnionShare");
        CHARACTERISTIC_NAMES.put(com.ucarhu.demo.sharelink.bluetooth.BleProtocolConstants.Uuids.CLIENT_INFO_CHARACTERISTIC.toString(), "Client info");
        CHARACTERISTIC_NAMES.put(com.ucarhu.demo.sharelink.bluetooth.BleProtocolConstants.Uuids.SERVER_CONNECTION_INFO_CHARACTERISTIC.toString(), "Server connection info");
    }

    public static java.lang.String getCharacteristicName(java.lang.String str) {
        java.lang.String name = CHARACTERISTIC_NAMES.get(str);
        return name != null ? name : str;
    }

    public static java.lang.String getServiceName(java.lang.String str) {
        java.lang.String name = SERVICE_NAMES.get(str);
        return name != null ? name : str;
    }
}
