package com.bsuir.weather.presentation.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.Geocoder.getCityFromAddress
import com.bsuir.weather.utils.Geocoder.getCityName
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CurrentLocationViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentLocation = MutableStateFlow<LocationModel?>(null)
    val currentLocation = _currentLocation.asStateFlow()

    fun fetchCurrentLocation() {
        val context = getApplication<Application>().applicationContext

        if (!hasLocationPermission(context)) {
            _currentLocation.value = null
            return
        }

        val fusedLocationClient = getFusedLocationProviderClient(context)
        requestLastKnownLocation(fusedLocationClient, context)
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
        context: Context
    ) {
        viewModelScope.launch {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        handleLocationResult(context, location.latitude, location.longitude)
                    } else {
                        _currentLocation.value = null
                    }
                }
                .addOnFailureListener {
                    _currentLocation.value = null
                }
        }
    }

    private fun handleLocationResult(context: Context, latitude: Double, longitude: Double) {
        context.getCityName(
            latitude = latitude,
            longitude = longitude,
            onResult = { address ->
                _currentLocation.value = LocationModel(latitude, longitude, getCityFromAddress(address))
            },
            onError = {
                _currentLocation.value = LocationModel(latitude, longitude)
            }
        )
    }
}
