package com.opencarlink.language;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;

import java.util.ArrayList;
import java.util.Locale;

/**
 * API 24 through API 32 locale-aware context wrapping.
 *
 * API 24 到 API 32 的 locale 感知上下文包装。
 *
 * <p>This class is isolated so {@code LocaleList} and {@code setLocales} are
 * never loaded on API 19 through API 23.</p>
 *
 * <p>该类被隔离，确保 API 19 到 API 23 不会加载 {@code LocaleList} 与
 * {@code setLocales}。</p>
 */
@SuppressLint("UseRequiresApi")
@TargetApi(Build.VERSION_CODES.N)
final class LanguageContextWrapperApi24 {

    private LanguageContextWrapperApi24() {
        // Utility class.
        // 工具类，禁止实例化。
    }

    /**
     * Wraps a context with the selected locale first and existing locales after it.
     *
     * 使用选中 locale 优先、现有 locale 依次跟随的顺序包装上下文。
     *
     * @param context context to wrap
     *                待包装上下文
     * @param locale selected application locale
     *               已选择的应用 locale
     * @return locale-aware context
     *         感知 locale 的上下文
     */
    static Context wrap(Context context, Locale locale) {
        Configuration configuration = new Configuration(
                context.getResources().getConfiguration()
        );

        LocaleList existingLocales = configuration.getLocales();
        ArrayList<Locale> localeChain = new ArrayList<>();
        localeChain.add(locale);
        if (existingLocales != null) {
            for (int index = 0; index < existingLocales.size(); index++) {
                Locale existingLocale = existingLocales.get(index);
                if (existingLocale != null && !locale.equals(existingLocale)) {
                    localeChain.add(existingLocale);
                }
            }
        }

        configuration.setLocales(new LocaleList(localeChain.toArray(new Locale[0])));
        return context.createConfigurationContext(configuration);
    }
}
