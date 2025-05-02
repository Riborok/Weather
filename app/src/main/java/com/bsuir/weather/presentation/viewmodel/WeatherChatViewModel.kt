package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.usecase.AskAiChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChatMessage(val question: String, val response: String?)

@HiltViewModel
class WeatherChatViewModel @Inject constructor(
    private val askAiChatUseCase: AskAiChatUseCase
) : ViewModel() {
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    fun addMessage(question: String, forecast: ForecastModel) {
        val initialMessage = ChatMessage(question, response = null)
        _chatMessages.value = _chatMessages.value + initialMessage

        viewModelScope.launch {
            try {
                val response = askAiChatUseCase.askWeatherAI(forecast, question)

                val updatedMessages = _chatMessages.value.map { message ->
                    if (message == initialMessage) {
                        message.copy(response = response)
                    } else {
                        message
                    }
                }

                _chatMessages.value = updatedMessages
            } catch (exception: Exception) {
                val updatedMessages = _chatMessages.value.map { message ->
                    if (message == initialMessage) {
                        message.copy(response = "Ошибка при запросе AI: ${exception.message}")
                    } else {
                        message
                    }
                }

                _chatMessages.value = updatedMessages
            }
        }
    }
}