package com.bsuir.weather.presentation.ui.component.main_screen.location

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.presentation.ui.component.modal.location.EditLocationDialog

@Composable
fun EditableLocationGroupWithDialog(
    title: String,
    locations: List<LocationModel>,
    onLocationClick: (location: LocationModel) -> Unit,
    onLocationRename: (location: LocationModel, newAlias: String) -> Unit,
    onLocationDelete: (location: LocationModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogLocation by remember { mutableStateOf<LocationModel?>(null) }

    if (showDialog) {
        dialogLocation?.let { location ->
            EditLocationDialog(
                location = location,
                onDismissRequest = {
                    showDialog = false
                    dialogLocation = null
                },
                onLocationRename = { loc, alias ->
                    onLocationRename(loc, alias)
                    showDialog = false
                    dialogLocation = null
                },
            )
        }
    }

    EditableLocationGroup(
        title = title,
        locations = locations,
        onLocationClick = onLocationClick,
        onEditClick = { location ->
            showDialog = true
            dialogLocation = location
        },
        onDeleteClick = onLocationDelete,
        modifier = modifier,
    )
}
