package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    pickedLocationName: String?,
    currentForecastModel: CurrentForecastModel,
    dailyForecastModel: DailyForecastModel,
    onOpenDrawerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = pickedLocationName ?: stringResource(R.string.loading),
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .clickable(onClick = onOpenDrawerClick)
                )
                Text(
                    text = "${currentForecastModel.temperature} " + stringResource(R.string.celsius_degrees),
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = "${dailyForecastModel.maxTemperature} "
                            + stringResource(R.string.celsius_degrees)
                            + " / ${dailyForecastModel.minTemperature} "
                            + stringResource(R.string.celsius_degrees),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = stringResource(currentForecastModel.weatherDescriptionId),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Image(
                painter = painterResource(currentForecastModel.iconId),
                contentDescription = stringResource(currentForecastModel.weatherDescriptionId),
                modifier = Modifier.size(128.dp)
            )
        }
    }
}