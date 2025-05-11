package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.CurrentForecastModel
import com.bsuir.weather.domain.model.DailyForecastModel

@Composable
fun MainInfo (
    pickedLocationName: String,
    currentForecast: CurrentForecastModel,
    dailyForecast: DailyForecastModel,
    onOpenDrawerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = pickedLocationName,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    modifier = Modifier
                        .clickable(onClick = onOpenDrawerClick)
                )
                Text(
                    text = "${currentForecast.temperature} " + stringResource(R.string.celsius_degrees),
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = "${dailyForecast.minTemperature} "
                            + stringResource(R.string.celsius_degrees)
                            + " / " +
                            "${dailyForecast.maxTemperature} "
                            + stringResource(R.string.celsius_degrees),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = stringResource(currentForecast.weatherDescriptionId),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Image(
                painter = painterResource(currentForecast.iconId),
                contentDescription = stringResource(currentForecast.weatherDescriptionId),
                modifier = Modifier
                    .size(128.dp)
                    .weight(1f)
            )
        }
    }
}