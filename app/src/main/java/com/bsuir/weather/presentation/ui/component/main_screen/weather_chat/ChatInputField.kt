package com.bsuir.weather.presentation.ui.component.main_screen.weather_chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.utils.ext.primaryIconButtonColors
import com.bsuir.weather.utils.ext.primaryTextFieldColors

@Composable
fun ColumnScope.ChatInputField(
    userMessage: String,
    onMessageChange: (String) -> Unit,
    onSendMessage: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = userMessage,
            onValueChange = onMessageChange,
            placeholder = { Text(stringResource(id = R.string.chat_placeholder_weather)) },
            colors = MaterialTheme.colorScheme.primaryTextFieldColors,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = onSendMessage,
            colors = MaterialTheme.colorScheme.primaryIconButtonColors,
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = stringResource(id = R.string.content_description_send)
            )
        }
    }
}