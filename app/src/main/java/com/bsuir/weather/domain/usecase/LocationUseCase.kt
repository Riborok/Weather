package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    fun getSavedLocations(): Flow<List<LocationModel>> {
        return locationRepository.getSavedLocations()
    }

    suspend fun saveLocation(location: LocationModel) {
        locationRepository.saveLocation(location)
    }
}
