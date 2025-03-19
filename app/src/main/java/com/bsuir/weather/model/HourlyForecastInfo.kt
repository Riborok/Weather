package com.bsuir.weather.model

import androidx.annotation.DrawableRes

data class HourlyForecastInfo(
    val temperature: String,
    @DrawableRes val icon: Int,
    val weatherDescription: String,
    val hour: String
)
