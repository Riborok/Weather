package com.bsuir.weather.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.presentation.state.ForecastState
import com.bsuir.weather.presentation.ui.component.main_screen.content.ErrorContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.ForecastSuccessContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.LoadingContent
import com.bsuir.weather.presentation.ui.component.main_screen.content.NoContent
import com.bsuir.weather.presentation.ui.component.modal.location.LocationModal
import com.bsuir.weather.presentation.ui.utils.RequestLocationPermission
import com.bsuir.weather.presentation.ui.utils.RequestNotificationPermission
import com.bsuir.weather.presentation.viewmodel.CurrentLocationViewModel
import com.bsuir.weather.presentation.viewmodel.ForecastViewModel
import com.bsuir.weather.presentation.viewmodel.PickedLocationViewModel
import com.bsuir.weather.presentation.viewmodel.SavedLocationViewModel
import com.bsuir.weather.utils.ext.deepCopy
import com.bsuir.weather.utils.ext.onNoContent
import com.bsuir.weather.utils.ext.onSuccess
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    onNavigate: (String) -> Unit,
    onNavigateToDayForecast: (Int) -> Unit,
    modifier: Modifier = Modifier,
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

    // Loading state
    var isRefreshing by remember { mutableStateOf(false) }

    // Location
    val currentLocationState by currentLocationViewModel.currentLocationState.collectAsState()
    val pickedLocationState by pickedLocationViewModel.pickedLocationState.collectAsState()
    val savedLocations by savedLocationViewModel.savedLocations.collectAsState()

    RequestLocationPermission { granted ->
        permissionGranted = granted
    }
    RequestNotificationPermission()

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            currentLocationViewModel.fetchCurrentLocation()
        }
    }

    LaunchedEffect(currentLocationState) {
        currentLocationState.onSuccess { currentLocation ->
            pickedLocationViewModel.setPickedLocation(currentLocation)
        }

        currentLocationState.onNoContent {
            pickedLocationViewModel.setPickedLocation(savedLocations.firstOrNull())
        }
    }

    LaunchedEffect(pickedLocationState) {
        pickedLocationState.onSuccess { pickedLocation ->
            forecastViewModel.loadForecast(pickedLocation)
        }

        pickedLocationState.onNoContent {
            forecastViewModel.setNoContent()
        }
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            pickedLocationState.onSuccess { pickedLocation ->
                isRefreshing = true
                forecastViewModel.loadForecast(pickedLocation)
            }
        }
    )

    LaunchedEffect(forecastState) {
        if (isRefreshing && forecastState !is ForecastState.Loading) {
            isRefreshing = false
        }
    }

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            LocationModal(
                currentLocationState = currentLocationState,
                savedLocations = savedLocations,
                onLocationClick = { location ->
                    pickedLocationViewModel.setPickedLocation(location)
                    scope.launch { drawerState.close() }
                },
                onLocationRename = { location, newAlias ->
                    val newLocation = location.deepCopy().also { it.address.alias = newAlias }
                    savedLocationViewModel.updateLocation(location, newLocation)
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
        Surface(
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            when (forecastState) {
                is ForecastState.NoContent -> NoContent()
                is ForecastState.Loading -> LoadingContent()
                is ForecastState.Success -> ForecastSuccessContent(
                    forecastLocation = (forecastState as ForecastState.Success).forecastLocation,
                    onNavigateToDayForecast = onNavigateToDayForecast,
                    onOpenDrawer = { scope.launch { drawerState.open() } }
                )
                is ForecastState.Error -> ErrorContent(
                    errorMessage = (forecastState as ForecastState.Error).error.message
                        ?: stringResource(R.string.unknown_error)
                )
            }

            Box (
                modifier = Modifier.fillMaxSize()
            ) {
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
}
