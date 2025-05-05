package com.bsuir.weather.presentation.ui.component.main_screen.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.presentation.ui.component.main_screen.AdditionalInfo
import com.bsuir.weather.presentation.ui.component.main_screen.DailyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.HourlyForecastItem
import com.bsuir.weather.presentation.ui.component.main_screen.MainInfo
import com.bsuir.weather.presentation.ui.component.modal.WeatherChatDialog
import com.bsuir.weather.utils.ext.formatAddress
import com.bsuir.weather.utils.ext.formattedOrUnknown
import java.time.LocalTime

@Composable
fun ForecastSuccessContent(
    forecastLocation: ForecastLocationModel,
    onOpenDrawer: () -> Unit,
    onNavigateToDayForecast: (Int, Double, Double) -> Unit,
) {
    var isChatOpen by remember { mutableStateOf(false) }

    val location = forecastLocation.location
    val forecast = forecastLocation.forecast

    val hourlyForecastList = forecast.hourlyForecasts
    val limitedHourlyForecastList = remember(hourlyForecastList) {
        val currentHour = LocalTime.now().hour
        val startIndex = hourlyForecastList.indexOfFirst {
            it.time.hour == currentHour
        }.coerceAtLeast(0)

        hourlyForecastList.subList(
            startIndex,
            (startIndex + 24).coerceAtMost(hourlyForecastList.size)
        )
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            item(key = "main_info") {
                MainInfo(
                    pickedLocationName = location.address.formattedOrUnknown(LocalContext.current),
                    currentForecast = forecast.currentForecast,
                    dailyForecast = forecast.dailyForecasts.first(),
                    onOpenDrawerClick = onOpenDrawer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 52.dp)
                )
            }
            item(key = "additional_info") {
                AdditionalInfo(
                    currentForecast = forecast.currentForecast,
                    dailyForecast = forecast.dailyForecasts.first(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
            item(key = "hourly_forecast") {
                HourlyForecast<HourlyForecastModel> (
                    title = stringResource(R.string.forecast_for_24_hour),
                    items = limitedHourlyForecastList,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) { hourlyForecast ->
                    HourlyForecastItem(
                        time = hourlyForecast.time.time.toString(),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        Image (
                            painter = painterResource(hourlyForecast.iconId),
                            contentDescription = stringResource(hourlyForecast.weatherDescriptionId),
                            modifier = Modifier
                                .size(42.dp)
                        )

                        Text (
                            text = "${hourlyForecast.temperature} ${stringResource(R.string.celsius_degrees)}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            item(key = "daily_forecast") {
                DailyForecast(
                    dailyForecastList = forecast.dailyForecasts,
                    onNavigateToDayForecast = { index ->
                        onNavigateToDayForecast(index, location.latitude, location.longitude)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }

        FloatingActionButton(
            onClick = { isChatOpen = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.QuestionAnswer,
                contentDescription = stringResource(R.string.content_description_ai_chat)
            )
        }

        if (isChatOpen) {
            WeatherChatDialog(
                forecastLocation = forecastLocation,
                onDismiss = { isChatOpen = false },
            )
        }
    }
}
