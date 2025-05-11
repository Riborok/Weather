package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.widget.updater.WeatherNotificationUpdater
import com.bsuir.weather.widget.WeatherWorkScheduler
import com.bsuir.weather.widget.updater.WeatherWidgetUpdater
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
    fun provideWeatherWidgetUpdater(@ApplicationContext context: Context): WeatherWidgetUpdater {
        return WeatherWidgetUpdater(context)
    }

    @Provides
    @Singleton
    fun provideWeatherNotificationUpdater(@ApplicationContext context: Context): WeatherNotificationUpdater {
        return WeatherNotificationUpdater(context)
    }

    @Provides
    @Singleton
    fun provideWeatherWorkScheduler(@ApplicationContext context: Context): WeatherWorkScheduler {
        return WeatherWorkScheduler(context)
    }
}