package com.bsuir.weather.data.db.cache

import com.bsuir.weather.data.db.dao.CoordinatesDao
import com.bsuir.weather.data.db.entity.CoordinatesEntity
import com.bsuir.weather.data.dto.CoordinatesDTO
import com.bsuir.weather.utils.TimeUtils.minutesToMillis
import javax.inject.Inject

class CoordinatesCache @Inject constructor(
    private val coordinatesDao: CoordinatesDao
) {
    companion object {
        private val CACHE_DURATION = minutesToMillis(5L)
        private const val CURRENT_COORDINATES_KEY = "current_coordinates"
    }

    suspend fun getCoordinates(): CoordinatesDTO? {
        val currentTime = System.currentTimeMillis()
        val minValidTime = currentTime - CACHE_DURATION
        return coordinatesDao.getCoordinates(CURRENT_COORDINATES_KEY, minValidTime)?.coordinates
    }

    suspend fun saveCoordinates(coordinates: CoordinatesDTO) {
        val currentTime = System.currentTimeMillis()
        coordinatesDao.insertCoordinates(
            CoordinatesEntity(
                id = CURRENT_COORDINATES_KEY,
                coordinates = coordinates,
                timestamp = currentTime
            )
        )
        coordinatesDao.deleteOldCoordinates(currentTime - CACHE_DURATION)
    }
}
