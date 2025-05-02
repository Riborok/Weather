package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.WeatherLocationModel
import com.bsuir.weather.domain.repository.AiChatRepository
import javax.inject.Inject

class AskAiChatUseCase @Inject constructor(
    private val aiChatRepository: AiChatRepository
) {
    suspend fun askWeatherAI(weatherLocation: WeatherLocationModel, userRequest: String): String {
        return aiChatRepository.askWeatherAI(weatherLocation, userRequest)
    }
}
