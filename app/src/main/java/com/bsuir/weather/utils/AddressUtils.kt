package com.bsuir.weather.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder.GeocodeListener
import android.os.Build
import com.bsuir.weather.domain.model.AddressModel
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.ext.weatherAppContext
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.libraries.places.api.model.AddressComponent
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object AddressUtils {
    suspend fun fetchAddressSuggestions(
        context: Context,
        query: String,
        sessionToken: AutocompleteSessionToken,
        onResult: (List<AutocompletePrediction>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        try {
            val placesClient = context.weatherAppContext.placesClient

            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .setTypesFilter(listOf(PlaceTypes.ADDRESS))
                .setSessionToken(sessionToken)
                .build()

            val predictions = withContext(Dispatchers.IO) {
                val response = placesClient.findAutocompletePredictions(request).await()
                response.autocompletePredictions
            }

            onResult(predictions)
        } catch (e: Exception) {
            onError(e)
        }
    }

    suspend fun fetchLocationDetailsByPlaceId(
        context: Context,
        placeId: String,
        onResult: (LocationModel) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        try {
            val placesClient = context.weatherAppContext.placesClient

            val placeFields = listOf(
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS_COMPONENTS,
                Place.Field.ADDRESS
            )

            val request = FetchPlaceRequest.builder(placeId, placeFields).build()

            val place = withContext(Dispatchers.IO) {
                placesClient.fetchPlace(request).await().place
            }

            val latLng = place.location
            val addressComponents = place.addressComponents?.asList()

            if (latLng != null) {
                with(addressComponents) {
                    val address = AddressModel(
                        countryName = extractAddressComponent("country"),
                        locality = extractAddressComponent("locality"),
                        subLocality = extractAddressComponent("sublocality"),
                        adminArea = extractAddressComponent("administrative_area_level_1"),
                        subAdminArea = extractAddressComponent("administrative_area_level_2"),
                        thoroughfare = extractAddressComponent("route"),
                        subThoroughfare = extractAddressComponent("street_number")
                    )

                    onResult(LocationModel(
                        latitude = latLng.latitude,
                        longitude = latLng.longitude,
                        address = address
                    ))
                }
            } else {
                onError(Exception())
            }
        } catch (e: Exception) {
            onError(e)
        }
    }

    private fun List<AddressComponent>?.extractAddressComponent(
        type: String
    ): String? {
        return this
            ?.find { it.types.contains(type) }
            ?.name
    }

    suspend fun fetchLocationModelFromCoordinates(
        context: Context,
        latitude: Double,
        longitude: Double
    ): LocationModel = suspendCancellableCoroutine { cont ->
        val cts = CancellationTokenSource()

        fetchLocationModelFromCoordinates(
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

    fun fetchLocationModelFromCoordinates(
        context: Context,
        latitude: Double,
        longitude: Double,
        ct: CancellationToken,
        onResult: (LocationModel) -> Unit
    ) {
        if (ct.isCancellationRequested) return
        fetchAddressByCoordinates(
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

    private fun fetchAddressByCoordinates(
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
