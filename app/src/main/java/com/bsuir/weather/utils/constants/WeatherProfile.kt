package com.bsuir.weather.utils.constants

import androidx.annotation.StringRes
import com.bsuir.weather.R

enum class WeatherProfile(
    @StringRes val nameResId: Int,
    val fields: List<ProfileField>
) {

    GENERAL(
        R.string.profile_main,
        listOf(
            ProfileField.TIME,
            ProfileField.TEMPERATURE,
            ProfileField.APPARENT_TEMPERATURE,
            ProfileField.RELATIVE_HUMIDITY,
            ProfileField.PRECIPITATION,
            ProfileField.CLOUD_COVER,
            ProfileField.UV_INDEX
        )
    ),

    DRIVER(
        R.string.profile_driver,
        listOf(
            ProfileField.TIME,
            ProfileField.TEMPERATURE,
            ProfileField.PRECIPITATION,
            ProfileField.VISIBILITY,
            ProfileField.WIND_SPEED_10M,
            ProfileField.WIND_GUSTS_10M,
            ProfileField.WIND_DIRECTION_ID_10M,
            ProfileField.SURFACE_PRESSURE,
            ProfileField.PRESSURE_MSL
        )
    ),

    AGRONOMIST(
        R.string.profile_agronomist,
        listOf(
            ProfileField.TIME,
            ProfileField.TEMPERATURE,
            ProfileField.RELATIVE_HUMIDITY,
            ProfileField.CLOUD_COVER,
            ProfileField.PRECIPITATION,
            ProfileField.PRECIPITATION_PROBABILITY,
            ProfileField.SOIL_TEMPERATURE_0CM,
            ProfileField.SOIL_TEMPERATURE_6CM,
            ProfileField.SOIL_TEMPERATURE_18CM,
            ProfileField.SOIL_TEMPERATURE_54CM,
            ProfileField.SOIL_MOISTURE_0TO1CM,
            ProfileField.SOIL_MOISTURE_1TO3CM,
            ProfileField.SOIL_MOISTURE_3TO9CM,
            ProfileField.SOIL_MOISTURE_9TO27CM,
            ProfileField.SOIL_MOISTURE_27TO81CM
        )
    ),

    TOURIST(
        R.string.profile_tourist,
        listOf(
            ProfileField.TIME,
            ProfileField.TEMPERATURE,
            ProfileField.APPARENT_TEMPERATURE,
            ProfileField.PRECIPITATION,
            ProfileField.CLOUD_COVER,
            ProfileField.UV_INDEX,
            ProfileField.UV_INDEX_CLEAR_SKY,
            ProfileField.VISIBILITY,
            ProfileField.WIND_SPEED_10M,
            ProfileField.WIND_GUSTS_10M
        )
    ),

    ROOFER(
        R.string.profile_roofer,
        listOf(
            ProfileField.TIME,
            ProfileField.TEMPERATURE_80M,
            ProfileField.TEMPERATURE_120M,
            ProfileField.TEMPERATURE_180M,
            ProfileField.WIND_SPEED_80M,
            ProfileField.WIND_SPEED_120M,
            ProfileField.WIND_SPEED_180M,
            ProfileField.WIND_DIRECTION_ID_80M,
            ProfileField.WIND_DIRECTION_ID_120M,
            ProfileField.WIND_DIRECTION_ID_180M
        )
    )
}