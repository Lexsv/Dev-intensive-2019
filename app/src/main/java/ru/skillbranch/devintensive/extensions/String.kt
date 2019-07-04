package ru.skillbranch.devintensive.extensions

import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist

fun String.stripHtml ():String {
    val content: String = Jsoup.clean(this, Whitelist.none()).trim()
    val list: List<String> = content.split(" ")
    return list.joinToString(separator = " ")
}