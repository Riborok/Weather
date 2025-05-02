package com.bsuir.weather.data.source.network

import android.content.Context
import android.util.Log
import com.bsuir.weather.data.dto.chat.ChatRequest
import com.bsuir.weather.data.dto.chat.ChatResponse
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.exception.NetworkRequestException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.ContentType
import javax.inject.Inject
import com.bsuir.weather.BuildConfig.AI_API_KEY
import com.bsuir.weather.data.dto.chat.ChatMessageRequest
import io.ktor.client.statement.bodyAsText

class AiChatNetwork @Inject constructor(
    private val http: HttpClient,
    private val context: Context
) {
    suspend fun askWeatherAI(forecast: ForecastModel, userRequest: String): ChatResponse {
        val response = makeAIRequest(forecast, userRequest)
        return handleAIResponse(response)
    }

    private suspend fun makeAIRequest(forecast: ForecastModel, userRequest: String): HttpResponse {
        val systemPrompt = buildWeatherPrompt(forecast)
        return http.post("https://api.aimlapi.com/v1/chat/completions") {
            contentType(ContentType.Application.Json)

            headers.append("Authorization", "Bearer $AI_API_KEY")

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
        if (!response.status.isSuccess()) {
            throw NetworkRequestException("AI API error", response.status)
        }

        return response.body()
    }

    private fun buildWeatherPrompt(forecast: ForecastModel): String {
        val current = forecast.currentForecastModel
        val hourly = forecast.hourlyForecastModels
        val daily = forecast.dailyForecastModels
        val locale = context.resources.configuration.locales[0]
        val languageName = locale.displayLanguage
        val languageCode = locale.language

        return buildString {
            appendLine("You are a weather expert. Answer questions using complete information about the weather forecast.")
            appendLine("Below are the data provided to assist you in answering:")

            appendLine("Current weather:")
            appendLine("• Temperature: ${current.temperature}°C (feels like ${current.apparentTemperature}°C)")
            appendLine("• Weather description: ${context.getString(current.weatherDescriptionId)}")
            appendLine("• Wind: ${current.windSpeed} m/s, direction: ${current.windDirection}")
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
