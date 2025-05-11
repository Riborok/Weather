package com.bsuir.weather.di

import com.bsuir.weather.domain.usecase.GetCurrentForecastLocationUseCase
import com.bsuir.weather.domain.usecase.GetCurrentLocationUseCase
import com.bsuir.weather.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentForecastLocationModule {
    @Provides
    @Singleton
    fun provideGetCurrentForecastLocationUseCase(
        getCurrentLocationUseCase: GetCurrentLocationUseCase,
        forecastUseCase: GetForecastUseCase,
    ): GetCurrentForecastLocationUseCase {
        return GetCurrentForecastLocationUseCase(getCurrentLocationUseCase, forecastUseCase)
    }
}