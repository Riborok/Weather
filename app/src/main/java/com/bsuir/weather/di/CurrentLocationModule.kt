package com.bsuir.weather.di

import com.bsuir.weather.data.db.cache.LocationCache
import com.bsuir.weather.data.db.dao.LocationDao
import com.bsuir.weather.data.repository.CurrentLocationRepositoryImpl
import com.bsuir.weather.data.source.android.location.CurrentCoordinatesFetcher
import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.domain.usecase.GetCurrentLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentLocationModule {
    @Provides
    @Singleton
    fun provideLocationCache(locationDao: LocationDao): LocationCache {
        return LocationCache(locationDao)
    }

    @Provides
    @Singleton
    fun provideCurrentLocationRepository(
        currentCoordinatesFetcher: CurrentCoordinatesFetcher,
        locationFetcher: LocationFetcher,
        locationCache: LocationCache
    ): CurrentLocationRepository {
        return CurrentLocationRepositoryImpl(currentCoordinatesFetcher, locationFetcher, locationCache)
    }

    @Provides
    @Singleton
    fun provideGetCurrentLocationUseCase(
        currentLocationRepository: CurrentLocationRepository
    ): GetCurrentLocationUseCase {
        return GetCurrentLocationUseCase(currentLocationRepository)
    }
}