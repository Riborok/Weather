package com.bsuir.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bsuir.weather.data.db.converter.ForecastConverter
import com.bsuir.weather.data.db.dao.ForecastDao
import com.bsuir.weather.data.entity.ForecastEntity

@Database(entities = [ForecastEntity::class], version = 1)
@TypeConverters(ForecastConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
}