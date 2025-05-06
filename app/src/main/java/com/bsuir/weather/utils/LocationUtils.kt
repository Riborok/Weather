package com.bsuir.weather.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.AddressUtils.fetchLocationModelFromCoordinates
import com.bsuir.weather.utils.ext.weatherAppContext
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine

object LocationUtils {
    suspend fun fetchCurrentLocation(context: Context): LocationModel? =
        suspendCancellableCoroutine { cont ->
            val cts = CancellationTokenSource()
            fetchCurrentLocation(context, cts.token) { location ->
                if (cont.isActive) {
                    cont.resume(location) {}
                }
            }
            cont.invokeOnCancellation {
                cts.cancel()
            }
        }

    fun fetchCurrentLocation(
        context: Context,
        ct: CancellationToken,
        setCurrentLocationCallback: (LocationModel?) -> Unit,
    ) {
        if (!hasLocationPermission(context)) {
            setCurrentLocationCallback(null)
            return
        }
        requestLastKnownLocation(context, ct, setCurrentLocationCallback)
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
        ct: CancellationToken,
        setCurrentLocationCallback: (LocationModel?) -> Unit
    ) {
        val fusedClient = context.weatherAppContext.fusedLocationClient
        fusedClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            ct
        )
            .addOnSuccessListener { location ->
                if (location != null) {
                    fetchLocationModelFromCoordinates(
                        context = context,
                        latitude = location.latitude,
                        longitude = location.longitude,
                        onResult = setCurrentLocationCallback,
                        ct = ct
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