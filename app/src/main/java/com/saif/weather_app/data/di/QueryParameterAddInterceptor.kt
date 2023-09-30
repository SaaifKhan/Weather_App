package com.saif.weather_app.data.di

import okhttp3.Interceptor
import okhttp3.Response

class QueryParameterAddInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url.newBuilder()
                .addQueryParameter("appid", "318ee6f6d614520685913f8571fb5b46")
                .build()
        val request = chain.request().newBuilder()
                // .addHeader("Authorization", "Bearer token")
                .url(url)
                .build()

        return chain.proceed(request)
    }
}