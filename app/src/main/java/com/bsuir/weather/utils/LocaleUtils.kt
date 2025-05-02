package com.bsuir.weather.utils

import android.content.Context
import java.util.Locale

object LocaleUtils {

    val Context.currentLocale: Locale
        get() {
            return resources.configuration.locales[0]
        }
}
