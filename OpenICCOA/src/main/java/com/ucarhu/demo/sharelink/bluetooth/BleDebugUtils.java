package com.ucarhu.demo.sharelink.bluetooth;

public class BleDebugUtils {

    private static final java.lang.String f83a = "BleAssist";

    public static java.lang.String bytesToHexString(byte[] bArr) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        if (bArr != null) {
            for (byte b2 : bArr) {
                sb.append(java.lang.String.format("%02X ", java.lang.Byte.valueOf(b2)));
            }
        }
        return sb.toString();
    }

    public static void logGattConnection(android.bluetooth.BluetoothGatt bluetoothGatt, java.lang.String str) {
        android.bluetooth.BluetoothDevice device;
        if (bluetoothGatt == null || (device = bluetoothGatt.getDevice()) == null) {
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + ": Connected to GATT server. address : " + device.getAddress());
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + ": Connected to GATT server. name : " + device.getName());
    }

    public static void logCharacteristic(android.bluetooth.BluetoothGattCharacteristic bluetoothGattCharacteristic, java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + " BluetoothGattCharacteristic begin");
        if (bluetoothGattCharacteristic != null) {
            com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + ": uuid " + com.ucarhu.demo.sharelink.bluetooth.BleProtocolConstants.getCharacteristicName(bluetoothGattCharacteristic.getUuid().toString()));
            com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + ": properties " + bluetoothGattCharacteristic.getProperties());
            com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + ": permissions " + bluetoothGattCharacteristic.getPermissions());
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value != null && value.length > 0) {
                java.lang.StringBuilder sb = new java.lang.StringBuilder(value.length);
                for (byte b2 : value) {
                    sb.append(java.lang.String.format("%02X ", java.lang.Byte.valueOf(b2)));
                }
                try {
                    com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + ": value " + new java.lang.String(value, com.ucarhu.demo.vehicle.sensor.SensorByteUtils.f799a) + "\n" + sb.toString());
                } catch (java.io.UnsupportedEncodingException e2) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f83a, "printCharacteristic UnsupportedEncodingException", e2);
                }
            }
            com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + ": descriptors size " + bluetoothGattCharacteristic.getDescriptors().size());
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + " BluetoothGattCharacteristic end");
    }

    public static void logGattService(android.bluetooth.BluetoothGattService bluetoothGattService, java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + " BluetoothGattService begin");
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + " Service uuid " + com.ucarhu.demo.sharelink.bluetooth.BleProtocolConstants.getServiceName(bluetoothGattService.getUuid().toString()));
        java.util.List<android.bluetooth.BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
        if (characteristics != null && characteristics.size() > 0) {
            java.util.Iterator<android.bluetooth.BluetoothGattCharacteristic> it = characteristics.iterator();
            while (it.hasNext()) {
                logCharacteristic(it.next(), str);
            }
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + " BluetoothGattService end");
    }

    public static void logGattServices(java.util.List<android.bluetooth.BluetoothGattService> list, java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + " BluetoothGatt begin");
        if (list != null && list.size() > 0) {
            java.util.Iterator<android.bluetooth.BluetoothGattService> it = list.iterator();
            while (it.hasNext()) {
                logGattService(it.next(), str);
            }
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f83a, str + " BluetoothGatt end");
    }

    public static byte[] utf8Bytes(java.lang.String str) {
        return str == null ? new byte[0] : str.getBytes(java.nio.charset.Charset.forName(com.ucarhu.demo.vehicle.sensor.SensorByteUtils.f799a));
    }

    public static byte[] fixedLengthUtf8Bytes(java.lang.String str, int i) {
        byte[] bytes;
        while (true) {
            bytes = str.getBytes(java.nio.charset.Charset.forName(com.ucarhu.demo.vehicle.sensor.SensorByteUtils.f799a));
            if (bytes.length <= i) {
                break;
            }
            str = str.substring(0, str.length() - 1);
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 < bytes.length) {
                bArr[i2] = bytes[i2];
            } else {
                bArr[i2] = 0;
            }
        }
        return bArr;
    }

    public static java.lang.String utf8String(byte[] bArr) {
        if (bArr != null) {
            return new java.lang.String(bArr, java.nio.charset.Charset.forName(com.ucarhu.demo.vehicle.sensor.SensorByteUtils.f799a));
        }
        com.ucarhu.demo.logging.EasyLogger.warn(f83a, "Can't convert a null byte[] to string.");
        return "";
    }

    public static byte[] trimTrailingZeroBytes(byte[] bArr) {
        int length = bArr.length - 1;
        while (length >= 0 && bArr[length] == 0) {
            length--;
        }
        int i = length + 1;
        byte[] bArr2 = new byte[i];
        java.lang.System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }
}
