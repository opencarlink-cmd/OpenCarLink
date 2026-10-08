package com.ucarhu.demo.sharelink.bluetooth;

public class CarBluetoothMacRecord {

    private static final java.lang.String f91c = "CarBluetoothMacRecord";

    private static final java.lang.String f92d = "car_bluetooth_config";

    private java.lang.String f93a;

    private android.content.SharedPreferences f94b;

    public CarBluetoothMacRecord(java.lang.String str, android.content.Context context) {
        this.f93a = str;
        this.f94b = context.getSharedPreferences(f92d, 0);
    }

    public static boolean containsMac(java.lang.String str, android.content.Context context) {
        if (android.text.TextUtils.isEmpty(str)) {
            com.ucarhu.demo.logging.EasyLogger.info(f91c, "mac empty");
            return false;
        }
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(f92d, 0);
        if (sharedPreferences == null) {
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f91c, "mac contains");
        return sharedPreferences.contains(com.ucarhu.demo.sharelink.util.Sha256Utils.sha256Hex(str));
    }

    public void save() {
        android.content.SharedPreferences sharedPreferences = this.f94b;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(this.f93a, java.lang.System.currentTimeMillis()).apply();
            com.ucarhu.demo.logging.EasyLogger.info(f91c, "sav successfully");
        }
    }

    public void delete() {
        android.content.SharedPreferences sharedPreferences = this.f94b;
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(this.f93a).apply();
            com.ucarhu.demo.logging.EasyLogger.info(f91c, "delete successfully");
        }
    }
}
