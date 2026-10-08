package com.opencarlink.language;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Persists the explicit language selection used before Android 13.
 *
 * 持久化 Android 13 之前使用的显式语言选择。
 *
 * <p>Android 13 and newer use {@code LocaleManager} as the source of truth.
 * This preference store remains the fallback for API 19 through API 32.</p>
 *
 * <p>Android 13 及更高版本以 {@code LocaleManager} 为准；该偏好存储用于
 * API 19 到 API 32 的回退实现。</p>
 */
final class LanguagePreferences {

    private static final String PREFERENCES_NAME = "open_language_preferences";
    private static final String KEY_LANGUAGE_TAG = "selected_language_tag";
    private static final String KEY_MIGRATED_TO_LOCALE_MANAGER = "migrated_to_locale_manager";

    private final SharedPreferences sharedPreferences;

    /**
     * Creates the preference store for the application context.
     *
     * 为应用上下文创建偏好存储。
     *
     * @param context application context
     *                应用上下文
     */
    LanguagePreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Returns the persisted language tag.
     *
     * 返回已持久化的语言标签。
     *
     * @return persisted tag, or the system tag when no value exists
     *         已持久化标签；无值时返回系统标签
     */
    String getSelectedLanguageTag() {
        return sharedPreferences.getString(
                KEY_LANGUAGE_TAG,
                LanguageManager.SYSTEM_LANGUAGE_TAG
        );
    }

    /**
     * Persists the language tag asynchronously.
     *
     * 异步持久化语言标签。
     *
     * @param languageTag canonical language tag
     *                    规范语言标签
     */
    void setSelectedLanguageTag(String languageTag) {
        sharedPreferences.edit().putString(KEY_LANGUAGE_TAG, languageTag).apply();
    }

    /**
     * Checks whether the legacy selection has been migrated to LocaleManager.
     *
     * 检查旧版语言选择是否已迁移到 LocaleManager。
     *
     * @return {@code true} when migration has completed
     *         已完成迁移时返回 {@code true}
     */
    boolean isMigratedToLocaleManager() {
        return sharedPreferences.getBoolean(KEY_MIGRATED_TO_LOCALE_MANAGER, false);
    }

    /**
     * Records the LocaleManager migration state.
     *
     * 记录 LocaleManager 迁移状态。
     *
     * @param migrated whether migration has completed
     *                 迁移是否已完成
     */
    void setMigratedToLocaleManager(boolean migrated) {
        sharedPreferences.edit()
                .putBoolean(KEY_MIGRATED_TO_LOCALE_MANAGER, migrated)
                .apply();
    }
}
