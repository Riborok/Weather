package com.bsuir.weather.ui.component.bar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    NavigationBar {
        BottomNavItem.entries.forEach { item ->
            val selected = currentRoute == item.route
            val animatedIconSize by animateDpAsState(
                targetValue = if (selected) 32.dp else 26.dp,
                animationSpec = tween(durationMillis = 250)
            )
            val animatedAlpha by animateFloatAsState(
                targetValue = if (selected) 1f else 0.4f,
                animationSpec = tween(durationMillis = 250)
            )
            val fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.getTitle(context),
                        modifier = Modifier.size(animatedIconSize).graphicsLayer { alpha = animatedAlpha }
                    )
                },
                label = {
                    Text(
                        text = item.getTitle(context),
                        fontWeight = fontWeight,
                        modifier = Modifier.graphicsLayer { alpha = animatedAlpha }
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}
