package com.bsuir.weather.data.repository

import com.bsuir.weather.data.db.cache.LocationCache
import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationFromCoordinatesRepository
import com.bsuir.weather.utils.mapper.LocationMapper.toDTO
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import javax.inject.Inject

class LocationFromCoordinatesRepositoryImpl @Inject constructor(
    private val locationFetcher: LocationFetcher,
    private val locationCache: LocationCache
) : LocationFromCoordinatesRepository {
    override suspend fun fetchLocationFromCoordinates(coords: Coordinates): LocationModel {
        locationCache.get(coords)?.let {
            return it.toModel()
        }

        return locationFetcher.fetchLocationFromCoordinates(coords).also {
            locationCache.save(it.toDTO())
        }
    }
}
