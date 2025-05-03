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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.Route

@Composable
fun LocationModal(
    currentLocation: LocationModel?,
    savedLocations: List<LocationModel>,
    onLocationClick: (location: LocationModel) -> Unit,
    onLocationDelete: (location: LocationModel) -> Unit,
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
                            title = stringResource(R.string.current),
                            locations = listOf(currentLocation),
                            onLocationClick = onLocationClick,
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }

                    if (savedLocations.isNotEmpty()) {
                        DeletableLocationGroup(
                            title = stringResource(R.string.saved),
                            locations = savedLocations,
                            onLocationClick = onLocationClick,
                            onLocationDelete = onLocationDelete,
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
                    Icon(Icons.Default.MoreVert, contentDescription = stringResource(R.string.menu))
                }

                DropdownMenu(
                    expanded = drawerMenuExpanded,
                    onDismissRequest = { drawerMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.add_with_map)) },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Map,
                                contentDescription = stringResource(R.string.add_with_map)
                            )
                        },
                        onClick = {
                            drawerMenuExpanded = !drawerMenuExpanded
                            onNavigate(Route.Map.name)
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.add_with_name)) },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Search,
                                contentDescription = stringResource(R.string.add_with_name)
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
