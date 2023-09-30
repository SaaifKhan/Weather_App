package com.saif.weather_app.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saif.weather_app.data.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM CurrentWeather")
    fun getWeatherList(): Flow<List<CurrentWeather>> // Change the return type to Flow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherList: CurrentWeather)

    @Query("delete from currentweather where id=:rowId")
    fun deleteFromList(rowId:Int)

    @Query("delete from currentweather")
    fun clearList()



}