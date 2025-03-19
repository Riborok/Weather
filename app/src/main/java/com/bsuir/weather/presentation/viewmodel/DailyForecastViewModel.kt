package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.DailyForecastModel
import com.bsuir.weather.domain.usecase.GetDailyForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(
    private val getDailyForecastUseCase: GetDailyForecastUseCase
) : ViewModel() {

    private val _dailyForecast = MutableStateFlow<List<DailyForecastModel>>(emptyList())
    val dailyForecast: StateFlow<List<DailyForecastModel>> = _dailyForecast

    fun loadDailyForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _dailyForecast.value = getDailyForecastUseCase.execute(latitude, longitude)
        }
    }
}
