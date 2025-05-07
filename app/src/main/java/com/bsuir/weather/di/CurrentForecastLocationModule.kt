package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.data.repository.CurrentLocationRepositoryImpl
import com.bsuir.weather.domain.repository.CurrentLocationRepository
import com.bsuir.weather.domain.usecase.GetCurrentForecastLocationUseCase
import com.bsuir.weather.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentForecastLocationModule {

    @Provides
    @Singleton
    fun provideCurrentLocationRepository(@ApplicationContext context: Context): CurrentLocationRepository {
        return CurrentLocationRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideGetCurrentForecastLocationUseCase(
        currentLocationRepository: CurrentLocationRepository,
        forecastUseCase: GetForecastUseCase,
    ): GetCurrentForecastLocationUseCase {
        return GetCurrentForecastLocationUseCase(currentLocationRepository, forecastUseCase)
    }
}