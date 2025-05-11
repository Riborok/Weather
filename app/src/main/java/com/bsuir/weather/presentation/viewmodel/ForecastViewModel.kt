package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.Coordinates
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.usecase.GetForecastUseCase
import com.bsuir.weather.exception.NetworkRequestException
import com.bsuir.weather.presentation.state.ForecastState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> = _forecastState.asStateFlow()

    fun setNoContent() {
        _forecastState.value = ForecastState.NoContent
    }

    fun loadForecast(location: LocationModel) {
        updateForecast(location) { coordinates ->
            getForecastUseCase.getForecast(coordinates)
        }
    }

    fun loadForecastForced(location: LocationModel) {
        updateForecast(location) { coordinates ->
            getForecastUseCase.getForecastForced(coordinates)
        }
    }

    private fun updateForecast(
        location: LocationModel,
        fetchForecast: suspend (Coordinates) -> ForecastModel
    ) {
        viewModelScope.launch {
            _forecastState.value = try {
                val forecast = fetchForecast(location.coordinates)
                ForecastState.Success(ForecastLocationModel(forecast, location))
            } catch (e: NetworkRequestException) {
                ForecastState.Error(e)
            }
        }
    }
}
