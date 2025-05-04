package com.bsuir.weather.utils.ext

import com.bsuir.weather.domain.model.AddressModel

fun AddressModel.aliasWithFormatAddress(): String? {
    return alias?.let { aliasName ->

        formatAddress()?.let { formatAddress ->
            "$aliasName ($formatAddress)"
        } ?: aliasName

    } ?: formatAddress()
}

fun AddressModel.formatAddress(): String? {
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

fun AddressModel.fullAddress(): String {
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
