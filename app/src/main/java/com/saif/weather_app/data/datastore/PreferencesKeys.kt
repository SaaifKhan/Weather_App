package com.saif.weather_app.data.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {

    val CURRENT_LAT_LON_RESPONSE = stringPreferencesKey("USER_LAT_LON")
    val SEARCHED_WEATHER_LIST = stringPreferencesKey("SEARCHED_WEATHER_LIST")

}