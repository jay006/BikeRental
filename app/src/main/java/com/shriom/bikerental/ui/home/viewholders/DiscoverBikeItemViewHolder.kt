package com.shriom.bikerental.ui.home.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ItemDiscoverBikeBinding
import com.shriom.bikerental.net.models.Bike

abstract class DiscoverBikeItemViewHolder(
    private val parent: ViewGroup,
    private val binding: ItemDiscoverBikeBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_discover_bike,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onImageClick(bike: Bike)

    fun bind(bike: Bike) {
        binding.bike = bike
        binding.setOnImageClick { onImageClick(bike) }
    }

}