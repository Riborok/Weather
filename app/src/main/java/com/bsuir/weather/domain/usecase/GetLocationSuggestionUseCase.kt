package com.bsuir.weather.domain.usecase

import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationSuggestionRepository
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import javax.inject.Inject

class GetLocationSuggestionUseCase @Inject constructor(
    private val locationSuggestionRepository: LocationSuggestionRepository
) {
    suspend fun getAddressSuggestions(
        query: String,
        sessionToken: AutocompleteSessionToken
    ): List<AutocompletePrediction> {
        return locationSuggestionRepository.fetchAddressSuggestions(
            query = query,
            sessionToken = sessionToken
        )
    }

    suspend fun getLocationByPlaceId(placeId: String): LocationModel {
        return locationSuggestionRepository.fetchLocationByPlaceId(placeId)
    }
}