package com.shriom.bikerental.utils

import androidx.recyclerview.widget.DiffUtil

abstract class RecyclerDiffUtil<T>(internal var oldList: MutableList<T>, internal var newList: MutableList<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int  = oldList.size

    override fun getNewListSize(): Int  = newList.size

    abstract fun areItemSame(oldItemPosition: Int, newItemPosition: Int) : Boolean

    abstract fun areContentsSame(oldItemPosition: Int, newItemPosition: Int) : Boolean

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemSame(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsSame(oldItemPosition, newItemPosition)
    }
}