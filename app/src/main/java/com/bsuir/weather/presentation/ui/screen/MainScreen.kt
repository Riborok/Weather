package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.presentation.state.ForecastState
import com.bsuir.weather.presentation.ui.component.main_screen.AdditionalInfo
import com.bsuir.weather.presentation.ui.component.main_screen.DailyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.MainInfo
import com.bsuir.weather.presentation.ui.component.modal.LocationModal
import com.bsuir.weather.presentation.ui.theme.WeatherTheme
import com.bsuir.weather.presentation.viewmodel.ForecastViewModel
import com.bsuir.weather.presentation.viewmodel.LocationViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    forecastViewModel: ForecastViewModel = hiltViewModel(),
    locationViewModel: LocationViewModel = hiltViewModel(),
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var drawerMenuExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        forecastViewModel.loadForecast(53.911, 27.593)
    }

    val forecastState by forecastViewModel.forecastState.collectAsState()
    val currentLocation by locationViewModel.currentLocation.collectAsState()
    val savedLocations by locationViewModel.savedLocations.collectAsState()

    Surface {
        ModalNavigationDrawer(
            modifier = Modifier
                .windowInsetsPadding(
                    WindowInsets.statusBars.union(WindowInsets.navigationBars)
                ),
            drawerState = drawerState,
            drawerContent = {
                LocationModal(
                    currentLocation = currentLocation,
                    savedLocations = savedLocations,
                    drawerMenuExpanded = drawerMenuExpanded,
                    onDrawerMenuExpandedChange = { drawerMenuExpanded = !drawerMenuExpanded },
                    onDrawerMenuDismissRequest = { drawerMenuExpanded = false },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.75f)
                )
            }
        ) {
            when (forecastState) {
                is ForecastState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is ForecastState.Success -> {
                    val forecast = (forecastState as ForecastState.Success).forecast
                    LazyColumn (
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
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
                                forecast.hourlyForecastModels,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )

                            DailyForecast(
                                forecast.dailyForecastModels,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
                is ForecastState.Error -> {
                    val errorMessage = (forecastState as ForecastState.Error).error.message ?: "Unknown error"
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Error: $errorMessage", color = Color.Red)
                        }
                    }
                }
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