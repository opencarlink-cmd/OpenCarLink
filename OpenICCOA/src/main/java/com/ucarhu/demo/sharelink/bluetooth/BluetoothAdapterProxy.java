package com.ucarhu.demo.sharelink.bluetooth;

@android.annotation.SuppressLint({"PrivateApi"})
public class BluetoothAdapterProxy {

    private static final java.lang.String f84a = "BluetoothAdapterProxy";

    public static java.lang.String f85b;

    public static int f86c;

    static {
        try {
            f85b = (java.lang.String) android.bluetooth.BluetoothAdapter.class.getField("ACTION_BLE_STATE_CHANGED").get(null);
        } catch (java.lang.IllegalAccessException | java.lang.NoSuchFieldException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f84a, "Exception trace:", e2);
        }
        try {
            f86c = ((java.lang.Integer) android.bluetooth.BluetoothAdapter.class.getField("STATE_BLE_ON").get(null)).intValue();
        } catch (java.lang.IllegalAccessException | java.lang.NoSuchFieldException e3) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f84a, "Exception trace:", e3);
        }
    }

    public static boolean disableBle(android.bluetooth.BluetoothAdapter bluetoothAdapter) {
        try {
            return ((java.lang.Boolean) android.bluetooth.BluetoothAdapter.class.getDeclaredMethod("disableBLE", new java.lang.Class[0]).invoke(bluetoothAdapter, new java.lang.Object[0])).booleanValue();
        } catch (java.lang.IllegalAccessException | java.lang.NoSuchMethodException | java.lang.reflect.InvocationTargetException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f84a, "disableBLE invoke failed.", e2);
            return false;
        }
    }

    public static boolean enableBle(android.bluetooth.BluetoothAdapter bluetoothAdapter) {
        try {
            return ((java.lang.Boolean) android.bluetooth.BluetoothAdapter.class.getDeclaredMethod("enableBLE", new java.lang.Class[0]).invoke(bluetoothAdapter, new java.lang.Object[0])).booleanValue();
        } catch (java.lang.IllegalAccessException | java.lang.NoSuchMethodException | java.lang.reflect.InvocationTargetException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f84a, "enableBLE invoke failed.", e2);
            return false;
        }
    }

    public static boolean isLeEnabled(android.bluetooth.BluetoothAdapter bluetoothAdapter) {
        try {
            return ((java.lang.Boolean) android.bluetooth.BluetoothAdapter.class.getDeclaredMethod("isLeEnabled", new java.lang.Class[0]).invoke(bluetoothAdapter, new java.lang.Object[0])).booleanValue();
        } catch (java.lang.IllegalAccessException | java.lang.NoSuchMethodException | java.lang.reflect.InvocationTargetException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f84a, "isLeEnabled invoke failed.", e2);
            return false;
        }
    }
}
