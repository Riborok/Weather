package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.chat.ChatResponse

object ChatMapper {
    fun ChatResponse.toModel(): String {
        return this.choices.joinToString(separator = "\n") { it.message.content }
    }
}