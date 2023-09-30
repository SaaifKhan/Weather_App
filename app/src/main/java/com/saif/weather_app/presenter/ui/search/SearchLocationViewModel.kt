package com.saif.weather_app.presenter.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.usecase.base.UseCase
import com.saif.weather_app.base.Event
import com.saif.weather_app.base.default
import com.saif.weather_app.data.db.WeatherDb
import com.saif.weather_app.data.model.SearchAreaResponse
import com.saif.weather_app.data.util.Applog
import com.saif.weather_app.domain.usecase.child.GetLocationByAreaNameUC
import com.saif.weather_app.domain.usecase.child.GetSavedWeatherUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor(
    private val getLocationByAreaNameUC: GetLocationByAreaNameUC,
    private val getSavedWeatherUC: GetSavedWeatherUC,
    val weatherDb: WeatherDb
):ViewModel() {

    // Define a StateFlow to hold the area name
    private val _areaName = MutableStateFlow("")
    val areaName: StateFlow<String> = _areaName


    private val _events = MutableLiveData<Event<SearchFragmentEvents>>()
    val event : LiveData<Event<SearchFragmentEvents>> = _events

    private var shouldFetchCurrentLocationWeather = MutableLiveData<Boolean>().default(true)


    // Call this function when text changes in the EditText
    fun onAreaNameTextChanged(text: String) {
        _areaName.value = text
    }

    fun getSearchLatLong() {

        viewModelScope.launch {
            getLocationByAreaNameUC(areaName.value)
                .onStart {

                }
                .catch {
                    Applog.d("${it.message}")
                }
                .collect { location ->
                    Applog.d("${location?.get(0)}")

                    afterSearch(location)

                }
        }
    }




    private fun afterSearch(response: List<SearchAreaResponse>?){
        _events.value = Event(SearchFragmentEvents.AfterSearch(response = response))
    }

    fun getSavedLastWeather(){
        viewModelScope.launch {
            weatherDb.currentWeatherDao().getWeatherList()
                .onStart {

                }
                .catch {

                }
                .collectLatest {
                    _events.value = Event(SearchFragmentEvents.GetSavedWeatherLast(it))
                }
        }
    }


}