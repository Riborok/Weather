package com.bsuir.weather.di

import com.bsuir.weather.data.repository.ForecastRepositoryImpl
import com.bsuir.weather.data.source.network.WeatherForecastNetwork
import com.bsuir.weather.domain.repository.ForecastRepository
import com.bsuir.weather.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastModule {

    @Provides
    @Singleton
    fun provideWeatherForecastNetwork(http: HttpClient): WeatherForecastNetwork {
        return WeatherForecastNetwork(http)
    }

    @Provides
    @Singleton
    fun provideForecastRepository(weatherForecastNetwork: WeatherForecastNetwork): ForecastRepository {
        return ForecastRepositoryImpl(weatherForecastNetwork)
    }

    @Provides
    @Singleton
    fun provideGetForecastUseCase(repository: ForecastRepository): GetForecastUseCase {
        return GetForecastUseCase(repository)
    }
}
