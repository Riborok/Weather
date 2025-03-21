package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.ForecastModel

interface ForecastRepository {
    suspend fun getForecast(latitude: Double, longitude: Double): ForecastModel
}
