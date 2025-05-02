package com.bsuir.weather.data.source.network

import android.content.Context
import com.bsuir.weather.BuildConfig
import com.bsuir.weather.R
import com.bsuir.weather.data.dto.chat.ChatMessageRequest
import com.bsuir.weather.data.dto.chat.ChatRequest
import com.bsuir.weather.data.dto.chat.ChatResponse
import com.bsuir.weather.domain.model.WeatherLocationModel
import com.bsuir.weather.exception.NetworkRequestException
import com.bsuir.weather.utils.LocaleUtils.currentLocale
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import javax.inject.Inject

class AiChatNetwork @Inject constructor(
    private val http: HttpClient,
    private val context: Context
) {
    suspend fun askWeatherAI(weatherLocation: WeatherLocationModel, userRequest: String): ChatResponse {
        val response = makeAIRequest(weatherLocation, userRequest)
        return handleAIResponse(response)
    }

    private suspend fun makeAIRequest(weatherLocation: WeatherLocationModel, userRequest: String): HttpResponse {
        val systemPrompt = buildWeatherPrompt(weatherLocation)
        return http.post("https://api.aimlapi.com/v1/chat/completions") {
            contentType(ContentType.Application.Json)

            headers.append("Authorization", "Bearer ${BuildConfig.AI_API_KEY}")

            setBody(
                ChatRequest(
                    model = "gpt-4o",
                    messages = listOf(
                        ChatMessageRequest(role = "system", content = systemPrompt),
                        ChatMessageRequest(role = "user", content = userRequest)
                    ),
                    temperature = 0.3,
                    topP = 0.95,
                    topK = 35,
                    maxTokens = 300,
                    repetitionPenalty = 1.5
                )
            )
        }
    }

    private suspend fun handleAIResponse(response: HttpResponse): ChatResponse {
        val status = response.status
        if (!status.isSuccess()) {
            val errorMessage = context.getString(R.string.ai_api_error, status.value.toString())
            throw NetworkRequestException(errorMessage, status)
        }
        return response.body()
    }

    private fun buildWeatherPrompt(weatherLocation: WeatherLocationModel): String {
        val forecast = weatherLocation.forecast
        val current = forecast.currentForecastModel
        val hourly = forecast.hourlyForecastModels
        val daily = forecast.dailyForecastModels

        val location = weatherLocation.location
        val address = location.address

        val locale = context.currentLocale
        val languageName = locale.displayLanguage
        val languageCode = locale.language

        return buildString {
            appendLine("You are a weather expert. Answer questions using complete information about the weather forecast.")
            appendLine("Below are the data provided to assist you in answering:")

            appendLine()
            appendLine("Location details:")
            appendLine("• Latitude: ${location.latitude}")
            appendLine("• Longitude: ${location.longitude}")
            appendLine("• Address: ${address.getFullAddress()}")
            appendLine()

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

            appendLine()
            appendLine("Provide the answer in the language: $languageName ($languageCode).")
        }
    }
}
