package com.bsuir.weather.utils.constants
import androidx.annotation.StringRes
import com.bsuir.weather.R

enum class ProfileField(@StringRes val nameResId: Int) {

    TEMPERATURE(R.string.profile_field_temperature),
    APPARENT_TEMPERATURE(R.string.profile_field_apparent_temperature),
    TEMPERATURE_80M(R.string.profile_field_temperature_80m),
    TEMPERATURE_120M(R.string.profile_field_temperature_120m),
    TEMPERATURE_180M(R.string.profile_field_temperature_180m),

    RELATIVE_HUMIDITY(R.string.profile_field_relative_humidity),
    PRECIPITATION(R.string.profile_field_precipitation),
    PRECIPITATION_PROBABILITY(R.string.profile_field_precipitation_probability),
    CLOUD_COVER(R.string.profile_field_cloud_cover),

    // Atmospheric pressure
    SURFACE_PRESSURE(R.string.profile_field_surface_pressure),
    PRESSURE_MSL(R.string.profile_field_pressure_msl),

    // UV and visibility
    UV_INDEX(R.string.profile_field_uv_index),
    UV_INDEX_CLEAR_SKY(R.string.profile_field_uv_index_clear_sky),
    VISIBILITY(R.string.profile_field_visibility),

    // Wind speed
    WIND_SPEED_10M(R.string.profile_field_wind_speed_10m),
    WIND_SPEED_80M(R.string.profile_field_wind_speed_80m),
    WIND_SPEED_120M(R.string.profile_field_wind_speed_120m),
    WIND_SPEED_180M(R.string.profile_field_wind_speed_180m),

    // Wind gusts
    WIND_GUSTS_10M(R.string.profile_field_wind_gusts_10m),

    // Wind direction
    WIND_DIRECTION_ID_10M(R.string.profile_field_wind_direction_id_10m),
    WIND_DIRECTION_ID_80M(R.string.profile_field_wind_direction_id_80m),
    WIND_DIRECTION_ID_120M(R.string.profile_field_wind_direction_id_120m),
    WIND_DIRECTION_ID_180M(R.string.profile_field_wind_direction_id_180m),

    // Soil temperature
    SOIL_TEMPERATURE_0CM(R.string.profile_field_soil_temperature_0cm),
    SOIL_TEMPERATURE_6CM(R.string.profile_field_soil_temperature_6cm),
    SOIL_TEMPERATURE_18CM(R.string.profile_field_soil_temperature_18cm),
    SOIL_TEMPERATURE_54CM(R.string.profile_field_soil_temperature_54cm),

    // Soil moisture
    SOIL_MOISTURE_0TO1CM(R.string.profile_field_soil_moisture_0to1cm),
    SOIL_MOISTURE_1TO3CM(R.string.profile_field_soil_moisture_1to3cm),
    SOIL_MOISTURE_3TO9CM(R.string.profile_field_soil_moisture_3to9cm),
    SOIL_MOISTURE_9TO27CM(R.string.profile_field_soil_moisture_9to27cm),
    SOIL_MOISTURE_27TO81CM(R.string.profile_field_soil_moisture_27to81cm),

    // Visual indicators
    ICON_ID(R.string.profile_field_icon_id),
    WEATHER_DESCRIPTION_ID(R.string.profile_field_weather_description_id)

}