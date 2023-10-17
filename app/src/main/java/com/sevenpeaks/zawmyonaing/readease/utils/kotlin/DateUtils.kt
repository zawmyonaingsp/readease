package com.sevenpeaks.zawmyonaing.readease.utils.kotlin

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Date.format(
    format: DateTimeFormat,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.ENGLISH
): String {
    val sdf = SimpleDateFormat(format.pattern, locale)
    timeZone?.let { sdf.timeZone = it }
    return sdf.format(this)
}

fun String.asDate(
    format: DateTimeFormat,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.ENGLISH
): Date? {
    val sdf = SimpleDateFormat(format.pattern, locale)
    timeZone?.let { sdf.timeZone = it }
    return if (isBlank()) null else runCatching { sdf.parse(this) }.getOrNull()
}

fun Long.asFormattedDate(
    format: DateTimeFormat,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.ENGLISH
): String? {
    return if (this > 0) {
        Date(this).format(format, timeZone, locale)
    } else {
        null
    }
}

val currentUTCTime: Date
    get() {
        val timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance(timeZone)
        return calendar.time
    }

@JvmInline
value class DateTimeFormat private constructor(val pattern: String) {
    companion object {
        val display_date_full_time = DateTimeFormat("dd MMMM yyyy, hh:mm:ss a")
        val server_date_time = DateTimeFormat("yyyy-MM-dd'T'HH:mm:ss")
        //        val display_date_time = DateTimeFormat("dd MMMM, hh:mm a")

//        val display_date_time_24hr = DateTimeFormat("dd MMMM, HH:mm")
//        val display_date_full_time_24hr = DateTimeFormat("dd MMMM yyyy, HH:mm")

        // 2022-10-12 15:06:29
//        val server_date_time = DateTimeFormat("yyyy-MM-dd HH:mm:s")
//        val timestamp = DateTimeFormat("yyyyMMdd_HHmms")
    }
}
