package com.bsuir.weather.di

import android.content.Context
import android.location.Geocoder
import com.bsuir.weather.BuildConfig.MAPS_API_KEY
import com.bsuir.weather.data.source.android.location.CurrentCoordinatesFetcher
import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.data.source.android.location.LocationSuggestionFetcher
import com.bsuir.weather.utils.ext.currentLocale
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AndroidLocationFetchersModule {

    @Provides
    @Singleton
    fun providePlacesClient(@ApplicationContext context: Context): PlacesClient {
        if (!Places.isInitialized()) {
            Places.initialize(
                context,
                MAPS_API_KEY
            )
        }
        return Places.createClient(context)
    }

    @Provides
    @Singleton
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder {
        return Geocoder(context, context.currentLocale)
    }

    @Provides
    @Singleton
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideCurrentCoordinatesFetcher(
        @ApplicationContext context: Context,
        fusedClient: FusedLocationProviderClient
    ): CurrentCoordinatesFetcher {
        return CurrentCoordinatesFetcher(context, fusedClient)
    }

    @Provides
    @Singleton
    fun provideLocationFetcher(
        geocoder: Geocoder
    ): LocationFetcher {
        return LocationFetcher(geocoder)
    }

    @Provides
    @Singleton
    fun provideLocationSuggestionFetcher(
        @ApplicationContext context: Context,
        placesClient: PlacesClient,
    ): LocationSuggestionFetcher {
        return LocationSuggestionFetcher(context, placesClient)
    }
}