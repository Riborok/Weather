package com.bsuir.weather.presentation.ui.component.day_forecast_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.presentation.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.utils.constants.WeatherProfile
import com.bsuir.weather.utils.ext.extractFieldValues

@Composable
fun HourlyProfileView(
    hourlyForecastList: List<HourlyForecastModel>,
    profile: WeatherProfile,
    modifier: Modifier = Modifier
) {
    val fields = profile.fields

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(fields) { field ->
            val items = hourlyForecastList.extractFieldValues(field)

            HourlyForecast<Any> (
                title = stringResource(field.nameResId),
                items = items
            ) { fieldValue ->
                val unit = if(stringResource(field.unitId).isNotEmpty())
                    " ${stringResource(field.unitId)}"
                else
                    ""

                Text(
                    text = fieldValue.toString() + unit,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}