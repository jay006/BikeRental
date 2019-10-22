package com.shriom.bikerental.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shriom.bikerental.net.Repository
import com.shriom.bikerental.net.models.Booking
import com.shriom.bikerental.net.models.Home
import com.shriom.bikerental.net.models.User
import com.shriom.bikerental.utils.SharedPref


class HomeViewModel() : ViewModel() {

    private val mBikeRepository: Repository = Repository()

    var mDiscover = MutableLiveData<List<Home>>()
        get() {
            if( field.value == null ){
                field = mBikeRepository.getDiscoverItems(field)
            }
            return field
        }

    var mUser = MutableLiveData<User>(SharedPref.getInstance().getUser())

    var bookings: MutableLiveData<List<Booking>> = MutableLiveData()

    fun refreshBooking() {
        mBikeRepository.getBookings(bookings)
    }


}