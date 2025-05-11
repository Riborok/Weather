package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.ForecastModel

interface ForecastRepository {
    suspend fun fetchForecast(coords: Coordinates): ForecastModel
}
