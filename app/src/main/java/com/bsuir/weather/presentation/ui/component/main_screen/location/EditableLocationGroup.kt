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
fun EditableLocationGroup(
    title: String,
    locations: List<LocationModel>,
    onLocationClick: (location: LocationModel) -> Unit,
    onLocationRename: (location: LocationModel, newAlias: String) -> Unit,
    onLocationDelete: (location: LocationModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var dialogLocation by remember { mutableStateOf<LocationModel?>(null) }
    var draftAlias by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog && dialogLocation != null) {
        dialogLocation?.let { location ->
            EditLocationDialog(
                location = location,
                draftAlias = draftAlias,
                onDismissRequest = {
                    showDialog = false
                    dialogLocation = null
                },
                onLocationRename = { loc, alias ->
                    onLocationRename(loc, alias)
                    showDialog = false
                    dialogLocation = null
                },
                onLocationDelete = { loc ->
                    onLocationDelete(loc)
                    showDialog = false
                    dialogLocation = null
                },
                onAliasChange = { draftAlias = it }
            )
        }
    }

    LocationGroup(
        title = title,
        locations = locations,
        onLocationClick = onLocationClick,
        onLocationLongClick = { location ->
            dialogLocation = location
            draftAlias = location.address.alias ?: ""
            showDialog = true
        },
        modifier = modifier
    )
}
