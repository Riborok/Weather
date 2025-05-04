package com.bsuir.weather.presentation.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.bsuir.weather.utils.ext.currentLocale
import java.time.DayOfWeek
import java.time.format.TextStyle

@Composable
fun getFormattedDayOfWeek(dayOfWeek: DayOfWeek): String {
    val context = LocalContext.current
    val locale = context.currentLocale
    var formattedDayName = dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, locale).toString()
    formattedDayName = formattedDayName.replaceFirstChar { it -> it.uppercase() }

    return formattedDayName
}