package com.saif.weather_app.presenter.ui

import androidx.recyclerview.widget.RecyclerView
import com.saif.weather_app.R
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.databinding.ItemTodayWeatherBinding
import com.saif.weather_app.presenter.databinding.load
import com.saif.weather_app.presenter.databinding.loadDrawable

class TimelyWeatherViewHolder(
    private val binding: ItemTodayWeatherBinding

): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Response.Data?,position:Int){
        binding.item2 = item

        val description = item?.weather?.getOrNull(position)?.description

        val iconResource = when (description) {
            "broken clouds" -> R.drawable.moon_cloud
            "overcast clouds" -> R.drawable.topimage2
            "few clouds" -> R.drawable.topimage4
            "clear sky" -> R.drawable.clear_sky
            else -> R.drawable.clear_sky // Use a default icon for unknown descriptions
        }
            binding.image.loadDrawable(iconResource)

    }
}