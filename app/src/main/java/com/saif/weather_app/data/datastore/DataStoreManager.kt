package com.saif.weather_app.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.saif.weather_app.data.fromJson
import com.saif.weather_app.data.fromJsonArray
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.SearchAreaResponse
import com.saif.weather_app.data.toJson
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.json.JSONException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DATASTORE")
const val TAG = "DataStoreManager"


class DataStoreManager @Inject constructor(
    val moshi: Moshi,
    @ApplicationContext val context: Context
){



    suspend fun putLatLonResponse(searchAreaResponse: List<SearchAreaResponse>){
        putObject(PreferencesKeys.CURRENT_LAT_LON_RESPONSE,searchAreaResponse)
    }

//    fun getLatLonResponse() = getProfiles<List<SearchAreaResponse>>(PreferencesKeys.CURRENT_LAT_LON_RESPONSE)




    suspend fun saveLast4WeathersList(currentWeather: List<CurrentWeather?>) {
        if (currentWeather.isNotEmpty()) {
            putObject(PreferencesKeys.SEARCHED_WEATHER_LIST, currentWeather.filterNotNull())
        }
    }




        @Throws(JSONException::class)
        suspend fun putObject(key: Preferences.Key<String>, `object`: Any) {
            val json = moshi.toJson(`object`) ?: ""
            context.dataStore.edit {
                it[stringPreferencesKey("$key")] = json
            }
        }



    @Throws(JSONException::class)
    suspend fun putProfiles(key: Preferences.Key<String>, `object`: Any) {
        var json = moshi.toJson(`object`) ?: ""
        context.dataStore.edit {
            it[stringPreferencesKey("$key")] = json
        }
    }


    private inline fun <reified T> getObject(key: Preferences.Key<String>) : Flow<T?> {
        return context.dataStore.data.map {
            it[stringPreferencesKey("$key")]
        }.map {
            moshi.fromJson<T>(it ?: "")
        }.flowOn(Dispatchers.IO)
    }

    inline fun <reified T> getProfiles(key: Preferences.Key<String>): Flow<List<T>?> =
        context.dataStore.data.map {
            it[stringPreferencesKey("$key")]
        }.map {
            moshi.fromJsonArray<T>(it ?: "")
        }.flowOn(Dispatchers.IO)
}