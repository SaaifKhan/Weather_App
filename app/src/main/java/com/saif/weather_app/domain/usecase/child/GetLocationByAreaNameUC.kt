package com.saif.weather_app.domain.usecase.child

import android.util.Log
import com.domain.usecase.base.UseCase
import com.saif.weather_app.data.model.SearchAreaResponse
import com.saif.weather_app.domain.repository.DataRepository
import com.saif.weather_app.domain.repository.InitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetLocationByAreaNameUC @Inject constructor(
    private val dataRepository: DataRepository,
    private val initRepository: InitRepository,
) : UseCase<List<SearchAreaResponse>?, String>() {
    override suspend fun run(params: String): Flow<List<SearchAreaResponse>?> {
        return dataRepository.getAreaName(params)
            .map { list ->
                list?.let {
                    list.forEach { item ->
                        item.isHandled = true
                    }

                    initRepository.updatedLatLon(list) // Save to datastore
                }
                Log.d("useCase","$list")
                list // Return the original list after mapping
            }
    }
}