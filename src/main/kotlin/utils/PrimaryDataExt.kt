package com.loko.utils.utils

import java.math.BigDecimal
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

/**
 * 将整型数据作为时间戳，转换到具体的[Date]类型实例。
 *
 * @param timeUnit 时间单位（默认单位：毫秒）
 */
@JvmOverloads
fun Long.toDate(timeUnit: TimeUnit = TimeUnit.MILLISECONDS) = Date(timeUnit.toMillis(this))

/**
 * 将整型数据作为时间戳，转换到具体的[Date]类型实例。
 *
 * @param timeUnit 时间单位（默认单位：毫秒）
 */
@JvmOverloads
fun Long.toCalendar(timeUnit: TimeUnit = TimeUnit.MILLISECONDS): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeUnit.toMillis(this)
    return calendar
}

/**
 * 安全将数据转为Int值
 */
fun Any?.safeToInt(): Int {
    return kotlin.runCatching {
        this.toString().toInt()
    }.fold(
        {
            it
        }, {
            0
        }
    )
}

/**
 * 安全将数据转为String值
 */
fun Any?.safeToString(): String {
    return kotlin.runCatching {
        this?.toString() ?: ""
    }.fold(
        {
            it
        }, {
            ""
        }
    )
}

/**
 * 安全将数据转为String值
 */
fun Any?.safeToStringOrNull(): String? {
    return kotlin.runCatching {
        this?.toString()
    }.fold(
        {
            it
        }, {
            null
        }
    )
}