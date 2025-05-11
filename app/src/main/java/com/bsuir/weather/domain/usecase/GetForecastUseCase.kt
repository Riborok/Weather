package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    suspend fun getForecast(coords: Coordinates): ForecastModel {
        return forecastRepository.fetchForecast(coords)
    }
}
