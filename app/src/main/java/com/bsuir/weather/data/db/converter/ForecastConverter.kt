package com.bsuir.weather.data.db.converter

import androidx.room.TypeConverter
import com.bsuir.weather.data.dto.ForecastDTO
import com.bsuir.weather.data.dto.ForecastDTO.Companion.serializer
import kotlinx.serialization.json.Json

class ForecastConverter {
    @TypeConverter
    fun fromForecast(forecast: ForecastDTO): String {
        return Json.encodeToString(serializer(), forecast)
    }

    @TypeConverter
    fun toForecast(value: String): ForecastDTO {
        return Json.decodeFromString(serializer(), value)
    }
}
