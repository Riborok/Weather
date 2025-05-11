package com.bsuir.weather.presentation.ui.component.main_screen.weather_chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    fun messageCardModifier(): Modifier {
        return Modifier
            .widthIn(max = 200.dp)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    colors = MaterialTheme.colorScheme.primaryCardColors,
                    modifier = messageCardModifier()
                ) {
                    Text(
                        text = message.question.content,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.content_description_user),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.SmartToy,
                    contentDescription = stringResource(id = R.string.content_description_ai),
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.Bottom)
                )

                Card(
                    colors = MaterialTheme.colorScheme.secondaryCardColors,
                    modifier = messageCardModifier()
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
                    "Привет, дорогой ChatGPT!!!"
                ),

                Message (
                    "Привет, уважаемый! Как я могу помочь вам в этот прекрасный день?"
                )
            )
            )
    }
}