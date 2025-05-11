package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.usecase.GetCurrentLocationUseCase
import com.bsuir.weather.domain.usecase.GetLocationFromCoordinatesUseCase
import com.bsuir.weather.domain.usecase.StoredLocationUseCase
import com.bsuir.weather.presentation.state.CoordinatesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationFromCoordinatesUseCase: GetLocationFromCoordinatesUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    @Named("SavedLocationUseCase") private val storedLocationUseCase: StoredLocationUseCase
) : ViewModel() {
    private val _currentCoordinatesState = MutableStateFlow<CoordinatesState>(CoordinatesState.Loading)
    val currentCoordinatesState: StateFlow<CoordinatesState> = _currentCoordinatesState.asStateFlow()

    private val _userInput = MutableStateFlow("")
    val userInput: StateFlow<String> = _userInput.asStateFlow()

    private val _selectedCoordinates = MutableStateFlow<Coordinates?>(null)
    val selectedCoordinates: StateFlow<Coordinates?> = _selectedCoordinates.asStateFlow()

    init {
        initializeCurrentCoordinatesState()
    }

    private fun initializeCurrentCoordinatesState() {
        viewModelScope.launch {
            _currentCoordinatesState.value = getCurrentLocationUseCase.getCurrentCoordinates()
                ?.let { coordinates -> CoordinatesState.Success(coordinates) }
                ?: CoordinatesState.NoContent
        }
    }


    fun onUserInputChange(input: String) {
        _userInput.value = input
    }

    fun onMapClick(coords: Coordinates) {
        _selectedCoordinates.value = coords
    }

    fun saveLocation() {
        viewModelScope.launch {
            _selectedCoordinates.value?.let { coords ->
                val location = getLocationFromCoordinatesUseCase.getLocationFromCoordinates(
                    coords = coords
                )
                location.address.alias = _userInput.value
                storedLocationUseCase.saveLocation(location)
            }
        }
    }
}
