package com.bsuir.weather.di

import android.content.Context
import androidx.room.Room
import com.bsuir.weather.data.db.AppDatabase
import com.bsuir.weather.data.db.cache.ForecastCache
import com.bsuir.weather.data.db.dao.ForecastDao
import com.bsuir.weather.data.repository.ForecastRepositoryImpl
import com.bsuir.weather.data.source.network.weather.WeatherForecastNetwork
import com.bsuir.weather.domain.repository.ForecastRepository
import com.bsuir.weather.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastModule {

    @Provides
    @Singleton
    fun provideWeatherForecastNetwork(http: HttpClient, @ApplicationContext context: Context): WeatherForecastNetwork {
        return WeatherForecastNetwork(http, context)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideForecastDao(database: AppDatabase): ForecastDao {
        return database.forecastDao()
    }

    @Provides
    @Singleton
    fun provideForecastCache(forecastDao: ForecastDao): ForecastCache {
        return ForecastCache(forecastDao)
    }

    @Provides
    @Singleton
    fun provideForecastRepository(
        weatherForecastNetwork: WeatherForecastNetwork,
        forecastCache: ForecastCache
    ): ForecastRepository {
        return ForecastRepositoryImpl(weatherForecastNetwork, forecastCache)
    }

    @Provides
    @Singleton
    fun provideGetForecastUseCase(repository: ForecastRepository): GetForecastUseCase {
        return GetForecastUseCase(repository)
    }
}
