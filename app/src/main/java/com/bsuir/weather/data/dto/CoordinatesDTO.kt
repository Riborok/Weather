package com.bsuir.weather.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesDTO (
    val latitude: Double,
    val longitude: Double
)
