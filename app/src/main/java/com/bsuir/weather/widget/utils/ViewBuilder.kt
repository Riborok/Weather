package com.bsuir.weather.widget.utils

import android.content.Context
import android.widget.RemoteViews
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.utils.forecast.ForecastUtils.getHourlyForecastSubset

class ViewBuilder(private val context: Context) {

    private val formatter = WeatherStringFormatter(context)

    fun createView(
        forecastLocation: ForecastLocationModel
    ): RemoteViews {
        val location = formatter.formatLocation(forecastLocation.location.address)
        val current = forecastLocation.forecast.currentForecast
        val hourly = forecastLocation.forecast.hourlyForecasts

        return RemoteViews(context.packageName, R.layout.weather).apply {
            setCurrentWeatherViews(location, current)
            setHourlyForecastViews(hourly)
        }
    }

    private fun RemoteViews.setCurrentWeatherViews(
        location: String,
        current: CurrentForecastModel
    ) = with(formatter) {
        setTextViewText(R.id.tv_location, location)
        setTextViewText(R.id.tv_current_temp, formatTemperature(current.temperature))
        setTextViewText(R.id.tv_feels_like, formatFeelsLike(current.apparentTemperature))
        setTextViewText(R.id.tv_description, formatDescription(current.weatherDescriptionId))
        setImageViewResource(R.id.iv_current_icon, current.iconId)
        setTextViewText(R.id.tv_widget_wind, formatWindInfo(current.windSpeed, current.windGusts))
    }

    private fun RemoteViews.setHourlyForecastViews(
        hourly: List<HourlyForecastModel>
    ) = with(formatter) {
        removeAllViews(R.id.hourly_container)
        getHourlyForecastSubset(hourly, 6).forEach { hour ->
            val item = RemoteViews(context.packageName, R.layout.weather_hourly_item)
            item.setTextViewText(R.id.tv_hour, formatHour(hour.time))
            item.setTextViewText(R.id.tv_temp, formatTemperature(hour.temperature))
            item.setImageViewResource(R.id.iv_icon, hour.iconId)
            addView(R.id.hourly_container, item)
        }
    }
}