package com.saif.weather_app.domain.usecase.child

import com.google.android.gms.maps.model.LatLng
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class GetWeatherListUCLocal @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(latLng: LatLng): Flow<List<CurrentWeather>> {
        val cachedWeatherList = dataRepository.getWeatherList()

        return if (cachedWeatherList.toList().isNotEmpty()) {
            callbackFlow {

            }
        } else {
            flowOf(dataRepository.getCurrentWeatherByLocation(latLng).toList())
        }
    }
}