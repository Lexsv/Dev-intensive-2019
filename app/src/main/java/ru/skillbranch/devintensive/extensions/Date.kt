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
    SECOND {
        override fun plural(i: Int): String? {
            var content: Int = i
            if (content < 0) content *= -1

            return when {
                content % 10 == 1 -> if (content == 11)"${content} секунд" else "${content} секунду"
                content % 10 == 2 || content % 10 == 3 ||content % 10 == 4 ->
                    if ( content == 12 || content == 13 || content == 14)"${content} секунд" else "${content} секунды"
                else -> "${content} секунд"
            }
        }
    },
    MINUTE {
        override fun plural(i: Int): String? {
            var content: Int = i
            if (content < 0) content *= -1

            return when {
                content % 10 == 1 -> if (content == 11)"${content} минут" else "${content} минуту"
                content % 10 == 2 || content % 10 == 3 ||content % 10 == 4 ->
                    if ( content == 12 || content == 13 || content == 14)"${content} минут" else "${content} минуты"
                else -> "${content} минут"
            }
        }
    },
    HOUR {
        override fun plural(i: Int): String? {
            var content: Int = i
            if (content < 0) content *= -1
            return when {
                content % 10 == 1 -> if (content == 11)"${content} часов" else "${content} час"
                content % 10 == 2 || content % 10 == 3 || content % 10 == 4 ->
                    if ( content == 12 || content == 13 || content == 14)"${content} часов" else "${content} часа"
                else -> "${content} часов"
            }
        }
    },
    DAY {
        override fun plural(i: Int): String? {
            var content: Int = i
            if (content < 0) content *= -1


            return when {
                content % 10 == 1 || content % 100 == 1  -> if (content  == 11 || content  == 111
                    || content  == 211 ||content  == 311 ) "${content} дней"
                else "${content} день"
                content % 10 == 2 || content % 10 == 3 || content % 10 == 4
                        || content % 100 == 2 || content % 100 == 3 || content % 100 == 4 ->
                    if (content == 12 || content == 13 ||  content == 14 ||
                        content == 112 || content == 113 ||  content == 114 ||
                        content == 212 || content == 213 ||  content == 214 ||
                        content == 312 || content == 313 ||  content == 314 ) "${content} дней" else "${content} дня"
                else -> "${content} дней"
            }
        }
    },
    MONTH {
        override fun plural(i: Int): String? {
            return ""
        }
    };

    abstract fun plural(i: Int): String?

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
            in -1..0 -> resalt =  "только что"
            in -45..-1 -> resalt =  "через несколько секунд"
            in -75..-45 -> resalt =  "через минуту"
            in -45 * 60..-75 -> resalt =  "через ${getStringMin(differenceValue)}"
            in -75 * 60..-45 * 60  -> resalt =  "через час"
            in -22 * 3_600..-75 * 60 -> resalt =  "через ${getStringHou(differenceValue)}"
            in -26 * 3_600..-22 * 3_600  -> resalt =  "через день"
            in -360 * 86_400..-26* 3_600 -> resalt =  "через ${getStringDay(differenceValue)}"

       }
       if (differenceValue < -360 * 86_400) resalt = "более чем через год"



       return resalt

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