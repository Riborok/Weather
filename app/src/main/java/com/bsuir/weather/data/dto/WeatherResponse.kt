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
    @SerialName("surface_pressure") val surfacePressure: String,
    @SerialName("precipitation") val precipitation: String,
    @SerialName("cloud_cover") val cloudCover: String,
    @SerialName("wind_gusts_10m") val windGusts10m: String,
    @SerialName("pressure_msl") val pressureMsl: String
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
    @SerialName("surface_pressure") val surfacePressure: Double,
    @SerialName("precipitation") val precipitation: Double,
    @SerialName("cloud_cover") val cloudCover: Int,
    @SerialName("wind_gusts_10m") val windGusts10m: Double,
    @SerialName("pressure_msl") val pressureMsl: Double
)

@Serializable
data class HourlyUnits(
    @SerialName("time") val time: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: String,
    @SerialName("apparent_temperature") val apparentTemperature: String,
    @SerialName("precipitation_probability") val precipitationProbability: String,
    @SerialName("weather_code") val weatherCode: String,
    @SerialName("surface_pressure") val surfacePressure: String,
    @SerialName("cloud_cover") val cloudCover: String,
    @SerialName("visibility") val visibility: String,
    @SerialName("wind_speed_10m") val windSpeed10m: String,
    @SerialName("wind_direction_10m") val windDirection10m: String,
    @SerialName("wind_gusts_10m") val windGusts10m: String,
    @SerialName("is_day") val isDay: String,
    @SerialName("uv_index") val uvIndex: String,
    @SerialName("uv_index_clear_sky") val uvIndexClearSky: String,
    @SerialName("pressure_msl") val pressureMsl: String,
    @SerialName("precipitation") val precipitation: String,
    @SerialName("wind_speed_80m") val windSpeed80m: String,
    @SerialName("wind_speed_120m") val windSpeed120m: String,
    @SerialName("wind_speed_180m") val windSpeed180m: String,
    @SerialName("wind_direction_80m") val windDirection80m: String,
    @SerialName("wind_direction_120m") val windDirection120m: String,
    @SerialName("wind_direction_180m") val windDirection180m: String,
    @SerialName("temperature_80m") val temperature80m: String,
    @SerialName("temperature_120m") val temperature120m: String,
    @SerialName("temperature_180m") val temperature180m: String,
    @SerialName("soil_temperature_0cm") val soilTemperature0cm: String,
    @SerialName("soil_temperature_6cm") val soilTemperature6cm: String,
    @SerialName("soil_temperature_18cm") val soilTemperature18cm: String,
    @SerialName("soil_temperature_54cm") val soilTemperature54cm: String,
    @SerialName("soil_moisture_0_to_1cm") val soilMoisture0to1cm: String,
    @SerialName("soil_moisture_1_to_3cm") val soilMoisture1to3cm: String,
    @SerialName("soil_moisture_3_to_9cm") val soilMoisture3to9cm: String,
    @SerialName("soil_moisture_9_to_27cm") val soilMoisture9to27cm: String,
    @SerialName("soil_moisture_27_to_81cm") val soilMoisture27to81cm: String
)

@Serializable
data class Hourly(
    @SerialName("time") val time: List<LocalDateTime>,
    @SerialName("temperature_2m") val temperature2m: List<Double>,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: List<Int>,
    @SerialName("apparent_temperature") val apparentTemperature: List<Double>,
    @SerialName("precipitation_probability") val precipitationProbability: List<Int>,
    @SerialName("weather_code") val weatherCode: List<Int>,
    @SerialName("surface_pressure") val surfacePressure: List<Double>,
    @SerialName("cloud_cover") val cloudCover: List<Int>,
    @SerialName("visibility") val visibility: List<Double>,
    @SerialName("wind_speed_10m") val windSpeed10m: List<Double>,
    @SerialName("wind_direction_10m") val windDirection10m: List<Int>,
    @SerialName("wind_gusts_10m") val windGusts10m: List<Double>,
    @SerialName("is_day") val isDay: List<Int>,
    @SerialName("uv_index") val uvIndex: List<Double>,
    @SerialName("uv_index_clear_sky") val uvIndexClearSky: List<Double>,
    @SerialName("pressure_msl") val pressureMsl: List<Double>,
    @SerialName("precipitation") val precipitation: List<Double>,
    @SerialName("wind_speed_80m") val windSpeed80m: List<Double>,
    @SerialName("wind_speed_120m") val windSpeed120m: List<Double>,
    @SerialName("wind_speed_180m") val windSpeed180m: List<Double>,
    @SerialName("wind_direction_80m") val windDirection80m: List<Int>,
    @SerialName("wind_direction_120m") val windDirection120m: List<Int>,
    @SerialName("wind_direction_180m") val windDirection180m: List<Int>,
    @SerialName("temperature_80m") val temperature80m: List<Double>,
    @SerialName("temperature_120m") val temperature120m: List<Double>,
    @SerialName("temperature_180m") val temperature180m: List<Double>,
    @SerialName("soil_temperature_0cm") val soilTemperature0cm: List<Double>,
    @SerialName("soil_temperature_6cm") val soilTemperature6cm: List<Double>,
    @SerialName("soil_temperature_18cm") val soilTemperature18cm: List<Double>,
    @SerialName("soil_temperature_54cm") val soilTemperature54cm: List<Double>,
    @SerialName("soil_moisture_0_to_1cm") val soilMoisture0to1cm: List<Double>,
    @SerialName("soil_moisture_1_to_3cm") val soilMoisture1to3cm: List<Double>,
    @SerialName("soil_moisture_3_to_9cm") val soilMoisture3to9cm: List<Double>,
    @SerialName("soil_moisture_9_to_27cm") val soilMoisture9to27cm: List<Double>,
    @SerialName("soil_moisture_27_to_81cm") val soilMoisture27to81cm: List<Double>
)

@Serializable
data class DailyUnits(
    @SerialName("time") val time: String,
    @SerialName("weather_code") val weatherCode: String,
    @SerialName("temperature_2m_max") val temperature2mMax: String,
    @SerialName("sunrise") val sunrise: String,
    @SerialName("sunset") val sunset: String,
    @SerialName("uv_index_max") val uvIndexMax: String,
    @SerialName("wind_speed_10m_max") val windSpeed10mMax: String,
    @SerialName("wind_gusts_10m_max") val windGusts10mMax: String,
    @SerialName("precipitation_sum") val precipitationSum: String,
    @SerialName("temperature_2m_min") val temperature2mMin: String,
    @SerialName("temperature_2m_mean") val temperature2mMean: String,
    @SerialName("cloud_cover_mean") val cloudCoverMean: String,
    @SerialName("precipitation_probability_mean") val precipitationProbabilityMean: String,
    @SerialName("relative_humidity_2m_mean") val relativeHumidity2mMean: String,
    @SerialName("surface_pressure_mean") val surfacePressureMean: String,
    @SerialName("visibility_mean") val visibilityMean: String,
    @SerialName("visibility_min") val visibilityMin: String,
    @SerialName("visibility_max") val visibilityMax: String,
    @SerialName("wind_speed_10m_mean") val windSpeed10mMean: String,
    @SerialName("wind_gusts_10m_mean") val windGusts10mMean: String,
    @SerialName("surface_pressure_max") val surfacePressureMax: String,
    @SerialName("surface_pressure_min") val surfacePressureMin: String,
    @SerialName("wind_direction_10m_dominant") val windDirection10mDominant: String,
    @SerialName("wind_gusts_10m_min") val windGusts10mMin: String,
    @SerialName("wind_speed_10m_min") val windSpeed10mMin: String,
    @SerialName("cloud_cover_max") val cloudCoverMax: String,
    @SerialName("cloud_cover_min") val cloudCoverMin: String,
    @SerialName("pressure_msl_mean") val pressureMslMean: String,
    @SerialName("pressure_msl_max") val pressureMslMax: String,
    @SerialName("pressure_msl_min") val pressureMslMin: String,
    @SerialName("daylight_duration") val daylightDuration: String,
    @SerialName("uv_index_clear_sky_max") val uvIndexClearSkyMax: String
)

@Serializable
data class Daily(
    @SerialName("time") val date: List<LocalDate>,
    @SerialName("weather_code") val weatherCode: List<Int>,
    @SerialName("temperature_2m_max") val temperature2mMax: List<Double>,
    @SerialName("sunrise") val sunrise: List<LocalDateTime>,
    @SerialName("sunset") val sunset: List<LocalDateTime>,
    @SerialName("uv_index_max") val uvIndexMax: List<Double>,
    @SerialName("wind_speed_10m_max") val windSpeed10mMax: List<Double>,
    @SerialName("wind_gusts_10m_max") val windGusts10mMax: List<Double>,
    @SerialName("precipitation_sum") val precipitationSum: List<Double>,
    @SerialName("temperature_2m_min") val temperature2mMin: List<Double>,
    @SerialName("temperature_2m_mean") val temperature2mMean: List<Double>,
    @SerialName("cloud_cover_mean") val cloudCoverMean: List<Int>,
    @SerialName("precipitation_probability_mean") val precipitationProbabilityMean: List<Int>,
    @SerialName("relative_humidity_2m_mean") val relativeHumidity2mMean: List<Int>,
    @SerialName("surface_pressure_mean") val surfacePressureMean: List<Double>,
    @SerialName("visibility_mean") val visibilityMean: List<Double>,
    @SerialName("visibility_min") val visibilityMin: List<Double>,
    @SerialName("visibility_max") val visibilityMax: List<Double>,
    @SerialName("wind_speed_10m_mean") val windSpeed10mMean: List<Double>,
    @SerialName("wind_gusts_10m_mean") val windGusts10mMean: List<Double>,
    @SerialName("surface_pressure_max") val surfacePressureMax: List<Double>,
    @SerialName("surface_pressure_min") val surfacePressureMin: List<Double>,
    @SerialName("wind_direction_10m_dominant") val windDirection10mDominant: List<Int>,
    @SerialName("wind_gusts_10m_min") val windGusts10mMin: List<Double>,
    @SerialName("wind_speed_10m_min") val windSpeed10mMin: List<Double>,
    @SerialName("cloud_cover_max") val cloudCoverMax: List<Int>,
    @SerialName("cloud_cover_min") val cloudCoverMin: List<Int>,
    @SerialName("pressure_msl_mean") val pressureMslMean: List<Double>,
    @SerialName("pressure_msl_max") val pressureMslMax: List<Double>,
    @SerialName("pressure_msl_min") val pressureMslMin: List<Double>,
    @SerialName("daylight_duration") val daylightDuration: List<Double>,
    @SerialName("uv_index_clear_sky_max") val uvIndexClearSkyMax: List<Double>
)
