package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.DailyForecastModel

@Composable
fun AdditionalInfo (
    currentForecastModel: CurrentForecastModel,
    dailyForecastModel: DailyForecastModel,
    modifier: Modifier = Modifier
) {
    val itemModifier = Modifier
        .fillMaxWidth()

    Card(
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            AdditionalInfoItem(
                title = stringResource(R.string.felt),
                value = "${currentForecastModel.temperature} " + stringResource(R.string.celsius_degrees),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.wind),
                value = "${currentForecastModel.windSpeed} "
                        + stringResource(R.string.kilometers_per_hour)
                        + ", ${currentForecastModel.windDirection}",
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.humidity),
                value = "${currentForecastModel.relativeHumidity} "
                        + stringResource(R.string.percent),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.pressure),
                value = "${currentForecastModel.surfacePressure} "
                        + stringResource(R.string.hectopascal),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.sunrise),
                value = "${dailyForecastModel.sunrise.time}",
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.sunset),
                value = "${dailyForecastModel.sunset.time}",
                modifier = itemModifier)
        }
    }
}