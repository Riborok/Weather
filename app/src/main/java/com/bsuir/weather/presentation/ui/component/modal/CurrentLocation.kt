package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel

@Composable
fun CurrentLocation(
    drawerMenuExpanded: Boolean,
    onDrawerMenuExpandedChange: () -> Unit,
    onDrawerMenuDismissRequest: () -> Unit,
    onAddWithMapClick: () -> Unit,
    modifier: Modifier = Modifier,
    currentLocationViewModel: CurrentLocationViewModel = hiltViewModel(),
) {


    val currentLocation by currentLocationViewModel.currentLocation.collectAsState()
    val locationName = currentLocation?.name

    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text (
                text = "Текущее местоположение",
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Box {
                IconButton(
                    onClick = { onDrawerMenuExpandedChange() }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Меню")
                }

                DropdownMenu(
                    expanded = drawerMenuExpanded,
                    onDismissRequest = onDrawerMenuDismissRequest
                ) {
                    DropdownMenuItem(
                        text = { Text("Добавить на карте") },
                        leadingIcon = { Icon(Icons.Outlined.Map, contentDescription = null) },
                        onClick = { onAddWithMapClick() }
                    )

                    DropdownMenuItem(
                        text = { Text("Добавить по названию") },
                        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                        onClick = { /* Do something... */ }
                    )
                }
            }
        }

        Text (
            text = locationName ?: "Разрешение не предоставлено",
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}