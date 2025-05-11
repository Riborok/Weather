package com.bsuir.weather.data.repository

import android.content.Context
import com.bsuir.weather.data.db.cache.LocationCache
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.utils.location.CurrentLocationUtils.fetchCurrentLocation
import com.bsuir.weather.utils.mapper.LocationMapper.toDTO
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import javax.inject.Inject

class CurrentLocationRepositoryImpl @Inject constructor(
    private val context: Context,
    private val locationCache: LocationCache
) : CurrentLocationRepository {
    override suspend fun getCurrentLocation(): LocationModel? {
        locationCache.getCurrentLocation()?.let { cachedLocation ->
            return cachedLocation.toModel()
        }

        val newLocation = fetchCurrentLocation(context) ?: return null

        locationCache.saveCurrentLocation(newLocation.toDTO())

        return newLocation
    }
}
