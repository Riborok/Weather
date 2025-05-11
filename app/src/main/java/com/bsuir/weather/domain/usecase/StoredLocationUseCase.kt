package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoredLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend fun updateLocation(location: LocationModel) {
        locationRepository.updateLocation(location)
    }

    fun getLocation(): Flow<LocationModel?> {
        return locationRepository.getLocation()
    }
}