package com.bsuir.weather.widget.updater

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.widget.WeatherWidgetProvider
import javax.inject.Inject

class WeatherWidgetUpdater @Inject constructor(
    private val context: Context
) {
    fun updateWidgetWithForecast(
        forecastLocation: ForecastLocationModel
    ) {
        val mgr = AppWidgetManager.getInstance(context)
        val ids = mgr.getAppWidgetIds(
            ComponentName(context, WeatherWidgetProvider::class.java)
        )
        WeatherWidgetProvider.updateWidgetWithForecast(context, mgr, ids, forecastLocation)
    }
}
