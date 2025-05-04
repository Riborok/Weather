package com.bsuir.weather

import android.app.Application
import android.location.Geocoder
import com.bsuir.weather.BuildConfig.MAPS_API_KEY
import com.bsuir.weather.utils.ext.currentLocale
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() {
    lateinit var placesClient: PlacesClient
    lateinit var geocoder: Geocoder

    override fun onCreate() {
        super.onCreate()

        if (!Places.isInitialized()) {
            Places.initialize(
                applicationContext,
                MAPS_API_KEY
            )
        }

        placesClient = Places.createClient(this)
        geocoder = Geocoder(this, currentLocale)
    }
}
