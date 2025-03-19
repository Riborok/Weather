package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.usecase.LocationUseCase
import com.bsuir.weather.presentation.ui.theme.WeatherTheme

@Composable
fun LocationModal(
    currentLocation: String,
    drawerMenuExpanded: Boolean,
    onDrawerMenuExpandedChange: () -> Unit,
    onDrawerMenuDismissRequest: () -> Unit,
    savedLocations: List<String>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            CurrentLocation (
                currentLocation,
                drawerMenuExpanded,
                onDrawerMenuExpandedChange,
                onDrawerMenuDismissRequest
            )

            SavedLocations (
                savedLocations
            )
        }
    }
}

@Preview
@Composable
fun ModalPreview() {
    val locationUseCase = LocationUseCase()
    var drawerMenuExpanded by remember { mutableStateOf(false) }

    WeatherTheme {
        LocationModal (
            currentLocation = locationUseCase.getCurrentLocation(),
            savedLocations = locationUseCase.getSavedLocations(),
            drawerMenuExpanded = drawerMenuExpanded,
            onDrawerMenuExpandedChange = { drawerMenuExpanded = !drawerMenuExpanded },
            onDrawerMenuDismissRequest = { drawerMenuExpanded = false },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.75f)
        )
    }
}
