package com.bsuir.weather.data.source.network.chat

import android.content.Context
import com.bsuir.weather.BuildConfig
import com.bsuir.weather.R
import com.bsuir.weather.data.dto.chat.ChatMessageRequest
import com.bsuir.weather.data.dto.chat.ChatRequest
import com.bsuir.weather.data.dto.chat.ChatResponse
import com.bsuir.weather.data.source.network.chat.utils.AiChatPromptBuilder
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.exception.NetworkRequestException
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
    suspend fun askWeatherAI(forecastLocation: ForecastLocationModel, userRequest: String): ChatResponse {
        val response = makeAIRequest(forecastLocation, userRequest)
        return handleAIResponse(response)
    }

    private suspend fun makeAIRequest(forecastLocation: ForecastLocationModel, userRequest: String): HttpResponse {
        val systemPrompt = AiChatPromptBuilder.buildWeatherPrompt(forecastLocation, context)
        return http.post("https://api.aimlapi.com/v1/chat/completions") {
            contentType(ContentType.Application.Json)

            headers.append("Authorization", "Bearer ${BuildConfig.AI_API_KEY}")

            setBody(
                ChatRequest(
                    model = "gpt-4o-mini",
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
            val errorMessage = context.getString(R.string.ai_api_error)
            throw NetworkRequestException(errorMessage, status)
        }
        return response.body()
    }
}