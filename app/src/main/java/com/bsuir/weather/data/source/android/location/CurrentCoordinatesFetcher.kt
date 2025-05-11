package com.bsuir.weather.data.source.android.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.bsuir.weather.domain.model.Coordinates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine

class CurrentCoordinatesFetcher(
    private val context: Context,
    private val fusedClient: FusedLocationProviderClient
) {
    suspend fun fetchCurrentCoordinates(): Coordinates? =
        suspendCancellableCoroutine { cont ->
            val cts = CancellationTokenSource()
            fetchCurrentCoordinates(cts.token) { location ->
                if (cont.isActive) {
                    cont.resume(location) {}
                }
            }
            cont.invokeOnCancellation {
                cts.cancel()
            }
        }

    private fun fetchCurrentCoordinates(
        ct: CancellationToken,
        setCurrentCoordinatesCallback: (Coordinates?) -> Unit,
    ) {
        if (hasLocationPermission()) {
            requestCurrentCoordinates(ct, setCurrentCoordinatesCallback)
        } else if (!ct.isCancellationRequested) {
            setCurrentCoordinatesCallback(null)
        }
    }

    private fun hasLocationPermission(): Boolean {
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
    private fun requestCurrentCoordinates(
        ct: CancellationToken,
        setCurrentCoordinatesCallback: (Coordinates?) -> Unit
    ) {
        fusedClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            ct
        )
            .addOnSuccessListener { location ->
                if (location != null) {
                    setCurrentCoordinatesCallback(
                        Coordinates(
                            latitude = location.latitude,
                            longitude = location.longitude
                        )
                    )
                } else {
                    setCurrentCoordinatesCallback(null)
                }
            }
            .addOnFailureListener {
                setCurrentCoordinatesCallback(null)
            }
    }
}