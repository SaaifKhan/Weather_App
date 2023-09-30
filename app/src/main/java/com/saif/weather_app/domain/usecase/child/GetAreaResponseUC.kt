package com.saif.weather_app.domain.usecase.child

import com.domain.usecase.base.UseCase
import com.saif.weather_app.data.model.SearchAreaResponse
import com.saif.weather_app.domain.repository.InitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAreaResponseUC @Inject constructor(
    private val initRepository: InitRepository
) : UseCase<List<SearchAreaResponse>?, UseCase.None>() {
    override suspend fun run(params: None): Flow<List<SearchAreaResponse>?> =
        initRepository.getUserSearchedLatLon()
}