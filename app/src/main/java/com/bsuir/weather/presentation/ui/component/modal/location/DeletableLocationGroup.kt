package com.bsuir.weather.presentation.ui.component.modal.location

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.LocationModel

@Composable
fun DeletableLocationGroup(
    title: String,
    locations: List<LocationModel>,
    onLocationClick: (location: LocationModel) -> Unit,
    onLocationDelete: (location: LocationModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var toDelete by remember { mutableStateOf<LocationModel?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        toDelete?.let { location ->
            val address = location.address
            val closeDialog = { showDialog = false; toDelete = null }
            AlertDialog(
                onDismissRequest = closeDialog,
                title = { Text(text = stringResource(R.string.delete_location_title)) },
                text = { Text(
                    text = stringResource(R.string.delete_location_message,
                        address.aliasWidthFormatAddress() ?: stringResource(R.string.unknown_address)
                )) },
                confirmButton = {
                    TextButton(onClick = {
                        onLocationDelete(location)
                        closeDialog()
                    }) {
                        Text(text = stringResource(R.string.delete))
                    }
                },
                dismissButton = {
                    TextButton(onClick = closeDialog) {
                        Text(text = stringResource(R.string.cancel))
                    }
                }
            )
        }
    }

    LocationGroup(
        title = title,
        locations = locations,
        onLocationClick = onLocationClick,
        onLocationLongClick = { location ->
            toDelete = location
            showDialog = true
        },
        modifier = modifier
    )
}