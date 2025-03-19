package com.bsuir.weather.domain.usecase

class LocationUseCase {
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