package com.bsuir.weather.data.repository

import com.bsuir.weather.data.source.network.AiChatNetwork
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.repository.AiChatRepository
import com.bsuir.weather.utils.mapper.ChatMapper.toModel
import javax.inject.Inject

class AiChatRepositoryImpl @Inject constructor(
    private val aiChatNetwork: AiChatNetwork
) : AiChatRepository {
    override suspend fun askWeatherAI(forecast: ForecastModel, userRequest: String): String {
        return aiChatNetwork.askWeatherAI(forecast, userRequest).toModel()
    }
}