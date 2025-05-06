package com.bsuir.weather.presentation.ui.component.day_forecast_screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.utils.constants.WeatherProfile

@Composable
fun ProfilePickView(
    pickedProfile: WeatherProfile,
    onProfilePick: (WeatherProfile) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WeatherProfile.entries.forEach { profile ->
                FilterChip(
                    selected = pickedProfile == profile,
                    onClick = { onProfilePick(profile) },
                    label = {
                        Text(
                            text = stringResource(profile.nameResId),
                            style = MaterialTheme.typography.displaySmall
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = profile.icon,
                            contentDescription = stringResource(profile.nameResId)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier
                        .defaultMinSize(minHeight = 40.dp)
                )
            }
        }
    }
}