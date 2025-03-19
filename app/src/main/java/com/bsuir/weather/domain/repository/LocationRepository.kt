package com.bsuir.weather.domain.repository

interface LocationRepository {
    fun getCurrentLocation(): String
    fun getSavedLocations(): List<String>
}
