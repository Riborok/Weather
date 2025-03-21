package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.usecase.GetForecastUseCase
import com.bsuir.weather.exception.NetworkRequestException
import com.bsuir.weather.presentation.state.ForecastState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> = _forecastState

    fun loadForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _forecastState.value = try {
                val forecastModel = getForecastUseCase.execute(latitude, longitude)
                ForecastState.Success(forecastModel)
            } catch (e: NetworkRequestException) {
                ForecastState.Error(e)
            }
        }
    }
}
