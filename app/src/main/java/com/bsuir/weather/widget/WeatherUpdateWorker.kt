package com.bsuir.weather.widget

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.usecase.ForecastLocationUseCase
import com.bsuir.weather.domain.usecase.GetCurrentForecastLocationUseCase
import com.bsuir.weather.widget.updater.WeatherNotificationUpdater
import com.bsuir.weather.widget.updater.WeatherWidgetUpdater
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class WeatherUpdateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getCurrentForecastLocationUseCase: GetCurrentForecastLocationUseCase,
    private val forecastLocationUseCase: ForecastLocationUseCase,
) : CoroutineWorker(appContext, workerParams) {

    @Inject lateinit var weatherNotificationUpdater: WeatherNotificationUpdater
    @Inject lateinit var weatherWidgetUpdater: WeatherWidgetUpdater

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
        weatherNotificationUpdater.updateNotification(forecastLocation)
    }

    private fun updateWidgets(forecastLocation: ForecastLocationModel) {
        weatherWidgetUpdater.updateWidgetWithForecast(forecastLocation)
    }
}
