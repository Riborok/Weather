package com.bsuir.weather.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.bsuir.weather.MainActivity
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.model.HourlyForecastModel

class WeatherNotifier(private val context: Context) {

    private val formatter = WeatherNotifierStringFormatter(context)

    companion object {
        const val CHANNEL_ID = "weather_notification_channel"
        const val CHANNEL_NAME = "Weather Updates"
        const val CHANNEL_DESCRIPTION = "Channel for weather updates"
        const val NOTIFICATION_ID = 1
    }

    fun createNotification(
        forecastLocation: ForecastLocationModel,
        forForeground: Boolean = false
    ): Notification {
        val location = formatter.formatLocation(forecastLocation.location.address)
        val current = forecastLocation.forecast.currentForecast
        val hourly = forecastLocation.forecast.hourlyForecasts

        val title = formatter.formatTitle(location, current.temperature)
        val contentText = formatter.formatContentText(current.apparentTemperature, current.weatherDescriptionId)
        val expandedView = createExpandedView(location, current, hourly)

        createNotificationChannel()
        val pendingIntent = createPendingIntent()

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_weather)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(title)
            .setContentText(contentText)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomBigContentView(expandedView)
            .setContentIntent(pendingIntent)
            .setOngoing(forForeground)
            .setAutoCancel(false)
            .build()
    }

    private fun createNotificationChannel() {
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESCRIPTION
        }

        notificationManager.createNotificationChannel(channel)
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createExpandedView(
        location: String,
        current: CurrentForecastModel,
        hourlyForecasts: List<HourlyForecastModel>
    ): RemoteViews {
        val packageName = context.packageName

        val expanded = RemoteViews(packageName, R.layout.notification_weather_expanded)

        expanded.setTextViewText(R.id.tv_location, location)
        expanded.setTextViewText(R.id.tv_current_temp, formatter.formatTemperature(current.temperature))
        expanded.setTextViewText(R.id.tv_feels_like, formatter.formatFeelsLike(current.apparentTemperature))
        expanded.setTextViewText(R.id.tv_description, formatter.formatDescription(current.weatherDescriptionId))
        expanded.setImageViewResource(R.id.iv_current_icon, current.iconId)

        expanded.removeAllViews(R.id.hourly_container)
        hourlyForecasts.take(6).forEach { hour ->
            val item = RemoteViews(packageName, R.layout.notification_hourly_item)
            item.setTextViewText(R.id.tv_hour, formatter.formatHour(hour.time))
            item.setTextViewText(R.id.tv_temp, formatter.formatTemperature(hour.temperature))
            item.setImageViewResource(R.id.iv_icon, hour.iconId)
            expanded.addView(R.id.hourly_container, item)
        }

        return expanded
    }
}
