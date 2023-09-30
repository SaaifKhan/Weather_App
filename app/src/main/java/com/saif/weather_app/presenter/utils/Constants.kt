package com.saif.weather_app.presenter.utils

import com.google.android.gms.maps.model.LatLng

object Constants {
    object Key {
        const val LAT_LNG = "lat_lng"
        const val LAT = "lat"
        const val LNG = "lng"
        const val FROM_ROUTE = "from_route"
        const val ADDRESS_NAME = "address_name"
    }

    object Default {
        val LAT_LNG_DEFAULT = LatLng(0.0, 0.0)
    }
}