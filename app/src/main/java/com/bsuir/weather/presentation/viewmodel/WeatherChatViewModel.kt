package com.bsuir.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.weather.domain.model.ForecastModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ChatMessage(val question: String, val response: String)

class WeatherChatViewModel : ViewModel() {
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private val aiResponsePlaceholder = "Это заглушка ответа. Реализация AI будет позже."

    fun addMessage(question: String, forecast: ForecastModel) {
        viewModelScope.launch {
            val response = getWeatherResponse(question, forecast)
            val newMessage = ChatMessage(question, response)

            _chatMessages.value = _chatMessages.value + newMessage
        }
    }

    private fun getWeatherResponse(question: String, forecast: ForecastModel): String {
        val currentWeather = forecast.currentForecastModel

        return aiResponsePlaceholder
    }
}