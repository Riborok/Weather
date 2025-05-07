package com.bsuir.weather.widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.usecase.ForecastLocationUseCase
import com.bsuir.weather.domain.usecase.GetCurrentForecastLocationUseCase
import com.bsuir.weather.widget.utils.notification.WeatherNotificationBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WeatherService
    : Service(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    @Inject
    lateinit var getCurrentForecastLocationUseCase: GetCurrentForecastLocationUseCase

    @Inject
    lateinit var forecastLocationUseCase: ForecastLocationUseCase

    companion object {
        private const val HOUR_MILLIS   = 60 * 60 * 1_000L
        private const val RETRY_MILLIS  = 5 * 60 * 1_000L
        private const val NOTIFICATION_ID = 1

        private fun calculateNextDelay(wasSuccessful: Boolean): Long =
            if (wasSuccessful) HOUR_MILLIS else RETRY_MILLIS
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        beginPeriodicUpdates()
        return START_STICKY
    }

    private fun beginPeriodicUpdates() {
        launch {
            while (isActive) {
                val wasSuccessful = getCurrentForecastLocationUseCase.fetchCurrentForecast()?.let {
                    forecastLocationUseCase.updateForecastLocation(it)
                    updateForegroundNotification(it)
                    updateAllWidgets(it)
                } != null
                val nextDelay = calculateNextDelay(wasSuccessful)
                delay(nextDelay)
            }
        }
    }

    private fun updateForegroundNotification(forecastLocation: ForecastLocationModel) {
        val notification = WeatherNotificationBuilder(this).createNotification(
            forecastLocation = forecastLocation,
            forForeground = true
        )
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun updateAllWidgets(forecastLocation: ForecastLocationModel) {
        val mgr = AppWidgetManager.getInstance(this)
        val ids = mgr.getAppWidgetIds(
            ComponentName(this, WeatherWidgetProvider::class.java)
        )
        ids.forEach { appWidgetId ->
            WeatherWidgetProvider.updateAppWidget(this, mgr, appWidgetId, forecastLocation)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}