package com.bsuir.weather.presentation.state

import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.exception.NetworkRequestException

sealed class ForecastState {
    object Loading : ForecastState()

    // Not data class to enforce reference comparison
    class Success(val forecastLocation: ForecastLocationModel) : ForecastState()

    // Not data class to enforce reference comparison
    class Error(val error: NetworkRequestException) : ForecastState()
}
