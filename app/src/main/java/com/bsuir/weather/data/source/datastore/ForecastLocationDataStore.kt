package com.bsuir.weather.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.bsuir.weather.data.dto.ForecastLocationDTO
import com.bsuir.weather.data.dto.ForecastLocationDTO.Companion.serializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.forecastLocationDataStore by preferencesDataStore("forecast_location_data_store")

class ForecastLocationDataStore(
    private val context: Context,
    private val forecastLocationKey: Preferences.Key<String>
) {

    val forecastLocation: Flow<ForecastLocationDTO?> = context.forecastLocationDataStore.data
        .map { preferences ->
            preferences[forecastLocationKey]?.let { Json.decodeFromString(serializer(), it) }
        }

    suspend fun updateForecastLocation(forecastLocation: ForecastLocationDTO) {
        val json = Json.encodeToString(serializer(), forecastLocation)
        context.forecastLocationDataStore.edit { preferences ->
            preferences[forecastLocationKey] = json
        }
    }
}
