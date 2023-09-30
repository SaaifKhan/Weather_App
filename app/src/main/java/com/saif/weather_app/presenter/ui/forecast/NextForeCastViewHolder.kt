package com.saif.weather_app.presenter.ui.forecast

import androidx.recyclerview.widget.RecyclerView
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.databinding.ItemReyclerviewNextForecastBinding

class NextForeCastViewHolder(
    private val binding: ItemReyclerviewNextForecastBinding

): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Response.Data?){
        binding.model = item
        binding.executePendingBindings()
    }
}