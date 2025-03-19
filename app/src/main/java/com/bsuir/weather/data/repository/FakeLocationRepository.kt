package com.bsuir.weather.data.repository

import com.bsuir.weather.domain.repository.LocationRepository
import javax.inject.Inject

class FakeLocationRepository @Inject constructor() : LocationRepository {
    override fun getCurrentLocation(): String {
        return "г. Минск"
    }

    override fun getSavedLocations(): List<String> {
        return listOf(
            "д. Копище",
            "г. Бобруйск",
            "г. Аляулюпалякулюмалютулюпинск"
        )
    }
}
