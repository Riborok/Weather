package com.bsuir.weather.presentation.ui.component.main_screen.location.item

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TitleItem(
    title: String,
) {
    Text (
        text = title,
        style = MaterialTheme.typography.titleSmall,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
    )
}