package com.bsuir.weather.di

import android.content.Context
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.bsuir.weather.data.repository.LocationListRepositoryImpl
import com.bsuir.weather.data.source.datastore.LocationListDataStore
import com.bsuir.weather.domain.repository.LocationListRepository
import com.bsuir.weather.domain.usecase.StoredLocationListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedLocationListModule {

    private val SAVED_LOCATIONS_KEY = stringSetPreferencesKey("saved_locations")

    @Provides
    @Singleton
    @Named("SavedLocationListDataStore")
    fun provideSavedLocationDataStore(@ApplicationContext context: Context): LocationListDataStore {
        return LocationListDataStore(context, SAVED_LOCATIONS_KEY)
    }

    @Provides
    @Singleton
    @Named("SavedLocationListRepository")
    fun provideSavedLocationRepository(
        @Named("SavedLocationListDataStore") savedLocationListDataStore: LocationListDataStore
    ): LocationListRepository {
        return LocationListRepositoryImpl(savedLocationListDataStore)
    }

    @Provides
    @Singleton
    @Named("SavedLocationListUseCase")
    fun provideSavedLocationUseCase(
        @Named("SavedLocationListRepository") savedLocationListRepository: LocationListRepository
    ): StoredLocationListUseCase {
        return StoredLocationListUseCase(savedLocationListRepository)
    }
}
