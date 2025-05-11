package com.bsuir.weather.data.db.cache

import com.bsuir.weather.data.db.dao.LocationDao
import com.bsuir.weather.data.db.entity.LocationEntity
import com.bsuir.weather.data.dto.LocationDTO
import javax.inject.Inject

class LocationCache @Inject constructor(
    private val locationDao: LocationDao
) {
    companion object {
        private const val CACHE_DURATION = 30 * 60 * 1000L
        private const val COORDINATE_PRECISION = 4
        private const val CURRENT_LOCATION_KEY = "current_location"
    }

    private fun generateCacheKey(latitude: Double, longitude: Double): String {
        val roundedLatitude = "%.${COORDINATE_PRECISION}f".format(latitude)
        val roundedLongitude = "%.${COORDINATE_PRECISION}f".format(longitude)
        return "$roundedLatitude,$roundedLongitude"
    }

    suspend fun get(latitude: Double, longitude: Double): LocationDTO? {
        val currentTime = System.currentTimeMillis()
        val minValidTime = currentTime - CACHE_DURATION
        return locationDao.getLocation(generateCacheKey(latitude, longitude), minValidTime)?.location
    }

    suspend fun save(location: LocationDTO) {
        val currentTime = System.currentTimeMillis()
        locationDao.insertLocation(
            LocationEntity(
                id = generateCacheKey(location.latitude, location.longitude),
                location = location,
                timestamp = currentTime
            )
        )
        locationDao.deleteOldLocations(currentTime - CACHE_DURATION)
    }

    suspend fun getCurrentLocation(): LocationDTO? {
        val currentTime = System.currentTimeMillis()
        val minValidTime = currentTime - CACHE_DURATION
        return locationDao.getLocation(CURRENT_LOCATION_KEY, minValidTime)?.location
    }

    suspend fun saveCurrentLocation(location: LocationDTO) {
        val currentTime = System.currentTimeMillis()
        locationDao.insertLocation(
            LocationEntity(
                id = CURRENT_LOCATION_KEY,
                location = location,
                timestamp = currentTime
            )
        )
        locationDao.deleteOldLocations(currentTime - CACHE_DURATION)
    }
}
