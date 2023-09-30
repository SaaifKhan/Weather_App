package com.saif.weather_app.data.network

import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.data.model.SearchAreaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("data/2.5/weather")
  suspend fun getCurrentWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en"
//        @Query("appid") appId: String,
    ):CurrentWeather


    @GET("/data/2.5/forecast?")
   suspend fun getTimelyWeatherUpdate(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en"
    ): Response

    @GET("geo/1.0/direct")
    suspend fun getGeoCoding(
        @Query("q") areaName: String,
    ): List<SearchAreaResponse>

}
