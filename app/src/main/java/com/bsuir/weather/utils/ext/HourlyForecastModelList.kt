package com.bsuir.weather.utils.ext

import com.bsuir.weather.domain.model.HourlyForecastModel
import com.bsuir.weather.utils.constants.ProfileField

fun List<HourlyForecastModel>.extractFieldValues(
    field: ProfileField
): List<Any> {
    val extractor = fieldExtractors[field] ?: return emptyList()
    return this.map { extractor(it) }
}

private val fieldExtractors: Map<ProfileField, HourlyForecastModel.() -> Any> = mapOf(
    // Time
    ProfileField.TIME                      to { time.time.toString() },

    // Temperature-related
    ProfileField.TEMPERATURE               to { temperature },
    ProfileField.APPARENT_TEMPERATURE      to { apparentTemperature },
    ProfileField.TEMPERATURE_80M           to { temperature80m },
    ProfileField.TEMPERATURE_120M          to { temperature120m },
    ProfileField.TEMPERATURE_180M          to { temperature180m },

    // Humidity & precipitation
    ProfileField.RELATIVE_HUMIDITY         to { relativeHumidity },
    ProfileField.PRECIPITATION             to { precipitation },
    ProfileField.PRECIPITATION_PROBABILITY to { precipitationProbability },
    ProfileField.CLOUD_COVER               to { cloudCover },

    // Pressure
    ProfileField.SURFACE_PRESSURE          to { surfacePressure },
    ProfileField.PRESSURE_MSL              to { pressureMsl },

    // UV & visibility
    ProfileField.UV_INDEX                  to { uvIndex },
    ProfileField.UV_INDEX_CLEAR_SKY        to { uvIndexClearSky },
    ProfileField.VISIBILITY                to { visibility },

    // Wind speeds
    ProfileField.WIND_SPEED_10M            to { windSpeed10m },
    ProfileField.WIND_SPEED_80M            to { windSpeed80m },
    ProfileField.WIND_SPEED_120M           to { windSpeed120m },
    ProfileField.WIND_SPEED_180M           to { windSpeed180m },

    // Gusts & directions
    ProfileField.WIND_GUSTS_10M            to { windGusts10m },
    ProfileField.WIND_DIRECTION_ID_10M     to { windDirectionId10m },
    ProfileField.WIND_DIRECTION_ID_80M     to { windDirectionId80m },
    ProfileField.WIND_DIRECTION_ID_120M    to { windDirectionId120m },
    ProfileField.WIND_DIRECTION_ID_180M    to { windDirectionId180m },

    // Soil temperature & moisture
    ProfileField.SOIL_TEMPERATURE_0CM      to { soilTemperature0cm },
    ProfileField.SOIL_TEMPERATURE_6CM      to { soilTemperature6cm },
    ProfileField.SOIL_TEMPERATURE_18CM     to { soilTemperature18cm },
    ProfileField.SOIL_TEMPERATURE_54CM     to { soilTemperature54cm },
    ProfileField.SOIL_MOISTURE_0TO1CM      to { soilMoisture0to1cm },
    ProfileField.SOIL_MOISTURE_1TO3CM      to { soilMoisture1to3cm },
    ProfileField.SOIL_MOISTURE_3TO9CM      to { soilMoisture3to9cm },
    ProfileField.SOIL_MOISTURE_9TO27CM     to { soilMoisture9to27cm },
    ProfileField.SOIL_MOISTURE_27TO81CM    to { soilMoisture27to81cm },

)