package com.bsuir.weather.utils.ext

import androidx.compose.material3.CardColors
import androidx.compose.material3.ColorScheme

val ColorScheme.primaryCardColors: CardColors
    get() = CardColors(
        containerColor = primaryContainer,
        contentColor = onPrimaryContainer,
        disabledContainerColor = secondaryContainer,
        disabledContentColor = onSecondaryContainer,
    )

val ColorScheme.secondaryCardColors: CardColors
    get() = CardColors(
        containerColor = secondaryContainer,
        contentColor = onSecondaryContainer,
        disabledContainerColor = tertiaryContainer,
        disabledContentColor = onTertiaryContainer,
    )