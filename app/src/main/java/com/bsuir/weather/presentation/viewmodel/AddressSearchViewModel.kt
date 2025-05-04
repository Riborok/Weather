package com.bsuir.weather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.AddressUtils.fetchAddressSuggestions
import com.bsuir.weather.utils.AddressUtils.fetchLocationDetailsByPlaceId
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.bsuir.weather.utils.ext.weatherAppContext
import com.google.android.libraries.places.api.model.AutocompletePrediction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressSearchViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _cityResults = MutableStateFlow<List<AutocompletePrediction>>(emptyList())
    val cityResults: StateFlow<List<AutocompletePrediction>> = _cityResults

    private val sessionToken = AutocompleteSessionToken.newInstance()

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
        searchCities(text)
    }

    private fun searchCities(query: String) {
        val context = getApplication<Application>().weatherAppContext
        if (query.isNotBlank()) {
            viewModelScope.launch {
                fetchAddressSuggestions(
                    context = context,
                    query = query,
                    sessionToken = sessionToken,
                    onResult = { addresses ->
                        _cityResults.value = addresses
                    },
                    onError = {
                        _cityResults.value = emptyList()
                    }
                )
            }
        } else {
            _cityResults.value = emptyList()
        }
    }

    fun onCitySelected(
        placeId: String,
        onResult: (LocationModel) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        val context = getApplication<Application>().weatherAppContext
        viewModelScope.launch {
            fetchLocationDetailsByPlaceId(
                context = context,
                placeId = placeId,
                onResult = onResult,
                onError = onError
            )
        }
    }
}
