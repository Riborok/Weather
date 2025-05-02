package com.bsuir.weather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.usecase.AskAiChatUseCase
import com.bsuir.weather.utils.weatherAppContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChatMessage(val question: String, val response: String?)

@HiltViewModel
class WeatherChatViewModel @Inject constructor(
    application: Application,
    private val askAiChatUseCase: AskAiChatUseCase
) : AndroidViewModel(application) {
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    fun addMessage(question: String, forecastLocation: ForecastLocationModel) {
        val initialMessage = ChatMessage(question, response = null)
        _chatMessages.update { currentMessages ->
            currentMessages + initialMessage
        }

        viewModelScope.launch {
            try {
                val response = askAiChatUseCase.askWeatherAI(forecastLocation, question)
                updateChatMessage(initialMessage, response)
            } catch (exception: Exception) {
                val context = getApplication<Application>().weatherAppContext
                val errorMessage = context.getString(R.string.ai_request_error, exception.message)
                updateChatMessage(initialMessage, errorMessage)
            }
        }
    }

    private fun updateChatMessage(initialMessage: ChatMessage, response: String) {
        _chatMessages.update { currentMessages ->
            currentMessages.map { message ->
                if (message == initialMessage) {
                    message.copy(response = response)
                } else {
                    message
                }
            }
        }
    }
}
