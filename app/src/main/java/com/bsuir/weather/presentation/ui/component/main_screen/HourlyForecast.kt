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

            val currentHour = LocalTime.now().hour
            val startIndex = hourlyForecastList.indexOfFirst {
                it.time.hour == currentHour
            }.coerceAtLeast(0)

            val limitedList = hourlyForecastList.subList(
                startIndex,
                (startIndex + 24).coerceAtMost(hourlyForecastList.size)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(limitedList.size) { index ->
                    val hourlyForecastModel = limitedList[index]
                    HourlyForecastItem(
                        hourlyForecastModel = hourlyForecastModel,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}
