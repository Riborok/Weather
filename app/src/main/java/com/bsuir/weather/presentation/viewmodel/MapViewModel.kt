package com.bsuir.weather.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.usecase.LocationUseCase
import com.bsuir.weather.utils.AddressUtils.fetchLocationModelFromCoordinates
import com.bsuir.weather.utils.ext.weatherAppContext
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    application: Application,
    private val locationUseCase: LocationUseCase
) : AndroidViewModel(application) {

    private val _userInput = MutableStateFlow("")
    val userInput: StateFlow<String> = _userInput.asStateFlow()

    private val _selectedCoordinates = MutableStateFlow<LatLng?>(null)
    val selectedCoordinates: StateFlow<LatLng?> = _selectedCoordinates.asStateFlow()

    fun onUserInputChange(input: String) {
        _userInput.value = input
    }

    fun onMapClick(latLng: LatLng) {
        _selectedCoordinates.value = latLng
    }

    fun saveLocation() {
        viewModelScope.launch {
            _selectedCoordinates.value?.let { coords ->
                val context = getApplication<Application>().weatherAppContext
                val location = fetchLocationModelFromCoordinates(
                    context = context,
                    latitude = coords.latitude,
                    longitude = coords.longitude
                )
                location.address.alias = _userInput.value
                locationUseCase.saveLocation(location)
            }
        }
    }
}
