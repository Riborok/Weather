package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.HourlyForecastModel

interface HourlyForecastRepository {
    fun getHourlyForecastList(): List<HourlyForecastModel>
}
