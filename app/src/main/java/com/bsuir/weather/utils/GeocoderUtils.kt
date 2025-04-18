package com.bsuir.weather.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder.GeocodeListener
import android.os.Build
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object GeocoderUtils {
    suspend fun Context.getAddressNamesByQuery(
        query: String,
        sessionToken: AutocompleteSessionToken,
        onResult: (List<String>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        try {
            val placesClient = weatherAppContext.placesClient

            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .setSessionToken(sessionToken)
                .build()

            val predictions = withContext(Dispatchers.IO) {
                val response = placesClient.findAutocompletePredictions(request).await()
                response.autocompletePredictions
            }

            val addresses = predictions.map { it.getFullText(null).toString() }
            onResult(addresses)
        } catch (e: Exception) {
            onError(e)
        }
    }

    fun Context.getAddressByName(
        name: String,
        onResult: (Address) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        val geocoder = weatherAppContext.geocoder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocationName(name, 1, object : GeocodeListener {
                override fun onGeocode(addresses: MutableList<Address>) {
                    if (addresses.isNotEmpty()) {
                        onResult(addresses[0])
                    } else {
                        onError(Exception("Address not found for name: $name"))
                    }
                }

                override fun onError(errorMessage: String?) {
                    onError(Exception(errorMessage))
                }
            })
        } else {
            try {
                @Suppress("DEPRECATION")
                val addresses = geocoder.getFromLocationName(name, 1)
                if (!addresses.isNullOrEmpty()) {
                    onResult(addresses[0])
                } else {
                    onError(Exception("Address not found for name: $name"))
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun Context.getAddressByCoordinates(
        latitude: Double,
        longitude: Double,
        onResult: (Address?) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        val geocoder = weatherAppContext.geocoder

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

    fun getCityFromAddress(address: Address?): String? {
        return address?.locality ?: address?.subAdminArea
    }
}
