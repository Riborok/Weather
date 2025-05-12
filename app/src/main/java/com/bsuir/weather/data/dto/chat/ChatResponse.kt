package com.bsuir.weather.data.dto.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ChatResponse(
    val id: String,
    @SerialName("object") val obj: String,
    val choices: List<ChatChoiceResponse>,
    val created: Long,
    val model: String,
    val usage: UsageInfoResponse,
    @SerialName("system_fingerprint") val systemFingerprint: String
)

@Serializable
data class ChatChoiceResponse(
    val index: Int,
    @SerialName("finish_reason") val finishReason: String,
    val logprobs: JsonElement? = null,
    val message: ChatMessageResponse
)

@Serializable
data class ChatMessageResponse(
    val role: String,
    val content: String,
    val refusal: JsonElement? = null,
    val annotations: List<JsonElement> = emptyList()
)

@Serializable
data class UsageInfoResponse(
    @SerialName("prompt_tokens") val promptTokens: Int,
    @SerialName("completion_tokens") val completionTokens: Int,
    @SerialName("total_tokens") val totalTokens: Int,
    @SerialName("prompt_tokens_details") val promptTokensDetails: TokenDetailsResponse,
    @SerialName("completion_tokens_details") val completionTokensDetails: TokenDetailsResponse
)

@Serializable
data class TokenDetailsResponse(
    @SerialName("cached_tokens") val cachedTokens: Int? = null,
    @SerialName("audio_tokens") val audioTokens: Int? = null,
    @SerialName("reasoning_tokens") val reasoningTokens: Int? = null,
    @SerialName("accepted_prediction_tokens") val acceptedPredictionTokens: Int? = null,
    @SerialName("rejected_prediction_tokens") val rejectedPredictionTokens: Int? = null
)
