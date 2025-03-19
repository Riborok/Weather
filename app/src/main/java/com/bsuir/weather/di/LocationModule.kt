package com.bsuir.weather.di

import com.bsuir.weather.data.repository.FakeLocationRepository
import com.bsuir.weather.domain.repository.LocationRepository
import com.bsuir.weather.domain.usecase.LocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationRepository(): LocationRepository {
        return FakeLocationRepository()
    }

    @Provides
    @Singleton
    fun provideLocationUseCase(repository: LocationRepository): LocationUseCase {
        return LocationUseCase(repository)
    }
}
