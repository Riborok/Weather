package com.bsuir.weather.utils.ext

import androidx.compose.material3.CardColors
import androidx.compose.material3.ColorScheme

val ColorScheme.cardColors: CardColors
    get() = CardColors(
        containerColor = primaryContainer,
        contentColor = onPrimaryContainer,
        disabledContainerColor = secondaryContainer,
        disabledContentColor = onSecondaryContainer,
    )