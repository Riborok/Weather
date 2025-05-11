package com.bsuir.weather.widget.updater

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.widget.utils.notification.WeatherNotificationBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherNotificationUpdater @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val NOTIFICATION_ID = 42
        private const val MAX_RETRY_COUNT = 5
        private const val BASE_DELAY_MINUTES = 1L
    }

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val notificationBuilder: WeatherNotificationBuilder by lazy {
        WeatherNotificationBuilder(context)
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var retryJob: Job? = null

    fun updateNotification(
        forecastLocation: ForecastLocationModel,
        forForeground: Boolean = true
    ) {
        if (hasNotificationPermission()) {
            retryJob?.cancel()
            showNotification(forecastLocation, forForeground)
        } else {
            scheduleRetries(forecastLocation, forForeground)
        }
    }

    private fun scheduleRetries(
        forecastLocation: ForecastLocationModel,
        forForeground: Boolean
    ) {
        retryJob?.cancel()
        retryJob = coroutineScope.launch {
            for (attempt in 0 until MAX_RETRY_COUNT) {
                val delayMinutes = BASE_DELAY_MINUTES shl attempt
                delay(delayMinutes * 60 * 1000)

                if (hasNotificationPermission()) {
                    showNotification(forecastLocation, forForeground)
                    break
                }
            }
        }
    }

    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun showNotification(
        forecastLocation: ForecastLocationModel,
        forForeground: Boolean
    ) {
        val notification = notificationBuilder.createNotification(
            forecastLocation = forecastLocation,
            forForeground = forForeground
        )
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    fun cancelNotification() {
        retryJob?.cancel()
        notificationManager.cancel(NOTIFICATION_ID)
    }
}
