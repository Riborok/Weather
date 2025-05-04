package com.bsuir.weather.presentation.ui.component.main_screen.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.presentation.ui.component.main_screen.AdditionalInfo
import com.bsuir.weather.presentation.ui.component.main_screen.DailyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.MainInfo
import com.bsuir.weather.presentation.ui.component.modal.WeatherChatDialog
import com.bsuir.weather.utils.ext.formatAddress

@Composable
fun ForecastSuccessContent(
    forecastLocation: ForecastLocationModel,
    onOpenDrawer: () -> Unit,
) {
    var isChatOpen by remember { mutableStateOf(false) }

    val location = forecastLocation.location
    val forecast = forecastLocation.forecast

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            item {
                MainInfo(
                    pickedLocationName = location.address.formatAddress()
                        ?: stringResource(R.string.unknown_address),
                    currentForecast = forecast.currentForecast,
                    dailyForecast = forecast.dailyForecasts.first(),
                    onOpenDrawerClick = onOpenDrawer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 52.dp)
                )
            }
            item {
                AdditionalInfo(
                    currentForecast = forecast.currentForecast,
                    dailyForecast = forecast.dailyForecasts.first(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
            item {
                HourlyForecast(
                    forecast.hourlyForecasts,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
            item {
                DailyForecast(
                    forecast.dailyForecasts,
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
