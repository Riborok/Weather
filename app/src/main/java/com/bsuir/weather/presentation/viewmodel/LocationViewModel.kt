package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.usecase.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    private val _currentLocation = MutableStateFlow<String>("")
    val currentLocation: StateFlow<String> = _currentLocation

    private val _savedLocations = MutableStateFlow<List<String>>(emptyList())
    val savedLocations: StateFlow<List<String>> = _savedLocations

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            _currentLocation.value = locationUseCase.getCurrentLocation()
            _savedLocations.value = locationUseCase.getSavedLocations()
        }
    }
}
