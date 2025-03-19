package com.bsuir.weather.domain.model

import androidx.annotation.DrawableRes

data class HourlyForecastModel(
    val temperature: String,
    @DrawableRes val icon: Int,
    val weatherDescription: String,
    val hour: String
)
