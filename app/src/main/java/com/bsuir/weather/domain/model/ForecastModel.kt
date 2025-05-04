package com.bsuir.weather.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class ForecastModel (
    val currentForecast: CurrentForecastModel,
    val hourlyForecasts: List<HourlyForecastModel>,
    val dailyForecasts: List<DailyForecastModel>
)

data class CurrentForecastModel(
    val time: LocalDateTime,

    val temperature: Int,
    val apparentTemperature: Int,

    val relativeHumidity: Int,
    val precipitation: Int,
    val cloudCover: Int,

    val surfacePressure: Int,
    val pressureMSL: Int,

    val windSpeed: Int,
    val windGusts: Int,
    val windDirectionId: Int,

    val iconId: Int,
    val weatherDescriptionId: Int
)

data class HourlyForecastModel(
    val time: LocalDateTime,

    val temperature: Int,
    val apparentTemperature: Int,
    val temperature80m: Int,
    val temperature120m: Int,
    val temperature180m: Int,

    val relativeHumidity: Int,
    val precipitation: Int,
    val precipitationProbability: Int,
    val cloudCover: Int,

    val surfacePressure: Int,
    val pressureMsl: Int,

    val uvIndex: Double,
    val uvIndexClearSky: Double,

    val visibility: Int,

    val windSpeed10m: Int,
    val windSpeed80m: Int,
    val windSpeed120m: Int,
    val windSpeed180m: Int,

    val windGusts10m: Int,

    val windDirectionId10m: Int,
    val windDirectionId80m: Int,
    val windDirectionId120m: Int,
    val windDirectionId180m: Int,

    val soilTemperature0cm: Int,
    val soilTemperature6cm: Int,
    val soilTemperature18cm: Int,
    val soilTemperature54cm: Int,

    val soilMoisture0to1cm: Double,
    val soilMoisture1to3cm: Double,
    val soilMoisture3to9cm: Double,
    val soilMoisture9to27cm: Double,
    val soilMoisture27to81cm: Double,

    val iconId: Int,
    val weatherDescriptionId: Int
)

data class DailyForecastModel(
    val date: LocalDate,
    val iconId: Int,
    val weatherDescriptionId: Int,

    val minTemperature: Int,
    val maxTemperature: Int,
    val meanTemperature: Int,

    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val daylightDuration: Int,

    val uvIndexMax: Double,
    val uvIndexClearSkyMax: Double,

    val windSpeed10mMin: Int,
    val windSpeed10mMean: Int,
    val windSpeed10mMax: Int,

    val windGusts10mMin: Int,
    val windGusts10mMean: Int,
    val windGusts10mMax: Int,

    val windDirectionId10mDominant: Int,

    val precipitationSum: Int,
    val precipitationProbabilityMean: Int,

    val relativeHumidityMean: Int,

    val surfacePressureMin: Int,
    val surfacePressureMean: Int,
    val surfacePressureMax: Int,

    val pressureMslMin: Int,
    val pressureMslMean: Int,
    val pressureMslMax: Int,

    val visibilityMin: Int,
    val visibilityMean: Int,
    val visibilityMax: Int,

    val cloudCoverMin: Int,
    val cloudCoverMean: Int,
    val cloudCoverMax: Int
)