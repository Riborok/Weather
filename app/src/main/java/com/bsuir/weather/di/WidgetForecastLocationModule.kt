package com.bsuir.weather.di

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.bsuir.weather.data.repository.ForecastLocationRepositoryImpl
import com.bsuir.weather.data.source.datastore.ForecastLocationDataStore
import com.bsuir.weather.domain.repository.ForecastLocationRepository
import com.bsuir.weather.domain.usecase.StoredForecastLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WidgetForecastLocationModule {

    private val WIDGET_FORECAST_LOCATION_KEY = stringPreferencesKey("widget_forecast_location")

    @Provides
    @Singleton
    @Named("WidgetForecastDataStore")
    fun provideWidgetForecastLocationDataStore(@ApplicationContext context: Context): ForecastLocationDataStore {
        return ForecastLocationDataStore(context, WIDGET_FORECAST_LOCATION_KEY)
    }

    @Provides
    @Singleton
    @Named("WidgetForecastLocationRepository")
    fun provideWidgetForecastLocationRepository(
        @Named("WidgetForecastDataStore") widgetForecastLocationDataStore: ForecastLocationDataStore
    ): ForecastLocationRepository {
        return ForecastLocationRepositoryImpl(widgetForecastLocationDataStore)
    }

    @Provides
    @Singleton
    @Named("WidgetForecastLocationUseCase")
    fun provideWidgetForecastLocationUseCase(
        @Named("WidgetForecastLocationRepository") widgetForecastLocationRepository: ForecastLocationRepository
    ): StoredForecastLocationUseCase {
        return StoredForecastLocationUseCase(widgetForecastLocationRepository)
    }
}