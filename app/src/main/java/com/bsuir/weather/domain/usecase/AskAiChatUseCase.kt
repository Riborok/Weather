package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.repository.AiChatRepository
import javax.inject.Inject

class AskAiChatUseCase @Inject constructor(
    private val aiChatRepository: AiChatRepository
) {
    suspend fun askWeatherAI(forecastLocation: ForecastLocationModel, userRequest: String): String {
        return aiChatRepository.askWeatherAI(forecastLocation, userRequest)
    }
}
