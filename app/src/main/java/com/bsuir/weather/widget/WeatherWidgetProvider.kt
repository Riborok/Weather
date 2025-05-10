package com.bsuir.weather.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.usecase.ForecastLocationUseCase
import com.bsuir.weather.utils.android.ThemeUtils.getDynamicSurfaceColor
import com.bsuir.weather.utils.ext.createMainActivityPendingIntent
import com.bsuir.weather.utils.ext.withBackgroundColor
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

    @Inject lateinit var forecastLocationUseCase: ForecastLocationUseCase

    @Inject lateinit var weatherWorkScheduler: WeatherWorkScheduler

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        scope.launch {
            val forecastLocation = forecastLocationUseCase.getForecastLocation().firstOrNull()
            forecastLocation
                ?.let { forecastLocation ->
                    updateWidgetWithForecast(context, appWidgetManager, appWidgetIds, forecastLocation)
                }
                ?: run {
                    updateWidgetWithLoadingState(context, appWidgetManager, appWidgetIds)
                    weatherWorkScheduler.enqueueImmediateUpdate()
                }
        }
    }

    override fun onDisabled(context: Context) {
        scope.cancel()
        super.onDisabled(context)
    }

    companion object {
        fun updateWidgetWithForecast(
            context: Context,
            forecastLocation: ForecastLocationModel
        ) {
            val mgr = AppWidgetManager.getInstance(context)
            val ids = mgr.getAppWidgetIds(
                ComponentName(context, WeatherWidgetProvider::class.java)
            )
            updateWidgetWithForecast(context, mgr, ids, forecastLocation)
        }

        private fun updateWidgetWithForecast(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray,
            forecastLocation: ForecastLocationModel
        ) {
            val padding = 22
            val pendingIntent = context.createMainActivityPendingIntent()
            val backgroundColor = getDynamicSurfaceColor(context)

            val view = ViewBuilder(context).createView(forecastLocation)
                .withPadding(padding)
                .withClickAction(pendingIntent)
                .withBackgroundColor(backgroundColor)
            appWidgetIds.forEach { id -> appWidgetManager.updateAppWidget(id, view) }
        }

        private fun updateWidgetWithLoadingState(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray
        ) {
            val pendingIntent = context.createMainActivityPendingIntent()
            val backgroundColor = getDynamicSurfaceColor(context)

            val loadingView = ViewBuilder(context).createLoadingView()
                .withClickAction(pendingIntent)
                .withBackgroundColor(backgroundColor)

            appWidgetIds.forEach { id -> appWidgetManager.updateAppWidget(id, loadingView) }
        }
    }
}
