package com.saif.weather_app.presenter.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.usecase.base.UseCase
import com.google.android.gms.maps.model.LatLng
import com.saif.weather_app.base.Event
import com.saif.weather_app.data.datastore.TAG
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.InitDataRequest
import com.saif.weather_app.domain.usecase.child.GetAreaResponseUC
import com.saif.weather_app.domain.usecase.child.GetCurrentLocationUseCase
import com.saif.weather_app.domain.usecase.child.GetCurrentWeatherByLocationUseCase
import com.saif.weather_app.domain.usecase.child.GetTimelyWeatherUpdateByLocationUC
import com.saif.weather_app.presenter.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLocationViewModel @Inject constructor(
    val getCurrentWeatherByLocationUseCase: GetCurrentWeatherByLocationUseCase,
    val getTimelyWeatherUpdateByLocationUC: GetTimelyWeatherUpdateByLocationUC,
    val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    val getAreaResponseUC: GetAreaResponseUC
) : ViewModel() {

    private var currentLocation = Constants.Default.LAT_LNG_DEFAULT

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> get() = _currentWeather


    private val _events = MutableLiveData<Event<LocationEvents>>()
    val event : LiveData<Event<LocationEvents>> = _events


    fun getCurrentWeather(latitude: Double?, longitude: Double?) {
        val latLng = LatLng(latitude!!, longitude!!)

        viewModelScope.launch {
            if (currentLocation != latLng) {
                currentLocation = latLng
            }
            getCurrentWeatherByLocationUseCase(latLng)
                .onStart { }
                .catch {
                    Log.e("MyLocation","${it.message}")
                }
                .collectLatest { weather ->
                    _events.value = Event(LocationEvents.OnWeatherDataLoader(weather))
                    _currentWeather.value = weather

                    getTimelyWeatherUpdateByLocation(latitude = latitude, longitude = longitude)

                }
        }
    }


    fun getTimelyWeatherUpdateByLocation(latitude: Double, longitude: Double) {
        val latLng = LatLng(latitude, longitude)

        viewModelScope.launch {
            if (currentLocation != latLng) {
                currentLocation = latLng
            }
            getTimelyWeatherUpdateByLocationUC(latLng)
                .onStart {

                }
                .catch {
                    Log.e("MyLocation","${it.message}")
                }
                .collect {
                    Log.d("OtherApi","$it")
                   _events.value = Event(LocationEvents.GetTimelyWeatherUpdate(it.list_data))
                }
        }
    }


    fun getCurrentLocation() {
        viewModelScope.launch {
            getCurrentLocationUseCase(UseCase.None()).collect {
                getCurrentWeather(it.latitude,it.longitude)
            }
        }
    }

    fun getInitData() {
        viewModelScope.launch {
            getAreaResponseUC(UseCase.None()).catch {
                Log.d(TAG,"${it.localizedMessage}")
                _events.value = Event(LocationEvents.CurrentLatLonOnException)

            }.collectLatest {
                Log.d(TAG,"$it")
                _events.value = Event(LocationEvents.GetSavedLatLon(it))

            }
        }
    }


}