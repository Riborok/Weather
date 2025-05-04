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
    val relativeHumidity: Int,
    val precipitation: Int,
    val cloudCover: Int,
    val iconId: Int,
    val weatherDescriptionId: Int,
    val surfacePressure: Int,
    val pressureMSL: Int,
    val windSpeed: Int,
    val windDirectionId: Int,
    val windGusts: Int,
    val time: LocalDateTime,
)

data class HourlyForecastModel(
    val temperature: Int,
    val iconId: Int,
    val weatherDescriptionId: Int,
    val relativeHumidity: Int,
    val apparentTemperature: Int,
    val precipitationProbability: Int,
    val surfacePressure: Int,
    val cloudCover: Int,
    val visibility: Int,
    val windSpeed10m: Int,
    val windDirectionId10m: Int,
    val windGusts10m: Int,
    val uvIndex: Double,
    val uvIndexClearSky: Double,
    val pressureMsl: Int,
    val precipitation: Int,
    val windSpeed80m: Int,
    val windSpeed120m: Int,
    val windSpeed180m: Int,
    val windDirectionId80m: Int,
    val windDirectionId120m: Int,
    val windDirectionId180m: Int,
    val temperature80m: Int,
    val temperature120m: Int,
    val temperature180m: Int,
    val soilTemperature0cm: Int,
    val soilTemperature6cm: Int,
    val soilTemperature18cm: Int,
    val soilTemperature54cm: Int,
    val soilMoisture0to1cm: Double,
    val soilMoisture1to3cm: Double,
    val soilMoisture3to9cm: Double,
    val soilMoisture9to27cm: Double,
    val soilMoisture27to81cm: Double,
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