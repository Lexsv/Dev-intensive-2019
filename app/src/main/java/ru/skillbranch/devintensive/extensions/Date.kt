package ru.skillbranch.devintensive.extensions

import java.util.*
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val MONTH = DAY * 30
const val YEAR = MONTH * 12

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    MONTH

}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy", locale: String = "ru"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale(locale))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time: Long = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.MONTH -> value * MONTH
    }
    this.time = time
    return this
}




fun Date.humanizeDiff(date: Date = Date()): String {
    val differenceValue = date.time - this.time / SECOND








    return "  "
}

