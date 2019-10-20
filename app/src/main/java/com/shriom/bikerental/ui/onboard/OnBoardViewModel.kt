package com.shriom.bikerental.ui.onboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shriom.bikerental.net.Repository
import com.shriom.bikerental.net.models.Home
import com.shriom.bikerental.net.models.User


class OnBoardViewModel() : ViewModel() {

    private val mBikeRepository: Repository = Repository()

    private val mUser = MutableLiveData<User>()

    fun registerUser(user: User?): LiveData<User> {
        return mBikeRepository.registerUser(user, mUser)
    }

    var mDiscover = MutableLiveData<List<Home>>()
        get() {
            if( field.value == null ){
                field = mBikeRepository.getDiscoverItems(field)
            }
            return field
        }


}