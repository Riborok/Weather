package com.bsuir.weather.di

import com.bsuir.weather.data.repository.LocationFromCoordinatesRepositoryImpl
import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.domain.repository.LocationFromCoordinatesRepository
import com.bsuir.weather.domain.usecase.GetLocationFromCoordinatesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationFromCoordinatesModule {
    @Provides
    @Singleton
    fun provideCurrentLocationRepository(
        locationFetcher: LocationFetcher
    ): LocationFromCoordinatesRepository {
        return LocationFromCoordinatesRepositoryImpl(locationFetcher)
    }

    @Provides
    @Singleton
    fun provideGetLocationFromCoordinatesUseCase(
        locationFromCoordinatesRepository: LocationFromCoordinatesRepository
    ): GetLocationFromCoordinatesUseCase {
        return GetLocationFromCoordinatesUseCase(locationFromCoordinatesRepository)
    }
}