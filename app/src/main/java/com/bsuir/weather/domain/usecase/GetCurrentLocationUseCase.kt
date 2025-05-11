package com.bsuir.weather.domain.usecase

import android.location.Location
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val currentLocationRepository: CurrentLocationRepository
) {
    suspend fun getCurrentCoordinates(): Coordinates? {
        return currentLocationRepository.fetchCurrentCoordinates()
    }

    suspend fun getCurrentLocation(): LocationModel? {
        return currentLocationRepository.fetchCurrentLocation()
    }
}