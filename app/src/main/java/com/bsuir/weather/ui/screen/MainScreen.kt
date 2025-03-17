package com.bsuir.weather.ui.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsuir.weather.ui.component.bar.BottomNavItem
import com.bsuir.weather.ui.component.bar.BottomNavigationBar
import com.bsuir.weather.ui.component.bar.TopBar
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Surface
import com.bsuir.weather.ui.component.modal.SettingsModal

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Surface {
        ModalNavigationDrawer(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            drawerState = drawerState,
            drawerContent = {
                SettingsModal()
            }
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        onSettingsClick = { scope.launch { drawerState.open() } }
                    )
                },
                bottomBar = { BottomNavigationBar(navController = navController) }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = BottomNavItem.Weather.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(BottomNavItem.Weather.route) { WeatherScreen() }
                    composable(BottomNavItem.Forecast.route) { ForecastScreen() }
                    composable(BottomNavItem.Ecology.route) { EcologyScreen() }
                }
            }
        }
    }
}
