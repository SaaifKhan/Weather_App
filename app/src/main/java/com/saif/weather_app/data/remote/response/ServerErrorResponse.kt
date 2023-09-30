package com.saif.weather_app.data.remote.response

data class ServerErrorResponse(
    val code: Int? = 0,
    val message: String? = "",
)