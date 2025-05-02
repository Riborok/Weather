package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.presentation.ui.component.main_screen.weather_chat.ChatInputField
import com.bsuir.weather.presentation.ui.component.main_screen.weather_chat.ChatMessageList
import com.bsuir.weather.presentation.ui.component.main_screen.weather_chat.HeaderSection
import com.bsuir.weather.presentation.ui.component.main_screen.weather_chat.PopularQuestionsSection
import com.bsuir.weather.presentation.viewmodel.WeatherChatViewModel

@Composable
fun WeatherChatDialog(
    forecast: ForecastModel,
    onDismiss: () -> Unit,
    weatherChatViewModel: WeatherChatViewModel = hiltViewModel()
) {
    var userMessage by remember { mutableStateOf("") }
    val messages by weatherChatViewModel.chatMessages.collectAsState()

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f),
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 6.dp,
            shadowElevation = 12.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    ChatMessageList(messages)

                    Spacer(modifier = Modifier.height(12.dp))

                    PopularQuestionsSection { question ->
                        weatherChatViewModel.addMessage(question, forecast)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    ChatInputField(
                        userMessage = userMessage,
                        onMessageChange = { userMessage = it },
                        onSendMessage = {
                            if (userMessage.isNotBlank()) {
                                weatherChatViewModel.addMessage(userMessage, forecast)
                                userMessage = ""
                            }
                        }
                    )
                }

                HeaderSection(onDismiss = onDismiss)
            }
        }
    }
}
