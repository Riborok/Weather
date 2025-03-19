package com.bsuir.weather.domain.model

import androidx.annotation.DrawableRes

data class DailyForecastModel(
    val dayName: String,
    @DrawableRes val icon: Int,
    val weatherDescription: String,
    val minTemperature: String,
    val maxTemperature: String
)
