package com.bsuir.weather.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.bsuir.weather.data.dto.LocationDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.locationDataStore by preferencesDataStore("location_data_store")

class LocationDataStore(private val context: Context) {

    companion object {
        private val SAVED_LOCATIONS_KEY = stringSetPreferencesKey("saved_locations")
    }

    val savedLocations: Flow<List<LocationDTO>> = context.locationDataStore.data
        .map { preferences ->
            preferences[SAVED_LOCATIONS_KEY]?.map { LocationDTO.fromJson(it) } ?: emptyList()
        }

    suspend fun addLocation(location: LocationDTO) {
        val json = location.toJson()
        context.locationDataStore.edit { preferences ->
            val currentSet = preferences[SAVED_LOCATIONS_KEY] ?: emptySet()
            preferences[SAVED_LOCATIONS_KEY] = currentSet + json
        }
    }

    suspend fun removeLocation(location: LocationDTO) {
        val json = location.toJson()
        context.locationDataStore.edit { preferences ->
            val currentSet = preferences[SAVED_LOCATIONS_KEY] ?: emptySet()
            preferences[SAVED_LOCATIONS_KEY] = currentSet - json
        }
    }

    suspend fun updateLocation(oldLocation: LocationDTO, newLocation: LocationDTO) {
        val oldJson = oldLocation.toJson()
        val newJson = newLocation.toJson()
        context.locationDataStore.edit { preferences ->
            val currentSet = preferences[SAVED_LOCATIONS_KEY] ?: emptySet()
            if (currentSet.contains(oldJson)) {
                preferences[SAVED_LOCATIONS_KEY] = currentSet - oldJson + newJson
            }
        }
    }
}
