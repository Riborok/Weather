package com.bsuir.weather.data.repository

import com.bsuir.weather.data.db.cache.ForecastCache
import com.bsuir.weather.data.source.network.weather.WeatherForecastNetwork
import com.bsuir.weather.domain.model.Coordinates
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
    override suspend fun fetchForecast(coords: Coordinates): ForecastModel {
        forecastCache.get(coords)?.let {
            return it.toModel()
        }

        return fetchForecastFromNetwork(coords)
    }

    override suspend fun fetchForecastForced(coords: Coordinates): ForecastModel {
        return fetchForecastFromNetwork(coords)
    }

    private suspend fun fetchForecastFromNetwork(coords: Coordinates): ForecastModel {
        return weatherForecastNetwork.getForecastList(coords, 7).toModel().also {
            forecastCache.save(coords, it.toDTO())
        }
    }
}
