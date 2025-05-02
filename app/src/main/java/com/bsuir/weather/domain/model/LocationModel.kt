package com.bsuir.weather.domain.model

data class LocationModel(
    val latitude: Double,
    val longitude: Double,
    val address: AddressModel = AddressModel()
)

data class AddressModel(
    val countryName: String? = null,
    val locality: String? = null,
    val subLocality: String? = null,
    val adminArea: String? = null,
    val subAdminArea: String? = null,
    val thoroughfare: String? = null,
    val subThoroughfare: String? = null,
) {
    var alias: String? = null
        set(value) {
            field = if (value.isNullOrBlank()) null else value
        }

    fun formatAddress(): String {
        return locality ?: subLocality ?: ""
    }

    fun getFullAddress(): String {
        return listOfNotNull(
            subThoroughfare,
            thoroughfare,
            subLocality,
            locality,
            subAdminArea,
            adminArea,
            countryName
        ).joinToString(", ")
    }
}
