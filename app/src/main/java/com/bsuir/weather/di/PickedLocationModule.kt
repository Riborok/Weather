package com.bsuir.weather.di

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
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
object PickedLocationModule {

    private val PICKED_LOCATION_KEY = stringPreferencesKey("picked_location")

    @Provides
    @Singleton
    @Named("PickedDataStore")
    fun providePickedLocationDataStore(@ApplicationContext context: Context): LocationDataStore {
        return LocationDataStore(context, PICKED_LOCATION_KEY)
    }

    @Provides
    @Singleton
    @Named("PickedLocationRepository")
    fun providePickedLocationRepository(
        @Named("PickedDataStore") pickedLocationDataStore: LocationDataStore
    ): LocationRepository {
        return LocationRepositoryImpl(pickedLocationDataStore)
    }

    @Provides
    @Singleton
    @Named("PickedLocationUseCase")
    fun providePickedLocationUseCase(
        @Named("PickedLocationRepository") pickedLocationRepository: LocationRepository
    ): StoredLocationUseCase {
        return StoredLocationUseCase(pickedLocationRepository)
    }
}