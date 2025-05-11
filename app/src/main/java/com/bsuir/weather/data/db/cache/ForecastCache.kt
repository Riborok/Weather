package com.bsuir.weather.data.db.cache

import com.bsuir.weather.data.db.dao.ForecastDao
import com.bsuir.weather.data.dto.ForecastDTO
import com.bsuir.weather.data.entity.ForecastEntity
import javax.inject.Inject

class ForecastCache @Inject constructor(
    private val forecastDao: ForecastDao
) {
    companion object {
        private const val CACHE_DURATION = 30 * 60 * 1000L
        private const val COORDINATE_PRECISION = 4
    }

    private fun generateCacheKey(latitude: Double, longitude: Double): String {
        val roundedLatitude = "%.${COORDINATE_PRECISION}f".format(latitude)
        val roundedLongitude = "%.${COORDINATE_PRECISION}f".format(longitude)
        return "$roundedLatitude,$roundedLongitude"
    }

    suspend fun get(latitude: Double, longitude: Double): ForecastDTO? {
        val currentTime = System.currentTimeMillis()
        val minValidTime = currentTime - CACHE_DURATION
        return forecastDao.getForecast(generateCacheKey(latitude, longitude), minValidTime)?.forecast
    }

    suspend fun save(latitude: Double, longitude: Double, forecast: ForecastDTO) {
        val currentTime = System.currentTimeMillis()
        forecastDao.insertForecast(
            ForecastEntity(
                id = generateCacheKey(latitude, longitude),
                forecast = forecast,
                timestamp = currentTime
            )
        )
        forecastDao.deleteOldForecasts(currentTime - CACHE_DURATION)
    }
}
