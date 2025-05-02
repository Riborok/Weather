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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.presentation.ui.utils.RequestLocationPermission
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel
import com.bsuir.weather.presentation.viewmodel.SavedLocationViewModel
import com.bsuir.weather.utils.AddressUtils.fetchLocationModelFromCoordinates
import com.bsuir.weather.utils.mapZoom
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    currentLocationViewModel: CurrentLocationViewModel = hiltViewModel(),
    locationViewModel: SavedLocationViewModel = hiltViewModel(),
    onNavigateToMainClick: () -> Unit
) {
    val context = LocalContext.current

    var permissionGranted by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }
    var showEmptyNameDialog by remember { mutableStateOf(false) }

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

    if (currentLocation != null) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            LatLng(currentLocation!!.latitude, currentLocation!!.longitude),
            mapZoom
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .weight(0.3f)
        ) {
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
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
                        onClick = { fetchLocationModelFromCoordinates(
                            context = context,
                            latitude = selectedCoordinates!!.latitude,
                            longitude = selectedCoordinates!!.longitude,
                            onResult = { locationModel ->
                                locationModel.address.alias = userInput
                                locationViewModel.saveLocation(locationModel)
                            },
                        )
                            onNavigateToMainClick()
                        }
                    ) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = stringResource(R.string.cancel)
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.latitude) + ": " + (selectedCoordinates?.latitude ?: ""),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.longitude) + ": " + (selectedCoordinates?.longitude ?: ""),
                    style = MaterialTheme.typography.bodyLarge
                )
                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = { Text(stringResource(R.string.enter_name)) },
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
                .weight(1f)
        ) { }
    }

    if (showEmptyNameDialog) {
        AlertDialog(
            onDismissRequest = { showEmptyNameDialog = false },
            title = { Text( text = stringResource(R.string.warning)) },
            text = { Text( text = stringResource(R.string.empty_location_name_error)) },
            confirmButton = {
                Button(onClick = { showEmptyNameDialog = false }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}