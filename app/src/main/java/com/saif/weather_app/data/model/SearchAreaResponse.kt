package com.saif.weather_app.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchAreaResponse(
    val name: String? = "",
    val local_names: Map<String, String>? = null,
    val lat: Double? = 1.0,
    val lon: Double? = 1.0,
    val country: String? = "",
    val state: String? = "",
    var isHandled:Boolean? = false
)





