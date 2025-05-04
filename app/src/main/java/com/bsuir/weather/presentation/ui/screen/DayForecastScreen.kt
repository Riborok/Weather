package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.presentation.state.ForecastState
import com.bsuir.weather.presentation.ui.component.day_forecast_screen.DayForecastSuccessContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.ErrorContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.LoadingContent
import com.bsuir.weather.presentation.ui.utils.getDefaultLocation
import com.bsuir.weather.presentation.viewmodel.ForecastViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DayForecastScreen(
    dailyForecastModelIndex: Int,
    latitude: Double?,
    longitude: Double?,
    onNavigateToMainClick: () -> Unit,
    modifier: Modifier = Modifier,
    forecastViewModel: ForecastViewModel = hiltViewModel()
) {
    // Loading state
    var isRefreshing by remember { mutableStateOf(false) }

    // Default location
    val defaultLocation = getDefaultLocation()

    // Forecast
    val forecastState by forecastViewModel.forecastState.collectAsState()

    // Location
    val pickedLocation: LocationModel = if (latitude != null && longitude != null) {
        LocationModel(latitude, longitude)
    } else {
        defaultLocation
    }

    LaunchedEffect(pickedLocation) {
        forecastViewModel.loadForecast(pickedLocation)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            forecastViewModel.loadForecast(pickedLocation)
        }
    )

    LaunchedEffect(forecastState) {
        if (isRefreshing && forecastState !is ForecastState.Loading) {
            isRefreshing = false
        }
    }

    Box(
        modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (forecastState) {
            is ForecastState.Loading -> LoadingContent()
            is ForecastState.Success -> DayForecastSuccessContent(
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

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            backgroundColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.16f),
            contentColor    = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )
    }
}