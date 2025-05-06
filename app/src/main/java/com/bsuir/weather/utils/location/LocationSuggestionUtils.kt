package com.bsuir.weather.utils.location

import android.content.Context
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.exception.AddressNotFoundException
import com.bsuir.weather.utils.ext.AddressModel
import com.bsuir.weather.utils.ext.weatherAppContext
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.tasks.await

object LocationSuggestionUtils {
    suspend fun fetchAddressSuggestions(
        context: Context,
        query: String,
        sessionToken: AutocompleteSessionToken
    ): List<AutocompletePrediction> {
        val placesClient = context.weatherAppContext.placesClient

        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .setTypesFilter(listOf(PlaceTypes.ADDRESS))
            .setSessionToken(sessionToken)
            .build()

        return placesClient
            .findAutocompletePredictions(request)
            .await().autocompletePredictions
    }

    suspend fun fetchLocationByPlaceId(
        context: Context,
        placeId: String,
    ): LocationModel {
        val placesClient = context.weatherAppContext.placesClient

        val placeFields = listOf(
            Place.Field.LAT_LNG,
            Place.Field.ADDRESS_COMPONENTS,
            Place.Field.ADDRESS
        )

        val request = FetchPlaceRequest.builder(placeId, placeFields).build()

        val place = placesClient.fetchPlace(request).await().place

        val latLng = place.location
        val address = place.AddressModel
        return latLng?.let { latLng ->
            LocationModel(
                latitude = latLng.latitude,
                longitude = latLng.longitude,
                address = address
            )
        } ?: throw AddressNotFoundException("No location found for placeId: $placeId", placeId)
    }
}