package com.bsuir.weather.data.dto

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("generationtime_ms") val generationTimeMs: Double,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerialName("elevation") val elevation: Double,
    @SerialName("current_units") val currentUnits: CurrentUnits,
    @SerialName("current") val current: Current,
    @SerialName("hourly_units") val hourlyUnits: HourlyUnits,
    @SerialName("hourly") val hourly: Hourly,
    @SerialName("daily_units") val dailyUnits: DailyUnits,
    @SerialName("daily") val daily: Daily
)

@Serializable
data class CurrentUnits(
    @SerialName("time") val time: String,
    @SerialName("interval") val interval: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("weather_code") val weatherCode: String,
    @SerialName("wind_speed_10m") val windSpeed10m: String,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: String,
    @SerialName("wind_direction_10m") val windDirection10m: String,
    @SerialName("apparent_temperature") val apparentTemperature: String,
    @SerialName("is_day") val isDay: String,
    @SerialName("surface_pressure") val surfacePressure: String
)

@Serializable
data class Current(
    @SerialName("time") val time: LocalDateTime,
    @SerialName("interval") val interval: Int,
    @SerialName("temperature_2m") val temperature2m: Double,
    @SerialName("weather_code") val weatherCode: Int,
    @SerialName("wind_speed_10m") val windSpeed10m: Double,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: Int,
    @SerialName("wind_direction_10m") val windDirection10m: Int,
    @SerialName("apparent_temperature") val apparentTemperature: Double,
    @SerialName("is_day") val isDay: Int,
    @SerialName("surface_pressure") val surfacePressure: Double
)

@Serializable
data class HourlyUnits(
    @SerialName("time") val time: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("weather_code") val weatherCode: String
)

@Serializable
data class Hourly(
    @SerialName("time") val time: List<LocalDateTime>,
    @SerialName("temperature_2m") val temperature2m: List<Double>,
    @SerialName("weather_code") val weatherCode: List<Int>
)

@Serializable
data class DailyUnits(
    @SerialName("time") val time: String,
    @SerialName("sunset") val sunset: String,
    @SerialName("sunrise") val sunrise: String,
    @SerialName("weather_code") val weatherCode: String,
    @SerialName("apparent_temperature_min") val apparentTemperatureMin: String,
    @SerialName("temperature_2m_max") val temperature2mMax: String
)

@Serializable
data class Daily(
    @SerialName("time") val date: List<LocalDate>,
    @SerialName("sunset") val sunset: List<LocalDateTime>,
    @SerialName("sunrise") val sunrise: List<LocalDateTime>,
    @SerialName("weather_code") val weatherCode: List<Int>,
    @SerialName("apparent_temperature_min") val apparentTemperatureMin: List<Double>,
    @SerialName("temperature_2m_max") val temperature2mMax: List<Double>
)
