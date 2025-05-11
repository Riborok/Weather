package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationListRepository {
    fun getSavedLocations(): Flow<List<LocationModel>>
    suspend fun saveLocation(location: LocationModel)
    suspend fun removeLocation(location: LocationModel)
    suspend fun updateLocation(oldLocation: LocationModel, newLocation: LocationModel)
}
