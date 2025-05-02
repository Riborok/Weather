package com.bsuir.weather.presentation.state

import com.bsuir.weather.domain.model.WeatherLocationModel
import com.bsuir.weather.exception.NetworkRequestException

sealed class ForecastState {
    object Loading : ForecastState()
    data class Success(val weatherLocation: WeatherLocationModel) : ForecastState()
    data class Error(val error: NetworkRequestException) : ForecastState()
}
