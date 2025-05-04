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
    private var _alias: String? = null
) {
    init {
        _alias = processAlias(_alias)
    }

    var alias: String?
        get() = _alias
        set(value) {
            _alias = processAlias(value)
        }

    private fun processAlias(value: String?): String? {
        return value
            ?.trim()
            ?.replace(Regex("\\s+"), " ")
            ?.takeIf { it.isNotBlank() }
    }
}
