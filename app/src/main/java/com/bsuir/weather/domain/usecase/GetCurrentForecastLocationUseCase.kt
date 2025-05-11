package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import javax.inject.Inject

class GetCurrentForecastLocationUseCase @Inject constructor(
    private val currentLocationRepository: CurrentLocationRepository,
    private val forecastUseCase: GetForecastUseCase,
) {
    suspend fun fetchCurrentForecast(): ForecastLocationModel? {
        val location = currentLocationRepository.getCurrentLocation() ?: return null
        return try {
            val forecast = forecastUseCase.getForecast(location.latitude, location.longitude)
            ForecastLocationModel(forecast, location)
        } catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            e.printStackTrace()
            null
        }
    }
}