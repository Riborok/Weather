package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.WeatherLocationModel

interface AiChatRepository {
    suspend fun askWeatherAI(weatherLocation: WeatherLocationModel, userRequest: String): String
}
