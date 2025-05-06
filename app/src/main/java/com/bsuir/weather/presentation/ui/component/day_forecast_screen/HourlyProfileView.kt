package com.bsuir.weather.presentation.ui.component.day_forecast_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.presentation.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.utils.constants.ProfileField
import com.bsuir.weather.utils.constants.WeatherProfile
import com.bsuir.weather.utils.ext.extractFieldValues

@Composable
fun HourlyProfileView(
    hourlyForecastList: List<HourlyForecastModel>,
    profile: WeatherProfile,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val itemWidth = 80
    val contentPadding = 16

    val fields = profile.fields
    val times = rememberSaveable {
        hourlyForecastList.extractFieldValues(ProfileField.TIME, context)
    }
    val currentIndex = rememberSaveable { mutableIntStateOf(0) }
    val currentOffset = rememberSaveable { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Time
        stickyHeader {
            val rowState = rememberLazyListState()
            LaunchedEffect(rowState.firstVisibleItemScrollOffset) {
                currentIndex.intValue = rowState.firstVisibleItemIndex
                currentOffset.intValue = rowState.firstVisibleItemScrollOffset
            }

            LaunchedEffect(currentIndex.intValue, currentOffset.intValue) {
                rowState.scrollToItem(currentIndex.intValue, currentOffset.intValue)
            }

            Surface  {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    state = rowState,
                    modifier = Modifier
                        .padding(
                            vertical = 8.dp,
                            horizontal = contentPadding.dp
                        )
                ) {
                    items(times) { time ->
                        FieldItem(
                            width = itemWidth
                        ) {
                            Text(
                                text = time.toString(),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }

        // Other fields
        items(fields) { field ->
            val items = hourlyForecastList.extractFieldValues(field, context)

            val rowState = rememberLazyListState()
            LaunchedEffect(rowState.firstVisibleItemScrollOffset) {
                currentIndex.intValue = rowState.firstVisibleItemIndex
                currentOffset.intValue = rowState.firstVisibleItemScrollOffset
            }

            LaunchedEffect(currentIndex.intValue, currentOffset.intValue) {
                rowState.scrollToItem(currentIndex.intValue, currentOffset.intValue)
            }

            var unit = ""
            if (stringResource(field.unitId).isNotEmpty()) {
                unit = ", ${stringResource(field.unitId)}"
            }

            HourlyForecast<(Any)> (
                title = stringResource(field.nameResId) + unit,
                items = items,
                contentPadding = PaddingValues(contentPadding.dp),
                lazyListState = rowState
            ) { fieldValue ->
                FieldItem(
                    width = itemWidth
                ) {
                    Text(
                        text = fieldValue.toString(),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
fun FieldItem(
    width: Int,
    content: @Composable (() -> Unit)
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(width.dp)
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}