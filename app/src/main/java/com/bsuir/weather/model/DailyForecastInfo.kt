package com.bsuir.weather.model

import androidx.annotation.DrawableRes

data class DailyForecastInfo(
    val dayName: String,
    @DrawableRes val icon: Int,
    val weatherDescription: String,
    val minTemperature: String,
    val maxTemperature: String
)
