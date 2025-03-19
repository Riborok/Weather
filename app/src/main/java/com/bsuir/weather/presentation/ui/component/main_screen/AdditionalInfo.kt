package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdditionalInfo (modifier: Modifier = Modifier) {
    val itemModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)

    Surface(
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AdditionalInfoItem("Ощущается", "+7°C", modifier = itemModifier)
            AdditionalInfoItem("Ветер", "17км/ч, →", modifier = itemModifier)
            AdditionalInfoItem("Влажность", "21%", modifier = itemModifier)
            AdditionalInfoItem("Давление", "1082 гПа", modifier = itemModifier)
            AdditionalInfoItem("Восход .. Закат", "7:18 .. 19:16", modifier = itemModifier)
        }
    }
}