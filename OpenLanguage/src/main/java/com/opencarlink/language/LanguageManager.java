package com.opencarlink.language;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Public entry point for OpenCarLink language selection.
 *
 * OpenCarLink 语言选择的公开入口。
 *
 * <p>The manager owns selection, validation, persistence, application, and
 * change notification. It does not own translated UI copy or theme state.</p>
 *
 * <p>该管理器负责选择、校验、持久化、应用与变更通知，不负责翻译文案或主题状态。</p>
 *
 * <p>This implementation assumes a single application process and deliberately
 * does not change {@code Locale.getDefault()}. Formatting code must pass the
 * selected locale explicitly.</p>
 *
 * <p>该实现假设应用为单进程，并刻意不修改 {@code Locale.getDefault()}。
 * 格式化代码必须显式传入所选 locale。</p>
 */
public final class LanguageManager {

    /**
     * Language tag that represents the Android system locale.
     *
     * 表示 Android 系统 locale 的语言标签。
     */
    public static final String SYSTEM_LANGUAGE_TAG = "system";

    private static final String LOG_TAG = "OpenLanguage";

    private static volatile LanguageManager instance;

    private final Context applicationContext;
    private final LanguageCatalog languageCatalog;
    private final LanguagePreferences languagePreferences;
    private final Object selectionLock = new Object();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final CopyOnWriteArrayList<OnLanguageChangedListener> listeners =
            new CopyOnWriteArrayList<>();

    /**
     * Returns the process-wide manager singleton.
     *
     * 返回进程级管理器单例。
     *
     * @param context any context owned by the application
     *                应用持有的任意上下文
     * @return manager singleton
     *         管理器单例
     */
    public static LanguageManager getInstance(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }

        if (instance == null) {
            synchronized (LanguageManager.class) {
                if (instance == null) {
                    Context applicationContext = context.getApplicationContext();
                    Context safeContext = applicationContext == null ? context : applicationContext;
                    instance = new LanguageManager(safeContext);
                }
            }
        }
        return instance;
    }

    private LanguageManager(Context applicationContext) {
        this.applicationContext = applicationContext;
        languageCatalog = LanguageCatalog.getInstance();
        languagePreferences = new LanguagePreferences(applicationContext);
    }

    /**
     * Returns all selectable language options.
     *
     * 返回所有可选语言选项。
     *
     * @return immutable option list
     *         不可变选项列表
     */
    public List<LanguageOption> getSupportedLanguages() {
        return languageCatalog.getLanguageOptions();
    }

    /**
     * Returns the currently selected canonical language tag.
     *
     * 返回当前已选择的规范语言标签。
     *
     * @return canonical language tag, or {@link #SYSTEM_LANGUAGE_TAG}
     *         规范语言标签，或 {@link #SYSTEM_LANGUAGE_TAG}
     */
    public String getSelectedLanguageTag() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && LanguageManagerApi33.isAvailable(applicationContext)) {
            String platformTag = LanguageManagerApi33.getSelectedLanguageTag(applicationContext);
            if (!SYSTEM_LANGUAGE_TAG.equals(platformTag)) {
                languagePreferences.setMigratedToLocaleManager(true);
                LanguageOption option = languageCatalog.findByTag(platformTag);
                return option == null ? platformTag : option.getLanguageTag();
            }

            if (!languagePreferences.isMigratedToLocaleManager()) {
                LanguageOption legacyOption = languageCatalog.findByTag(
                        languagePreferences.getSelectedLanguageTag()
                );
                if (legacyOption != null && !legacyOption.isSystem()) {
                    return legacyOption.getLanguageTag();
                }
                languagePreferences.setMigratedToLocaleManager(true);
            }
            return SYSTEM_LANGUAGE_TAG;
        }

        LanguageOption option = languageCatalog.findByTag(
                languagePreferences.getSelectedLanguageTag()
        );
        return option == null ? SYSTEM_LANGUAGE_TAG : option.getLanguageTag();
    }

    /**
     * Returns the currently selected language option.
     *
     * 返回当前已选择的语言选项。
     *
     * @return selected option, falling back to the system option
     *         已选择选项；无法解析时回退到系统选项
     */
    public LanguageOption getSelectedLanguage() {
        LanguageOption option = languageCatalog.findByTag(getSelectedLanguageTag());
        if (option != null) {
            return option;
        }
        return languageCatalog.findByTag(SYSTEM_LANGUAGE_TAG);
    }

    /**
     * Selects a language and applies it through the correct platform path.
     *
     * 选择语言，并通过正确的平台路径应用。
     *
     * @param languageTag canonical tag or {@link #SYSTEM_LANGUAGE_TAG}
     *                    规范标签或 {@link #SYSTEM_LANGUAGE_TAG}
     * @return {@code true} when the tag is supported and the request is accepted
     *         标签受支持且请求被接受时返回 {@code true}
     *
     * <p>On API 19 through API 32, the caller must recreate the visible Activity
     * after this method returns {@code true}. On API 33 and newer, the platform
     * may recreate the Activity through LocaleManager.</p>
     *
     * <p>在 API 19 到 API 32 上，该方法返回 {@code true} 后，调用方必须重建可见的
     * Activity；在 API 33 及更高版本上，平台可能通过 LocaleManager 自动重建
     * Activity。</p>
     */
    public boolean setSelectedLanguage(String languageTag) {
        if (languageTag == null) {
            return false;
        }

        String normalizedInput = languageTag.trim();
        if (normalizedInput.isEmpty()) {
            return false;
        }

        String canonicalTag;
        if (SYSTEM_LANGUAGE_TAG.equalsIgnoreCase(normalizedInput)) {
            canonicalTag = SYSTEM_LANGUAGE_TAG;
        } else {
            LanguageOption option = languageCatalog.findByTag(normalizedInput);
            if (option == null || option.isSystem()) {
                return false;
            }
            canonicalTag = option.getLanguageTag();
        }

        synchronized (selectionLock) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                    && LanguageManagerApi33.isAvailable(applicationContext)) {
                boolean applied = LanguageManagerApi33.setSelectedLanguageTag(
                        applicationContext,
                        canonicalTag
                );
                if (applied) {
                    languagePreferences.setMigratedToLocaleManager(true);
                    notifyLanguageChanged(canonicalTag);
                    return true;
                }
            }

            languagePreferences.setSelectedLanguageTag(canonicalTag);
            notifyLanguageChanged(canonicalTag);
            return true;
        }
    }

    /**
     * Checks whether the current selection follows the system locale.
     *
     * 检查当前选择是否跟随系统 locale。
     *
     * @return {@code true} when the system option is selected
     *         当前选择为系统选项时返回 {@code true}
     */
    public boolean isSystemLanguage() {
        return SYSTEM_LANGUAGE_TAG.equals(getSelectedLanguageTag());
    }

    /**
     * Wraps a context with the persisted locale on API 19 through API 32.
     *
     * 在 API 19 到 API 32 上使用已持久化 locale 包装上下文。
     *
     * <p>Android 13 and newer already apply the per-application locale through
     * {@code LocaleManager}, so this method returns the original context there.</p>
     *
     * <p>Android 13 及更高版本已通过 {@code LocaleManager} 应用应用级 locale，
     * 因此该方法在这些版本上返回原始上下文。</p>
     *
     * @param context context to wrap
     *                待包装上下文
     * @return locale-aware context, or the original context
     *         感知 locale 的上下文，或原始上下文
     */
    public Context wrap(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        Locale locale = resolveLocaleForContext();
        if (locale == null) {
            return context;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return LanguageContextWrapperApi24.wrap(context, locale);
        }

        Configuration configuration = new Configuration(
                context.getResources().getConfiguration()
        );
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    /**
     * Resolves the locale that must be applied manually to a context.
     *
     * 解析必须手动应用到上下文的 locale。
     *
     * @return locale to apply, or {@code null} when the platform already applies it
     *         待应用 locale；平台已应用时返回 {@code null}
     */
    private Locale resolveLocaleForContext() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && LanguageManagerApi33.isAvailable(applicationContext)) {
            String platformTag = LanguageManagerApi33.getSelectedLanguageTag(applicationContext);
            if (!SYSTEM_LANGUAGE_TAG.equals(platformTag)) {
                return null;
            }
            if (languagePreferences.isMigratedToLocaleManager()) {
                return null;
            }
        }

        LanguageOption option = languageCatalog.findByTag(
                languagePreferences.getSelectedLanguageTag()
        );
        if (option == null || option.isSystem()) {
            return null;
        }
        return option.getLocale();
    }

    /**
     * Adds a language change listener.
     *
     * 添加语言变更监听器。
     *
     * @param listener listener to add
     *                 待添加监听器
     */
    public void addOnLanguageChangedListener(OnLanguageChangedListener listener) {
        if (listener != null) {
            listeners.addIfAbsent(listener);
        }
    }

    /**
     * Removes a language change listener.
     *
     * 移除语言变更监听器。
     *
     * @param listener listener to remove
     *                 待移除监听器
     */
    public void removeOnLanguageChangedListener(OnLanguageChangedListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    /**
     * Notifies listeners after a successful selection.
     *
     * 在成功选择后通知监听器。
     *
     * @param languageTag canonical language tag
     *                    规范语言标签
     */
    private void notifyLanguageChanged(String languageTag) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            dispatchLanguageChanged(languageTag);
            return;
        }

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                dispatchLanguageChanged(languageTag);
            }
        });
    }

    /**
     * Dispatches a language change on the main thread.
     *
     * 在主线程分发语言变更事件。
     *
     * @param languageTag canonical language tag
     *                    规范语言标签
     */
    private void dispatchLanguageChanged(String languageTag) {
        for (OnLanguageChangedListener listener : listeners) {
            try {
                listener.onLanguageChanged(languageTag);
            } catch (RuntimeException exception) {
                Log.e(LOG_TAG, "Language change listener failed", exception);
            }
        }
    }

    /**
     * Receives successful language changes.
     *
     * 接收成功的语言变更事件。
     */
    public interface OnLanguageChangedListener {

        /**
         * Called after the language selection changes.
         *
         * 语言选择变更后调用。
         *
         * @param languageTag canonical language tag
         *                    规范语言标签
         */
        void onLanguageChanged(String languageTag);
    }
}
