package com.bsuir.weather.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsuir.weather.ui.component.main_screen.AdditionalInfo
import com.bsuir.weather.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.ui.component.main_screen.HourlyForecastItem
import com.bsuir.weather.ui.component.main_screen.MainInfo
import com.bsuir.weather.ui.component.modal.SettingsModal
import com.bsuir.weather.ui.theme.WeatherTheme
import com.bsuir.weather.usecase.HourlyForecastManager
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //TODO: Добавить прослойку viewmodel вместо этого
    val hourlyForecastManger = HourlyForecastManager()

    Surface {
        ModalNavigationDrawer(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            drawerState = drawerState,
            drawerContent = {
                SettingsModal()
            }
        ) {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainInfo(
                    drawerOpen = { scope.launch { drawerState.open() } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                AdditionalInfo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                HourlyForecast(
                    hourlyForecastManger.getHourlyForecastList(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    WeatherTheme {
        MainScreen()
    }
}