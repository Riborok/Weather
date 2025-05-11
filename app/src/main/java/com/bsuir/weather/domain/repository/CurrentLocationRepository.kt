package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel

interface CurrentLocationRepository {
    suspend fun fetchCurrentCoordinates(): Coordinates?
    suspend fun fetchCurrentLocation(): LocationModel?
}
