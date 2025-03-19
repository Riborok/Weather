package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.domain.usecase.GetHourlyForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HourlyForecastViewModel @Inject constructor(
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase
) : ViewModel() {

    private val _hourlyForecast = MutableStateFlow<List<HourlyForecastModel>>(emptyList())
    val hourlyForecast: StateFlow<List<HourlyForecastModel>> = _hourlyForecast

    fun loadHourlyForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _hourlyForecast.value = getHourlyForecastUseCase.execute(latitude, longitude)
        }
    }
}