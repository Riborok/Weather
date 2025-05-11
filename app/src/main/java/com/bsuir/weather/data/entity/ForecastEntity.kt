package com.bsuir.weather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsuir.weather.data.dto.ForecastDTO

@Entity(tableName = "forecasts")
data class ForecastEntity(
    @PrimaryKey
    val id: String,
    val forecast: ForecastDTO,
    val timestamp: Long
)
