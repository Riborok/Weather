package com.bsuir.weather.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsuir.weather.ui.component.main_screen.AdditionalInfo
import com.bsuir.weather.ui.component.main_screen.DailyForecast
import com.bsuir.weather.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.ui.component.main_screen.MainInfo
import com.bsuir.weather.ui.component.modal.LocationModal
import com.bsuir.weather.ui.theme.WeatherTheme
import com.bsuir.weather.usecase.DailyForecastManager
import com.bsuir.weather.usecase.HourlyForecastManager
import com.bsuir.weather.usecase.LocationManager
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var drawerMenuExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    //TODO: Добавить прослойку viewmodel вместо этого
    val hourlyForecastManger = HourlyForecastManager()
    val dailyForecastManager = DailyForecastManager()
    val locationManager = LocationManager()

    Surface {
        ModalNavigationDrawer(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            drawerState = drawerState,
            drawerContent = {
                LocationModal (
                    currentLocation = locationManager.getCurrentLocation(),
                    savedLocations = locationManager.getSavedLocations(),
                    drawerMenuExpanded = drawerMenuExpanded,
                    onDrawerMenuExpandedChange = { drawerMenuExpanded = !drawerMenuExpanded },
                    onDrawerMenuDismissRequest = { drawerMenuExpanded = false },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.75f)
                )
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

                DailyForecast(
                    dailyForecastManager.getHourlyForecastList(),
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