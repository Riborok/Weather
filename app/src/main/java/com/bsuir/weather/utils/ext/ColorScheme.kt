package com.bsuir.weather.utils.ext

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.TextFieldColors

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

val ColorScheme.primaryButtonColors: ButtonColors
    get() = ButtonColors(
        containerColor = primaryContainer,
        contentColor = onPrimaryContainer,
        disabledContainerColor = secondaryContainer,
        disabledContentColor = onSecondaryContainer,
    )

val ColorScheme.primaryTextFieldColors: TextFieldColors
    get() = TextFieldColors(
        focusedTextColor = onSurface,
        unfocusedTextColor = onSurfaceVariant,
        disabledTextColor = onSurface.copy(alpha = 0.38f),
        errorTextColor = onError,
        focusedContainerColor = surface,
        unfocusedContainerColor = surfaceVariant,
        disabledContainerColor = surface.copy(alpha = 0.12f),
        errorContainerColor = errorContainer,

        cursorColor = primary,
        errorCursorColor = error,

        textSelectionColors = TextSelectionColors(
            handleColor = primary,
            backgroundColor = primary.copy(alpha = 0.4f)
        ),

        focusedIndicatorColor = primary,
        unfocusedIndicatorColor = outline,
        disabledIndicatorColor = onSurface.copy(alpha = 0.12f),
        errorIndicatorColor = error,

        focusedLeadingIconColor = onSurface,
        unfocusedLeadingIconColor = onSurfaceVariant,
        disabledLeadingIconColor = onSurface.copy(alpha = 0.38f),
        errorLeadingIconColor = onError,

        focusedTrailingIconColor = onSurface,
        unfocusedTrailingIconColor = onSurfaceVariant,
        disabledTrailingIconColor = onSurface.copy(alpha = 0.38f),
        errorTrailingIconColor = onError,

        focusedLabelColor = primary,
        unfocusedLabelColor = onSurfaceVariant,
        disabledLabelColor = onSurface.copy(alpha = 0.38f),
        errorLabelColor = error,

        focusedPlaceholderColor = onSurfaceVariant,
        unfocusedPlaceholderColor = onSurfaceVariant,
        disabledPlaceholderColor = onSurface.copy(alpha = 0.38f),
        errorPlaceholderColor = onError,

        focusedSupportingTextColor = onSurfaceVariant,
        unfocusedSupportingTextColor = onSurfaceVariant,
        disabledSupportingTextColor = onSurface.copy(alpha = 0.38f),
        errorSupportingTextColor = onError,

        focusedPrefixColor = onSurfaceVariant,
        unfocusedPrefixColor = onSurfaceVariant,
        disabledPrefixColor = onSurface.copy(alpha = 0.38f),
        errorPrefixColor = onError,

        focusedSuffixColor = onSurfaceVariant,
        unfocusedSuffixColor = onSurfaceVariant,
        disabledSuffixColor = onSurface.copy(alpha = 0.38f),
        errorSuffixColor = onError
    )