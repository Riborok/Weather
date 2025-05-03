package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.presentation.state.ForecastState
import com.bsuir.weather.presentation.ui.component.main_screen.content.ForecastErrorContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.ForecastSuccessContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.LoadingContent
import com.bsuir.weather.presentation.ui.component.modal.location.LocationModal
import com.bsuir.weather.presentation.ui.utils.RequestLocationPermission
import com.bsuir.weather.presentation.ui.utils.getDefaultLocation
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel
import com.bsuir.weather.presentation.viewmodel.ForecastViewModel
import com.bsuir.weather.presentation.viewmodel.PickedLocationViewModel
import com.bsuir.weather.presentation.viewmodel.SavedLocationViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    onNavigate: (String) -> Unit,
    forecastViewModel: ForecastViewModel = hiltViewModel(),
    currentLocationViewModel: CurrentLocationViewModel = hiltViewModel(),
    pickedLocationViewModel: PickedLocationViewModel = hiltViewModel(),
    savedLocationViewModel: SavedLocationViewModel = hiltViewModel()
) {
    // Default location
    val defaultLocation = getDefaultLocation()

    // Drawer
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Scope
    val scope = rememberCoroutineScope()

    // Permission
    var permissionGranted by remember { mutableStateOf(false) }

    // Forecast
    val forecastState by forecastViewModel.forecastState.collectAsState()

    // Loading state
    var isRefreshing by remember { mutableStateOf(false) }

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
                savedLocations.firstOrNull()
            )
        }
    }

    LaunchedEffect(pickedLocation) {
        forecastViewModel.loadForecast(pickedLocation ?: defaultLocation)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            forecastViewModel.loadForecast(pickedLocation ?: defaultLocation)
        }
    )

    LaunchedEffect(forecastState) {
        if (isRefreshing && forecastState !is ForecastState.Loading) {
            isRefreshing = false
        }
    }

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
                onLocationClick = { location ->
                    pickedLocationViewModel.setPickedLocation(location)
                    scope.launch { drawerState.close() }
                },
                onLocationDelete = { location ->
                    savedLocationViewModel.removeLocation(location)
                },
                onNavigate = onNavigate,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.75f)
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            when (forecastState) {
                is ForecastState.Loading -> LoadingContent()
                is ForecastState.Success -> ForecastSuccessContent(
                    forecastLocation = (forecastState as ForecastState.Success).forecastLocation,
                    onOpenDrawer = { scope.launch { drawerState.open() } }
                )
                is ForecastState.Error -> ForecastErrorContent(
                    errorMessage = (forecastState as ForecastState.Error).error.message
                        ?: stringResource(R.string.unknown_error)
                )
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                backgroundColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.16f),
                contentColor    = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )
        }
    }
}
