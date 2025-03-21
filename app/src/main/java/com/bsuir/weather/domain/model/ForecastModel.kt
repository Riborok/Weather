package com.bsuir.weather.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class ForecastModel (
    val currentForecastModel: CurrentForecastModel,
    val hourlyForecastModels: List<HourlyForecastModel>,
    val dailyForecastModels: List<DailyForecastModel>
)

data class CurrentForecastModel (
    val temperature: Double,
    val apparentTemperature: Double,
    val iconId: Int,
    val weatherDescriptionId: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val surfacePressure: Double,
    val relativeHumidity: Int,
    val time: LocalDateTime,
)

data class HourlyForecastModel (
    val temperature: Double,
    val iconId: Int,
    val weatherDescriptionId: Int,
    val time: LocalDateTime,
)

data class DailyForecastModel(
    val iconId: Int,
    val weatherDescriptionId: Int,
    val minTemperature: Double,
    val maxTemperature: Double,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val date: LocalDate,
)