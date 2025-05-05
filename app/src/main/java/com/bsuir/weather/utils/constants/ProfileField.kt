package com.bsuir.weather.utils.constants
import androidx.annotation.StringRes
import com.bsuir.weather.R

enum class ProfileField(@StringRes val nameResId: Int, @StringRes val unitId: Int = R.string.no_unit) {

    TIME(R.string.time),

    TEMPERATURE(R.string.profile_field_temperature, R.string.celsius_degrees),
    APPARENT_TEMPERATURE(R.string.profile_field_apparent_temperature, R.string.celsius_degrees),
    TEMPERATURE_80M(R.string.profile_field_temperature_80m, R.string.celsius_degrees),
    TEMPERATURE_120M(R.string.profile_field_temperature_120m, R.string.celsius_degrees),
    TEMPERATURE_180M(R.string.profile_field_temperature_180m, R.string.celsius_degrees),

    RELATIVE_HUMIDITY(R.string.profile_field_relative_humidity, R.string.percent),
    PRECIPITATION(R.string.profile_field_precipitation, R.string.millimeters),
    PRECIPITATION_PROBABILITY(R.string.profile_field_precipitation_probability, R.string.percent),
    CLOUD_COVER(R.string.profile_field_cloud_cover, R.string.percent),

    // Atmospheric pressure
    SURFACE_PRESSURE(R.string.profile_field_surface_pressure, R.string.hectopascal),
    PRESSURE_MSL(R.string.profile_field_pressure_msl, R.string.hectopascal),

    // UV and visibility
    UV_INDEX(R.string.profile_field_uv_index),
    UV_INDEX_CLEAR_SKY(R.string.profile_field_uv_index_clear_sky),
    VISIBILITY(R.string.profile_field_visibility, R.string.kilometers),

    // Wind speed
    WIND_SPEED_10M(R.string.profile_field_wind_speed_10m, R.string.kilometers_per_hour),
    WIND_SPEED_80M(R.string.profile_field_wind_speed_80m, R.string.kilometers_per_hour),
    WIND_SPEED_120M(R.string.profile_field_wind_speed_120m, R.string.kilometers_per_hour),
    WIND_SPEED_180M(R.string.profile_field_wind_speed_180m, R.string.kilometers_per_hour),

    // Wind gusts
    WIND_GUSTS_10M(R.string.profile_field_wind_gusts_10m, R.string.kilometers_per_hour),

    // Wind direction
    WIND_DIRECTION_ID_10M(R.string.profile_field_wind_direction_id_10m),
    WIND_DIRECTION_ID_80M(R.string.profile_field_wind_direction_id_80m),
    WIND_DIRECTION_ID_120M(R.string.profile_field_wind_direction_id_120m),
    WIND_DIRECTION_ID_180M(R.string.profile_field_wind_direction_id_180m),

    // Soil temperature
    SOIL_TEMPERATURE_0CM(R.string.profile_field_soil_temperature_0cm, R.string.celsius_degrees),
    SOIL_TEMPERATURE_6CM(R.string.profile_field_soil_temperature_6cm, R.string.celsius_degrees),
    SOIL_TEMPERATURE_18CM(R.string.profile_field_soil_temperature_18cm, R.string.celsius_degrees),
    SOIL_TEMPERATURE_54CM(R.string.profile_field_soil_temperature_54cm, R.string.celsius_degrees),

    // Soil moisture
    SOIL_MOISTURE_0TO1CM(R.string.profile_field_soil_moisture_0to1cm, R.string.m3_per_m3),
    SOIL_MOISTURE_1TO3CM(R.string.profile_field_soil_moisture_1to3cm, R.string.m3_per_m3),
    SOIL_MOISTURE_3TO9CM(R.string.profile_field_soil_moisture_3to9cm, R.string.m3_per_m3),
    SOIL_MOISTURE_9TO27CM(R.string.profile_field_soil_moisture_9to27cm, R.string.m3_per_m3),
    SOIL_MOISTURE_27TO81CM(R.string.profile_field_soil_moisture_27to81cm, R.string.m3_per_m3),

}