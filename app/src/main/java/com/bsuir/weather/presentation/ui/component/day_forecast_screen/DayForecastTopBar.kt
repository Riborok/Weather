package com.bsuir.weather.presentation.ui.component.day_forecast_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.presentation.ui.utils.getFormattedDayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DayForecastTopBar(
    onNavigateToMainClick: () -> Unit,
    date: LocalDate,
    modifier: Modifier = Modifier
) {
    Surface (
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { onNavigateToMainClick() },
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }

            val javaDate = date.toJavaLocalDate()
            val formatter = DateTimeFormatter.ofPattern("dd.MM")
            val formattedJavaDate = javaDate.format(formatter)
            Text(
                text = "${getFormattedDayOfWeek(date.dayOfWeek)}, " + formattedJavaDate,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(0.9f)
            )
        }
    }
}