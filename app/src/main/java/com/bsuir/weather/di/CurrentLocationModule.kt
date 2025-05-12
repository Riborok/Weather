package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.data.db.cache.CoordinatesCache
import com.bsuir.weather.data.db.dao.CoordinatesDao
import com.bsuir.weather.data.repository.CurrentLocationRepositoryImpl
import com.bsuir.weather.data.source.android.location.CurrentCoordinatesFetcher
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.domain.repository.LocationFromCoordinatesRepository
import com.bsuir.weather.domain.usecase.GetCurrentLocationUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentLocationModule {
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