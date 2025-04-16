package com.bsuir.weather.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LocationDTO (
    val latitude: Double,
    val longitude: Double,
    val name: String,
) {
    fun toJson(): String = Json.encodeToString(serializer(), this)

    companion object {
        fun fromJson(json: String): LocationDTO = Json.decodeFromString(serializer(), json)
    }
}
