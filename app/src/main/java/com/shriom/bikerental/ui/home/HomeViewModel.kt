package com.shriom.bikerental.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shriom.bikerental.net.Repository
import com.shriom.bikerental.net.models.Home


class HomeViewModel() : ViewModel() {

    private val mBikeRepository: Repository = Repository()

    var mDiscover = MutableLiveData<List<Home>>()
        get() {
            if( field.value == null ){
                field = mBikeRepository.getDiscoverItems(field)
            }
            return field
        }



}