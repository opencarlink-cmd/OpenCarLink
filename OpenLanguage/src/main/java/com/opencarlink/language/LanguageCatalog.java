package com.opencarlink.language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Central catalog of languages supported by OpenCarLink.
 *
 * OpenCarLink 支持语言的中央目录。
 *
 * <p>The catalog owns language identity and lookup rules only. It does not own
 * translated UI strings, theme resources, or protocol-specific formatting.</p>
 *
 * <p>该目录只负责语言身份与查找规则，不负责翻译文案、主题资源或协议专用格式化。</p>
 */
public final class LanguageCatalog {

    private static final String TAG_ENGLISH = "en";
    private static final String TAG_CHINESE_SIMPLIFIED = "zh-CN";
    private static final String TAG_CHINESE_TRADITIONAL = "zh-TW";
    private static final String TAG_CHINESE_HONG_KONG = "zh-HK";
    private static final String TAG_PORTUGUESE_BRAZIL = "pt-BR";
    private static final String TAG_INDONESIAN = "id";

    private static final LanguageCatalog INSTANCE = new LanguageCatalog();

    private final List<LanguageOption> languageOptions;

    private LanguageCatalog() {
        List<LanguageOption> options = new ArrayList<>();
        options.add(new LanguageOption(LanguageManager.SYSTEM_LANGUAGE_TAG, null, true));
        options.add(createOption(TAG_ENGLISH));
        options.add(createOption(TAG_CHINESE_SIMPLIFIED));
        options.add(createOption(TAG_CHINESE_TRADITIONAL));
        options.add(createOption(TAG_CHINESE_HONG_KONG));
        options.add(createOption("ja"));
        options.add(createOption("ko"));
        options.add(createOption("es"));
        options.add(createOption(TAG_PORTUGUESE_BRAZIL));
        options.add(createOption("fr"));
        options.add(createOption("de"));
        options.add(createOption("it"));
        options.add(createOption("ru"));
        options.add(createOption("ar"));
        options.add(createOption("tr"));
        options.add(createOption("hi"));
        options.add(createOption(TAG_INDONESIAN));
        options.add(createOption("th"));
        options.add(createOption("vi"));
        languageOptions = Collections.unmodifiableList(options);
    }

    /**
     * Returns the process-wide catalog singleton.
     *
     * 返回进程级目录单例。
     *
     * @return catalog singleton
     *         目录单例
     */
    public static LanguageCatalog getInstance() {
        return INSTANCE;
    }

    /**
     * Returns all selectable options, including the system option.
     *
     * 返回所有可选选项，包含系统选项。
     *
     * @return immutable option list
     *         不可变选项列表
     */
    public List<LanguageOption> getLanguageOptions() {
        return languageOptions;
    }

    /**
     * Finds one option by canonical tag or compatible locale representation.
     *
     * 按规范标签或兼容 locale 表示查找一个选项。
     *
     * @param languageTag language tag to find
     *                    待查找语言标签
     * @return matching option, or {@code null}
     *         匹配的选项，或 {@code null}
     */
    public LanguageOption findByTag(String languageTag) {
        if (languageTag == null) {
            return null;
        }

        String normalizedTag = normalize(languageTag);
        if (LanguageManager.SYSTEM_LANGUAGE_TAG.equalsIgnoreCase(normalizedTag)) {
            return languageOptions.get(0);
        }
        if (normalizedTag.isEmpty()) {
            return null;
        }

        String script = LanguageTagParser.extractScript(normalizedTag);
        if ("Hans".equals(script)) {
            return findByTag(TAG_CHINESE_SIMPLIFIED);
        }
        if ("Hant".equals(script)) {
            String region = LanguageTagParser.extractRegion(normalizedTag);
            if ("HK".equals(region) || "MO".equals(region)) {
                return findByTag(TAG_CHINESE_HONG_KONG);
            }
            return findByTag(TAG_CHINESE_TRADITIONAL);
        }

        for (LanguageOption option : languageOptions) {
            if (!option.isSystem() && option.getLanguageTag().equalsIgnoreCase(normalizedTag)) {
                return option;
            }
        }

        Locale locale = LanguageTagParser.parse(normalizedTag);
        return findByLocale(locale);
    }

    /**
     * Checks whether the catalog contains a matching option.
     *
     * 检查目录是否包含匹配选项。
     *
     * @param languageTag language tag to check
     *                    待检查语言标签
     * @return {@code true} when the tag resolves to a catalog option
     *         标签可解析为目录选项时返回 {@code true}
     */
    public boolean contains(String languageTag) {
        return findByTag(languageTag) != null;
    }

    /**
     * Normalizes a tag to the catalog form when possible.
     *
     * 尽可能将标签规范化为目录形式。
     *
     * @param languageTag language tag to normalize
     *                    待规范化语言标签
     * @return canonical tag, system tag, or the original non-empty tag
     *         规范标签、系统标签或原始非空标签
     */
    public String canonicalizeTag(String languageTag) {
        if (languageTag == null) {
            return LanguageManager.SYSTEM_LANGUAGE_TAG;
        }

        String normalizedTag = normalize(languageTag);
        if (normalizedTag.isEmpty()) {
            return LanguageManager.SYSTEM_LANGUAGE_TAG;
        }
        if (LanguageManager.SYSTEM_LANGUAGE_TAG.equalsIgnoreCase(normalizedTag)) {
            return LanguageManager.SYSTEM_LANGUAGE_TAG;
        }

        LanguageOption option = findByTag(normalizedTag);
        return option == null ? normalizedTag : option.getLanguageTag();
    }

    /**
     * Finds an option by language and region, ignoring script where required.
     *
     * 按语言与地区查找选项，并在必要时忽略 script。
     *
     * @param locale parsed locale
     *               已解析 locale
     * @return matching option, or {@code null}
     *         匹配的选项，或 {@code null}
     */
    private LanguageOption findByLocale(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        LanguageOption languageOnlyMatch = null;

        for (LanguageOption option : languageOptions) {
            if (option.isSystem()) {
                continue;
            }

            Locale optionLocale = option.getLocale();
            if (!language.equals(optionLocale.getLanguage())) {
                continue;
            }
            if (!country.isEmpty() && country.equals(optionLocale.getCountry())) {
                return option;
            }
            if (optionLocale.getCountry().isEmpty()) {
                languageOnlyMatch = option;
            }
        }

        if (languageOnlyMatch != null) {
            return languageOnlyMatch;
        }

        // Android may expose legacy or script-based tags for supported languages.
        // Android 可能为受支持语言返回旧式或带 script 的标签，因此显式映射到目录选项。
        if ("zh".equals(language)) {
            if ("HK".equals(country) || "MO".equals(country)) {
                return findByTag(TAG_CHINESE_HONG_KONG);
            }
            if ("CN".equals(country) || "SG".equals(country) || "MY".equals(country)) {
                return findByTag(TAG_CHINESE_SIMPLIFIED);
            }
            if (!country.isEmpty() && !"TW".equals(country)) {
                return null;
            }
            return findByTag(TAG_CHINESE_TRADITIONAL);
        }
        if ("id".equals(language) || "in".equals(language)) {
            return findByTag(TAG_INDONESIAN);
        }
        return null;
    }

    /**
     * Creates an explicit language option from a canonical tag.
     *
     * 根据规范标签创建显式语言选项。
     *
     * @param languageTag canonical language tag
     *                    规范语言标签
     * @return explicit language option
     *         显式语言选项
     */
    private static LanguageOption createOption(String languageTag) {
        return new LanguageOption(languageTag, LanguageTagParser.parse(languageTag), false);
    }

    /**
     * Normalizes separators and trims surrounding whitespace.
     *
     * 规范化分隔符并去除首尾空白。
     *
     * @param languageTag language tag to normalize
     *                    待规范化语言标签
     * @return normalized tag
     *         规范化后的标签
     */
    private static String normalize(String languageTag) {
        return languageTag.trim().replace('_', '-');
    }
}
