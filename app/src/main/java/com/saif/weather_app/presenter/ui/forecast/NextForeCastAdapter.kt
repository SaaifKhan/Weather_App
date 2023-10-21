package com.saif.weather_app.presenter.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import com.saif.weather_app.R
import com.saif.weather_app.base.BaseAdapter
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.databinding.ItemReyclerviewNextForecastBinding
import com.saif.weather_app.presenter.databinding.loadDrawable

class NextForeCastAdapter : BaseAdapter<Response.Data?, ItemReyclerviewNextForecastBinding>(R.layout.item_reyclerview_next_forecast) {

    init {
        setHasStableIds(true)
    }
    override fun onItemInflated(
        items: Response.Data?,
        position: Int,
        binding: ItemReyclerviewNextForecastBinding
    ) {
        binding.model = items

        val desc = items?.weather?.get(0)?.description
        val iconResource = desc?.let {
            setIcon(it) }
        if (iconResource != null) {
            binding.image.loadDrawable(iconResource)
        }
    }

    fun setIcon(description: String): Int {
        return when (description) {
            "clear sky" -> R.drawable.clear_sky
            "few clouds" -> R.drawable.moon_cloud
            "overcast clouds" -> R.drawable.topimage2
            "broken clouds" -> R.drawable.topimage6
            else -> {
                return 0
            }
        }
    }

}