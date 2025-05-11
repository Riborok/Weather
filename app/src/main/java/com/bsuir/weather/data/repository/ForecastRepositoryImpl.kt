package com.bsuir.weather.data.repository

import com.bsuir.weather.data.db.cache.ForecastCache
import com.bsuir.weather.data.source.network.weather.WeatherForecastNetwork
import com.bsuir.weather.domain.model.ForecastModel
import com.bsuir.weather.domain.repository.ForecastRepository
import com.bsuir.weather.utils.mapper.ForecastMapper.toDTO
import com.bsuir.weather.utils.mapper.ForecastMapper.toModel
import com.bsuir.weather.utils.mapper.WeatherResponseMapper.toModel
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val weatherForecastNetwork: WeatherForecastNetwork,
    private val forecastCache: ForecastCache
) : ForecastRepository {
    override suspend fun getForecast(latitude: Double, longitude: Double): ForecastModel {
        forecastCache.get(latitude, longitude)?.let {
            return it.toModel()
        }

        val forecast = weatherForecastNetwork.getForecastList(latitude, longitude, 7).toModel()

        forecastCache.save(latitude, longitude, forecast.toDTO())

        return forecast
    }
}
