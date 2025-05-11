package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.data.repository.LocationSuggestionRepositoryImpl
import com.bsuir.weather.data.source.android.location.LocationSuggestionFetcher
import com.bsuir.weather.domain.repository.LocationSuggestionRepository
import com.bsuir.weather.domain.usecase.GetLocationSuggestionUseCase
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationSuggestionModule {
    @Provides
    @Singleton
    fun provideLocationSuggestionFetcher(
        @ApplicationContext context: Context,
        placesClient: PlacesClient,
    ): LocationSuggestionFetcher {
        return LocationSuggestionFetcher(context, placesClient)
    }

    @Provides
    @Singleton
    fun provideCurrentLocationRepository(
        locationSuggestionFetcher: LocationSuggestionFetcher
    ): LocationSuggestionRepository {
        return LocationSuggestionRepositoryImpl(locationSuggestionFetcher)
    }

    @Provides
    @Singleton
    fun provideGetLocationFromCoordinatesUseCase(
        locationSuggestionRepository: LocationSuggestionRepository
    ): GetLocationSuggestionUseCase {
        return GetLocationSuggestionUseCase(locationSuggestionRepository)
    }
}