package com.bsuir.weather.presentation.state

import com.bsuir.weather.domain.model.LocationModel

sealed class LocationState {
    object NoContent : LocationState()

    object Loading : LocationState()

    // Not data class to enforce reference comparison
    class Success(val location: LocationModel) : LocationState()
}
