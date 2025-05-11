package com.bsuir.weather.data.source.android.location

import android.content.Context
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.exception.AddressNotFoundException
import com.bsuir.weather.utils.ext.AddressModel
import com.bsuir.weather.utils.mapper.CoordinatesMapper.toModel
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.tasks.await

class LocationSuggestionFetcher(
    private val context: Context,
    private val placesClient: PlacesClient,
) {
    suspend fun fetchAddressSuggestions(
        query: String,
        sessionToken: AutocompleteSessionToken
    ): List<AutocompletePrediction> {
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
        placeId: String
    ): LocationModel {
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
                coordinates = latLng.toModel(),
                address = address
            )
        } ?: throw AddressNotFoundException(
            context.getString(R.string.error_no_location_found),
            placeId
        )
    }
}