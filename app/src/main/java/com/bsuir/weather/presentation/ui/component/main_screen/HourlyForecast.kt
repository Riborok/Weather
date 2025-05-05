package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.model.HourlyForecastModel
import androidx.compose.foundation.lazy.items
import com.bsuir.weather.utils.ext.toEpochMillisUTC

@Composable
fun HourlyForecast (
    title: String,
    hourlyForecastList: List<HourlyForecastModel>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (ColumnScope.(HourlyForecastModel) -> Unit),
) {
    Card (
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text (
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = hourlyForecastList,
                    key = { it.time.toEpochMillisUTC() }
                ) { hourlyForecast ->
                    itemContent(hourlyForecast)
                }
            }
        }
    }
}
