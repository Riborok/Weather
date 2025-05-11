package com.bsuir.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bsuir.weather.data.db.converter.CoordinatesConverter
import com.bsuir.weather.data.db.converter.ForecastConverter
import com.bsuir.weather.data.db.converter.LocationConverter
import com.bsuir.weather.data.db.dao.CoordinatesDao
import com.bsuir.weather.data.db.dao.ForecastDao
import com.bsuir.weather.data.db.dao.LocationDao
import com.bsuir.weather.data.db.entity.CoordinatesEntity
import com.bsuir.weather.data.db.entity.ForecastEntity
import com.bsuir.weather.data.db.entity.LocationEntity

@Database(
    entities = [
        ForecastEntity::class,
        LocationEntity::class,
        CoordinatesEntity::class
    ],
    version = 1
)
@TypeConverters(ForecastConverter::class, LocationConverter::class, CoordinatesConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
    abstract fun locationDao(): LocationDao
    abstract fun coordinatesDao(): CoordinatesDao
}
