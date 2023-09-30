package com.saif.weather_app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saif.weather_app.data.db.dao.CurrentWeatherDao
import com.saif.weather_app.data.model.CurrentWeather

@TypeConverters(Converters::class)
@Database(
    entities = [CurrentWeather::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDb : RoomDatabase() {
    abstract fun currentWeatherDao() : CurrentWeatherDao
}