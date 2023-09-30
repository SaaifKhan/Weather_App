package com.saif.weather_app.presenter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saif.weather_app.base.Event
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.data.model.SearchAreaResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() :ViewModel() {


    private val _pastWeather = MutableStateFlow<List<Response.Data>>(emptyList())
    val pastWeather: StateFlow<List<Response.Data>> get() = _pastWeather

    private val _searchResponse = MutableStateFlow<SearchAreaResponse>(SearchAreaResponse())
    val searchResponse : StateFlow<SearchAreaResponse> get() = _searchResponse


    private val _events = MutableLiveData<Event<SharedEvents>>()
    val event : LiveData<Event<SharedEvents>> = _events


    private val _shouldFetchCurrentLocationWeather = MutableLiveData(true)
    val shouldFetchCurrentLocationWeather = _shouldFetchCurrentLocationWeather


    fun setPastWeatherData(data: List<Response.Data>) {
        _pastWeather.value = data
    }

    fun setSearchResponse (data: SearchAreaResponse){
        _searchResponse.value = data
    }

    fun onPopBackLocationUpdate(response: SearchAreaResponse?){
        _events.value = Event(SharedEvents.onPopBackStackAfterLocationUpdate(response))
    }


    fun updateShouldFetchCurrentLocationWeather(value: Boolean) {
        _shouldFetchCurrentLocationWeather.value = value
    }

}