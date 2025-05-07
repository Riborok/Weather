package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.data.repository.ForecastLocationRepositoryImpl
import com.bsuir.weather.data.source.datastore.ForecastLocationDataStore
import com.bsuir.weather.domain.repository.ForecastLocationRepository
import com.bsuir.weather.domain.usecase.ForecastLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastLocationModule {

    @Provides
    @Singleton
    fun provideForecastLocationDataStore(@ApplicationContext context: Context): ForecastLocationDataStore {
        return ForecastLocationDataStore(context)
    }

    @Provides
    @Singleton
    fun provideForecastLocationRepository(forecastLocationDataStore: ForecastLocationDataStore): ForecastLocationRepository {
        return ForecastLocationRepositoryImpl(forecastLocationDataStore)
    }

    @Provides
    @Singleton
    fun provideForecastLocationUseCase(repository: ForecastLocationRepository): ForecastLocationUseCase {
        return ForecastLocationUseCase(repository)
    }
}