package com.bsuir.weather.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder.GeocodeListener
import android.os.Build
import com.bsuir.weather.domain.model.AddressModel
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.mapper.LocationDTOMapper.toModel
import com.google.android.libraries.places.api.model.AddressComponent
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object GeocoderUtils {
    suspend fun getAddressNamesByQuery(
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

    suspend fun getLocationModelByPlaceId(
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
                val addressModel = AddressModel(
                    countryName = getAddressComponent(addressComponents, "country"),
                    locality = getAddressComponent(addressComponents, "locality"),
                    subLocality = getAddressComponent(addressComponents, "sublocality"),
                    adminArea = getAddressComponent(addressComponents, "administrative_area_level_1"),
                    subAdminArea = getAddressComponent(addressComponents, "administrative_area_level_2"),
                    thoroughfare = getAddressComponent(addressComponents, "route"),
                    subThoroughfare = getAddressComponent(addressComponents, "street_number")
                )

                onResult(LocationModel(
                    latitude = latLng.latitude,
                    longitude = latLng.longitude,
                    address = addressModel
                ))
            } else {
                onError(Exception())
            }
        } catch (e: Exception) {
            onError(e)
        }
    }

    private fun getAddressComponent(
        addressComponents: List<AddressComponent>?,
        type: String
    ): String? {
        return addressComponents
            ?.find { it.types.contains(type) }
            ?.name
    }

    fun getLocationModel(
        context: Context,
        latitude: Double,
        longitude: Double,
        onResult: (LocationModel) -> Unit
    ) {
        getAddressByCoordinates(
            context = context,
            latitude = latitude,
            longitude = longitude,
            onResult = { address ->
                onResult(address?.toModel() ?: LocationModel(latitude, longitude))
            },
            onError = {
                onResult(LocationModel(latitude, longitude))
            }
        )
    }

    fun getAddressByCoordinates(
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
