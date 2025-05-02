package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bsuir.weather.R

@Composable
fun TemperatureRange (minTemperature: String, maxTemperature: String, modifier: Modifier = Modifier) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text (
            text = minTemperature + " ${stringResource(R.string.celsius_degrees)}",
            style = MaterialTheme.typography.titleLarge
        )

        Text (text = "  |  ")

        Text (
            text = maxTemperature + " ${stringResource(R.string.celsius_degrees)}",
            style = MaterialTheme.typography.titleLarge
        )
    }
}