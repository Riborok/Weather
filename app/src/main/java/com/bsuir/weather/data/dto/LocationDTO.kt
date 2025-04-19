package com.bsuir.weather.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LocationDTO (
    val latitude: Double,
    val longitude: Double,
    val address: AddressDTO,
) {
    fun toJson(): String = Json.encodeToString(serializer(), this)

    companion object {
        fun fromJson(json: String): LocationDTO = Json.decodeFromString(serializer(), json)
    }
}

@Serializable
data class AddressDTO (
    val countryName: String?,
    val locality: String?,
    val subLocality: String?,
    val adminArea: String?,
    val subAdminArea: String?,
    val thoroughfare: String?,
    val subThoroughfare: String?,
    val alias: String?
)
