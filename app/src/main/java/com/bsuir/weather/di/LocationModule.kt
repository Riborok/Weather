package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.data.repository.LocationRepositoryImpl
import com.bsuir.weather.data.source.datastore.LocationDataStore
import com.bsuir.weather.domain.repository.LocationRepository
import com.bsuir.weather.domain.usecase.LocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationDataStore(@ApplicationContext context: Context): LocationDataStore {
        return LocationDataStore(context)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(locationDataStore: LocationDataStore): LocationRepository {
        return LocationRepositoryImpl(locationDataStore)
    }

    @Provides
    @Singleton
    fun provideLocationUseCase(repository: LocationRepository): LocationUseCase {
        return LocationUseCase(repository)
    }
}
