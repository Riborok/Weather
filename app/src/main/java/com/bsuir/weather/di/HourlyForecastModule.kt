package com.bsuir.weather.di

import com.bsuir.weather.data.repository.FakeHourlyForecastRepository
import com.bsuir.weather.domain.repository.HourlyForecastRepository
import com.bsuir.weather.domain.usecase.GetHourlyForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HourlyForecastModule {

    @Provides
    @Singleton
    fun provideHourlyForecastRepository(): HourlyForecastRepository {
        return FakeHourlyForecastRepository()
    }

    @Provides
    @Singleton
    fun provideGetHourlyForecastUseCase(repository: HourlyForecastRepository): GetHourlyForecastUseCase {
        return GetHourlyForecastUseCase(repository)
    }
}
