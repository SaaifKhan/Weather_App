package com.saif.weather_app.presenter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.saif.weather_app.R
import com.saif.weather_app.base.BaseAdapter
import com.saif.weather_app.data.model.DefaultModel
import com.saif.weather_app.databinding.ItemRecyclerviewOneBinding

class TopRecyclerView():BaseAdapter<DefaultModel,ItemRecyclerviewOneBinding>(R.layout.item_recyclerview_one) {
    private val staticModels = mutableListOf<DefaultModel>()

    init {
        staticModels.add(DefaultModel(R.drawable.big_rain,"Rain"))
        staticModels.add(DefaultModel(R.drawable.topimage2,"Drizz"))
        staticModels.add(DefaultModel(R.drawable.topimage3,"Thunder"))
        staticModels.add(DefaultModel(R.drawable.topimage4,"Light Rain"))
        staticModels.add(DefaultModel(R.drawable.topimage5,"Moon Cloud"))
        staticModels.add(DefaultModel(R.drawable.topimage6,"fast wind"))
        addItems(staticModels)
    }


    override fun onItemInflated(
        items: DefaultModel,
        position: Int,
        binding: ItemRecyclerviewOneBinding
    ) {
        binding.model = items

    }

}