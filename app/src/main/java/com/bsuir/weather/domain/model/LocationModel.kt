package com.bsuir.weather.domain.model

data class LocationModel(
    val latitude: Double,
    val longitude: Double,
    val name: String
) {
    constructor(latitude: Double, longitude: Double, anyName: Any? = null) : this(
        latitude,
        longitude,
        anyName?.toString() ?: "Unknown"
    )
}
