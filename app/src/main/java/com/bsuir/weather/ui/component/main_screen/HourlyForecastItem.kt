package com.bsuir.weather.ui.component.main_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HourlyForecastItem(
    temperature: String,
    @DrawableRes icon: Int,
    weatherDescription: String,
    hour: String,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text (
            text = temperature,
            style = MaterialTheme.typography.bodyLarge
        )

        Image (
            painter = painterResource(icon),
            contentDescription = weatherDescription,
            modifier = Modifier
                .size(42.dp)
        )

        Text (
            text = hour,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}