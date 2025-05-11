package com.bsuir.weather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.usecase.GetCurrentLocationUseCase
import com.bsuir.weather.presentation.state.LocationState
import com.bsuir.weather.utils.ext.weatherAppContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentLocationViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
) : ViewModel() {

    private val _currentLocationState = MutableStateFlow<LocationState>(LocationState.Loading)
    val currentLocationState: StateFlow<LocationState> = _currentLocationState.asStateFlow()

    fun fetchCurrentLocation() {
        viewModelScope.launch {
            _currentLocationState.value = getCurrentLocationUseCase.getCachedCurrentLocation()
                ?.let(LocationState::Success)
                ?: LocationState.NoContent
        }
    }
}
