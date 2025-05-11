package com.bsuir.weather.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.bsuir.weather.data.dto.ForecastLocationDTO
import com.bsuir.weather.data.dto.ForecastLocationDTO.Companion.serializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.forecastDataStore by preferencesDataStore("forecast_location_data_store")

class ForecastLocationDataStore(
    private val context: Context,
    private val forecastKey: Preferences.Key<String>
) {

    val forecastLocation: Flow<ForecastLocationDTO?> = context.forecastDataStore.data
        .map { preferences ->
            preferences[forecastKey]?.let { Json.decodeFromString(serializer(), it) }
        }

    suspend fun updateForecastLocation(forecastLocation: ForecastLocationDTO) {
        val json = Json.encodeToString(serializer(), forecastLocation)
        context.forecastDataStore.edit { preferences ->
            preferences[forecastKey] = json
        }
    }
}
