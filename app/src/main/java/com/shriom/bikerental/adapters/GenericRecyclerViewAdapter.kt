package com.shriom.bikerental.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shriom.bikerental.utils.RecyclerDiffUtil


abstract class GenericRecyclerViewAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter<List<T>> {

    var items: MutableList<T> = ArrayList()

    abstract fun setViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindData(holder: RecyclerView.ViewHolder, `val`: T, position: Int)

    abstract fun getViewType(position: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return setViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        onBindData(viewHolder, items[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return getViewType(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun setData(data: List<T>) {
        items = data as MutableList<T>
        notifyDataSetChanged()
    }

    fun updateData(newList: MutableList<T>,  diffUtilCallBack: RecyclerDiffUtil<T>) {

        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)

        items.clear()
        items.addAll(newList)

        diffResult.dispatchUpdatesTo(this)

    }

}
