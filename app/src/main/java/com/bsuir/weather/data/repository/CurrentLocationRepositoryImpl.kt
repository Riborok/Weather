package com.bsuir.weather.data.repository

import com.bsuir.weather.data.db.cache.LocationCache
import com.bsuir.weather.data.source.android.location.CurrentCoordinatesFetcher
import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.utils.mapper.LocationMapper.toDTO
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import javax.inject.Inject

class CurrentLocationRepositoryImpl @Inject constructor(
    private val currentCoordinatesFetcher: CurrentCoordinatesFetcher,
    private val locationFetcher: LocationFetcher,
    private val locationCache: LocationCache
) : CurrentLocationRepository {
    override suspend fun fetchCurrentCoordinates(): Coordinates? {
        return currentCoordinatesFetcher.fetchCurrentCoordinates()
    }

    override suspend fun fetchCurrentLocation(): LocationModel? {
        val coords = currentCoordinatesFetcher.fetchCurrentCoordinates() ?: return null
        return locationFetcher.fetchLocationFromCoordinates(coords)
    }

    override suspend fun fetchCachedCurrentLocation(): LocationModel? {
        locationCache.getCurrentLocation()?.let { cachedLocation ->
            return cachedLocation.toModel()
        }

        val coords = currentCoordinatesFetcher.fetchCurrentCoordinates() ?: return null
        val locationModel = locationFetcher.fetchLocationFromCoordinates(coords)

        locationCache.saveCurrentLocation(locationModel.toDTO())

        return locationModel
    }
}
