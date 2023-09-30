package com.saif.weather_app.domain

import com.saif.weather_app.data.repository.DataRepositoryImpl
import com.saif.weather_app.data.repository.InitRepositoryImpl
import com.saif.weather_app.data.repository.LocationRepositoryImpl
import com.saif.weather_app.domain.repository.DataRepository
import com.saif.weather_app.domain.repository.InitRepository
import com.saif.weather_app.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HomeModule {

    @Binds
    fun bindsDataRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository

    @Binds
    fun bindsLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository

    @Binds
    fun bindsLocalRepository(initRepositoryImpl: InitRepositoryImpl): InitRepository


}