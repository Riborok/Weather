package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.ForecastLocationModel
import kotlinx.coroutines.flow.Flow

interface ForecastLocationRepository {
    suspend fun updateForecastLocation(forecastLocation: ForecastLocationModel)
    fun getForecastLocation(): Flow<ForecastLocationModel?>
}
