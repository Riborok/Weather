package com.bsuir.weather.data.repository

import com.bsuir.weather.data.source.android.location.LocationSuggestionFetcher
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationSuggestionRepository
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import javax.inject.Inject

class LocationSuggestionRepositoryImpl @Inject constructor(
    private val locationSuggestionFetcher: LocationSuggestionFetcher
): LocationSuggestionRepository {
    override suspend fun fetchAddressSuggestions(
        query: String,
        sessionToken: AutocompleteSessionToken
    ): List<AutocompletePrediction> {
        return locationSuggestionFetcher.fetchAddressSuggestions(query, sessionToken)
    }

    override suspend fun fetchLocationByPlaceId(placeId: String): LocationModel {
        return locationSuggestionFetcher.fetchLocationByPlaceId(placeId)
    }
}