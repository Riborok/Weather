package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bsuir.weather.utils.ext.primaryCardColors

@Composable
fun AdditionalInfoItem (
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primaryCardColors.contentColor
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text (
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        Text (
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
    }

    HorizontalDivider (
        thickness = 1.dp,
        color = textColor.copy(alpha = 0.2f)
    )
}