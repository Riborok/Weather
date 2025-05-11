package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    suspend fun getForecast(latitude: Double, longitude: Double): ForecastModel {
        return forecastRepository.getForecast(latitude, longitude)
    }
}