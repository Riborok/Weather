package com.bsuir.weather.presentation.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.key
import androidx.compose.ui.platform.LocalContext
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.LocaleUtils.currentLocale
import com.bsuir.weather.utils.defaultLocation

@Composable
fun getDefaultLocation(): LocationModel {
    val context = LocalContext.current
    val locale = context.currentLocale

    return key(locale) {
        remember(locale) { context.defaultLocation }
    }
}
