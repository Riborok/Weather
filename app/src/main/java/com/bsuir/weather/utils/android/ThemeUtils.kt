package com.bsuir.weather.utils.android

import android.content.Context
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import com.bsuir.weather.presentation.ui.theme.Pink40
import com.bsuir.weather.presentation.ui.theme.Pink80
import com.bsuir.weather.presentation.ui.theme.Purple40
import com.bsuir.weather.presentation.ui.theme.Purple80
import com.bsuir.weather.presentation.ui.theme.PurpleGrey40
import com.bsuir.weather.presentation.ui.theme.PurpleGrey80
import android.content.res.Configuration
import androidx.compose.ui.graphics.toArgb

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

object ThemeUtils {
    fun getColorScheme(
        context: Context,
        darkTheme: Boolean = isSystemInDarkTheme(context),
        dynamicColor: Boolean = true
    ): ColorScheme {
        return when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }
    }

    fun isSystemInDarkTheme(context: Context): Boolean {
        return (
                context.resources.configuration.uiMode
                        and
                Configuration.UI_MODE_NIGHT_MASK
                ) == Configuration.UI_MODE_NIGHT_YES
    }

    fun getDynamicSurfaceColor(context: Context): Int {
        return getColorScheme(context).surface.toArgb()
    }
}