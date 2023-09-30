package com.saif.weather_app.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.saif.weather_app.data.network.NetworkService
import com.saif.weather_app.data.network.RequestInterceptor
import com.saif.weather_app.data.remote.HeaderInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext context: Context,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(QueryParameterAddInterceptor())
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(
                    ChuckerCollector(
                        context, showNotification = true,
                        RetentionManager.Period.ONE_HOUR
                    )
                )
                .maxContentLength(250000L)
                .alwaysReadResponseBody(true)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(true)
                .build()
        )
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().also {
            it.level = level
        }
    }


    @Provides
    @Singleton
    fun provideNetworkServices(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    fun moshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)






}