package com.bsuir.weather.ui.component.main_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DailyForecastItem(
    dayName: String,
    @DrawableRes icon: Int,
    weatherDescription: String,
    minTemperature: String,
    maxTemperature: String,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text (
            text = dayName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .weight(1f)
        )

        Image (
            painter = painterResource(icon),
            contentDescription = weatherDescription,
            modifier = Modifier
                .size(42.dp)
                .weight(0.5f)
        )

        TemperatureRange (
            minTemperature = minTemperature,
            maxTemperature = maxTemperature,
            modifier = Modifier
                .weight(1f)
        )
    }
}