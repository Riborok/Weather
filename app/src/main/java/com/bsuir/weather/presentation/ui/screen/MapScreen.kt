package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.presentation.ui.utils.RequestLocationPermission
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel
import com.bsuir.weather.presentation.viewmodel.MapViewModel
import com.bsuir.weather.utils.constants.mapZoom
import com.bsuir.weather.utils.ext.onSuccess
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    onNavigateToMainClick: () -> Unit,
    modifier: Modifier = Modifier,
    currentLocationViewModel: CurrentLocationViewModel = hiltViewModel(),
    mapViewModel: MapViewModel = hiltViewModel()
) {
    var permissionGranted by remember { mutableStateOf(false) }
    val currentLocationState by currentLocationViewModel.currentLocationState.collectAsState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), mapZoom)
    }

    val userInput by mapViewModel.userInput.collectAsState()
    val selectedCoordinates by mapViewModel.selectedCoordinates.collectAsState()

    RequestLocationPermission { granted ->
        permissionGranted = granted
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            currentLocationViewModel.fetchCurrentLocation()
        }
    }

    currentLocationState.onSuccess { currentLocation ->
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            LatLng(currentLocation.latitude, currentLocation.longitude), mapZoom
        )
    }

    Column (
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
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
                        onClick = { onNavigateToMainClick() },
                        modifier = Modifier.size(50.dp)
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
                    IconButton(
                        onClick = {
                            mapViewModel.saveLocation()
                            onNavigateToMainClick()
                        },
                        enabled = selectedCoordinates != null,
                        modifier = Modifier.size(50.dp)
                    ) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = stringResource(R.string.done)
                        )
                    }
                }

                OutlinedTextField(
                    value = userInput,
                    onValueChange = mapViewModel::onUserInputChange,
                    label = { Text(stringResource(R.string.enter_alias)) },
                    maxLines = 1,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }
        }

        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = permissionGranted),
            onMapClick = mapViewModel::onMapClick,
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
