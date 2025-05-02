package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.ForecastModel

interface AiChatRepository {
    suspend fun askWeatherAI(forecast: ForecastModel, userRequest: String): String
}
