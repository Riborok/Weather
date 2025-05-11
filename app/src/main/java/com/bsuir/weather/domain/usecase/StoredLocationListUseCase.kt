package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoredLocationListUseCase @Inject constructor(
    private val locationListRepository: LocationListRepository
) {
    fun getLocations(): Flow<List<LocationModel>> {
        return locationListRepository.getLocations()
    }

    suspend fun saveLocation(location: LocationModel) {
        locationListRepository.saveLocation(location)
    }

    suspend fun removeLocation(location: LocationModel) {
        locationListRepository.removeLocation(location)
    }

    suspend fun updateLocation(oldLocation: LocationModel, newLocation: LocationModel) {
        locationListRepository.updateLocation(oldLocation, newLocation)
    }
}
