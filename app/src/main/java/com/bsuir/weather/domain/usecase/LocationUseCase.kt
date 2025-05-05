package com.bsuir.weather.domain.usecase

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationRepository
import com.bsuir.weather.utils.AddressUtils.fetchLocationModelFromCoordinates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    fun getSavedLocations(): Flow<List<LocationModel>> {
        return locationRepository.getSavedLocations()
    }

    suspend fun saveLocation(location: LocationModel) {
        locationRepository.saveLocation(location)
    }

    suspend fun removeLocation(location: LocationModel) {
        locationRepository.removeLocation(location)
    }

    suspend fun updateLocation(oldLocation: LocationModel, newLocation: LocationModel) {
        locationRepository.updateLocation(oldLocation, newLocation)
    }
}
