package com.bsuir.weather.utils.ext

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.bsuir.weather.MainActivity
import com.bsuir.weather.WeatherApplication
import java.util.Locale

val Context.weatherAppContext: WeatherApplication
    get() = this.applicationContext as WeatherApplication

val Context.currentLocale: Locale
    get() {
        return resources.configuration.locales[0]
    }

fun Context.createMainActivityPendingIntent(): PendingIntent {
    val intent = Intent(this, MainActivity::class.java)
    return PendingIntent.getActivity(
        this,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}