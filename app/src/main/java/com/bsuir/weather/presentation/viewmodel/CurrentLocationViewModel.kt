package com.bsuir.weather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.usecase.LocationUseCase
import com.bsuir.weather.utils.ext.weatherAppContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentLocationViewModel @Inject constructor(
    application: Application,
    private val locationUseCase: LocationUseCase
) : AndroidViewModel(application) {

    private val _currentLocation = MutableStateFlow<LocationModel?>(null)
    val currentLocation = _currentLocation.asStateFlow()

    fun fetchCurrentLocation() {
        viewModelScope.launch {
            val context = getApplication<Application>().weatherAppContext

            locationUseCase.fetchCurrentLocation(context) { currentLocation ->
                _currentLocation.value = currentLocation
            }
        }
    }


}
