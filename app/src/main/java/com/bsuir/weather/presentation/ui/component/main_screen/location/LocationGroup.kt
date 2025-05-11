package com.bsuir.weather.presentation.ui.component.main_screen.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.presentation.ui.component.main_screen.location.item.LocationItem
import com.bsuir.weather.presentation.ui.component.main_screen.location.item.TitleItem
import com.bsuir.weather.utils.ext.primaryCardColors
import com.bsuir.weather.utils.ext.secondaryCardColors

@Composable
fun LocationGroup(
    title: String,
    locations: List<LocationModel>,
    onLocationClick: (location: LocationModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        TitleItem(title)

        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            locations.forEach { location ->
                Card (
                    colors = MaterialTheme.colorScheme.secondaryCardColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = { onLocationClick(location) },
                        )
                        .padding(horizontal = 2.dp)
                ) {
                    LocationItem(
                        location = location,
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}
