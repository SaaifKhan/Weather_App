package com.saif.weather_app.data.util

import android.util.Log

object Applog {
    private var isDialogShowing = false
    //log TAG
    private val TAG = "APP:"

    //logs
    fun d(msg: String) = Log.d(TAG, msg)

    fun d(tag: String, msg: String) = Log.d(TAG + tag, msg)
}