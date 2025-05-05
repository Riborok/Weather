package com.bsuir.weather.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.AddressUtils.fetchLocationModelFromCoordinates
import com.bsuir.weather.utils.ext.weatherAppContext

object LocationUtility {
    fun fetchCurrentLocation(
        context: Context,
        setCurrentLocationCallback: (LocationModel?) -> Unit
    ) {
        if (!hasLocationPermission(context)) {
            setCurrentLocationCallback(null)
            return
        }
        requestLastKnownLocation(context, setCurrentLocationCallback)
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
        context: Context,
        setCurrentLocationCallback: (LocationModel?) -> Unit
    ) {
        val fusedLocationClient = context.weatherAppContext.fusedLocationClient
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    fetchLocationModelFromCoordinates(
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