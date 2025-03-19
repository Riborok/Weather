package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.repository.LocationRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    fun getCurrentLocation(): String {
        return locationRepository.getCurrentLocation()
    }

    fun getSavedLocations(): List<String> {
        return locationRepository.getSavedLocations()
    }
}
