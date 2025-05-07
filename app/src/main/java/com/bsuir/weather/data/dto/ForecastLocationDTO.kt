package com.bsuir.weather.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastLocationDTO (
    val forecast: ForecastDTO,
    val location: LocationDTO
)
