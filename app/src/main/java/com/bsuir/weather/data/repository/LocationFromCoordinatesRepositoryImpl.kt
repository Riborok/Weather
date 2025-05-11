package com.bsuir.weather.data.repository

import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationFromCoordinatesRepository
import javax.inject.Inject

class LocationFromCoordinatesRepositoryImpl @Inject constructor(
    private val locationFetcher: LocationFetcher
) : LocationFromCoordinatesRepository {
    override suspend fun fetchLocationFromCoordinates(coords: Coordinates): LocationModel {
        return locationFetcher.fetchLocationFromCoordinates(coords)
    }
}
