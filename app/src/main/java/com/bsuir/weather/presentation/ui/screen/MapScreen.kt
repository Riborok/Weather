package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.presentation.ui.utils.RequestLocationPermission
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel
import com.bsuir.weather.presentation.viewmodel.SavedLocationViewModel
import com.bsuir.weather.utils.AddressUtils.fetchLocationModelFromCoordinates
import com.bsuir.weather.utils.constants.mapZoom
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    onNavigateToMainClick: () -> Unit,
    modifier: Modifier = Modifier,
    currentLocationViewModel: CurrentLocationViewModel = hiltViewModel(),
    locationViewModel: SavedLocationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var permissionGranted by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }

    var selectedCoordinates by remember { mutableStateOf<LatLng?>(null) }

    val currentLocation by currentLocationViewModel.currentLocation.collectAsState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), mapZoom)
    }

    RequestLocationPermission { granted ->
        permissionGranted = granted
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            currentLocationViewModel.fetchCurrentLocation()
        }
    }

    currentLocation?.let {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            LatLng(it.latitude, it.longitude), mapZoom
        )
    }

    Column (
        modifier = modifier
    ) {
        Surface(
            tonalElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton (
                        onClick = { onNavigateToMainClick() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.cancel)
                        )
                    }
                    Text(
                        text = stringResource(R.string.add_with_map),
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton (
                        onClick = {
                            selectedCoordinates?.let { coords ->
                                coroutineScope.launch {
                                    val location = fetchLocationModelFromCoordinates(
                                        context = context,
                                        latitude = coords.latitude,
                                        longitude = coords.longitude
                                    )
                                    location.address.alias = userInput
                                    locationViewModel.saveLocation(location)
                                }
                                onNavigateToMainClick()
                            }
                        },
                        enabled = selectedCoordinates != null
                    ) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = stringResource(R.string.done)
                        )
                    }
                }

                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = { Text(stringResource(R.string.enter_alias)) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = permissionGranted),
            onMapClick = { latLng ->
                selectedCoordinates = latLng
            },
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            selectedCoordinates?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = userInput.ifBlank { stringResource(R.string.selected_point) }
                )
            }
        }
    }
}
