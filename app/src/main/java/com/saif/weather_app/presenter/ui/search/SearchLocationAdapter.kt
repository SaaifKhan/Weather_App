package com.saif.weather_app.presenter.ui.search

import com.saif.weather_app.R
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.databinding.ItemSearchedLocationsBinding

class SearchLocationAdapter :com.saif.weather_app.base.BaseAdapter<CurrentWeather,ItemSearchedLocationsBinding>(
    R.layout.item_searched_locations) {
    override fun onItemInflated(
        items: CurrentWeather,
        position: Int,
        binding: ItemSearchedLocationsBinding
    ) {
        binding.model = items
    }

}