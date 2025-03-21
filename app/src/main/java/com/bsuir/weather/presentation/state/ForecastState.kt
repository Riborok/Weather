package com.bsuir.weather.presentation.state

import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.exception.NetworkRequestException

sealed class ForecastState {
    object Loading : ForecastState()
    data class Success(val forecast: ForecastModel) : ForecastState()
    data class Error(val error: NetworkRequestException) : ForecastState()
}
