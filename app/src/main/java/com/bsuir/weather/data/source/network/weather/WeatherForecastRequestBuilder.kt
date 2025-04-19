package com.bsuir.weather.data.source.network.weather

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

class WeatherForecastRequestBuilder private constructor(private val builder: HttpRequestBuilder) {
    private val dailyParams = mutableListOf<String>()
    private val hourlyParams = mutableListOf<String>()
    private val currentParams = mutableListOf<String>()

    companion object {
        fun HttpRequestBuilder.weatherForecast(block: WeatherForecastRequestBuilder.() -> Unit) {
            WeatherForecastRequestBuilder(this).apply {
                block()
                applyParameters()
            }
        }
    }

    private fun applyParameters() {
        if (dailyParams.isNotEmpty()) {
            builder.parameter("daily", dailyParams.joinToString(","))
        }
        if (hourlyParams.isNotEmpty()) {
            builder.parameter("hourly", hourlyParams.joinToString(","))
        }
        if (currentParams.isNotEmpty()) {
            builder.parameter("current", currentParams.joinToString(","))
        }
    }

    fun setCoordinates(latitude: Double, longitude: Double): WeatherForecastRequestBuilder = apply {
        builder.parameter("latitude", latitude)
        builder.parameter("longitude", longitude)
    }

    fun addDailyParam(param: String): WeatherForecastRequestBuilder = apply {
        dailyParams.add(param)
    }

    fun addHourlyParam(param: String): WeatherForecastRequestBuilder = apply {
        hourlyParams.add(param)
    }

    fun addCurrentParam(param: String): WeatherForecastRequestBuilder = apply {
        currentParams.add(param)
    }

    fun addDailyParams(params: List<String>): WeatherForecastRequestBuilder = apply {
        dailyParams.addAll(params)
    }

    fun addHourlyParams(params: List<String>): WeatherForecastRequestBuilder = apply {
        hourlyParams.addAll(params)
    }

    fun addCurrentParams(params: List<String>): WeatherForecastRequestBuilder = apply {
        currentParams.addAll(params)
    }

    fun setForecastDays(days: Int): WeatherForecastRequestBuilder = apply {
        builder.parameter("forecast_days", days)
    }

    fun setTimezone(timezone: String): WeatherForecastRequestBuilder = apply {
        builder.parameter("timezone", timezone)
    }
}
