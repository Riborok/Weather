package com.bsuir.weather.data.repository

import com.bsuir.weather.data.source.datastore.LocationListDataStore
import com.bsuir.weather.domain.model.LocationModel
import com.bsuir.weather.domain.repository.LocationListRepository
import com.bsuir.weather.utils.mapper.LocationMapper.toDTO
import com.bsuir.weather.utils.mapper.LocationMapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationListRepositoryImpl @Inject constructor(
    private val locationListDataStore: LocationListDataStore
) : LocationListRepository {
    override fun getSavedLocations(): Flow<List<LocationModel>> {
        return locationListDataStore.savedLocations.map { dtoList ->
            dtoList.map { it.toModel() }
        }
    }

    override suspend fun saveLocation(location: LocationModel) {
        locationListDataStore.addLocation(location.toDTO())
    }

    override suspend fun removeLocation(location: LocationModel) {
        locationListDataStore.removeLocation(location.toDTO())
    }

    override suspend fun updateLocation(oldLocation: LocationModel, newLocation: LocationModel) {
        locationListDataStore.updateLocation(oldLocation.toDTO(), newLocation.toDTO())
    }
}
