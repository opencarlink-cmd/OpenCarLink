package com.opencarlink.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.opencarlink.language.LanguageManager;

/**
 * Entry point for the OpenCarLink head-unit application.
 *
 * OpenCarLink 车机应用入口。
 *
 * <p>The initial implementation deliberately contains no protocol logic. It only
 * establishes a stable Android application shell so protocol modules can be
 * integrated incrementally without changing the application identity.</p>
 *
 * <p>初始实现刻意不包含协议逻辑，只建立稳定的 Android 应用外壳，使后续协议模块
 * 可以逐步接入，而无需修改应用身份配置。</p>
 */
public final class MainActivity extends Activity {

    /**
     * Applies the persisted application locale before Activity resources are created.
     *
     * 在 Activity 资源创建前应用已持久化的应用 locale。
     *
     * <p>On Android 13 and newer the platform LocaleManager owns the application
     * locale. On older versions OpenLanguage wraps the base context so resource
     * lookup follows the persisted selection.</p>
     *
     * <p>在 Android 13 及更高版本上，应用 locale 由平台 LocaleManager 管理；
     * 在更旧版本上，OpenLanguage 包装基础上下文，使资源查找遵循已持久化选择。</p>
     *
     * @param newBase base context supplied by the Android framework
     *                Android 框架提供的基础上下文
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageManager.getInstance(newBase).wrap(newBase));
    }

    /**
     * Creates the initial application screen.
     *
     * 创建应用初始界面。
     *
     * @param savedInstanceState previous activity state, or {@code null} on first creation.
     *                           上一次 Activity 状态；首次创建时为 {@code null}。
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind the minimal layout owned by the application shell.
        // 绑定应用外壳所拥有的最小布局。
        setContentView(R.layout.activity_main);
    }
}
