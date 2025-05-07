package com.bsuir.weather.data.repository

import android.content.Context
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.utils.location.CurrentLocationUtils.fetchCurrentLocation
import javax.inject.Inject

class CurrentLocationRepositoryImpl @Inject constructor(
    private val context: Context
) : CurrentLocationRepository {
    override suspend fun getCurrentLocation(): LocationModel? {
        return fetchCurrentLocation(context)
    }
}