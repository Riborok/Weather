package com.bsuir.weather.data.source.network.weather

import android.content.Context
import com.bsuir.weather.R
import com.bsuir.weather.data.dto.WeatherResponse
import com.bsuir.weather.data.source.network.weather.WeatherForecastRequestBuilder.Companion.weatherForecast
import com.bsuir.weather.exception.NetworkRequestException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import javax.inject.Inject

class WeatherForecastNetwork @Inject constructor(
    private val http: HttpClient,
    private val context: Context
) {
    suspend fun getForecastList(
        latitude: Double,
        longitude: Double,
        forecastDays: Int
    ): WeatherResponse {
        val response = makeWeatherForecastRequest(latitude, longitude, forecastDays)
        return handleWeatherForecastResponse(response)
    }

    private suspend fun makeWeatherForecastRequest(
        latitude: Double,
        longitude: Double,
        forecastDays: Int
    ): HttpResponse {
        return http.get("https://api.open-meteo.com/v1/forecast",
            buildWeatherForecastRequest(latitude, longitude, forecastDays))
    }

    private fun buildWeatherForecastRequest(
        latitude: Double,
        longitude: Double,
        forecastDays: Int
    ): HttpRequestBuilder.() -> Unit {
        return {
            weatherForecast {
                setCoordinates(latitude, longitude)
                setForecastDays(forecastDays)
                setTimezone("Europe/Moscow")

                addDailyParams(WeatherParameters.dailyParameters)
                addHourlyParams(WeatherParameters.hourlyParameters)
                addCurrentParams(WeatherParameters.currentParameters)
            }
        }
    }

    private suspend fun handleWeatherForecastResponse(response: HttpResponse): WeatherResponse {
        val status = response.status
        if (!status.isSuccess()) {
            val errorMessage = context.getString(R.string.open_meteo_error, status.value.toString())
            throw NetworkRequestException(errorMessage, status)
        }
        return response.body()
    }
}
