package com.bsuir.weather.data.repository

import com.bsuir.weather.data.db.cache.CoordinatesCache
import com.bsuir.weather.data.db.cache.LocationCache
import com.bsuir.weather.data.source.android.location.CurrentCoordinatesFetcher
import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.domain.repository.LocationFromCoordinatesRepository
import com.bsuir.weather.utils.mapper.CoordinatesMapper.toDTO
import com.bsuir.weather.utils.mapper.CoordinatesMapper.toModel
import com.bsuir.weather.utils.mapper.LocationMapper.toDTO
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import javax.inject.Inject

class CurrentLocationRepositoryImpl @Inject constructor(
    private val currentCoordinatesFetcher: CurrentCoordinatesFetcher,
    private val coordinatesCache: CoordinatesCache,
    private val locationFromCoordinatesRepository: LocationFromCoordinatesRepository,
) : CurrentLocationRepository {
    override suspend fun fetchCurrentCoordinates(): Coordinates? {
        coordinatesCache.get()?.let {
            return it.toModel()
        }

        return currentCoordinatesFetcher.fetchCurrentCoordinates()?.also {
            coordinatesCache.save(it.toDTO())
        }
    }

    override suspend fun fetchCurrentLocation(): LocationModel? {
        val coords = fetchCurrentCoordinates() ?: return null
        return locationFromCoordinatesRepository.fetchLocationFromCoordinates(coords)
    }
}
