package com.bsuir.weather.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bsuir.weather.data.dto.CoordinatesDTO

@Entity(
    tableName = "coordinates",
    indices = [Index(value = ["timestamp"]), Index(value = ["id", "timestamp"])]
)
data class CoordinatesEntity(
    @PrimaryKey
    val id: String,
    val coordinates: CoordinatesDTO,
    val timestamp: Long
)
