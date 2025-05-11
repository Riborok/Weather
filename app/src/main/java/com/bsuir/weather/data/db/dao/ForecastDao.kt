package com.bsuir.weather.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsuir.weather.data.entity.ForecastEntity

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecasts WHERE id = :id AND timestamp > :minTimestamp")
    suspend fun getForecast(id: String, minTimestamp: Long): ForecastEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecast: ForecastEntity)

    @Query("DELETE FROM forecasts WHERE timestamp <= :timestamp")
    suspend fun deleteOldForecasts(timestamp: Long)
}