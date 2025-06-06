package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bsuir.weather.utils.ext.getCapitalizedDisplayName
import com.bsuir.weather.utils.ext.primaryCardColors
import kotlinx.datetime.DayOfWeek

@Composable
fun DailyForecastItem(
    dayName: DayOfWeek,
    @DrawableRes icon: Int,
    weatherDescriptionId: Int,
    minTemperature: String,
    maxTemperature: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primaryCardColors.contentColor,
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text (
            text = dayName.getCapitalizedDisplayName(LocalContext.current),
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
        )

        Image (
            painter = painterResource(icon),
            contentDescription = stringResource(weatherDescriptionId),
            modifier = Modifier
                .size(42.dp)
                .weight(0.5f)
        )

        TemperatureRange (
            minTemperature = minTemperature,
            maxTemperature = maxTemperature,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }

    HorizontalDivider(
        thickness = 1.dp,
        color = textColor.copy(alpha = 0.2f)
    )
}