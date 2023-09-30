package com.saif.weather_app.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saif.weather_app.data.model.CurrentWeather

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromMain(main: CurrentWeather.Main?): String? {
        return Gson().toJson(main)
    }

    @TypeConverter
    @JvmStatic
    fun toMain(json: String?): CurrentWeather.Main? {
        return Gson().fromJson(json, CurrentWeather.Main::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun fromSys(sys: CurrentWeather.Sys?): String? {
        return Gson().toJson(sys)
    }

    @TypeConverter
    @JvmStatic
    fun toSys(json: String?): CurrentWeather.Sys? {
        return Gson().fromJson(json, CurrentWeather.Sys::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun fromWeatherList(weatherList: List<CurrentWeather.Weather?>?): String? {
        return Gson().toJson(weatherList)
    }

    @TypeConverter
    @JvmStatic
    fun toWeatherList(json: String?): List<CurrentWeather.Weather?>? {
        return Gson().fromJson(json, object : TypeToken<List<CurrentWeather.Weather?>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun fromWind(wind: CurrentWeather.Wind?): String? {
        return Gson().toJson(wind)
    }

    @TypeConverter
    @JvmStatic
    fun toWind(json: String?): CurrentWeather.Wind? {
        return Gson().fromJson(json, CurrentWeather.Wind::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun fromCloud(clouds: CurrentWeather.Clouds?): String? {
        return Gson().toJson(clouds)
    }

    @TypeConverter
    @JvmStatic
    fun toClouds(json: String?): CurrentWeather.Clouds? {
        return Gson().fromJson(json, CurrentWeather.Clouds::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun fromCoord(coords: CurrentWeather.Coord?): String? {
        return Gson().toJson(coords)
    }

    @TypeConverter
    @JvmStatic
    fun toCoords(json: String?): CurrentWeather.Coord? {
        return Gson().fromJson(json, CurrentWeather.Coord::class.java)
    }
}