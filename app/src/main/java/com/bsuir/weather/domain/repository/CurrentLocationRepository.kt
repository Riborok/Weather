package com.bsuir.weather.domain.repository

import android.location.Location
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel

interface CurrentLocationRepository {
    suspend fun fetchCurrentCoordinates(): Coordinates?
    suspend fun fetchCurrentLocation(): LocationModel?
    suspend fun fetchCachedCurrentLocation(): LocationModel?
}
