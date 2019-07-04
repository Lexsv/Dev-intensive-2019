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
    val differenceValue = (date.time - this.time) / SECOND
    var resalt: String = ""
       when (differenceValue) {
            in 0..1 -> resalt = "только что"
            in 1..45 -> resalt = "несколько секунд назад"
            in 45..75 -> resalt = "минуту назад"
            in 75..45 * 60 -> resalt = "${getStringMin(differenceValue)} назад"
            in 45 * 60 ..75 * 60 -> resalt = "час назад"
            in 75 * 60 ..22 * 3_600 -> resalt = "${getStringHou(differenceValue)} назад"
            in 22 * 3_600..26 * 3_600 -> resalt = "день назад"
            in 26* 3_600..360 * 86_400 -> resalt = "${getStringDay(differenceValue)} назад"
            else -> if (differenceValue > 360 * 86_400) resalt = "более года назад"
       }


       when (differenceValue) {
            in -1..0 -> resalt =  "прямо сейчас"
            in -45..-1 -> resalt =  "через несколько секунд"
            in -75..-45 -> resalt =  "через минуту"
            in -45 * MINUTE..-75 -> resalt =  "через ${getStringMin(differenceValue)}"
            in -75 * MINUTE..-45 * MINUTE  -> resalt =  "через час"
            in -22 * HOUR..-75 * MINUTE  -> resalt =  "через ${getStringHou(differenceValue)}"
            in -26 * HOUR..-22 * HOUR  -> resalt =  "через день"
            in  -360 * DAY..-26* HOUR -> resalt =  "через ${getStringDay(differenceValue)}"

       }
       if (differenceValue < -360 * DAY) resalt = "более чем через год"



       return "какое-то время назад"

}

fun getStringMin (value: Long): String{
    var content: Long = value / 60
    if (content < 0) content *= -1

    return when {
        content % 10 == 1L -> if (content == 11L)"${content} минут" else "${content} минуту"
        content % 10 == 2L || content % 10 == 3L ||content % 10 == 4L ->
            if ( content == 12L || content == 13L || content == 14L)"${content} минут" else "${content} минуты"
        else -> "${content} минут"
    }

}

fun getStringHou (value: Long): String {
    var content: Long = value / 3_600
    if (content < 0) content *= -1
    return when {
        content % 10 == 1L -> if (content == 11L)"${content} часов" else "${content} час"
        content % 10 == 2L || content % 10 == 3L || content % 10 == 4L ->
            if ( content == 12L || content == 13L || content == 14L)"${content} часов" else "${content} часа"
        else -> "${content} часов"
    }
}
fun getStringDay (value: Long): String {
    var content: Long = value / 86_400
    if (content < 0) content *= -1

    return when {
        content % 10 == 1L || content % 100 == 1L  -> if (content  == 11L || content  == 111L
                                                            || content  == 211L ||content  == 311L ) "${content} дней"
                                                      else "${content} день"
        content % 10 == 2L || content % 10 == 3L || content % 10 == 4L
                || content % 100 == 2L || content % 100 == 3L || content % 100 == 4L ->
                 if (content == 12L || content == 13L ||  content == 14L ||
                     content == 112L || content == 113L ||  content == 114L ||
                     content == 212L || content == 213L ||  content == 214L ||
                     content == 312L || content == 313L ||  content == 314L ) "${content} дней" else "${content} дня"
        else -> "${content} дней"
    }
}