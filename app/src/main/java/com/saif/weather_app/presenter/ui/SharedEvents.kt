package com.saif.weather_app.presenter.ui

import com.saif.weather_app.data.model.SearchAreaResponse

sealed class SharedEvents{

    class onPopBackStackAfterLocationUpdate(var response: SearchAreaResponse?) : SharedEvents()


}
