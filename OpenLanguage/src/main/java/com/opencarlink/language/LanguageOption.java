package com.opencarlink.language;

import java.util.Locale;

/**
 * Immutable description of one selectable language.
 *
 * 一个可选择语言的不可变描述。
 *
 * <p>The system option is represented by a {@code null} locale because its actual
 * value is resolved by Android at runtime.</p>
 *
 * <p>系统选项使用 {@code null} locale 表示，因为其实际语言值由 Android 在运行时决定。</p>
 */
public final class LanguageOption {

    private final String languageTag;
    private final Locale locale;
    private final boolean system;

    /**
     * Creates one language option.
     *
     * 创建一个语言选项。
     *
     * @param languageTag canonical BCP 47 style tag, or {@link LanguageManager#SYSTEM_LANGUAGE_TAG}
     *                    规范化的 BCP 47 风格标签，或 {@link LanguageManager#SYSTEM_LANGUAGE_TAG}
     * @param locale locale for an explicit language; {@code null} for the system option
     *               显式语言对应的 locale；系统选项为 {@code null}
     * @param system whether this option follows the system locale
     *               该选项是否跟随系统语言
     */
    LanguageOption(String languageTag, Locale locale, boolean system) {
        this.languageTag = languageTag;
        this.locale = locale;
        this.system = system;
    }

    /**
     * Returns the canonical language tag.
     *
     * 返回规范化的语言标签。
     *
     * @return canonical language tag
     *         规范化的语言标签
     */
    public String getLanguageTag() {
        return languageTag;
    }

    /**
     * Returns the explicit locale, or {@code null} for the system option.
     *
     * 返回显式 locale；系统选项返回 {@code null}。
     *
     * @return explicit locale, or {@code null}
     *         显式 locale，或 {@code null}
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Returns whether this option follows the system locale.
     *
     * 返回该选项是否跟随系统语言。
     *
     * @return {@code true} for the system option, otherwise {@code false}
     *         系统选项返回 {@code true}，否则返回 {@code false}
     */
    public boolean isSystem() {
        return system;
    }

    /**
     * Returns the locale display name in the requested display locale.
     *
     * 返回指定显示语言下的 locale 显示名称。
     *
     * <p>The system option returns an empty string because the host application
     * owns the localized label for "follow system".</p>
     *
     * <p>系统选项返回空字符串，因为“跟随系统”的本地化文案由宿主应用负责。</p>
     *
     * @param displayLocale locale used to render the display name
     *                      用于渲染显示名称的 locale
     * @return localized display name, or an empty string for the system option
     *         本地化显示名称；系统选项返回空字符串
     */
    public String getDisplayName(Locale displayLocale) {
        if (system) {
            return "";
        }
        Locale safeDisplayLocale = displayLocale == null ? Locale.getDefault() : displayLocale;
        return locale.getDisplayName(safeDisplayLocale);
    }

    /**
     * Compares options by language tag.
     *
     * 按语言标签比较选项。
     *
     * @param other other object
     *              其他对象
     * @return {@code true} when both options represent the same language tag
     *         两个选项表示同一语言标签时返回 {@code true}
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LanguageOption)) {
            return false;
        }
        LanguageOption that = (LanguageOption) other;
        return languageTag.equals(that.languageTag);
    }

    /**
     * Returns a stable hash code for the language tag.
     *
     * 返回基于语言标签的稳定哈希值。
     *
     * @return hash code
     *         哈希值
     */
    @Override
    public int hashCode() {
        return languageTag.hashCode();
    }

    /**
     * Returns a diagnostic representation of this option.
     *
     * 返回该选项的诊断字符串。
     *
     * @return diagnostic string
     *         诊断字符串
     */
    @Override
    public String toString() {
        return "LanguageOption{" + languageTag + '}';
    }
}
