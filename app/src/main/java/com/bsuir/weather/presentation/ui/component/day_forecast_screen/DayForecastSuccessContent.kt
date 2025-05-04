package com.bsuir.weather.presentation.ui.component.day_forecast_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.presentation.ui.component.main_screen.DayForecastTopBar

@Composable
fun DayForecastSuccessContent(
    dailyForecastModelIndex: Int,
    forecastLocation: ForecastLocationModel,
    onNavigateToMainClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hourlyForecastModel = forecastLocation.forecast.hourlyForecasts
    val dailyForecastModel = forecastLocation.forecast.dailyForecasts

    if (dailyForecastModelIndex !in 0 .. (dailyForecastModel.size - 1)) {
        Text(
            text = stringResource(R.string.wrong_daily_forecast_index_error),
            color = MaterialTheme.colorScheme.error
        )
    } else {
        val dailyForecastModel = dailyForecastModel[dailyForecastModelIndex]

        Scaffold (
            topBar = {
                DayForecastTopBar(
                    onNavigateToMainClick = onNavigateToMainClick,
                    date = dailyForecastModel.date,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = modifier
        ) { innerPadding ->
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(innerPadding)
            ) {
                item {

                }
            }
        }
    }
}