package com.bsuir.weather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.usecase.LocationUseCase
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.bsuir.weather.utils.ext.weatherAppContext
import com.bsuir.weather.utils.location.LocationSuggestionUtils.fetchAddressSuggestions
import com.bsuir.weather.utils.location.LocationSuggestionUtils.fetchLocationByPlaceId
import com.google.android.libraries.places.api.model.AutocompletePrediction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddressSearchViewModel @Inject constructor(
    application: Application,
    private val locationUseCase: LocationUseCase
) : AndroidViewModel(application) {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _cityResults = MutableStateFlow<List<AutocompletePrediction>>(emptyList())
    val cityResults: StateFlow<List<AutocompletePrediction>> = _cityResults.asStateFlow()

    private val sessionToken = AutocompleteSessionToken.newInstance()

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
        searchCities(text)
    }

    private fun searchCities(query: String) {
        val context = getApplication<Application>().weatherAppContext
        if (query.isNotBlank()) {
            viewModelScope.launch {
                _cityResults.value = fetchAddressSuggestions(
                    context = context,
                    query = query,
                    sessionToken = sessionToken
                )
            }
        } else {
            _cityResults.value = emptyList()
        }
    }

    fun saveLocation(
        placeId: String
    ) {
        val context = getApplication<Application>().weatherAppContext
        viewModelScope.launch {
            val location = fetchLocationByPlaceId(
                context = context,
                placeId = placeId
            )
            locationUseCase.saveLocation(location)
        }
    }
}
