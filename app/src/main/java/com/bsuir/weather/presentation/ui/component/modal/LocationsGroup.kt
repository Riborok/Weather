package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.model.LocationModel

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
        Text (
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )

        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            locations.forEach { location ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onLocationClick(location) })
                ) {
                    Column (
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp,
                                horizontal = 12.dp
                            )
                    ) {
                        if (location.address.alias != null) {
                            Text(
                                text = location.address.alias!!,
                                style = MaterialTheme.typography.titleLarge,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )

                            Text(
                                text = location.address.formatAddress(),
                                style = MaterialTheme.typography.bodyLarge,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        } else {
                            Text(
                                text = location.address.formatAddress(),
                                style = MaterialTheme.typography.titleLarge,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}