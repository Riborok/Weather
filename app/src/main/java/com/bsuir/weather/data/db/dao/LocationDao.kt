package com.bsuir.weather.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsuir.weather.data.db.entity.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations WHERE id = :id AND timestamp > :minTimestamp")
    suspend fun getLocation(id: String, minTimestamp: Long): LocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("DELETE FROM locations WHERE timestamp <= :timestamp")
    suspend fun deleteOldLocations(timestamp: Long)
}
