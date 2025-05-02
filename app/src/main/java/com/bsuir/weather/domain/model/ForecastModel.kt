package com.bsuir.weather.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class ForecastModel (
    val currentForecast: CurrentForecastModel,
    val hourlyForecasts: List<HourlyForecastModel>,
    val dailyForecasts: List<DailyForecastModel>
)

data class CurrentForecastModel (
    val temperature: Int,
    val apparentTemperature: Int,
    val iconId: Int,
    val weatherDescriptionId: Int,
    val windSpeed: Int,
    val windDirectionId: Int,
    val surfacePressure: Int,
    val relativeHumidity: Int,
    val time: LocalDateTime,
)

data class HourlyForecastModel (
    val temperature: Int,
    val iconId: Int,
    val weatherDescriptionId: Int,
    val time: LocalDateTime,
)

data class DailyForecastModel (
    val iconId: Int,
    val weatherDescriptionId: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val date: LocalDate,
)