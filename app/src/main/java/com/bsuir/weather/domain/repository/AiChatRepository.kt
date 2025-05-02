package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.ForecastLocationModel

interface AiChatRepository {
    suspend fun askWeatherAI(forecastLocation: ForecastLocationModel, userRequest: String): String
}
