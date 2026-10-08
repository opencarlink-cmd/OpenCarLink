package com.opencarlink.language;

import java.util.Locale;

/**
 * Parses the subset of BCP 47 tags used by OpenCarLink without requiring API 21.
 *
 * 在不依赖 API 21 的前提下，解析 OpenCarLink 使用的 BCP 47 标签子集。
 *
 * <p>Android API 19 does not expose {@code Locale.forLanguageTag} or
 * {@code Locale.Builder}, so this parser only extracts language, optional script,
 * and optional region. Script data is intentionally ignored for locale selection
 * because the supported catalog uses language and region tags.</p>
 *
 * <p>Android API 19 不提供 {@code Locale.forLanguageTag} 与 {@code Locale.Builder}，
 * 因此该解析器只提取语言、可选 script 和可选地区。由于支持目录使用语言加地区标签，
 * locale 选择阶段会刻意忽略 script 数据。</p>
 */
final class LanguageTagParser {

    private LanguageTagParser() {
        // Utility class.
        // 工具类，禁止实例化。
    }

    /**
     * Parses a language tag into a locale compatible with API 19.
     *
     * 将语言标签解析为兼容 API 19 的 locale。
     *
     * @param languageTag language tag such as {@code en}, {@code zh-CN}, or {@code pt-BR}
     *                    诸如 {@code en}、{@code zh-CN} 或 {@code pt-BR} 的语言标签
     * @return parsed locale, or the default locale for invalid input
     *         解析后的 locale；输入无效时返回默认 locale
     */
    @SuppressWarnings("deprecation")
    static Locale parse(String languageTag) {
        String[] parts = splitTag(languageTag);
        if (parts.length == 0 || parts[0].isEmpty()) {
            return Locale.getDefault();
        }

        String language = parts[0].toLowerCase(Locale.US);
        String country = "";
        if (parts.length > 1 && isRegionSubtag(parts[1])) {
            country = parts[1].toUpperCase(Locale.US);
        } else if (parts.length > 2 && isRegionSubtag(parts[2])) {
            country = parts[2].toUpperCase(Locale.US);
        }

        if (country.isEmpty()) {
            return new Locale(language);
        }
        return new Locale(language, country);
    }

    /**
     * Extracts a supported script subtag from a language tag.
     *
     * 从语言标签中提取受支持的 script 子标签。
     *
     * <p>Only {@code Hans} and {@code Hant} are returned because they are the
     * script values that affect OpenCarLink Chinese resource selection.</p>
     *
     * <p>仅返回 {@code Hans} 与 {@code Hant}，因为这两个 script 值会影响
     * OpenCarLink 的中文资源选择。</p>
     *
     * @param languageTag language tag to inspect
     *                    待检查语言标签
     * @return {@code Hans}, {@code Hant}, or an empty string
     *         {@code Hans}、{@code Hant} 或空字符串
     */
    static String extractScript(String languageTag) {
        String[] parts = splitTag(languageTag);
        if (parts.length > 1 && isScriptSubtag(parts[1])) {
            String script = parts[1].toLowerCase(Locale.US);
            if ("hans".equals(script)) {
                return "Hans";
            }
            if ("hant".equals(script)) {
                return "Hant";
            }
        }
        return "";
    }

    /**
     * Extracts a region subtag from a language tag.
     *
     * 从语言标签中提取地区子标签。
     *
     * @param languageTag language tag to inspect
     *                    待检查语言标签
     * @return uppercase region subtag, or an empty string
     *         大写地区子标签，或空字符串
     */
    static String extractRegion(String languageTag) {
        String[] parts = splitTag(languageTag);
        if (parts.length > 1 && isRegionSubtag(parts[1])) {
            return parts[1].toUpperCase(Locale.US);
        }
        if (parts.length > 2 && isRegionSubtag(parts[2])) {
            return parts[2].toUpperCase(Locale.US);
        }
        return "";
    }

    /**
     * Splits a normalized language tag into subtags.
     *
     * 将规范化语言标签拆分为子标签。
     *
     * @param languageTag language tag to split
     *                    待拆分语言标签
     * @return subtag array, or an empty array for blank input
     *         子标签数组；空输入返回空数组
     */
    private static String[] splitTag(String languageTag) {
        if (languageTag == null) {
            return new String[0];
        }
        String normalizedTag = languageTag.trim().replace('_', '-');
        if (normalizedTag.isEmpty()) {
            return new String[0];
        }
        return normalizedTag.split("-");
    }

    /**
     * Checks whether a tag segment is a valid region subtag for this module.
     *
     * 检查标签片段是否为本模块认可的合法地区子标签。
     *
     * @param value tag segment
     *              标签片段
     * @return {@code true} for two letters or three digits
     *         两个字母或三个数字时返回 {@code true}
     */
    private static boolean isRegionSubtag(String value) {
        if (value.length() == 2) {
            return isAlphabetic(value);
        }
        if (value.length() == 3) {
            return isNumeric(value);
        }
        return false;
    }

    /**
     * Checks whether a tag segment is a script subtag.
     *
     * 检查标签片段是否为 script 子标签。
     *
     * @param value tag segment
     *              标签片段
     * @return {@code true} for four ASCII letters
     *         四个 ASCII 字母时返回 {@code true}
     */
    private static boolean isScriptSubtag(String value) {
        return value.length() == 4 && isAlphabetic(value);
    }

    /**
     * Checks whether all characters are ASCII letters.
     *
     * 检查所有字符是否均为 ASCII 字母。
     *
     * @param value value to check
     *              待检查值
     * @return {@code true} when every character is an ASCII letter
     *         每个字符均为 ASCII 字母时返回 {@code true}
     */
    private static boolean isAlphabetic(String value) {
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            boolean upperCase = character >= 'A' && character <= 'Z';
            boolean lowerCase = character >= 'a' && character <= 'z';
            if (!upperCase && !lowerCase) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether all characters are ASCII digits.
     *
     * 检查所有字符是否均为 ASCII 数字。
     *
     * @param value value to check
     *              待检查值
     * @return {@code true} when every character is an ASCII digit
     *         每个字符均为 ASCII 数字时返回 {@code true}
     */
    private static boolean isNumeric(String value) {
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            if (character < '0' || character > '9') {
                return false;
            }
        }
        return true;
    }
}
