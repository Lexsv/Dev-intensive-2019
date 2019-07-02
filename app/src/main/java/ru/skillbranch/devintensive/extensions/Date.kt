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
    MONTH,
    YEAR
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
        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var humanizeSrt: String = ""
    var differenceValue = (date.time - this.time) / SECOND
    when (differenceValue) {
        in 1..59 -> humanizeSrt = "несколько секунд назад"

        in 60..119 -> humanizeSrt = "минуту назад"

        in 120..299,     // 2 - 4 MINUTE
        in 1_320..1_499,  // 22 - 24 MINUTE
        in 1_920..2_099, // 32 - 34 MINUTE
        in 2_520..2_699,  // 42 - 44 MINUTE
        in 3_120..3_299   /* 52 - 54 MINUTE */ -> humanizeSrt = "${differenceValue / 60} минуты назад"

        in 1_260..1_319, //21 MINUTE
        in 1_860..1_919, //31 MINUTE
        in 2_460..2_519, //41 MINUTE
        in 3_060..3_119 /* 51  MINUTE */ -> humanizeSrt = "${differenceValue / 60} минуту назад"

        in 60..3_599    /* 5 - 60 MINUTE */ -> humanizeSrt = "${differenceValue / 60} минут назад"

        in 3_600..7_199 -> humanizeSrt = " час назад" //1 HOUR
        in 7_200..17_999, //2 - 4
        in 79_200..86_399 /* 22 - 24 */-> humanizeSrt = " ${differenceValue / 3_600} часа назад"

        in 18_000..75_599 -> humanizeSrt = " ${differenceValue / 3_600} часов назад"  //5 - 20 HOUR
        in 75_600..79_199 -> humanizeSrt = " ${differenceValue / 3_600} час назад"  //21 HOUR

        in 86_400..172_799 -> humanizeSrt = " день назад" // 1 DAY
        in 172_800..431_999 -> humanizeSrt = "${differenceValue / 86_400} деня назад" // 2 - 4 DAY
        in 432_000..604_799 -> humanizeSrt = "${differenceValue / 86_400} деней назад" // 5 - 30 DAY
        in 604_800..1_209_599 -> humanizeSrt = "неделю назад" // 1 WEEK
        in 1_209_600..2_591_999 -> humanizeSrt = "${differenceValue / 604_800} недели назад"     // 2 - 4 WEEK

        in 2_592_000..5_183_999 -> humanizeSrt = "месяц назад" // 1 MONTH
        in 5_184_000..10_367_999 -> humanizeSrt = "${differenceValue / 2_592_000} месяца назад" // 2 - 4 MONTH
        in 10_368_000..31_535_999 -> if (differenceValue in 31_103_999..31_535_999)   // 5 - 12 MONTH
                                         humanizeSrt = "11 месяцев назад"
                                     else humanizeSrt = "${differenceValue / 2_592_000} месяцев назад"
    }
    if (differenceValue >= 31_536_000) humanizeSrt = "более года назад"


    if (differenceValue <= 0) differenceValue--
    when (differenceValue) {
        in -59..0 -> humanizeSrt = "через несколько секунд"

        in -119..-60 ->  humanizeSrt = "через минуту"2

        in -299..-120,     // 2 - 4 MINUTE
        in -1_499..-1_320,  // 22 - 24 MINUTE
        in -2_099..-1_920, // 32 - 34 MINUTE
        in -2_699..-2_520,  // 42 - 44 MINUTE
        in -3_299..-3_120  /* 52 - 54 MINUTE */ -> humanizeSrt = "через ${differenceValue / 60 * -1} минуты"

        in -1_319..-1_260, //21 MINUTE
        in -1_919..-1_860, //31 MINUTE
        in -2_519..-2_460, //41 MINUTE
        in -3_119..-3_060 /* 51  MINUTE */ -> humanizeSrt = "через ${differenceValue / 60 * -1} минуту"

        in -3_599..-60    /* 5 - 60 MINUTE */ -> humanizeSrt = "через ${differenceValue / 60 * -1} минут"

        in -7_199..-3_600 -> humanizeSrt = "через час" //1 HOUR
        in -17_999..-7_200 -> humanizeSrt = "через ${differenceValue / 3_600 * -1} часа" //2 - 4 HOUR
        in -75_599..-18_000 -> humanizeSrt = "через ${differenceValue / 3_600 * -1} часов"  //5 - 20 HOUR
        in -79_199..-75_600 -> humanizeSrt = "через ${differenceValue / 3_600 * -1} час"  //21 HOUR
        in -86_399..-79_200 -> humanizeSrt = "через ${differenceValue / 3_600 * -1} часа" // 22 - 24 HOUR
        in -172_799..-86_400 -> humanizeSrt = "через день" // 1 DAY
        in -431_999..-172_800 -> humanizeSrt = "через ${differenceValue / 86_400 * -1} деня" // 2 - 4 DAY
        in -604_799..-432_000 -> humanizeSrt = "через ${differenceValue / 86_400 * -1} деней" // 5 - 30 DAY
        in -1_209_599..-604_800 -> humanizeSrt = "через неделю" // 1 WEEK
        in -2_591_999..-1_209_600 -> humanizeSrt = "через ${differenceValue / 604_800 * -1} недели"  // 2 - 4 WEEK

        in -5_184_000..-2_592_000 -> humanizeSrt = "через месяц" // 1 MONTH
        in -10_367_999..-5_183_999 -> humanizeSrt = "через ${differenceValue / 2_592_000 * -1} месяца" // 2 - 4 MONTH
        in -31_535_999..-10_368_000 -> if (differenceValue in -31_535_999..-31_103_999)   // 5 - 12 MONTH
                                            humanizeSrt = "через 11 месяцев"
                                        else humanizeSrt = "через ${differenceValue / 2_592_000 * -1} месяцев"
    }
    if (differenceValue <= -31_536_000) humanizeSrt = "более чем через года"

    return humanizeSrt
}

