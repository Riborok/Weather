package com.bsuir.weather.utils.ext

import android.content.Context
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

fun kotlinx.datetime.LocalDate.getLocalizedDayOfWeekAndDate(context: Context): String {
    val javaDate = toJavaLocalDate()
    val formatter = DateTimeFormatter.ofPattern("dd.MM")
    val formattedJavaDate = javaDate.format(formatter)

    return "${dayOfWeek.getCapitalizedDisplayName(context)}, " + formattedJavaDate
}

