package com.bsuir.weather.domain.model

data class WeatherLocationModel(
    val forecast: ForecastModel,
    val location: LocationModel
)
