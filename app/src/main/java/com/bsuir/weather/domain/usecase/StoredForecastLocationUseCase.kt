package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.repository.ForecastLocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoredForecastLocationUseCase @Inject constructor(
    private val forecastLocationRepository: ForecastLocationRepository
) {
    suspend fun updateForecastLocation(forecastLocation: ForecastLocationModel) {
        forecastLocationRepository.updateForecastLocation(forecastLocation)
    }

    fun getForecastLocation(): Flow<ForecastLocationModel?> {
        return forecastLocationRepository.getForecastLocation()
    }
}