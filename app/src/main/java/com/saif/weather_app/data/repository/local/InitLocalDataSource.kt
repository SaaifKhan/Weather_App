package com.saif.weather_app.data.repository.local

import com.saif.weather_app.data.datastore.DataStoreManager
import com.saif.weather_app.data.datastore.PreferencesKeys
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.SearchAreaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InitLocalDataSource @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    suspend fun saveLatLonResponse(searchAreaResponse: List<SearchAreaResponse>){
        dataStoreManager.putLatLonResponse(searchAreaResponse)
    }

    fun getLatLonResponse() : Flow<List<SearchAreaResponse>?> {
        return dataStoreManager.getProfiles(PreferencesKeys.CURRENT_LAT_LON_RESPONSE)

    }

    suspend fun listOfSearchedWeather(currentWeather: List<CurrentWeather?>){
        dataStoreManager.saveLast4WeathersList(currentWeather)
    }

    fun getListOfSavedWeather() : Flow<List<CurrentWeather>?> {
         return  dataStoreManager.getProfiles(PreferencesKeys.SEARCHED_WEATHER_LIST)
    }
}