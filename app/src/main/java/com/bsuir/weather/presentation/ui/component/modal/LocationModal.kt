package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationModal(
    drawerMenuExpanded: Boolean,
    onDrawerMenuExpandedChange: () -> Unit,
    onDrawerMenuDismissRequest: () -> Unit,
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
                drawerMenuExpanded,
                onDrawerMenuExpandedChange,
                onDrawerMenuDismissRequest
            )
            SavedLocations()
        }
    }
}
