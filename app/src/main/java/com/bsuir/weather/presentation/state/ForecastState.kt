package com.bsuir.weather.presentation.state

import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.exception.NetworkRequestException

sealed class ForecastState {
    object Loading : ForecastState()
    data class Success(val forecastLocation: ForecastLocationModel) : ForecastState()
    data class Error(val error: NetworkRequestException) : ForecastState()
}
