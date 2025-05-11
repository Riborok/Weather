package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.ForecastLocationModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import javax.inject.Inject

class GetCurrentForecastLocationUseCase @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val forecastUseCase: GetForecastUseCase,
) {
    suspend fun getCurrentForecast(): ForecastLocationModel? {
        val location = getCurrentLocationUseCase.getCachedCurrentLocation() ?: return null
        return try {
            val forecast = forecastUseCase.getForecast(location.coordinates)
            ForecastLocationModel(forecast, location)
        } catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            e.printStackTrace()
            null
        }
    }
}
