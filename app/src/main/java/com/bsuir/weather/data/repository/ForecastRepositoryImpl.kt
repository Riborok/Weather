package com.bsuir.weather.data.repository

import com.bsuir.weather.data.source.network.weather.WeatherForecastNetwork
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.repository.ForecastRepository
import com.bsuir.weather.utils.mapper.WeatherResponseMapper.toModel
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val weatherForecastNetwork: WeatherForecastNetwork
) : ForecastRepository {
    override suspend fun getForecast(latitude: Double, longitude: Double): ForecastModel {
        return weatherForecastNetwork.getForecastList(latitude, longitude, 7).toModel()
    }
}
