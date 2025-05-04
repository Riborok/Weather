package com.bsuir.weather.presentation.ui.component.main_screen.location.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.utils.ext.formatAddress

@Composable
fun LocationItem(
    location: LocationModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
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