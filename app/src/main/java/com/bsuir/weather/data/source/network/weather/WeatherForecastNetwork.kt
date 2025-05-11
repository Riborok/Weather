package com.bsuir.weather.data.source.network.weather

import android.content.Context
import com.bsuir.weather.R
import com.bsuir.weather.data.dto.WeatherResponse
import com.bsuir.weather.data.source.network.weather.utils.WeatherForecastRequestBuilder.Companion.weatherForecast
import com.bsuir.weather.data.source.network.weather.utils.WeatherParameters
import com.bsuir.weather.domain.model.Coordinates
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
        coords: Coordinates,
        forecastDays: Int
    ): WeatherResponse {
        val response = makeWeatherForecastRequest(coords, forecastDays)
        return handleWeatherForecastResponse(response)
    }

    private suspend fun makeWeatherForecastRequest(
        coords: Coordinates,
        forecastDays: Int
    ): HttpResponse {
        return http.get("https://api.open-meteo.com/v1/forecast",
            buildWeatherForecastRequest(coords, forecastDays))
    }

    private fun buildWeatherForecastRequest(
        coords: Coordinates,
        forecastDays: Int
    ): HttpRequestBuilder.() -> Unit {
        return {
            weatherForecast {
                setCoordinates(coords)
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
            val errorMessage = context.getString(R.string.open_meteo_error)
            throw NetworkRequestException(errorMessage, status)
        }
        return response.body()
    }
}
