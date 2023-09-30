package com.saif.weather_app.data.repository

import com.google.android.gms.maps.model.LatLng
import com.saif.weather_app.data.db.WeatherDb
import com.saif.weather_app.data.db.dao.CurrentWeatherDao
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.data.model.SearchAreaResponse
import com.saif.weather_app.data.network.NetworkService
import com.saif.weather_app.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    val weatherDb: WeatherDb
) : DataRepository {
    override suspend fun getCurrentWeatherByLocation(latLng: LatLng): Flow<CurrentWeather> =
        flow {
            val currentWeather = networkService.getCurrentWeatherByLocation(
                latitude = latLng.latitude,
                longitude = latLng.longitude
            )
            emit(currentWeather)
            weatherDb.currentWeatherDao().insertWeather(currentWeather)




        }

    override suspend fun getTimelyWeatherByLocation(latLng: LatLng): Flow<Response> =
        flow {
            val timelyWeather = networkService.getTimelyWeatherUpdate(
                latitude = latLng.latitude,
                longitude = latLng.longitude
            )
            emit(timelyWeather)
        }

    override suspend fun getAreaName(areaName: String): Flow<List<SearchAreaResponse>?> =
        flow {
            val searchResponseList = networkService.getGeoCoding(areaName)
            emit(searchResponseList)
        }

    override suspend fun insertCurrentWeatherList(weatherList: List<CurrentWeather>) {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherList(): Flow<List<CurrentWeather>> {
        return weatherDb.currentWeatherDao().getWeatherList()
    }


}