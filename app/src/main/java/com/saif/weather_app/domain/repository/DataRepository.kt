package com.saif.weather_app.domain.repository

import com.google.android.gms.maps.model.LatLng
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.data.model.SearchAreaResponse
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    suspend fun getCurrentWeatherByLocation(latLng: LatLng): Flow<CurrentWeather>

    suspend fun getTimelyWeatherByLocation(latLng: LatLng): Flow<Response>

    suspend fun getAreaName(areaName:String): Flow<List<SearchAreaResponse>?>

    suspend fun insertCurrentWeatherList(weatherList: List<CurrentWeather>)

    suspend fun getWeatherList(): Flow<List<CurrentWeather>>
}