package com.bsuir.weather.data.db.cache

import com.bsuir.weather.data.db.dao.ForecastDao
import com.bsuir.weather.data.dto.ForecastDTO
import com.bsuir.weather.data.db.entity.ForecastEntity
import com.bsuir.weather.domain.model.Coordinates
import javax.inject.Inject

class ForecastCache @Inject constructor(
    private val forecastDao: ForecastDao
) {
    companion object {
        private const val CACHE_DURATION = 30 * 60 * 1000L
        private const val COORDINATE_PRECISION = 4
    }

    private fun generateCacheKey(coords: Coordinates): String {
        val roundedLatitude = "%.${COORDINATE_PRECISION}f".format(coords.latitude)
        val roundedLongitude = "%.${COORDINATE_PRECISION}f".format(coords.longitude)
        return "$roundedLatitude,$roundedLongitude"
    }

    suspend fun get(coords: Coordinates): ForecastDTO? {
        val currentTime = System.currentTimeMillis()
        val minValidTime = currentTime - CACHE_DURATION
        return forecastDao.getForecast(generateCacheKey(coords), minValidTime)?.forecast
    }

    suspend fun save(coords: Coordinates, forecast: ForecastDTO) {
        val currentTime = System.currentTimeMillis()
        forecastDao.insertForecast(
            ForecastEntity(
                id = generateCacheKey(coords),
                forecast = forecast,
                timestamp = currentTime
            )
        )
        forecastDao.deleteOldForecasts(currentTime - CACHE_DURATION)
    }
}
