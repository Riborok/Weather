package com.bsuir.weather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.usecase.AskAiChatUseCase
import com.bsuir.weather.utils.ext.weatherAppContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.collections.orEmpty

data class Message(val content: String, val time: LocalDateTime = LocalDateTime.now())
data class ChatMessage(val question: Message, val response: Message?)

@HiltViewModel
class WeatherChatViewModel @Inject constructor(
    application: Application,
    private val askAiChatUseCase: AskAiChatUseCase
) : AndroidViewModel(application) {
    private val _messagesByLocation = MutableStateFlow<Map<ForecastLocationModel, List<ChatMessage>>>(emptyMap())

    fun getMessagesForLocation(locationModel: ForecastLocationModel): StateFlow<List<ChatMessage>> =
        _messagesByLocation
            .map { currentMap -> currentMap[locationModel].orEmpty() }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addMessage(question: String, forecastLocation: ForecastLocationModel) {
        val initialMessage = ChatMessage(Message(question), response = null)

        _messagesByLocation.update { currentMap ->
            val currentMessages = currentMap[forecastLocation].orEmpty()
            currentMap + (forecastLocation to (currentMessages + initialMessage))
        }

        viewModelScope.launch {
            try {
                val response = askAiChatUseCase.askWeatherAI(forecastLocation, question)
                updateChatMessage(forecastLocation, initialMessage, response)
            } catch (exception: Exception) {
                val context = getApplication<Application>().weatherAppContext
                val errorMessage = context.getString(R.string.ai_request_error, exception.message)
                updateChatMessage(forecastLocation, initialMessage, errorMessage)
            }
        }
    }

    private fun updateChatMessage(forecastLocation: ForecastLocationModel, initialMessage: ChatMessage, response: String) {
        _messagesByLocation.update { currentMap ->
            val currentMessages = currentMap[forecastLocation].orEmpty()
            val updatedMessages = currentMessages.map { message ->
                if (message == initialMessage) {
                    message.copy(response = Message(response))
                } else {
                    message
                }
            }
            currentMap + (forecastLocation to updatedMessages)
        }
    }
}
