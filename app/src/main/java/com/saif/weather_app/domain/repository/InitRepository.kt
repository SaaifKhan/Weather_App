package com.saif.weather_app.domain.repository

import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.SearchAreaResponse
import kotlinx.coroutines.flow.Flow

interface InitRepository {
   suspend fun updatedLatLon(list: List<SearchAreaResponse>)

   suspend fun getUserSearchedLatLon(): Flow<List<SearchAreaResponse>?>

   suspend fun saveListForSearchedWeather(currentWeather: List<CurrentWeather?>)

   suspend fun getSaveListForSearchedWeatherUpdate(): Flow<List<CurrentWeather>?>

}