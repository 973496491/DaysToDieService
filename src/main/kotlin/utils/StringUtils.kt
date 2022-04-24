@file:Suppress("unused")
@file:JvmName("StringExt")

package com.loko.utils.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * <h2> 合法正整数正则 </h2>
 *
 * @author Loko.yao
 * @since 2020年09月21日  周一 20时17分59秒
 */
const val PATTERN_LEGAL_POSITIVE_INTEGER = "^(0|[1-9]\\d*)$"

/**
 * <h2> 合法正小数正则 </h2>
 *
 * @author Loko.yao
 * @since 2020年09月21日  周一 20时31分20秒
 */
const val PATTERN_LEGAL_DECIMAL = "^(0|[1-9]\\d*)\\.\\d+$"

/**
 * 手机号码正则
 */
const val PATTERN_MOBILE_PHONE = "^[1][1-9][0-9]{9}$"

/**
 * <h2> 判断给定字符串是否为合法正整数 </h2>
 *
 * @author Loko.yao
 * @since 2020年09月21日  周一 20时12分32秒
 *
 * @param str  需要判断的字符串
 *
 * @return {@code true : 是合法正整数} {@code false : 不是合法正整数}
 */
fun isLegalPositiveInteger(str: String?): Boolean {
    return str?.let {
        Pattern.compile(PATTERN_LEGAL_POSITIVE_INTEGER).matcher(it).matches()
    } ?: false
}

/**
 * <h2> 将给定字符串转化为正整数 </h2>
 *
 * @author Loko.yao
 * @since 2020年09月21日  周一 20时20分56秒
 *
 * @param str  需要转化的字符串
 *
 * @return  {@code 合法字符串 : 正整数} {@code 不合法字符串 : 0}
 */
fun convertToPosInteger(str: String?): Int {
    return str?.run {
        if (isLegalPositiveInteger(str)) {
            return toInt()
        }
        0
    } ?: 0
}

/**
 * 判断当前字符串是否为正整数, 如果为正整数则返回 true, 否则返回 false
 */
fun String?.isLegalDecimal(): Boolean {
    return this?.let {
        Pattern.compile(PATTERN_LEGAL_DECIMAL).matcher(it).matches()
    } ?: false
}

/**
 * string 转 double
 * @param num
 * @return
 */
fun getDouble(num: String?): Double {
    num?.let {
        return num.toDouble()
    }
    return 0.toDouble()
}

/**
 * Returns a char sequence with content of this char sequence where its part at the given range
 * is replaced with the [replacement] char sequence.
 *
 * @param startIndex the index of the first character to be replaced.
 * @param endIndex the index of the first character after the replacement to keep in the string.
 */
fun String.replaceAllAt(startIndex: Int, endIndex: Int, replacement: CharSequence): CharSequence {
    if (this.isEmpty()) {
        return ""
    }
    if (startIndex < 0)
        throw IndexOutOfBoundsException("Start index ($startIndex) is non-existent subscript.")
    if (endIndex > this.length)
        throw IndexOutOfBoundsException("End index ($endIndex) is byte length exceeded.")
    if (endIndex < startIndex)
        throw IndexOutOfBoundsException("End index ($endIndex) is less than start index ($startIndex).")
    return this.replaceRange(startIndex until endIndex, replacement.repeat(endIndex - startIndex))
}

fun String.replaceAllAt(range: IntRange, replacement: CharSequence): CharSequence {
    return this.replaceAllAt(range.first, range.last, replacement)
}

/**
 * 使用指定的日期格式[pattern]和语言环境[locale]将传入的String构建成一个日期[Date]并将结果返回, 如果日期无法进行构建, 则返回 "null".
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用系统默认值, 同[Locale.getDefault]取值一样.
 * @param timeZone 设置日期所在时区, 如果没有指定, 则根据语言使用系统默认的时区, 同[TimeZone.getDefault]取值一样.
 *
 * @return [Date] or Null.
 */
@JvmOverloads
fun String.toDateOrNull(
    pattern: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone = TimeZone.getDefault()
): Date? {
    val format = SimpleDateFormat(pattern, locale)
    format.timeZone = timeZone
    return runCatching {
        format.parse(this)
    }.getOrNull()
}

/**
 * 使用指定的日期格式[pattern]和语言环境[locale]将传入的String构建成一个日期[Date]并将结果返回, 如果日期无法进行构建, 则返回默认值[defaultValue]。
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用系统默认值, 同[Locale.getDefault]取值一样.
 * @param timeZone 设置日期所在时区, 如果没有指定, 则根据语言使用系统默认的时区, 同[TimeZone.getDefault]取值一样.
 * @param defaultValue 日期[Date]构建失败后所返回的默认结果.
 *
 * @return [Date] or [defaultValue]
 */
@JvmOverloads
fun String.toDateOrElse(
    pattern: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone = TimeZone.getDefault(),
    defaultValue: Date,
): Date {
    val format = SimpleDateFormat(pattern, locale)
    format.timeZone = timeZone
    return runCatching {
        format.parse(this)
    }.getOrElse { defaultValue }
}

/**
 * 使用指定的日期格式[pattern]和语言环境[locale]将传入的String构建成一个日期[Date], 然后将构建的日期[Date]以新的日期格式[newPattern]转化成新的日期[Date],
 * 其后将新的日期[Date]转化成[String]并将结果返回, 如果在转化过程中发生错误, 则返回 'null'。
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用系统默认值, 同[Locale.getDefault]取值一样.
 * @param timeZone 设置日期所在时区, 如果没有指定, 则根据语言使用系统默认的时区, 同[TimeZone.getDefault]取值一样.
 *
 * @return [Date] or 'null'
 */
@JvmOverloads
fun String.toTransformNewDateOrNull(
    pattern: String,
    newPattern: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone = TimeZone.getDefault(),
): String? {
    return this.toDateOrNull(pattern, locale, timeZone)?.toDateStringOrNull(newPattern, locale)
}

/**
 * 使用指定的日期格式[pattern]和语言环境[locale]将传入的String构建成一个日期[Date], 然后将构建的日期[Date]以新的日期格式[newPattern]转化成新的日期[Date],
 * 其后将新的日期[Date]转化成[String]并将结果返回, 如果在转化过程中发生错误, 则返回默认值[defaultValue]。
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用系统默认值, 同[Locale.getDefault]取值一样.
 * @param timeZone 设置日期所在时区, 如果没有指定, 则根据语言使用系统默认的时区, 同[TimeZone.getDefault]取值一样.
 * @param defaultValue 日期[Date]构建失败后所返回的默认结果.
 *
 * @return [Date] or 'null'
 */
@JvmOverloads
fun String.toTransformNewDateOrElse(
    pattern: String,
    newPattern: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone = TimeZone.getDefault(),
    defaultValue: String
): String {
    val newDateStr = this.toDateOrNull(pattern, locale, timeZone)?.toDateStringOrNull(newPattern, locale)
    return if (newDateStr.isNullOrEmpty()) {
        defaultValue
    } else {
        newDateStr
    }
}

/**
 * 使用指定的日期格式[pattern]和语言环境[locale]将传入的String构建成一个日历[Calendar]并将结果返回, 如果日历无法进行构建, 则返回 "null"。
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用默认值[Locale.getDefault].
 *
 * @return [Date] or null.
 */
@JvmOverloads
fun String.toCalendarOrNull(pattern: String, locale: Locale = Locale.getDefault()): Calendar? {
    val date: Date? = toDateOrNull(pattern, locale)
    return date?.let {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar
    }
}

/**
 * 使用指定的日期格式[pattern]和语言环境[locale]将传入的String构建成一个日历[Calendar]并将结果返回, 如果日历无法进行构建, 则返回默认值[defaultValue]。
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用默认值[Locale.getDefault].
 * @param defaultValue 日期[Date]构建失败后所返回的默认结果.
 *
 * @return [Date] or [defaultValue].
 */
@JvmOverloads
fun String.toCalendarOrElse(pattern: String, locale: Locale = Locale.getDefault(), defaultValue: Calendar): Calendar {
    val calendar = this.toCalendarOrNull(pattern, locale)
    return calendar ?: defaultValue
}

/**
 * 传入一个日期解析格式[pattern], 返回String对象所表示毫秒级别的时间戳, 如果该对象转换失败则返回 "null".
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用系统默认值, 同[Locale.getDefault]取值一样.
 * @param timeZone 设置日期所在时区, 如果没有指定, 则根据语言使用系统默认的时区, 同[TimeZone.getDefault]取值一样.
 *
 * @return 时间戳 or null.
 */
@JvmOverloads
fun String.toTimestampOrNull(
    pattern: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone = TimeZone.getDefault()
): Long? {
    return this.toDateOrNull(pattern, locale, timeZone)?.time
}

/**
 * 传入一个日期解析格式[pattern], 返回String对象所表示毫秒级别的时间戳, 如果该对象转换失败则返回 "0".
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用系统默认值, 同[Locale.getDefault]取值一样.
 * @param timeZone 设置日期所在时区, 如果没有指定, 则根据语言使用系统默认的时区, 同[TimeZone.getDefault]取值一样.
 *
 * @return 时间戳 or 0.
 */
@JvmOverloads
fun String.toTimestampOrZero(
    pattern: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone = TimeZone.getDefault()
): Long {
    return this.toDateOrNull(pattern, locale, timeZone)?.time ?: 0
}

/**
 * 传入一个日期解析格式[pattern], 返回String对象所表示毫秒级别的时间戳, 如果该对象转换失败则返回传入的默认值[defaultValue].
 *
 * @param pattern 时间和日期的格式.
 * @param locale 将语言环境指定为传入值, 如果没有指定, 则使用系统默认值, 同[Locale.getDefault]取值一样.
 * @param timeZone 设置日期所在时区, 如果没有指定, 则根据语言使用系统默认的时区, 同[TimeZone.getDefault]取值一样.
 * @param defaultValue 日期[Date]构建失败后所返回的默认结果.
 *
 * @return 时间戳 or [defaultValue].
 */
@JvmOverloads
fun String.toTimestampOrElse(
    pattern: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone = TimeZone.getDefault(),
    defaultValue: Long
): Long {
    return this.toDateOrNull(pattern, locale, timeZone)?.time ?: defaultValue
}

/**
 * 判断当前字符集是否为一个手机号码, 如果手机号码正则表达式[PATTERN_MOBILE_PHONE]能与字符集进行匹配, 则返回 "true", 否则返回 "false".
 */
fun CharSequence?.isPhoneNumber(): Boolean {
    return this?.let {
        Pattern.matches(PATTERN_MOBILE_PHONE, this)
    } ?: false
}

fun String?.isNotNullOrEmpty(): Boolean {
    return this.isNullOrEmpty().not()
}