package com.bsuir.weather.widget.utils.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.utils.ext.createMainActivityPendingIntent
import com.bsuir.weather.widget.utils.ViewBuilder
import com.bsuir.weather.widget.utils.WeatherStringFormatter
import com.bsuir.weather.widget.utils.notification.NotificationChannelHelper.CHANNEL_ID
import com.bsuir.weather.widget.utils.notification.NotificationChannelHelper.createWeatherChannel

class WeatherNotificationBuilder(private val context: Context) {
    fun createNotification(
        forecastLocation: ForecastLocationModel,
        forForeground: Boolean = false
    ): Notification {
        val formatter = WeatherStringFormatter(context)
        val title = buildTitle(formatter, forecastLocation)
        val contentText = buildContentText(formatter, forecastLocation)
        val view = buildCustomView(forecastLocation)
        val pendingIntent = context.createMainActivityPendingIntent()

        createWeatherChannel(context)
        return buildNotification(title, contentText, view, pendingIntent, forForeground)
    }

    private fun buildTitle(
        formatter: WeatherStringFormatter,
        forecastLocation: ForecastLocationModel
    ): String {
        val location = formatter.formatLocation(forecastLocation.location.address)
        val current = forecastLocation.forecast.currentForecast
        return formatter.formatTitle(location, current.temperature)
    }

    private fun buildContentText(
        formatter: WeatherStringFormatter,
        forecastLocation: ForecastLocationModel
    ): String {
        val current = forecastLocation.forecast.currentForecast
        return formatter.formatContentText(current.apparentTemperature, current.weatherDescriptionId)
    }

    private fun buildCustomView(forecastLocation: ForecastLocationModel) =
        ViewBuilder(context).createView(forecastLocation)

    private fun buildNotification(
        title: String,
        contentText: String,
        view: RemoteViews,
        pendingIntent: PendingIntent,
        forForeground: Boolean
    ): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_weather)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(title)
            .setContentText(contentText)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomBigContentView(view)
            .setContentIntent(pendingIntent)
            .setOngoing(forForeground)
            .setAutoCancel(false)
            .build()
    }
}
