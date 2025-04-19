package com.bsuir.weather.presentation.ui.component.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R

@Composable
fun MainInfo (
    pickedLocationName: String,
    onOpenDrawerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = pickedLocationName,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .clickable(onClick = onOpenDrawerClick)
                )
                Text(
                    text = "+14°C",
                    style = MaterialTheme.typography.displayLarge
                )
            }

            Image(
                painter = painterResource(R.drawable.cloud_basic),
                contentDescription = "Облачно",
                modifier = Modifier.size(128.dp)
            )
        }
    }
}