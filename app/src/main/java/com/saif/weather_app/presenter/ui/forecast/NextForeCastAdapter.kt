package com.saif.weather_app.presenter.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import com.saif.weather_app.R
import com.saif.weather_app.base.BaseAdapter
import com.saif.weather_app.data.model.Response
import com.saif.weather_app.databinding.ItemReyclerviewNextForecastBinding

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
    }

}