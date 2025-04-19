package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bsuir.weather.domain.model.LocationModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PickedLocationViewModel: ViewModel() {
    private val _pickedLocation = MutableStateFlow<LocationModel?>(null)
    val pickedLocation: StateFlow<LocationModel?> = _pickedLocation

    fun setPickedLocation(location: LocationModel?) {
        _pickedLocation.value = location
    }
}