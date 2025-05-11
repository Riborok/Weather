package com.bsuir.weather.data.source.android.location

import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Build
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class LocationFetcher @Inject constructor(
    private val geocoder: Geocoder
) {
    suspend fun fetchLocationFromCoordinates(
        coords: Coordinates
    ): LocationModel = suspendCancellableCoroutine { cont ->
        val cts = CancellationTokenSource()

        fetchLocationFromCoordinates(
            coords  = coords,
            ct        = cts.token
        ) { model ->
            if (cont.isActive) {
                cont.resume(model) {}
            }
        }

        cont.invokeOnCancellation {
            cts.cancel()
        }
    }

    private fun fetchLocationFromCoordinates(
        coords: Coordinates,
        ct: CancellationToken,
        onResult: (LocationModel) -> Unit
    ) {
        if (ct.isCancellationRequested) return
        fetchAddressFromCoordinates(
            coords = coords,
            onResult = { address ->
                if (!ct.isCancellationRequested) {
                    onResult(address?.toModel() ?: LocationModel(coords))
                }
            },
            onError = {
                if (!ct.isCancellationRequested) {
                    onResult(LocationModel(coords))
                }
            },
        )
    }

    private fun fetchAddressFromCoordinates(
        coords: Coordinates,
        onResult: (Address?) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(coords.latitude, coords.longitude, 1, object : GeocodeListener {
                override fun onGeocode(addresses: MutableList<Address>) {
                    if (addresses.isNotEmpty()) {
                        val cityName = addresses[0]
                        onResult(cityName)
                    } else {
                        onResult(null)
                    }
                }
                override fun onError(errorMessage: String?) {
                    onError(Exception(errorMessage))
                }
            })
        } else {
            try {
                @Suppress("DEPRECATION")
                val addresses = geocoder.getFromLocation(coords.latitude, coords.longitude, 1)
                if (!addresses.isNullOrEmpty()) {
                    val cityName = addresses[0]
                    onResult(cityName)
                } else {
                    onResult(null)
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}