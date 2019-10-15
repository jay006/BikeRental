package com.shriom.bikerental.ui.home.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shriom.bikerental.R
import com.shriom.bikerental.adapters.GenericRecyclerViewAdapter
import com.shriom.bikerental.databinding.ItemHomeBannerBinding
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.net.models.Home

abstract class DiscoverItemViewHolder (
    private val parent: ViewGroup,
    private val binding: ItemHomeBannerBinding = DataBindingUtil.inflate(
    LayoutInflater.from(parent.context),
    R.layout.item_home_banner,
    parent,
    false
)
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onBannerItemClick(id: String?, category: String )

    fun bind(mHome: Home) {

        binding.home = mHome

        if(mHome.subtitle?.isEmpty()!!) {
            binding.bannerDescription.visibility = View.GONE
        }

        val bannerAdapter = object : GenericRecyclerViewAdapter<Bike>() {

            override fun setViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : DiscoverBikeItemViewHolder(parent){
                    override fun onImageClick(id: String, code: String) {
                        onBannerItemClick(id, code)
                    }

                }
            }

            override fun onBindData(holder: RecyclerView.ViewHolder, `val`: Bike, position: Int) {
                if( holder is DiscoverBikeItemViewHolder) {
                    holder.bind(`val`)
                }
            }

            override fun getViewType(position: Int): Int {
                return 1
            }
        }

        binding.bannerRecyclerView.layoutManager = LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
        binding.bannerRecyclerView.hasFixedSize()
        binding.bannerRecyclerView.adapter = bannerAdapter

    }

}