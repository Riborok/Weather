package com.bsuir.weather.data.db.cache

import com.bsuir.weather.data.db.dao.LocationDao
import com.bsuir.weather.data.db.entity.LocationEntity
import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.utils.TimeUtils.minutesToMillis
import com.bsuir.weather.utils.mapper.CoordinatesMapper.toModel
import javax.inject.Inject

class LocationCache @Inject constructor(
    private val locationDao: LocationDao
) {
    companion object {
        private const val COORDINATE_PRECISION = 4

        private val CACHE_DURATION = minutesToMillis(30L)
        private const val CURRENT_LOCATION_KEY = "current_location"
    }

    private fun generateCacheKey(coords: Coordinates): String {
        val roundedLatitude = "%.${COORDINATE_PRECISION}f".format(coords.latitude)
        val roundedLongitude = "%.${COORDINATE_PRECISION}f".format(coords.longitude)
        return "$roundedLatitude,$roundedLongitude"
    }

    suspend fun get(coords: Coordinates): LocationDTO? {
        val currentTime = System.currentTimeMillis()
        val minValidTime = currentTime - CACHE_DURATION
        return locationDao.getLocation(generateCacheKey(coords), minValidTime)?.location
    }

    suspend fun save(location: LocationDTO) {
        val currentTime = System.currentTimeMillis()
        locationDao.insertLocation(
            LocationEntity(
                id = generateCacheKey(location.coordinates.toModel()),
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
