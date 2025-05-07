package com.bsuir.weather.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.usecase.ForecastLocationUseCase
import com.bsuir.weather.utils.ext.createMainActivityPendingIntent
import com.bsuir.weather.utils.ext.withClickAction
import com.bsuir.weather.utils.ext.withPadding
import com.bsuir.weather.widget.utils.ViewBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WeatherWidgetProvider : AppWidgetProvider() {

    @Inject
    lateinit var forecastLocationUseCase: ForecastLocationUseCase

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        scope.launch {
            val forecastLocation = forecastLocationUseCase.getForecastLocation().firstOrNull()
            forecastLocation?.let { forecastLocation ->
                updateAppWidgets(context, appWidgetManager, appWidgetIds, forecastLocation)
            }
        }
    }

    override fun onDisabled(context: Context) {
        scope.cancel()
        super.onDisabled(context)
    }

    companion object {
        fun updateAppWidgets(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray,
            forecastLocation: ForecastLocationModel
        ) {
            val view = ViewBuilder(context).createView(forecastLocation)
                .withPadding(4)
                .withClickAction(context.createMainActivityPendingIntent())
            appWidgetIds.forEach { id -> appWidgetManager.updateAppWidget(id, view) }
        }
    }
}
