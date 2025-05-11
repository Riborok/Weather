package com.bsuir.weather.di

import com.bsuir.weather.data.db.cache.CoordinatesCache
import com.bsuir.weather.data.db.cache.LocationCache
import com.bsuir.weather.data.db.dao.CoordinatesDao
import com.bsuir.weather.data.db.dao.LocationDao
import com.bsuir.weather.data.repository.CurrentLocationRepositoryImpl
import com.bsuir.weather.data.source.android.location.CurrentCoordinatesFetcher
import com.bsuir.weather.data.source.android.location.LocationFetcher
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.domain.repository.LocationFromCoordinatesRepository
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
    fun provideCoordinatesCache(coordinatesDao: CoordinatesDao): CoordinatesCache {
        return CoordinatesCache(coordinatesDao)
    }

    @Provides
    @Singleton
    fun provideCurrentLocationRepository(
        currentCoordinatesFetcher: CurrentCoordinatesFetcher,
        coordinatesCache: CoordinatesCache,
        locationFromCoordinatesRepository: LocationFromCoordinatesRepository,
    ): CurrentLocationRepository {
        return CurrentLocationRepositoryImpl(currentCoordinatesFetcher, coordinatesCache, locationFromCoordinatesRepository)
    }

    @Provides
    @Singleton
    fun provideGetCurrentLocationUseCase(
        currentLocationRepository: CurrentLocationRepository
    ): GetCurrentLocationUseCase {
        return GetCurrentLocationUseCase(currentLocationRepository)
    }
}