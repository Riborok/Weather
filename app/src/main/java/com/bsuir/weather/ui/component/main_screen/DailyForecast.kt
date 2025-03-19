package com.bsuir.weather.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.weather.model.DailyForecastInfo

@Composable
fun DailyForecast (dailyForecastList: List<DailyForecastInfo>, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text (
                text = "Прогноз на 7 дней",
                style = MaterialTheme.typography.titleLarge
            )

            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(dailyForecastList) { dailyForecastInfo ->
                    DailyForecastItem(
                        dailyForecastInfo.dayName,
                        dailyForecastInfo.icon,
                        dailyForecastInfo.weatherDescription,
                        dailyForecastInfo.minTemperature,
                        dailyForecastInfo.maxTemperature,
                        Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}