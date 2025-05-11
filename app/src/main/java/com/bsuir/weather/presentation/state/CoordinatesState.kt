package com.bsuir.weather.presentation.state

import com.bsuir.weather.domain.model.Coordinates

sealed class CoordinatesState {
    object NoContent : CoordinatesState()

    object Loading : CoordinatesState()

    // Not data class to enforce reference comparison
    class Success(val coordinates: Coordinates) : CoordinatesState()
}