package com.saif.weather_app.domain.usecase.child

import com.domain.usecase.base.UseCase
import com.google.android.gms.maps.model.LatLng
import com.saif.weather_app.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : UseCase<LatLng,UseCase.None>() {

    override suspend fun run(params: None): Flow<LatLng> =
        locationRepository.getCurrentLocation()

}