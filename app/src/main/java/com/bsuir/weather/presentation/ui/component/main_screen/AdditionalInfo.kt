package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.DailyForecastModel
import com.bsuir.weather.utils.ext.secondaryCardColors

@Composable
fun AdditionalInfo (
    currentForecast: CurrentForecastModel,
    dailyForecast: DailyForecastModel,
    modifier: Modifier = Modifier
) {
    val itemModifier = Modifier
        .fillMaxWidth()

    Card(
        modifier = modifier,
        colors = MaterialTheme.colorScheme.secondaryCardColors
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            AdditionalInfoItem(
                title = stringResource(R.string.felt),
                value = "${currentForecast.temperature} "
                        + stringResource(R.string.celsius_degrees),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.wind),
                value = "${currentForecast.windSpeed} "
                        + stringResource(R.string.kilometers_per_hour)
                        + ", ${stringResource(currentForecast.windDirectionId)}",
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.wind_gusts),
                value = "${currentForecast.windGusts} "
                        + stringResource(R.string.kilometers_per_hour),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.humidity),
                value = "${currentForecast.relativeHumidity} "
                        + stringResource(R.string.percent),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.precipitation),
                value = "${currentForecast.precipitation} "
                        + stringResource(R.string.millimeters),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.cloud_cover),
                value = "${currentForecast.cloudCover} "
                        + stringResource(R.string.percent),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.pressure),
                value = "${currentForecast.pressureMSL} "
                        + stringResource(R.string.hectopascal),
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.sunrise),
                value = "${dailyForecast.sunrise.time}",
                modifier = itemModifier)
            AdditionalInfoItem(
                title = stringResource(R.string.sunset),
                value = "${dailyForecast.sunset.time}",
                modifier = itemModifier)
        }
    }
}