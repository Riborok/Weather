package com.bsuir.weather.di

import android.location.Geocoder
import com.bsuir.weather.data.db.cache.LocationCache
import com.bsuir.weather.data.db.dao.LocationDao
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
    fun provideLocationFetcher(
        geocoder: Geocoder
    ): LocationFetcher {
        return LocationFetcher(geocoder)
    }

    @Provides
    @Singleton
    fun provideLocationCache(locationDao: LocationDao): LocationCache {
        return LocationCache(locationDao)
    }

    @Provides
    @Singleton
    fun provideCurrentLocationRepository(
        locationFetcher: LocationFetcher,
        locationCache: LocationCache
    ): LocationFromCoordinatesRepository {
        return LocationFromCoordinatesRepositoryImpl(locationFetcher, locationCache)
    }

    @Provides
    @Singleton
    fun provideGetLocationFromCoordinatesUseCase(
        locationFromCoordinatesRepository: LocationFromCoordinatesRepository
    ): GetLocationFromCoordinatesUseCase {
        return GetLocationFromCoordinatesUseCase(locationFromCoordinatesRepository)
    }
}