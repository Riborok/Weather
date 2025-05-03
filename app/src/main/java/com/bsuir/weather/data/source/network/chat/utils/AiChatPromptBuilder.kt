package com.bsuir.weather.data.source.network.chat.utils

import android.content.Context
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.utils.LocaleUtils.currentLocale
import java.util.Locale

object AiChatPromptBuilder {
    fun buildWeatherPrompt(forecastLocation: ForecastLocationModel, context: Context): String {
        val forecast = forecastLocation.forecast
        val location = forecastLocation.location
        val locale = context.currentLocale

        return buildString {
            appendLine("You are a weather expert. Answer questions using complete information about the weather forecast.")
            appendLine("Below are the data provided to assist you in answering:")

            appendLine()
            append(buildLocationSection(location))
            appendLine()
            append(buildForecastSection(forecast, context))
            appendLine()
            append(buildLocaleSection(locale))
        }
    }

    private fun buildLocationSection(location: LocationModel): String {
        return buildString {
            appendLine("Location details:")
            appendLine("• Latitude: ${location.latitude}")
            appendLine("• Longitude: ${location.longitude}")
            appendLine("• Address: ${location.address.fullAddress()}")
        }
    }

    private fun buildForecastSection(forecast: ForecastModel, context: Context): String {
        val current = forecast.currentForecast
        val hourly = forecast.hourlyForecasts
        val daily = forecast.dailyForecasts

        return buildString {
            appendLine("Current weather:")
            appendLine("• Temperature: ${current.temperature}°C (feels like ${current.apparentTemperature}°C)")
            appendLine("• Weather description: ${context.getString(current.weatherDescriptionId)}")
            appendLine("• Wind: ${current.windSpeed} m/s, direction: ${context.getString(current.windDirectionId)}")
            appendLine("• Pressure: ${current.surfacePressure} hPa")
            appendLine("• Humidity: ${current.relativeHumidity}%")
            appendLine("• Time: ${current.time}")
            appendLine()

            appendLine("Hourly forecast:")
            hourly.forEach {
                appendLine("• Time: ${it.time}, Temperature: ${it.temperature}°C, Weather description: ${context.getString(it.weatherDescriptionId)}")
            }
            appendLine()

            appendLine("Daily forecast:")
            daily.forEach {
                appendLine("• Date: ${it.date}, Minimum Temperature: ${it.minTemperature}°C, Maximum Temperature: ${it.maxTemperature}°C")
                appendLine("• Sunrise: ${it.sunrise}, Sunset: ${it.sunset}")
                appendLine("• Weather description: ${context.getString(it.weatherDescriptionId)}")
            }
        }
    }

    private fun buildLocaleSection(locale: Locale): String {
        return "Provide the answer in the language: ${locale.displayLanguage} (${locale.language})."
    }
}