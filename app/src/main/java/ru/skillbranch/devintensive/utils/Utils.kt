package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName (fullName: String?) : Pair <String?,String?> {

        return when (fullName) {
            "", " " -> {
                val firstName = null
                val lastName = null
                firstName to lastName
            }
            else -> {
                var cleanValue = fullName?.trim()
                val pars: List<String>? = cleanValue?.split(" ")
                val clearPars: List<String>? =  pars?.filter { s -> s != "" }
                val firstName = clearPars?.getOrNull(0)
                val lastName = clearPars?.getOrNull(1)
                firstName  to  lastName
            }
        }
    }

    fun transliteration(payloat: String, divider:String = " "): String? {
        var translitNeme: String? = payloat.toLowerCase()
        val translitExemplL: Map<String,String> = mapOf(
            "а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d",
            "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i",
            "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n",
            "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t",
            "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch",
            "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "",
            "э" to "e", "ю" to "yu", "я" to "ya")


            for (r in translitExemplL){
                translitNeme = translitNeme?.replace(r.key, r.value)
            }

        val pars: List<String>? = translitNeme?.split(divider)
        translitNeme = pars?.getOrNull(0)?.
                             replaceFirst(pars.get(0).toString().get(0),
                                          pars.get(0).toString().get(0).toUpperCase()) +
                             divider +
                             pars?.getOrNull(1)?.
                             replaceFirst(pars.get(1).toString().get(0),
                                          pars.get(1).toString().get(0).toUpperCase())


        return  translitNeme
    }

    fun toInitials(firstName: String? = null, lastName: String?=null): String? {
        val firstNameInitiale: Char? = firstName?.getOrNull(0)?.toUpperCase()
        val lastNameInitiale: Char? = lastName?.getOrNull(0)?.toUpperCase()

       return when (firstNameInitiale to lastNameInitiale) {
           null to null, ' ' to null, null to ' '  -> null
           firstNameInitiale to null, firstNameInitiale to ' ' -> firstNameInitiale.toString()
           null to lastName, ' ' to lastName -> lastNameInitiale.toString()
           lastNameInitiale to null, lastNameInitiale to ' ' -> lastNameInitiale.toString()
           else -> firstNameInitiale.toString() + lastNameInitiale.toString()
        }
    }
}