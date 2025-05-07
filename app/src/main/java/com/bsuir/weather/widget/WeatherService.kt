package com.bsuir.weather.widget

import android.app.Service
import android.content.Intent
import android.os.IBinder
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

    private companion object {
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
                val wasSuccessful = showForegroundNotification()
                val nextDelay = calculateNextDelay(wasSuccessful)
                delay(nextDelay)
            }
        }
    }

    private suspend fun showForegroundNotification(): Boolean {
        val forecastLocation = getCurrentForecastLocationUseCase.fetchCurrentForecast()
            ?: return false

        val notification = WeatherNotificationBuilder(this).createNotification(
            forecastLocation = forecastLocation,
            forForeground = true
        )
        startForeground(NOTIFICATION_ID, notification)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}