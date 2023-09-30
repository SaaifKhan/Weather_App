package com.saif.weather_app.data

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


inline fun <reified T> Moshi.toJson(obj: T): String? {
    val jsonAdapter: JsonAdapter<T> = adapter(T::class.java)
    return jsonAdapter.toJson(obj)
}

inline fun <reified T> Moshi.fromJson(json: String): T? {
    val jsonAdapter: JsonAdapter<T> = adapter(T::class.java)
    return jsonAdapter.fromJson(json)
}

inline fun <reified T> Moshi.fromJsonArray(json: String): List<T>? {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    val jsonAdapter : JsonAdapter<List<T>> = adapter(type)
    return jsonAdapter.fromJson(json)
}