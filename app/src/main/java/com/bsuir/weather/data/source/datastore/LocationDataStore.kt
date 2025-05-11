package com.bsuir.weather.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.data.dto.LocationDTO.Companion.serializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.locationDataStore by preferencesDataStore("location_data_store")

class LocationDataStore(
    private val context: Context,
    private val locationKey: Preferences.Key<String>
) {

    val location: Flow<LocationDTO?> = context.locationDataStore.data
        .map { preferences ->
            preferences[locationKey]?.let { Json.decodeFromString(serializer(), it) }
        }

    suspend fun updateLocation(location: LocationDTO) {
        val json = Json.encodeToString(serializer(), location)
        context.locationDataStore.edit { preferences ->
            preferences[locationKey] = json
        }
    }
}