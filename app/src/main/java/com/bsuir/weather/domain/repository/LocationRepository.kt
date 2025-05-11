package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun updateLocation(location: LocationModel)
    fun getLocation(): Flow<LocationModel?>
}