package com.saif.weather_app.presenter.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.saif.weather_app.data.model.CurrentWeather
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.databinding.ItemSearchedLocationsBinding


class SearchLocationsViewHolder(
    private val binding: ItemSearchedLocationsBinding

): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CurrentWeather?){
        binding.model = item
        binding.executePendingBindings()
    }
}