package com.bsuir.weather.utils

import android.content.Context
import com.bsuir.weather.R
import com.bsuir.weather.domain.model.AddressModel
import com.bsuir.weather.domain.model.LocationModel

val Context.defaultLocation: LocationModel
    get() = LocationModel(
        latitude = 53.9115384,
        longitude = 27.5935445,
        address = AddressModel (
            countryName = getString(R.string.default_country_name),
            locality = getString(R.string.default_locality),
            subLocality = getString(R.string.default_sub_locality),
            adminArea = getString(R.string.default_admin_area),
            subAdminArea = getString(R.string.default_sub_admin_area),
            thoroughfare = getString(R.string.default_thoroughfare),
            subThoroughfare = getString(R.string.default_sub_thoroughfare)
        )
    )
