package com.saif.weather_app.presenter.ui

import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.data.model.SearchAreaResponse

sealed class LocationEvents {
    class OnWeatherDataLoader(var response: CurrentWeather?) : LocationEvents()
    class GetTimelyWeatherUpdate(var list: List<Response.Data>?) : LocationEvents()
    class GetSavedLatLon(var areaResponse: List<SearchAreaResponse>?) : LocationEvents()
    object CurrentLatLonOnException : LocationEvents()
}
