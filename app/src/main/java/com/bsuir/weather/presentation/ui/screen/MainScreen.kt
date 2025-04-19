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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.RequestLocationPermission
import com.bsuir.weather.presentation.state.ForecastState
import com.bsuir.weather.presentation.ui.component.main_screen.AdditionalInfo
import com.bsuir.weather.presentation.ui.component.main_screen.DailyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.HourlyForecast
import com.bsuir.weather.presentation.ui.component.main_screen.MainInfo
import com.bsuir.weather.presentation.ui.component.modal.LocationModal
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel
import com.bsuir.weather.presentation.viewmodel.ForecastViewModel
import com.bsuir.weather.presentation.viewmodel.PickedLocationViewModel
import com.bsuir.weather.presentation.viewmodel.SavedLocationViewModel
import com.bsuir.weather.utils.defaultLocation
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onNavigate: (String) -> Unit,
    forecastViewModel: ForecastViewModel = hiltViewModel(),
    currentLocationViewModel: CurrentLocationViewModel = hiltViewModel(),
    pickedLocationViewModel: PickedLocationViewModel = hiltViewModel(),
    savedLocationViewModel: SavedLocationViewModel = hiltViewModel()
) {
    // Drawer
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Scope
    val scope = rememberCoroutineScope()

    // Permission
    var permissionGranted by remember { mutableStateOf(false) }

    // Forecast
    val forecastState by forecastViewModel.forecastState.collectAsState()

    // Location
    val currentLocation by currentLocationViewModel.currentLocation.collectAsState()
    val pickedLocation by pickedLocationViewModel.pickedLocation.collectAsState()
    val savedLocations by savedLocationViewModel.savedLocations.collectAsState()

    RequestLocationPermission { granted ->
        permissionGranted = granted
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            currentLocationViewModel.fetchCurrentLocation()
        }
    }

    LaunchedEffect(currentLocation) {
        if (permissionGranted && currentLocation != null) {
            pickedLocationViewModel.setPickedLocation(currentLocation)
        } else {
            pickedLocationViewModel.setPickedLocation(
                if (savedLocations.isNotEmpty())
                    savedLocations[0]
                else
                    null
            )
        }
    }

    LaunchedEffect(pickedLocation) {
        forecastViewModel.loadForecast(
            pickedLocation?.latitude ?: defaultLocation.latitude,
            pickedLocation?.longitude ?: defaultLocation.longitude
        )
    }

    ModalNavigationDrawer(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.statusBars.union(WindowInsets.navigationBars)
            ),
        drawerState = drawerState,
        drawerContent = {
            LocationModal(
                savedLocations,
                onNavigate = onNavigate,
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
                            pickedLocationName = pickedLocation?.address?.formatAddress() ?: "",
                            onOpenDrawerClick = { scope.launch { drawerState.open() } },
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