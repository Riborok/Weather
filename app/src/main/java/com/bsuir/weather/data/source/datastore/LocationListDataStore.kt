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

private val Context.locationListDataStore by preferencesDataStore("location_list_data_store")

class LocationListDataStore(
    private val context: Context,
    private val locationListKey: Preferences.Key<Set<String>>
) {

    val locations: Flow<List<LocationDTO>> = context.locationListDataStore.data
        .map { preferences ->
            preferences[locationListKey]?.map {
                Json.decodeFromString(serializer(), it)
            } ?: emptyList()
        }

    suspend fun addLocation(location: LocationDTO) {
        val json = Json.encodeToString(serializer(), location)
        context.locationListDataStore.edit { preferences ->
            val currentSet = preferences[locationListKey] ?: emptySet()
            preferences[locationListKey] = currentSet + json
        }
    }

    suspend fun removeLocation(location: LocationDTO) {
        val json = Json.encodeToString(serializer(), location)
        context.locationListDataStore.edit { preferences ->
            val currentSet = preferences[locationListKey] ?: emptySet()
            preferences[locationListKey] = currentSet - json
        }
    }

    suspend fun updateLocation(oldLocation: LocationDTO, newLocation: LocationDTO) {
        val oldJson = Json.encodeToString(serializer(), oldLocation)
        val newJson = Json.encodeToString(serializer(), newLocation)
        context.locationListDataStore.edit { preferences ->
            val currentSet = preferences[locationListKey] ?: emptySet()
            if (currentSet.contains(oldJson)) {
                preferences[locationListKey] = currentSet - oldJson + newJson
            }
        }
    }
}
