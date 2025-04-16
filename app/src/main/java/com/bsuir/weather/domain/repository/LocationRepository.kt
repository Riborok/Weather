package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getSavedLocations(): Flow<List<LocationModel>>
    suspend fun saveLocation(location: LocationModel)
}
