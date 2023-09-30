package com.saif.weather_app.data.di

import android.content.Context
import androidx.room.Room
import com.saif.weather_app.Constant
import com.saif.weather_app.data.db.WeatherDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): WeatherDb =
        Room.databaseBuilder(
            context,
            WeatherDb::class.java,
            Constant.DB_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
}
