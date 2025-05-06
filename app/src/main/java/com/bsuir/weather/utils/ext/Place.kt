package com.bsuir.weather.utils.ext

import com.bsuir.weather.domain.model.AddressModel
import com.google.android.libraries.places.api.model.AddressComponent
import com.google.android.libraries.places.api.model.Place

val Place.AddressModel: AddressModel
    get() = with(addressComponents?.asList()) {
        AddressModel(
            countryName = extractAddressComponent("country"),
            locality = extractAddressComponent("locality"),
            subLocality = extractAddressComponent("sublocality"),
            adminArea = extractAddressComponent("administrative_area_level_1"),
            subAdminArea = extractAddressComponent("administrative_area_level_2"),
            thoroughfare = extractAddressComponent("route"),
            subThoroughfare = extractAddressComponent("street_number")
        )
    }

private fun List<AddressComponent>?.extractAddressComponent(
    type: String
): String? {
    return this
        ?.find { it.types.contains(type) }
        ?.name
}