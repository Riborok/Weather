package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.usecase.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SavedLocationViewModel @Inject constructor(
    @Named("SavedLocationUseCase") private val locationUseCase: LocationUseCase
) : ViewModel() {
    private var _savedLocations = MutableStateFlow<List<LocationModel>>(emptyList())
    val savedLocations: StateFlow<List<LocationModel>> = _savedLocations.asStateFlow()

    init {
        observeLocations()
    }

    private fun observeLocations() {
        locationUseCase.getSavedLocations()
            .onEach { locations ->
                _savedLocations.value = locations
            }
            .launchIn(viewModelScope)
    }

    fun saveLocation(location: LocationModel) {
        viewModelScope.launch {
            locationUseCase.saveLocation(location)
        }
    }

    fun removeLocation(location: LocationModel) {
        viewModelScope.launch {
            locationUseCase.removeLocation(location)
        }
    }

    fun updateLocation(oldLocation: LocationModel, newLocation: LocationModel) {
        if (oldLocation != newLocation) {
            viewModelScope.launch {
                locationUseCase.updateLocation(oldLocation, newLocation)
            }
        }
    }
}
