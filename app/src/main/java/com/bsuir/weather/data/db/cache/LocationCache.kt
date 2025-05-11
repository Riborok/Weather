package com.bsuir.weather.data.db.cache

import com.bsuir.weather.data.db.dao.LocationDao
import com.bsuir.weather.data.db.entity.LocationEntity
import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.utils.mapper.CoordinatesMapper.toModel
import javax.inject.Inject

class LocationCache @Inject constructor(
    private val locationDao: LocationDao
) {
    companion object {
        private const val CACHE_LIMIT = 500
        private const val COORDINATE_PRECISION = 2
    }

    private fun generateCacheKey(coords: Coordinates): String {
        val roundedLatitude = "%.${COORDINATE_PRECISION}f".format(coords.latitude)
        val roundedLongitude = "%.${COORDINATE_PRECISION}f".format(coords.longitude)
        return "$roundedLatitude,$roundedLongitude"
    }

    suspend fun get(coords: Coordinates): LocationDTO? {
        return locationDao.getLocation(generateCacheKey(coords))?.location
    }

    suspend fun save(location: LocationDTO) {
        locationDao.insertLocation(
            LocationEntity(
                id = generateCacheKey(location.coordinates.toModel()),
                location = location,
                timestamp = System.currentTimeMillis()
            )
        )
        locationDao.deleteExcessLocations(CACHE_LIMIT)
    }
}
