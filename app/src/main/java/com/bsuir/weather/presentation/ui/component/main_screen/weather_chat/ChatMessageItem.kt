package com.bsuir.weather.presentation.ui.component.main_screen.weather_chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import com.bsuir.weather.presentation.ui.theme.WeatherTheme
import com.bsuir.weather.presentation.viewmodel.ChatMessage
import com.bsuir.weather.presentation.viewmodel.Message
import com.bsuir.weather.utils.ext.primaryCardColors
import com.bsuir.weather.utils.ext.secondaryCardColors

@Composable
fun ChatMessageItem(
    message: ChatMessage
) {
    fun messageBoxModifier(): Modifier {
        return Modifier
            .padding(12.dp)
            .widthIn(max = 200.dp)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(
                    colors = MaterialTheme.colorScheme.primaryCardColors,
                    modifier = messageBoxModifier()
                ) {
                    Text(
                        text = message.question.content,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.content_description_user),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.SmartToy,
                    contentDescription = stringResource(id = R.string.content_description_ai),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Card(
                    colors = MaterialTheme.colorScheme.secondaryCardColors
                ) {
                    if (message.response == null) {
                        LoadingAnimation()
                    } else {
                        Text(
                            text = message.response.content,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChatMessageItemPreview() {
    WeatherTheme {
        ChatMessageItem(
            ChatMessage (
                Message (
                    "Привет дорогой ChatGPT!!!"
                ),

                Message (
                    "Иди нахуй."
                )
            )
            )
    }
}