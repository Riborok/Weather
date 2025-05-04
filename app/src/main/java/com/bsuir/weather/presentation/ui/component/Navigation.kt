package com.bsuir.weather.presentation.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bsuir.weather.presentation.ui.screen.AddressSearchScreen
import com.bsuir.weather.presentation.ui.screen.DayForecastScreen
import com.bsuir.weather.presentation.ui.screen.MainScreen
import com.bsuir.weather.presentation.ui.screen.MapScreen
import com.bsuir.weather.utils.constants.Route

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Surface {
        NavHost(
            navController = navController,
            startDestination = Route.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.statusBars.union(WindowInsets.navigationBars)
                )
        ) {
            composable(route = Route.Main.name) {
                MainScreen(
                    onNavigate = { navController.navigate(it) },
                    onNavigateToDayForecast = { dailyForecastModelIndex, latitude, longitude ->
                        navController.navigate(
                            route = Route.DayForecast.name
                                    + "/$dailyForecastModelIndex/$latitude/$longitude"
                        )
                    }
                )
            }

            composable(route = Route.Map.name) {
                MapScreen(
                    onNavigateToMainClick = {
                        navController.popBackStack()
                        navController.navigate(Route.Main.name)
                    }
                )
            }

            composable(route = Route.AddressSearch.name) {
                AddressSearchScreen(
                    onNavigateToMainClick = {
                        navController.popBackStack()
                        navController.navigate(Route.Main.name)
                    }
                )
            }

            composable(
                route = Route.DayForecast.name + "/{dayIndex}/{latitude}/{longitude}",
                arguments = listOf (
                    navArgument("dayIndex") { type = NavType.IntType },
                    navArgument("latitude") { type = NavType.StringType },
                    navArgument("longitude") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                DayForecastScreen(
                    dailyForecastModelIndex = backStackEntry.arguments?.getInt("dayIndex") ?: -1,
                    latitude = backStackEntry.arguments?.getInt("latitude")?.toDouble(),
                    longitude = backStackEntry.arguments?.getInt("longitude")?.toDouble(),
                    onNavigateToMainClick = {
                        navController.popBackStack()
                        navController.navigate(Route.Main.name)
                    }
                )
            }
        }
    }
}