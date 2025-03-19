package com.bsuir.weather.di

import com.bsuir.weather.data.repository.FakeDailyForecastRepository
import com.bsuir.weather.domain.repository.DailyForecastRepository
import com.bsuir.weather.domain.usecase.GetDailyForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DailyForecastModule {

    @Provides
    @Singleton
    fun provideDailyForecastRepository(): DailyForecastRepository {
        return FakeDailyForecastRepository()
    }

    @Provides
    @Singleton
    fun provideGetDailyForecastUseCase(repository: DailyForecastRepository): GetDailyForecastUseCase {
        return GetDailyForecastUseCase(repository)
    }
}
