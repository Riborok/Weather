package com.bsuir.weather.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO (
    val latitude: Double,
    val longitude: Double,
    val address: AddressDTO,
)

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
