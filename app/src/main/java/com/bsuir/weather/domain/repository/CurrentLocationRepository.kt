package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.LocationModel

interface CurrentLocationRepository {
    suspend fun getCurrentLocation(): LocationModel?
}
