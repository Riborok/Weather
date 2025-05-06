package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.presentation.state.ForecastState
import com.bsuir.weather.presentation.ui.component.day_forecast_screen.DayForecastContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.ErrorContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.LoadingContent
import com.bsuir.weather.presentation.viewmodel.ForecastViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DayForecastScreen(
    dailyForecastModelIndex: Int,
    onNavigateToMainClick: () -> Unit,
    modifier: Modifier = Modifier,
    forecastViewModel: ForecastViewModel = hiltViewModel()
) {
    val forecastState by forecastViewModel.forecastState.collectAsState()

    Box(
        modifier
            .fillMaxSize()
    ) {
        when (forecastState) {
            is ForecastState.Loading -> LoadingContent()
            is ForecastState.Success -> DayForecastContent(
                dailyForecastModelIndex = dailyForecastModelIndex,
                forecastLocation = (forecastState as ForecastState.Success).forecastLocation,
                onNavigateToMainClick = onNavigateToMainClick,
                modifier = modifier.fillMaxSize()
            )
            is ForecastState.Error -> ErrorContent(
                errorMessage = (forecastState as ForecastState.Error).error.message
                    ?: stringResource(R.string.unknown_error)
            )
        }
    }
}