package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel

interface LocationFromCoordinatesRepository {
    suspend fun fetchLocationFromCoordinates(coords: Coordinates): LocationModel
}
