package dev.irfannm.newsee.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FormatDate {
    fun formatDate(rawDate: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val formattedDate = LocalDate.parse(rawDate, dateFormat)
        return formattedDate.dayOfMonth.toString() + "/" + formattedDate.monthValue.toString() + "/" + formattedDate.year.toString()
    }
}