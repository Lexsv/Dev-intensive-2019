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
                val pars: List<String>? = fullName?.split(" ")
                val firstName = pars?.getOrNull(0)
                val lastName = pars?.getOrNull(1)
                firstName to lastName
            }
        }
    }

    fun transliteration(payloat: String, divider:String = " "): String? {
        val translitNeme: String? = null

        return translitNeme
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstNameInitiale: Char? = firstName?.getOrNull(0)?.toUpperCase()
        val lastNameInitiale: Char? = lastName?.getOrNull(0)?.toUpperCase()

       return when (firstNameInitiale to lastNameInitiale) {
           null to null, ' ' to null, null to ' '  -> null
           firstNameInitiale to null, firstNameInitiale to ' ' -> firstNameInitiale.toString()
           lastNameInitiale to null, lastNameInitiale to ' ' -> lastNameInitiale.toString()
           else -> firstNameInitiale.toString() + lastNameInitiale.toString()
        }
    }
}