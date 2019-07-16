package ru.skillbranch.devintensive.extensions

//import org.jsoup.Jsoup
//import org.jsoup.safety.Whitelist
//
//fun String.stripHtml(): String {
//    val content: String = Jsoup.clean(this, Whitelist.none()).trim()
//    val list: List<String> = content.split(" ")
//    return list.joinToString(separator = " ")
//}
//
//fun String.truncate (value: Int = 16): String {
//    val valueThis = if (this.length < value) this.length else value
//    if (this.length == value)return  this.trim()
//    var stringThis = this.take(valueThis)
//    val lastThis : String = this.takeLast(this.length - valueThis).trim()
//    if (stringThis.last() == ' ')
//        if (this.length > valueThis && lastThis.length >= 1)stringThis = stringThis.trim().plus("...")
//        else stringThis = stringThis.trim()
//    else stringThis = stringThis.plus("...")
//    return stringThis
//}