package com.bsuir.weather.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsuir.weather.data.db.entity.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getLocation(id: String): LocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("DELETE FROM locations WHERE id NOT IN (SELECT id FROM locations ORDER BY timestamp DESC LIMIT :limit)")
    suspend fun deleteExcessLocations(limit: Int)
}
