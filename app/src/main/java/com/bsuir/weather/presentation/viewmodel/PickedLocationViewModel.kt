package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.usecase.StoredLocationUseCase
import com.bsuir.weather.presentation.state.LocationState
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
class PickedLocationViewModel @Inject constructor(
    @Named("PickedLocationUseCase")
    private val storedLocationUseCase: StoredLocationUseCase,
): ViewModel() {
    private val _pickedLocationState = MutableStateFlow<LocationState>(LocationState.Loading)
    val pickedLocationState: StateFlow<LocationState> = _pickedLocationState.asStateFlow()

    init {
        observeLocations()
    }

    private fun observeLocations() {
        storedLocationUseCase.getLocation()
            .onEach { location ->
                _pickedLocationState.value = location
                    ?.let { LocationState.Success(it) }
                    ?: LocationState.NoContent
            }
            .launchIn(viewModelScope)
    }

    fun setPickedLocation(location: LocationModel) {
        viewModelScope.launch {
            storedLocationUseCase.updateLocation(location)
        }
    }
}
