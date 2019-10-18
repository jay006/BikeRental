package com.shriom.bikerental.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ItemBikeBinding
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.net.models.Booking

abstract class BikeViewHolder(
    private val parent: ViewGroup,
    private val binding: ItemBikeBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_bike,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onBikeClick(bike: Bike)

    fun bind(bike: Bike) {


        if (bike.bikeName?.isNotEmpty() == true) {
            binding.shimmerContainer.setShimmer(
                Shimmer.AlphaHighlightBuilder()
                    .setBaseAlpha(1f)
                    .setIntensity(0f)
                    .build()
            )
            binding.shimmerContainer.stopShimmer()
        } else {
            binding.shimmerContainer.startShimmer()
        }

        binding.bike = bike
        binding.onBikeClick = View.OnClickListener { if(bike.bikeName?.isNotEmpty() == true) onBikeClick(bike) }
    }

}