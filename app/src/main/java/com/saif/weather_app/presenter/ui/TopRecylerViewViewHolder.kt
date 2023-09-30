package com.saif.weather_app.presenter.ui

import androidx.recyclerview.widget.RecyclerView
import com.saif.weather_app.data.model.DefaultModel
import com.saif.weather_app.databinding.ItemRecyclerviewOneBinding

class TopRecylerViewViewHolder(
    private val binding: ItemRecyclerviewOneBinding

): RecyclerView.ViewHolder(binding.root) {

    fun bind(item:DefaultModel){
        binding.model = item
        binding.executePendingBindings()
    }
}