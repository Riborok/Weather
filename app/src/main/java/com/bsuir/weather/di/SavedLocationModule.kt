package com.bsuir.weather.di

import android.content.Context
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.bsuir.weather.data.repository.LocationRepositoryImpl
import com.bsuir.weather.data.source.datastore.LocationDataStore
import com.bsuir.weather.domain.repository.LocationRepository
import com.bsuir.weather.domain.usecase.StoredLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedLocationModule {

    private val SAVED_LOCATIONS_KEY = stringSetPreferencesKey("saved_locations")

    @Provides
    @Singleton
    @Named("SavedLocationDataStore")
    fun provideSavedLocationDataStore(@ApplicationContext context: Context): LocationDataStore {
        return LocationDataStore(context, SAVED_LOCATIONS_KEY)
    }

    @Provides
    @Singleton
    @Named("SavedLocationRepository")
    fun provideSavedLocationRepository(
        @Named("SavedLocationDataStore") savedLocationDataStore: LocationDataStore
    ): LocationRepository {
        return LocationRepositoryImpl(savedLocationDataStore)
    }

    @Provides
    @Singleton
    @Named("SavedLocationUseCase")
    fun provideSavedLocationUseCase(
        @Named("SavedLocationRepository") savedLocationRepository: LocationRepository
    ): StoredLocationUseCase {
        return StoredLocationUseCase(savedLocationRepository)
    }
}
