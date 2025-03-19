package com.bsuir.weather.usecase

class LocationManager {
    fun getCurrentLocation(): String {
        return "г. Минск"
    }

    fun getSavedLocations(): List<String> {
        return listOf<String>(
            "д. Копище",
            "г. Бобруйск",
            "г. Аляулюпалякулюмалютулюпинск",
        )
    }
}