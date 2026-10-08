package com.ucarhu.demo;

import android.util.Log;
import androidx.multidex.MultiDexApplication;

/**
 * ICCOA application entry point.
 *
 * ICCOA 应用入口。
 *
 * <p>The host application installs this class through manifest merging. Extending
 * MultiDexApplication keeps the migrated ICCOA library loadable on Android 4.4.</p>
 *
 * <p>宿主应用通过 Manifest 合并安装该类。继承 MultiDexApplication 可确保迁移后的
 * ICCOA Library 在 Android 4.4 上正常加载。</p>
 */
public class CarLinkApplication extends MultiDexApplication {

    private static final String TAG = "ICCOA CarLink";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Start OCarHu, app version:v1.2.10");
    }
}
