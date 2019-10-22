package com.shriom.bikerental.ui.home.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ItemBikeBinding
import com.shriom.bikerental.databinding.ItemBookingBinding
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.net.models.Booking

abstract class BookingViewHolder(
    private val parent: ViewGroup,
    private val binding: ItemBookingBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_booking,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onBookingClick(booking: Booking)

    fun bind(booking: Booking) {

        binding.booking = booking
//        binding.onBikeClick = View.OnClickListener { if(bike.bikeName?.isNotEmpty() == true) onBikeClick(bike) }

    }

}