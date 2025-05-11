package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.unit.Dp
import com.bsuir.weather.utils.ext.primaryCardColors
import com.bsuir.weather.utils.ext.secondaryCardColors

@Composable
fun <T> HourlyForecast (
    title: String,
    items: List<T>,
    modifier: Modifier = Modifier,
    contentPaddingSize: Dp = 16.dp,
    lazyListState: LazyListState = rememberLazyListState(),
    itemContent: @Composable (ColumnScope.(T) -> Unit),
) {
    Card (
        modifier = modifier,
        colors = MaterialTheme.colorScheme.secondaryCardColors
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(vertical = contentPaddingSize)
        ) {
            Text (
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = contentPaddingSize)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                state = lazyListState,
                contentPadding = PaddingValues(horizontal = contentPaddingSize),
            ) {
                items(items) { hourlyForecast ->
                    itemContent(hourlyForecast)
                }
            }
        }
    }
}
