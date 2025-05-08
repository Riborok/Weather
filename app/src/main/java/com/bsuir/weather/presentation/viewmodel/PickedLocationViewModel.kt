package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.presentation.state.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PickedLocationViewModel @Inject constructor(): ViewModel() {
    private val _pickedLocationState = MutableStateFlow<LocationState>(LocationState.Loading)
    val pickedLocationState: StateFlow<LocationState> = _pickedLocationState.asStateFlow()

    fun setPickedLocation(location: LocationModel?) {
        _pickedLocationState.value = location
            ?.let { LocationState.Success(it) }
            ?: LocationState.NoContent
    }
}
