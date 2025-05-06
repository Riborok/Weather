package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bsuir.weather.domain.model.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PickedLocationViewModel @Inject constructor(): ViewModel() {
    private val _pickedLocation = MutableStateFlow<LocationModel?>(null)
    val pickedLocation: StateFlow<LocationModel?> = _pickedLocation.asStateFlow()

    fun setPickedLocation(location: LocationModel?) {
        _pickedLocation.value = location
    }
}