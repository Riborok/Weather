package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.RequestLocationPermission
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel
import com.bsuir.weather.presentation.viewmodel.SavedLocationViewModel
import com.bsuir.weather.utils.GeocoderUtils.getLocationModel
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
    onSaveLocationClick: () -> Unit
) {
    val context = LocalContext.current
    var selectedCoordinates by remember { mutableStateOf<LatLng?>(null) }
    var isDialogOpen by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }

    val currentLocation by currentLocationViewModel.currentLocation.collectAsState()
    var permissionGranted by remember { mutableStateOf(false) }
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

    GoogleMap(
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = permissionGranted),
        modifier = Modifier
            .fillMaxSize(),
                onMapClick = { latLng ->
                    selectedCoordinates = latLng
                    isDialogOpen = true
                }
    ) { }

    if (isDialogOpen && selectedCoordinates != null) {
        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            confirmButton = {
                Button(
                    onClick = {
                        isDialogOpen = false
                        getLocationModel(
                            context = context,
                            latitude = selectedCoordinates!!.latitude,
                            longitude = selectedCoordinates!!.longitude,
                            onResult = { locationModel ->
                                locationModel.address.alias = userInput
                                locationViewModel.saveLocation(locationModel)
                            },
                        )
                        onSaveLocationClick()
                    }
                ) {
                    Text(stringResource(R.string.save))
                }
            },
            dismissButton = {
                Button(
                    onClick = { isDialogOpen = false }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
            title = { Text(stringResource(R.string.enter_name)) },
            text = {
                Column {
                    Text(stringResource(R.string.langitude, selectedCoordinates!!.latitude))
                    Text(stringResource(R.string.longitude, selectedCoordinates!!.longitude))
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = userInput,
                        onValueChange = { userInput = it },
                    )
                }
            }
        )
    }

}