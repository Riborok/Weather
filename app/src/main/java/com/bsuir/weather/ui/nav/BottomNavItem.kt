package com.bsuir.weather.ui.nav

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.ui.graphics.vector.ImageVector
import com.bsuir.weather.R

enum class BottomNavItem(private val titleResId: Int, val icon: ImageVector, val route: String) {
    Weather(R.string.weather, Icons.Filled.WbSunny, "weather"),
    Forecast(R.string.forecast, Icons.Filled.AccessTime, "forecast"),
    Ecology(R.string.ecology, Icons.Outlined.Eco, "ecology");

    fun getTitle(context: Context): String {
        return context.getString(titleResId)
    }
}
