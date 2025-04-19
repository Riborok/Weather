package com.bsuir.weather.utils

import com.bsuir.weather.domain.model.AddressModel
import com.bsuir.weather.domain.model.LocationModel

const val mapZoom = 15f

val defaultLocation = LocationModel(
    latitude = 53.9115384,
    longitude = 27.5935445,
    address = AddressModel(
        countryName = "Беларусь",
        locality = "Минск",
        subLocality = "Советский район",
        adminArea = "Минская область",
        subAdminArea = "Минский район",
        thoroughfare = "улица Гикало",
        subThoroughfare = "24/1",
    )
)