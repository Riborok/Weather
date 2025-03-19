package com.bsuir.weather.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.weather.model.HourlyForecastInfo
import com.bsuir.weather.ui.component.main_screen.HourlyForecastItem

@Composable
fun HourlyForecast (hourlyForecastList: List<HourlyForecastInfo>, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text (
                text = "Прогноз на 24 часа",
                style = MaterialTheme.typography.titleLarge
            )

            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(hourlyForecastList) { hourlyForecastInfo ->
                    HourlyForecastItem(
                        hourlyForecastInfo.temperature,
                        hourlyForecastInfo.icon,
                        hourlyForecastInfo.weatherDescription,
                        hourlyForecastInfo.hour
                    )
                }
            }
        }
    }
}