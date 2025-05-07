package com.bsuir.weather.data.repository

import com.bsuir.weather.data.source.datastore.ForecastLocationDataStore
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.domain.repository.ForecastLocationRepository
import com.bsuir.weather.utils.mapper.ForecastLocationMapper.toDTO
import com.bsuir.weather.utils.mapper.ForecastLocationMapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ForecastLocationRepositoryImpl @Inject constructor(
    private val forecastLocationDataStore: ForecastLocationDataStore
) : ForecastLocationRepository {

    override suspend fun updateForecastLocation(forecastLocation: ForecastLocationModel) {
        forecastLocationDataStore.updateForecastLocation(forecastLocation.toDTO())
    }

    override fun getForecastLocation(): Flow<ForecastLocationModel?> {
        return forecastLocationDataStore.forecastLocation.map {
            it?.toModel()
        }
    }
}