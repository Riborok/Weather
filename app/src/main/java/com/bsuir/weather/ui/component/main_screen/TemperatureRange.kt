package com.bsuir.weather.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TemperatureRange (minTemperature: String, maxTemperature: String, modifier: Modifier = Modifier) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text (
            text = minTemperature,
            style = MaterialTheme.typography.titleLarge
        )

        Text (
            text = " .. ",
            style = MaterialTheme.typography.titleLarge
        )

        Text (
            text = maxTemperature,
            style = MaterialTheme.typography.titleLarge
        )
    }
}