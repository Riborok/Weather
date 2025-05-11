package com.bsuir.weather.utils.android

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.toArgb
import com.bsuir.weather.presentation.ui.theme.backgroundDark
import com.bsuir.weather.presentation.ui.theme.backgroundLight
import com.bsuir.weather.presentation.ui.theme.errorContainerDark
import com.bsuir.weather.presentation.ui.theme.errorContainerLight
import com.bsuir.weather.presentation.ui.theme.errorDark
import com.bsuir.weather.presentation.ui.theme.errorLight
import com.bsuir.weather.presentation.ui.theme.inverseOnSurfaceDark
import com.bsuir.weather.presentation.ui.theme.inverseOnSurfaceLight
import com.bsuir.weather.presentation.ui.theme.inversePrimaryDark
import com.bsuir.weather.presentation.ui.theme.inversePrimaryLight
import com.bsuir.weather.presentation.ui.theme.inverseSurfaceDark
import com.bsuir.weather.presentation.ui.theme.inverseSurfaceLight
import com.bsuir.weather.presentation.ui.theme.onBackgroundDark
import com.bsuir.weather.presentation.ui.theme.onBackgroundLight
import com.bsuir.weather.presentation.ui.theme.onErrorContainerDark
import com.bsuir.weather.presentation.ui.theme.onErrorContainerLight
import com.bsuir.weather.presentation.ui.theme.onErrorDark
import com.bsuir.weather.presentation.ui.theme.onErrorLight
import com.bsuir.weather.presentation.ui.theme.onPrimaryContainerDark
import com.bsuir.weather.presentation.ui.theme.onPrimaryContainerLight
import com.bsuir.weather.presentation.ui.theme.onPrimaryDark
import com.bsuir.weather.presentation.ui.theme.onPrimaryLight
import com.bsuir.weather.presentation.ui.theme.onSecondaryContainerDark
import com.bsuir.weather.presentation.ui.theme.onSecondaryContainerLight
import com.bsuir.weather.presentation.ui.theme.onSecondaryDark
import com.bsuir.weather.presentation.ui.theme.onSecondaryLight
import com.bsuir.weather.presentation.ui.theme.onSurfaceDark
import com.bsuir.weather.presentation.ui.theme.onSurfaceLight
import com.bsuir.weather.presentation.ui.theme.onSurfaceVariantDark
import com.bsuir.weather.presentation.ui.theme.onSurfaceVariantLight
import com.bsuir.weather.presentation.ui.theme.onTertiaryContainerDark
import com.bsuir.weather.presentation.ui.theme.onTertiaryContainerLight
import com.bsuir.weather.presentation.ui.theme.onTertiaryDark
import com.bsuir.weather.presentation.ui.theme.onTertiaryLight
import com.bsuir.weather.presentation.ui.theme.outlineDark
import com.bsuir.weather.presentation.ui.theme.outlineLight
import com.bsuir.weather.presentation.ui.theme.outlineVariantDark
import com.bsuir.weather.presentation.ui.theme.outlineVariantLight
import com.bsuir.weather.presentation.ui.theme.primaryContainerDark
import com.bsuir.weather.presentation.ui.theme.primaryContainerLight
import com.bsuir.weather.presentation.ui.theme.primaryDark
import com.bsuir.weather.presentation.ui.theme.primaryLight
import com.bsuir.weather.presentation.ui.theme.scrimDark
import com.bsuir.weather.presentation.ui.theme.scrimLight
import com.bsuir.weather.presentation.ui.theme.secondaryContainerDark
import com.bsuir.weather.presentation.ui.theme.secondaryContainerLight
import com.bsuir.weather.presentation.ui.theme.secondaryDark
import com.bsuir.weather.presentation.ui.theme.secondaryLight
import com.bsuir.weather.presentation.ui.theme.surfaceBrightDark
import com.bsuir.weather.presentation.ui.theme.surfaceBrightLight
import com.bsuir.weather.presentation.ui.theme.surfaceContainerDark
import com.bsuir.weather.presentation.ui.theme.surfaceContainerHighDark
import com.bsuir.weather.presentation.ui.theme.surfaceContainerHighLight
import com.bsuir.weather.presentation.ui.theme.surfaceContainerHighestDark
import com.bsuir.weather.presentation.ui.theme.surfaceContainerHighestLight
import com.bsuir.weather.presentation.ui.theme.surfaceContainerLight
import com.bsuir.weather.presentation.ui.theme.surfaceContainerLowDark
import com.bsuir.weather.presentation.ui.theme.surfaceContainerLowLight
import com.bsuir.weather.presentation.ui.theme.surfaceContainerLowestDark
import com.bsuir.weather.presentation.ui.theme.surfaceContainerLowestLight
import com.bsuir.weather.presentation.ui.theme.surfaceDark
import com.bsuir.weather.presentation.ui.theme.surfaceDimDark
import com.bsuir.weather.presentation.ui.theme.surfaceDimLight
import com.bsuir.weather.presentation.ui.theme.surfaceLight
import com.bsuir.weather.presentation.ui.theme.surfaceVariantDark
import com.bsuir.weather.presentation.ui.theme.surfaceVariantLight
import com.bsuir.weather.presentation.ui.theme.tertiaryContainerDark
import com.bsuir.weather.presentation.ui.theme.tertiaryContainerLight
import com.bsuir.weather.presentation.ui.theme.tertiaryDark
import com.bsuir.weather.presentation.ui.theme.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

object ThemeUtils {
    fun getColorScheme(
        context: Context,
        darkTheme: Boolean = isSystemInDarkTheme(context),
        dynamicColor: Boolean = false
    ): ColorScheme {
        return when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> darkScheme
            else -> lightScheme
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