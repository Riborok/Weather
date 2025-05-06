package com.bsuir.weather.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.usecase.GetForecastUseCase
import com.bsuir.weather.notification.WeatherNotifier
import com.bsuir.weather.utils.location.CurrentLocationUtils.fetchCurrentLocation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WeatherService
    : Service(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    @Inject
    lateinit var getForecastUseCase: GetForecastUseCase

    private companion object {
        private const val HOUR_MILLIS   = 60 * 60 * 1_000L
        private const val RETRY_MILLIS  = 5 * 60 * 1_000L
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        beginPeriodicUpdates()
        return START_STICKY
    }

    private fun beginPeriodicUpdates() {
        launch {
            while (isActive) {
                val wasSuccessful = updateAndNotify()
                val nextDelay = calculateNextDelay(wasSuccessful)
                delay(nextDelay)
            }
        }
    }

    private suspend fun updateAndNotify(): Boolean {
        val location = fetchCurrentLocation(this) ?: return false
        return try {
            val forecast = getForecastUseCase.execute(location.latitude, location.longitude)
            showForegroundNotification(forecast, location)
            true
        } catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            e.printStackTrace()
            false
        }
    }

    private fun showForegroundNotification(forecast: ForecastModel, location: LocationModel) {
        val weatherNotifier = WeatherNotifier(this)
        val notification = weatherNotifier.createNotification(
            forecastLocation = ForecastLocationModel(forecast, location),
            forForeground = true
        )
        startForeground(WeatherNotifier.NOTIFICATION_ID, notification)
    }

    private fun calculateNextDelay(wasSuccessful: Boolean): Long =
        if (wasSuccessful) HOUR_MILLIS else RETRY_MILLIS

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
