@file:Suppress("unused")
@file:JvmName("DateExt")

package com.loko.utils.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * 将日期转换到指定格式的字符串，如果转换失败将返回null。
 *
 * @param pattern 日期正则表达式，例如：yyyy-MM-dd HH:mm:ss
 * @param locale 具体时区、国家等相关信息
 *
 * @return 目标字符串
 */
@JvmOverloads
fun Date?.toDateStringOrNull(pattern: String, locale: Locale = Locale.getDefault()): String? {
    val formatter = SimpleDateFormat(pattern, locale)
    formatter.timeZone = TimeZone.getTimeZone("GMT+00:00")
    return try {
        formatter.format(this)
    } catch (ex: Exception) {
        null
    }
}

/**
 * 将日期转换到指定格式的字符串，如果转换失败将返回null。
 *
 * @param pattern 日期正则表达式，例如：yyyy-MM-dd HH:mm:ss
 * @param locale 具体时区、国家等相关信息
 *
 * @return 目标字符串
 */
@JvmOverloads
fun Calendar?.toDateStringOrNull(pattern: String, locale: Locale = Locale.getDefault()): String? {
    Date()
    return this?.time.toDateStringOrNull(pattern, locale)
}