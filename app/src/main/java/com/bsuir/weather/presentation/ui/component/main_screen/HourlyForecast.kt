package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.HourlyForecastModel
import java.time.LocalTime

@Composable
fun HourlyForecast (hourlyForecastList: List<HourlyForecastModel>, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text (
                text = stringResource(R.string.forecast_for_24_hour),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            LazyRow {
                item {
                    val index = hourlyForecastList.indexOfFirst {
                        hourlyForecast -> hourlyForecast.time.hour == LocalTime.now().hour
                    }

                    hourlyForecastList.slice(index..index + 23).forEach { hourlyForecastModel ->
                        HourlyForecastItem(
                            hourlyForecastModel = hourlyForecastModel,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    }
}