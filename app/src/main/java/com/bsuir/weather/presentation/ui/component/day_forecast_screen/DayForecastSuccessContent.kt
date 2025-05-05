package com.bsuir.weather.presentation.ui.component.day_forecast_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.presentation.ui.component.main_screen.DayForecastTopBar
import com.bsuir.weather.utils.constants.WeatherProfile

@Composable
fun DayForecastSuccessContent(
    dailyForecastModelIndex: Int,
    forecastLocation: ForecastLocationModel,
    onNavigateToMainClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hourlyForecasts = forecastLocation.forecast.hourlyForecasts
    val dailyForecasts = forecastLocation.forecast.dailyForecasts

    if (dailyForecastModelIndex !in 0 .. (dailyForecasts.lastIndex)) {
        Text(
            text = stringResource(R.string.wrong_daily_forecast_index_error),
            color = MaterialTheme.colorScheme.error
        )
    } else {
        val dailyForecastModel = dailyForecasts[dailyForecastModelIndex]

        val limitedHourlyForecastList = remember(hourlyForecasts) {
            val startIndex = 24 * dailyForecastModelIndex

            hourlyForecasts.subList(
                startIndex,
                (startIndex + 24).coerceAtMost(hourlyForecasts.size)
            )
        }

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
            HourlyProfileView(
                hourlyForecastList = limitedHourlyForecastList,
                profile = WeatherProfile.GENERAL,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}