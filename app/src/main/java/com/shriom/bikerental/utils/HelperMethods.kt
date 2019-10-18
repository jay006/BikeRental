package com.shriom.bikerental.utils

inline fun <reified T> getEmptyList(size: Int = 5) : MutableList<T> {

    if( size == 0) { return mutableListOf() }

    val list = mutableListOf<T>()
    for( i in 0 until size ) {
        list.add(T::class.java.newInstance())
    }
    return list
}