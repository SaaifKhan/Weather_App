package com.saif.weather_app.presenter.ui.search

import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.SearchAreaResponse

sealed class SearchFragmentEvents{
    class AfterSearch(var response: List<SearchAreaResponse>?) : SearchFragmentEvents()

    class GetSavedWeatherLast(var list : List<CurrentWeather>?) : SearchFragmentEvents()

}
