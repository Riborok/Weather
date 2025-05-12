package com.bsuir.weather.data.dto.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    val model: String,
    val messages: List<ChatMessageRequest>,
    val temperature: Double,
    @SerialName("top_p") val topP: Double,
    @SerialName("top_k") val topK: Int,
    @SerialName("max_tokens") val maxTokens: Int,
    @SerialName("repetition_penalty") val repetitionPenalty: Double
)

@Serializable
data class ChatMessageRequest(
    val role: String,
    val content: String
)
