package com.bsuir.weather.utils.mapper

import com.bsuir.weather.data.dto.ForecastLocationDTO
import com.bsuir.weather.domain.model.ForecastLocationModel
import com.bsuir.weather.utils.mapper.ForecastMapper.toDTO
import com.bsuir.weather.utils.mapper.ForecastMapper.toModel
import com.bsuir.weather.utils.mapper.LocationMapper.toDTO
import com.bsuir.weather.utils.mapper.LocationMapper.toModel

object ForecastLocationMapper {
    fun ForecastLocationDTO.toModel(): ForecastLocationModel {
        return ForecastLocationModel(
            forecast = forecast.toModel(),
            location = location.toModel()
        )
    }

    fun ForecastLocationModel.toDTO(): ForecastLocationDTO {
        return ForecastLocationDTO(
            forecast = forecast.toDTO(),
            location = location.toDTO()
        )
    }
}