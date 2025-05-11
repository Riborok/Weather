package com.bsuir.weather.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bsuir.weather.data.dto.LocationDTO

@Entity(
    tableName = "locations",
    indices = [Index(value = ["timestamp"])]
)
data class LocationEntity(
    @PrimaryKey
    val id: String,
    val location: LocationDTO,
    val timestamp: Long
)
