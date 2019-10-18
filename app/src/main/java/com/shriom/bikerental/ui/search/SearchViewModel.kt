package com.shriom.bikerental.ui.search

import android.content.Context
import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shriom.bikerental.net.Repository
import com.shriom.bikerental.net.models.Bike

class SearchViewModel : ViewModel() {

    private val mBikeRepository: Repository = Repository()

    val mQuery: MutableLiveData<String> = MutableLiveData("")

    var mBikes: MutableLiveData<List<Bike>> = MutableLiveData()
        get() {
            if (field.value == null) {
                mBikeRepository.getAllBikes(field)
            }
            return field
        }

    var mQuerryList = emptyList<Bike>()

}