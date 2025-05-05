package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.HourlyForecastModel

@Composable
fun HourlyForecastItem(
    hourlyForecastModel: HourlyForecastModel,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text (
            text = hourlyForecastModel.time.time.toString(),
            style = MaterialTheme.typography.bodyLarge
        )

        Image (
            painter = painterResource(hourlyForecastModel.iconId),
            contentDescription = stringResource(hourlyForecastModel.weatherDescriptionId),
            modifier = Modifier
                .size(42.dp)
        )

        Text (
            text = "${hourlyForecastModel.temperature} ${stringResource(R.string.celsius_degrees)}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}