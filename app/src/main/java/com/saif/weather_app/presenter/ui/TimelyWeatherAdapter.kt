package com.saif.weather_app.presenter.ui

import com.saif.weather_app.R
import com.saif.weather_app.base.BaseAdapter
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.databinding.ItemTodayWeatherBinding
import com.saif.weather_app.presenter.databinding.loadDrawable
import com.saif.weather_app.presenter.databinding.loadDrawableCoil

class TimelyWeatherAdapter :BaseAdapter<Response.Data,ItemTodayWeatherBinding>(R.layout.item_today_weather) {

    override fun onItemInflated(
        items: Response.Data,
        position: Int,
        binding: ItemTodayWeatherBinding
    ) {
        binding.item2 = items

        val desc = items.weather?.get(0)?.description
        val iconResource = desc?.let {
            setIcon(it) }
        if (iconResource != null) {
            binding.image.loadDrawableCoil(iconResource)
        }





    }


    fun setIcon(description: String): Int {
        return when (description) {
            "clear sky" -> R.drawable.clear_sky
            "few clouds" -> R.drawable.moon_cloud
            "overcast clouds" -> R.drawable.topimage2
            "broken clouds" -> R.drawable.moon_cloud
            else -> {
                return 0
            }
        }
    }

}