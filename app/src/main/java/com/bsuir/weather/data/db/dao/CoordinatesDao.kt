package com.bsuir.weather.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsuir.weather.data.db.entity.CoordinatesEntity

@Dao
interface CoordinatesDao {
    @Query("SELECT * FROM coordinates WHERE id = :id AND timestamp > :minTimestamp")
    suspend fun getCoordinates(id: String, minTimestamp: Long): CoordinatesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinates(coordinates: CoordinatesEntity)

    @Query("DELETE FROM coordinates WHERE timestamp <= :timestamp")
    suspend fun deleteOldCoordinates(timestamp: Long)
}
