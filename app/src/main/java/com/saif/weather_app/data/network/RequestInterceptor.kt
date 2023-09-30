package com.saif.weather_app.data.network

import com.saif.weather_app.data.util.Applog
import com.saif.weather_app.data.util.InternetConnection
import com.saif.weather_app.domain.exception.ConnectivityException
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor(
    private val internetConnection: InternetConnection,
) : Interceptor {

    companion object {
        private const val API_KEY = "318ee6f6d614520685913f8571fb5b46"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!internetConnection.isNetworkConnected()) {
            throw ConnectivityException()
        }

        val originalRequest = chain.request()

        val originalUrl: HttpUrl = originalRequest.url

        val modifiedUrl = originalUrl.newBuilder()
            .addQueryParameter("appid", API_KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder()
            .url(modifiedUrl)
            .addHeader("Accept", "application/json")

        val request = requestBuilder.build()

        Applog.d("endpoint: ${request.url.toUrl()}")
        Applog.d("headerMap: ${request.headers}")
        Applog.d("queryMap: ${request.url.query}")
        Applog.d("bodyMap: ${request.body}")

        return chain.proceed(request)
    }
}