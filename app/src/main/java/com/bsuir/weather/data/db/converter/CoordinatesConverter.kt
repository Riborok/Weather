package com.bsuir.weather.data.db.converter

import androidx.room.TypeConverter
import com.bsuir.weather.data.dto.CoordinatesDTO
import com.bsuir.weather.data.dto.CoordinatesDTO.Companion.serializer
import kotlinx.serialization.json.Json

class CoordinatesConverter {
    @TypeConverter
    fun fromCoordinate(coordinates: CoordinatesDTO): String {
        return Json.encodeToString(serializer(), coordinates)
    }

    @TypeConverter
    fun toCoordinate(value: String): CoordinatesDTO {
        return Json.decodeFromString(serializer(), value)
    }
}