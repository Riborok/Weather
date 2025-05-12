package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.usecase.StoredLocationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SavedLocationViewModel @Inject constructor(
    @Named("SavedLocationListUseCase") private val storedLocationListUseCase: StoredLocationListUseCase
) : ViewModel() {
    private var _savedLocations = MutableStateFlow<List<LocationModel>>(emptyList())
    val savedLocations: StateFlow<List<LocationModel>> = _savedLocations.asStateFlow()

    init {
        observeLocations()
    }

    private fun observeLocations() {
        storedLocationListUseCase.getLocations()
            .onEach { locations ->
                _savedLocations.value = locations
            }
            .launchIn(viewModelScope)
    }

    fun saveLocation(location: LocationModel) {
        viewModelScope.launch {
            storedLocationListUseCase.saveLocation(location)
        }
    }

    fun removeLocation(location: LocationModel) {
        viewModelScope.launch {
            storedLocationListUseCase.removeLocation(location)
        }
    }

    fun updateLocation(oldLocation: LocationModel, newLocation: LocationModel) {
        if (oldLocation != newLocation) {
            viewModelScope.launch {
                storedLocationListUseCase.updateLocation(oldLocation, newLocation)
            }
        }
    }
}
