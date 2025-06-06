package com.bsuir.weather.utils.ext

import android.content.Context
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.AddressModel

fun AddressModel.aliasWithFormatAddress(): String? {
    return alias?.let { aliasName ->

        formatAddress()?.let { formatAddress ->
            "$aliasName ($formatAddress)"
        } ?: aliasName

    } ?: formatAddress()
}

fun AddressModel.formattedOrUnknown(context: Context): String {
    return this.formatAddress()
        ?: context.getString(R.string.unknown_address)
}

fun AddressModel.formatAddress(): String? {
    return when {
        subLocality != null && locality != null -> "$locality, $subLocality"
        else -> locality
            ?: subAdminArea
            ?: adminArea
            ?: countryName
    }?.replaceFirstChar { it.uppercase() }
}

fun AddressModel.fullAddress(): String {
    return listOfNotNull(
        subLocality,
        locality,
        subAdminArea,
        adminArea,
        countryName
    ).joinToString(", ")
}
