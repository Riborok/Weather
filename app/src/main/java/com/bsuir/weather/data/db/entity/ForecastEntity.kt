package com.bsuir.weather.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bsuir.weather.data.dto.ForecastDTO

@Entity(
    tableName = "forecasts",
    indices = [Index(value = ["timestamp"]), Index(value = ["id", "timestamp"])]
)
data class ForecastEntity(
    @PrimaryKey
    val id: String,
    val forecast: ForecastDTO,
    val timestamp: Long
)
