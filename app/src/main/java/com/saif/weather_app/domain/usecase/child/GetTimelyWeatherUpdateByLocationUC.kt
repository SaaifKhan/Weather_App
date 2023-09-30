package com.saif.weather_app.domain.usecase.child

import com.domain.usecase.base.UseCase
import com.google.android.gms.maps.model.LatLng
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTimelyWeatherUpdateByLocationUC @Inject constructor(
    private val dataRepository: DataRepository
) : UseCase<Response, LatLng>() {

    override suspend fun run(params: LatLng): Flow<Response> {
        return dataRepository.getTimelyWeatherByLocation(params)
    }
}