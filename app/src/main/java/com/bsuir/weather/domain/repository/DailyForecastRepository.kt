package com.bsuir.weather.domain.repository

import com.bsuir.weather.domain.model.DailyForecastModel

interface DailyForecastRepository {
    fun getDailyForecastList(latitude: Double, longitude: Double): List<DailyForecastModel>
}
