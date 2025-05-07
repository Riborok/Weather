package com.bsuir.weather.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.bsuir.weather.data.dto.LocationDTO
import com.bsuir.weather.data.dto.LocationDTO.Companion.serializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.locationDataStore by preferencesDataStore("location_data_store")

class LocationDataStore(private val context: Context) {

    companion object {
        private val SAVED_LOCATIONS_KEY = stringSetPreferencesKey("saved_locations")
    }

    val savedLocations: Flow<List<LocationDTO>> = context.locationDataStore.data
        .map { preferences ->
            preferences[SAVED_LOCATIONS_KEY]?.map {
                Json.decodeFromString(serializer(), it)
            } ?: emptyList()
        }

    suspend fun addLocation(location: LocationDTO) {
        val json = Json.encodeToString(serializer(), location)
        context.locationDataStore.edit { preferences ->
            val currentSet = preferences[SAVED_LOCATIONS_KEY] ?: emptySet()
            preferences[SAVED_LOCATIONS_KEY] = currentSet + json
        }
    }

    suspend fun removeLocation(location: LocationDTO) {
        val json = Json.encodeToString(serializer(), location)
        context.locationDataStore.edit { preferences ->
            val currentSet = preferences[SAVED_LOCATIONS_KEY] ?: emptySet()
            preferences[SAVED_LOCATIONS_KEY] = currentSet - json
        }
    }

    suspend fun updateLocation(oldLocation: LocationDTO, newLocation: LocationDTO) {
        val oldJson = Json.encodeToString(serializer(), oldLocation)
        val newJson = Json.encodeToString(serializer(), newLocation)
        context.locationDataStore.edit { preferences ->
            val currentSet = preferences[SAVED_LOCATIONS_KEY] ?: emptySet()
            if (currentSet.contains(oldJson)) {
                preferences[SAVED_LOCATIONS_KEY] = currentSet - oldJson + newJson
            }
        }
    }
}
