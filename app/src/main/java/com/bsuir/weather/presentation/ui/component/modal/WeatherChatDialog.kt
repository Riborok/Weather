package com.bsuir.weather.presentation.ui.component.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.presentation.ui.component.main_screen.weather_chat.ChatInputField
import com.bsuir.weather.presentation.ui.component.main_screen.weather_chat.ChatMessageList
import com.bsuir.weather.presentation.ui.component.main_screen.weather_chat.HeaderSection
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
                .fillMaxWidth(1f)
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
