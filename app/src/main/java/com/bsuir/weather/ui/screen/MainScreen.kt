package com.bsuir.weather.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsuir.weather.ui.nav.BottomNavItem
import com.bsuir.weather.ui.nav.BottomNavigationBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
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
