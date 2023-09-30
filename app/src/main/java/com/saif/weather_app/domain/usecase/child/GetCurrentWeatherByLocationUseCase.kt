package com.saif.weather_app.domain.usecase.child

import com.domain.usecase.base.UseCase
import com.google.android.gms.maps.model.LatLng
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherByLocationUseCase @Inject constructor(
    private val dataRepository: DataRepository,
) : UseCase<CurrentWeather, LatLng>() {


    override suspend fun run(params: LatLng): Flow<CurrentWeather> {
        return dataRepository.getCurrentWeatherByLocation(params)

    }
}





