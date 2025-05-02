package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.repository.AiChatRepository
import javax.inject.Inject

class AskAiChatUseCase @Inject constructor(
    private val aiChatRepository: AiChatRepository
) {
    suspend fun askWeatherAI(forecast: ForecastModel, userRequest: String): String {
        return aiChatRepository.askWeatherAI(forecast, userRequest)
    }
}
