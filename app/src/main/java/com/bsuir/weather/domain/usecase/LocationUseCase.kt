package com.bsuir.weather.domain.usecase

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationRepository
import com.bsuir.weather.utils.GeocoderUtils.getLocationModel
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

    fun fetchCurrentLocation(
        context: Context,
        setCurrentLocationCallback: (LocationModel?) -> Unit
    ) {
        if (!hasLocationPermission(context)) {
            setCurrentLocationCallback(null)
            return
        }

        val fusedLocationClient = getFusedLocationProviderClient(context)
        requestLastKnownLocation(fusedLocationClient, context, setCurrentLocationCallback)
    }

    private fun hasLocationPermission(context: Context): Boolean {
        val fineLocationGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return fineLocationGranted || coarseLocationGranted
    }

    @SuppressLint("MissingPermission")
    private fun requestLastKnownLocation(
        fusedLocationClient: FusedLocationProviderClient,
        context: Context,
        setCurrentLocationCallback: (LocationModel?) -> Unit
    ) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    getLocationModel(
                        context = context,
                        latitude = location.latitude,
                        longitude = location.longitude,
                        onResult = setCurrentLocationCallback,
                    )
                } else {
                    setCurrentLocationCallback(null)
                }
            }
            .addOnFailureListener {
                setCurrentLocationCallback(null)
            }
    }
}
