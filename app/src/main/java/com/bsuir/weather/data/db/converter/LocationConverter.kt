package com.bsuir.weather.data.db.converter

import androidx.room.TypeConverter
import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.data.dto.LocationDTO.Companion.serializer
import kotlinx.serialization.json.Json

class LocationConverter {
    @TypeConverter
    fun fromLocation(location: LocationDTO): String {
        return Json.encodeToString(serializer(), location)
    }

    @TypeConverter
    fun toLocation(value: String): LocationDTO {
        return Json.decodeFromString(serializer(), value)
    }
}
