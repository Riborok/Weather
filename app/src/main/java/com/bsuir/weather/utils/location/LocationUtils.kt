package com.bsuir.weather.utils.location

import android.content.Context
import android.location.Address
import android.location.Geocoder.GeocodeListener
import android.os.Build
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.ext.weatherAppContext
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine

object LocationUtils {
    suspend fun fetchLocationFromCoordinates(
        context: Context,
        latitude: Double,
        longitude: Double
    ): LocationModel = suspendCancellableCoroutine { cont ->
        val cts = CancellationTokenSource()

        fetchLocationFromCoordinates(
            context   = context,
            latitude  = latitude,
            longitude = longitude,
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

    fun fetchLocationFromCoordinates(
        context: Context,
        latitude: Double,
        longitude: Double,
        ct: CancellationToken,
        onResult: (LocationModel) -> Unit
    ) {
        if (ct.isCancellationRequested) return
        fetchAddressFromCoordinates(
            context = context,
            latitude = latitude,
            longitude = longitude,
            onResult = { address ->
                if (!ct.isCancellationRequested) {
                    onResult(address?.toModel() ?: LocationModel(latitude, longitude))
                }
            },
            onError = {
                if (!ct.isCancellationRequested) {
                    onResult(LocationModel(latitude, longitude))
                }
            },
        )
    }

    private fun fetchAddressFromCoordinates(
        context: Context,
        latitude: Double,
        longitude: Double,
        onResult: (Address?) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        val geocoder = context.weatherAppContext.geocoder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(latitude, longitude, 1, object : GeocodeListener {
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
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
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