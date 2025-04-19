package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.Route

@Composable
fun LocationModal(
    currentLocation: LocationModel?,
    savedLocations: List<LocationModel>,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var drawerMenuExpanded by remember { mutableStateOf(false) }

    Surface (
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                item {
                    if (currentLocation != null) {
                        LocationGroup(
                            title = "Текущее:",
                            locations = listOf(currentLocation),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }

                    if (savedLocations.isNotEmpty()) {
                        LocationGroup(
                            title = "Сохраненные:",
                            locations = savedLocations,
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            }

            Column (
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                IconButton(
                    onClick = { drawerMenuExpanded = !drawerMenuExpanded },
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Меню")
                }

                DropdownMenu(
                    expanded = drawerMenuExpanded,
                    onDismissRequest = { drawerMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Добавить на карте") },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Map,
                                contentDescription = "Добавить на карте"
                            )
                        },
                        onClick = {
                            drawerMenuExpanded = !drawerMenuExpanded
                            onNavigate(Route.Map.name)
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Добавить по названию") },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Search,
                                contentDescription = "Добавить по названию"
                            )
                        },
                        onClick = {
                            drawerMenuExpanded = !drawerMenuExpanded
                            onNavigate(Route.AddressSearch.name)
                        }
                    )
                }
            }

        }
    }
}
