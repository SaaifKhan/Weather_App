package com.saif.weather_app.data.repository

import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.SearchAreaResponse
import com.saif.weather_app.data.repository.local.InitLocalDataSource
import com.saif.weather_app.domain.repository.InitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InitRepositoryImpl @Inject constructor(
    private val localDataSource: InitLocalDataSource
):InitRepository {
    override suspend fun updatedLatLon(list: List<SearchAreaResponse>) =
        localDataSource.saveLatLonResponse(list)

    override suspend fun getUserSearchedLatLon(): Flow<List<SearchAreaResponse>?> =
        localDataSource.getLatLonResponse()

    override suspend fun saveListForSearchedWeather(currentWeather: List<CurrentWeather?>) =
        localDataSource.listOfSearchedWeather(currentWeather)

    override suspend fun getSaveListForSearchedWeatherUpdate(): Flow<List<CurrentWeather>?> =
        localDataSource.getListOfSavedWeather()



}