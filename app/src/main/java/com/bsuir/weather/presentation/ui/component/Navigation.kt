package com.bsuir.weather.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsuir.weather.presentation.ui.screen.MainScreen
import com.bsuir.weather.presentation.ui.screen.MapScreen
import com.bsuir.weather.utils.Route

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Main.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Route.Main.name) {
            MainScreen(
                onAddWithMapClick = { navController.navigate(Route.Map.name) }
            )
        }

        composable(route = Route.Map.name) {
            MapScreen(
                onSaveLocationClick = {
                    navController.popBackStack()
                    navController.navigate(Route.Main.name)
                }
            )
        }
    }
}