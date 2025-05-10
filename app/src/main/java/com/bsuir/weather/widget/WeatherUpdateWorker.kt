package com.bsuir.weather.widget

import android.app.NotificationManager
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.usecase.ForecastLocationUseCase
import com.bsuir.weather.domain.usecase.GetCurrentForecastLocationUseCase
import com.bsuir.weather.widget.utils.notification.WeatherNotificationBuilder
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class WeatherUpdateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getCurrentForecastLocationUseCase: GetCurrentForecastLocationUseCase,
    private val forecastLocationUseCase: ForecastLocationUseCase,
) : CoroutineWorker(appContext, workerParams) {

    private companion object {
        const val NOTIFICATION_ID = 1
    }

    override suspend fun doWork(): Result {
        return try {
            getCurrentForecastLocationUseCase.fetchCurrentForecast()?.let { forecast ->
                forecastLocationUseCase.updateForecastLocation(forecast)
                updateNotification(forecast)
                updateWidgets(forecast)
                Result.success()
            } ?: Result.retry()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    private fun updateNotification(forecastLocation: ForecastLocationModel) {
        val notification = WeatherNotificationBuilder(applicationContext).createNotification(
            forecastLocation = forecastLocation,
            forForeground = true
        )
        val notificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun updateWidgets(forecastLocation: ForecastLocationModel) {
        WeatherWidgetProvider.updateWidgetWithForecast(applicationContext, forecastLocation)
    }
}
