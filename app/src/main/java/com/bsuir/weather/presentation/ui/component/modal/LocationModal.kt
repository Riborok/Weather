package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.model.LocationModel

@Composable
fun LocationModal(
    savedLocations: List<LocationModel>,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var drawerMenuExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            CurrentLocation (
                drawerMenuExpanded = drawerMenuExpanded,
                onDrawerMenuExpandedChange = { drawerMenuExpanded = !drawerMenuExpanded },
                onDrawerMenuDismissRequest = { drawerMenuExpanded = false },
                onNavigate = onNavigate
            )

            SavedLocations(
                savedLocations
            )
        }
    }
}
