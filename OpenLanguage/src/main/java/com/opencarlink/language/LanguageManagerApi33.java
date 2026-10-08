package com.opencarlink.language;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.LocaleManager;
import android.content.Context;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

/**
 * Android 13 and newer locale integration.
 *
 * Android 13 及更高版本的 locale 集成。
 *
 * <p>This class is isolated so classes introduced after API 19 are never loaded
 * on older devices.</p>
 *
 * <p>该类被隔离，确保 API 19 之前的设备不会加载 API 19 之后才引入的类。</p>
 */
@SuppressLint("UseRequiresApi")
@TargetApi(Build.VERSION_CODES.TIRAMISU)
final class LanguageManagerApi33 {

    private LanguageManagerApi33() {
        // Utility class.
        // 工具类，禁止实例化。
    }

    /**
     * Checks whether the platform LocaleManager service is available.
     *
     * 检查平台 LocaleManager 服务是否可用。
     *
     * @param context application context
     *                应用上下文
     * @return {@code true} when the platform service exists
     *         平台服务存在时返回 {@code true}
     */
    static boolean isAvailable(Context context) {
        return context.getSystemService(LocaleManager.class) != null;
    }

    /**
     * Reads the per-application locale from the platform.
     *
     * 从平台读取应用级 locale。
     *
     * @param context application context
     *                应用上下文
     * @return selected language tag, or the system tag when the platform list is empty
     *         已选择语言标签；平台列表为空时返回系统标签
     */
    static String getSelectedLanguageTag(Context context) {
        try {
            LocaleManager localeManager = context.getSystemService(LocaleManager.class);
            if (localeManager == null) {
                return LanguageManager.SYSTEM_LANGUAGE_TAG;
            }

            LocaleList localeList = localeManager.getApplicationLocales();
            if (localeList == null || localeList.isEmpty()) {
                return LanguageManager.SYSTEM_LANGUAGE_TAG;
            }

            Locale locale = localeList.get(0);
            if (locale == null) {
                return LanguageManager.SYSTEM_LANGUAGE_TAG;
            }
            return locale.toLanguageTag();
        } catch (RuntimeException exception) {
            return LanguageManager.SYSTEM_LANGUAGE_TAG;
        }
    }

    /**
     * Writes the per-application locale to the platform.
     *
     * 将应用级 locale 写入平台。
     *
     * @param context application context
     *                应用上下文
     * @param languageTag canonical language tag, or the system tag to clear the override
     *                    规范语言标签；传入系统标签可清除应用级覆盖
     */
    static boolean setSelectedLanguageTag(Context context, String languageTag) {
        try {
            LocaleManager localeManager = context.getSystemService(LocaleManager.class);
            if (localeManager == null) {
                return false;
            }

            LocaleList localeList;
            if (LanguageManager.SYSTEM_LANGUAGE_TAG.equals(languageTag)) {
                localeList = LocaleList.getEmptyLocaleList();
            } else {
                localeList = new LocaleList(LanguageTagParser.parse(languageTag));
            }
            localeManager.setApplicationLocales(localeList);
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }
}
