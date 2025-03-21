package com.bsuir.weather.data.source.network

import com.bsuir.weather.data.dto.WeatherResponse
import com.bsuir.weather.data.source.network.WeatherForecastRequestBuilder.Companion.weatherForecast
import com.bsuir.weather.exception.NetworkRequestException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import javax.inject.Inject

class WeatherForecastNetwork @Inject constructor(
    private val http: HttpClient
) {
    suspend fun getForecastList(latitude: Double, longitude: Double, forecastDays: Int): WeatherResponse {
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

                addDailyParam("sunset")
                addDailyParam("sunrise")
                addDailyParam("weather_code")
                addDailyParam("apparent_temperature_min")
                addDailyParam("temperature_2m_max")

                addHourlyParam("temperature_2m")
                addHourlyParam("weather_code")

                addCurrentParam("temperature_2m")
                addCurrentParam("weather_code")
                addCurrentParam("wind_speed_10m")
                addCurrentParam("relative_humidity_2m")
                addCurrentParam("wind_direction_10m")
                addCurrentParam("apparent_temperature")
                addCurrentParam("is_day")
                addCurrentParam("surface_pressure")
            }
        }
    }

    private suspend fun handleWeatherForecastResponse(response: HttpResponse): WeatherResponse {
        val status = response.status
        if (!status.isSuccess()) {
            throw NetworkRequestException("Server returned an error", status)
        }
        return response.body()
    }
}
