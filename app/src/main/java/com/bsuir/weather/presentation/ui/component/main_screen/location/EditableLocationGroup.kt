package com.bsuir.weather.presentation.ui.component.main_screen.location

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.presentation.ui.component.main_screen.location.item.LocationItem
import com.bsuir.weather.presentation.ui.component.main_screen.location.item.TitleItem
import com.bsuir.weather.utils.ext.cardColors
import kotlinx.coroutines.delay

@Composable
fun EditableLocationGroup(
    title: String,
    locations: List<LocationModel>,
    onLocationClick: (location: LocationModel) -> Unit,
    onEditClick: (location: LocationModel) -> Unit,
    onDeleteClick: (location: LocationModel) -> Unit,
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
                var isLongPressed by remember { mutableStateOf(false) }

                if (isLongPressed) {
                    LaunchedEffect(location) {
                        delay(3000)
                        isLongPressed = false
                    }
                }

                val infiniteTransition = rememberInfiniteTransition()
                val shakeOffset by infiniteTransition.animateFloat(
                    initialValue = -4f,
                    targetValue = 4f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(120, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Card(
                    colors = MaterialTheme.colorScheme.cardColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = {
                                if (!isLongPressed) onLocationClick(location)
                                isLongPressed = false
                            },
                            onLongClick = { isLongPressed = true }
                        )
                        .animateContentSize()
                        .graphicsLayer {
                            translationX = if (isLongPressed) shakeOffset else 0f
                        }
                        .padding(horizontal = 2.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LocationItem(
                            location = location,
                            modifier = Modifier.weight(1f)
                        )

                        if (isLongPressed) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(start = 8.dp)
                            ) {
                                IconButton(onClick = {
                                    isLongPressed = false
                                    onEditClick(location)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = stringResource(R.string.edit),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                                IconButton(onClick = {
                                    isLongPressed = false
                                    onDeleteClick(location)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.delete),
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
