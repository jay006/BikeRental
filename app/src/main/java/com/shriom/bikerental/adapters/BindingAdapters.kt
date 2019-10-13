package com.shriom.bikerental.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shriom.bikerental.R


@BindingAdapter("data")
fun <T> setRecyclerData(recyclerView: RecyclerView, data: T?) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        if (data != null) (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}


@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    if (visible) view.visibility = View.VISIBLE else view.visibility = View.GONE
}


@BindingAdapter("image")
fun loadImage(imageView: ImageView, url: String?) {
    if (url != null) {
        val requestOptions = RequestOptions().placeholder(R.drawable.bike_rent)
        Glide.with(imageView.context).setDefaultRequestOptions(requestOptions).load(url)
            .into(imageView)
    }
}

interface BindableAdapter<T> {
    fun setData(data: T)
}
