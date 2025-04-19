package com.bsuir.weather.data.repository

import com.bsuir.weather.data.source.datastore.LocationDataStore
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationRepository
import com.bsuir.weather.utils.mapper.LocationMapper.toDTO
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDataStore: LocationDataStore
) : LocationRepository {
    override fun getSavedLocations(): Flow<List<LocationModel>> {
        return locationDataStore.savedLocations.map { dtoList ->
            dtoList.map { it.toModel() }
        }
    }

    override suspend fun saveLocation(location: LocationModel) {
        locationDataStore.addLocation(location.toDTO())
    }
}
