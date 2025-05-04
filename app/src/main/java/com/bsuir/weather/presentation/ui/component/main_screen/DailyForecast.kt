package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.DailyForecastModel

@Composable
fun DailyForecast (
    dailyForecastList: List<DailyForecastModel>,
    onNavigateToDayForecast: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text (
                text = stringResource(R.string.forecast_for_7_days),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                dailyForecastList.forEachIndexed { index, dailyForecastModel ->
                    DailyForecastItem(
                        dailyForecastModel.date.dayOfWeek,
                        dailyForecastModel.iconId,
                        dailyForecastModel.weatherDescriptionId,
                        dailyForecastModel.minTemperature.toString(),
                        dailyForecastModel.maxTemperature.toString(),
                        Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToDayForecast(index) }
                    )
                }
            }
        }
    }
}