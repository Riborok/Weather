package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.LocationModel
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken

interface LocationSuggestionRepository {
    suspend fun fetchAddressSuggestions(
        query: String,
        sessionToken: AutocompleteSessionToken
    ): List<AutocompletePrediction>

    suspend fun fetchLocationByPlaceId(placeId: String): LocationModel
}