package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationFromCoordinatesRepository
import javax.inject.Inject

class GetLocationFromCoordinatesUseCase @Inject constructor(
    private val locationFromCoordinatesRepository: LocationFromCoordinatesRepository
) {
    suspend fun getLocationFromCoordinates(coords: Coordinates): LocationModel {
        return locationFromCoordinatesRepository.fetchLocationFromCoordinates(coords)
    }
}
