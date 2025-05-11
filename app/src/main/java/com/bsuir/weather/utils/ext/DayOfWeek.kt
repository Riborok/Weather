package com.bsuir.weather.utils.ext

import android.content.Context
import java.time.format.TextStyle

fun java.time.DayOfWeek.getCapitalizedDisplayName(context: Context): String {
    val locale = context.currentLocale
    var formattedDayName = getDisplayName(TextStyle.FULL_STANDALONE, locale).toString()
    formattedDayName = formattedDayName.replaceFirstChar { it -> it.uppercase() }

    return formattedDayName
}