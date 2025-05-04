package com.bsuir.weather.presentation.ui.component.main_screen.location

import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.ext.formatAddress

@Composable
fun LocationGroup(
    title: String,
    locations: List<LocationModel>,
    onLocationClick: (location: LocationModel) -> Unit,
    modifier: Modifier = Modifier,
    onLocationLongClick: ((location: LocationModel) -> Unit)? = null,
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
                        .combinedClickable(
                            onClick = { onLocationClick(location) },
                            onLongClick = onLocationLongClick?.let { { it(location) } }
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(12.dp)
                    ) {
                        location.address.alias?.let { alias ->
                            Text(
                                text = alias,
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = location.address.formatAddress()
                                    ?: stringResource(R.string.unknown_address),
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        } ?: Text(
                            text = location.address.formatAddress()
                                ?: stringResource(R.string.unknown_address),
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
