package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.widget.WeatherWorkScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherWorkModule {

    @Provides
    @Singleton
    fun provideWeatherWorkScheduler(@ApplicationContext context: Context): WeatherWorkScheduler {
        return WeatherWorkScheduler(context)
    }
}