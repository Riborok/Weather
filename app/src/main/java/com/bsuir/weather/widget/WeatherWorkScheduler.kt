package com.bsuir.weather.widget

import android.content.Context
import androidx.work.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherWorkScheduler @Inject constructor(
    private val context: Context
) {

    companion object {
        private const val WORK_NAME_IMMEDIATE = "hourly_immediate_update"
        private const val WORK_NAME_HOURLY = "hourly_weather_update"
    }

    fun cancelHourlyUpdates() {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME_HOURLY)
    }

    fun isHourlyUpdateScheduled(): Flow<List<WorkInfo>> {
        return WorkManager.getInstance(context)
            .getWorkInfosForUniqueWorkFlow(WORK_NAME_HOURLY)
    }

    fun scheduleWeatherUpdates() {
        enqueueImmediateUpdate()
        enqueueHourlyUpdates()
    }

    fun enqueueImmediateUpdate() {
        val request = OneTimeWorkRequestBuilder<WeatherUpdateWorker>()
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork(
            WORK_NAME_IMMEDIATE,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    private fun enqueueHourlyUpdates() {
        val delay = calculateInitialDelayToNextHour()
        val periodicRequest = PeriodicWorkRequestBuilder<WeatherUpdateWorker>(
            1, TimeUnit.HOURS
        )
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME_HOURLY,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
    }

    private fun calculateInitialDelayToNextHour(): Long {
        val now = LocalDateTime.now()
        val nextHour = now.plusHours(1).withMinute(0).withSecond(0).withNano(0)
        return ChronoUnit.MILLIS.between(now, nextHour)
    }
}
