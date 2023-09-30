package com.saif.weather_app.data.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InternetConnection @Inject constructor(var connectivityManager: ConnectivityManager) {
    fun isNetworkConnected(): Boolean {

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT < 23) {
                val ni = connectivityManager.activeNetworkInfo

                if (ni != null) {
                    return ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
                }
            } else {
                val n = connectivityManager.activeNetwork

                if (n != null) {
                    val nc = connectivityManager.getNetworkCapabilities(n)

                    return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            nc!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            nc!!.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                }
            }
        }

        return false
    }
}