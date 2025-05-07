package com.bsuir.weather

import android.app.Application
import android.location.Geocoder
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.bsuir.weather.BuildConfig.MAPS_API_KEY
import com.bsuir.weather.utils.ext.currentLocale
import com.bsuir.weather.widget.WeatherWorkScheduler
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application(), Configuration.Provider {
    lateinit var placesClient: PlacesClient
    lateinit var geocoder: Geocoder
    lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject lateinit var workerFactory: HiltWorkerFactory
    @Inject lateinit var weatherWorkScheduler: WeatherWorkScheduler

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
        fusedLocationClient = getFusedLocationProviderClient(this)

        weatherWorkScheduler.scheduleWeatherUpdates()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
