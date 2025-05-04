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
    private var aliasName: String? = null
) {
    init {
        aliasName = processAlias(aliasName)
    }

    var alias: String?
        get() = aliasName
        set(value) {
            aliasName = processAlias(value)
        }

    private fun processAlias(value: String?): String? {
        return value
            ?.trim()
            ?.replace(Regex("\\s+"), " ")
            ?.takeIf { it.isNotBlank() }
    }

    fun aliasWithFormatAddress(): String? {
        return alias?.let { aliasName ->

            formatAddress()?.let { formatAddress ->
                "$aliasName ($formatAddress)"
            } ?: aliasName

        } ?: formatAddress()
    }

    fun formatAddress(): String? {
        return when {
            thoroughfare != null && subThoroughfare != null -> "$thoroughfare, $subThoroughfare"
            thoroughfare != null -> thoroughfare
            else -> subLocality
                ?: locality
                ?: subAdminArea
                ?: adminArea
                ?: countryName
        }?.replaceFirstChar { it.uppercase() }
    }

    fun fullAddress(): String {
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
