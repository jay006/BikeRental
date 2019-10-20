package com.shriom.bikerental.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shriom.bikerental.net.Repository
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.net.models.Booking
import com.shriom.bikerental.net.models.User
import org.joda.time.Days
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

class CheckOutViewModel(var mUser: User, var mBike: Bike) : ViewModel() {

    private val mBikeRepository: Repository = Repository()

    val DATE_FORMAT = DateTimeFormat.forPattern("dd MMM, yyyy")

    val mCheckInDate: MutableLiveData<LocalDate> =
        MutableLiveData(LocalDate.now().plusDays(2))

    val mCheckOutDate: MutableLiveData<LocalDate> =
        MutableLiveData(LocalDate.now().plusDays(3))

    val mTotal = MutableLiveData(0)

    internal fun calculate() {
        mTotal.value = Days.daysBetween(mCheckInDate.value!!, mCheckOutDate.value!!).days * mBike.price
    }

    fun createBooking(): LiveData<Booking> {
        return mBikeRepository.createBooking(mUser, mBike, DATE_FORMAT.parseMillis(DATE_FORMAT.print(mCheckInDate.value)), DATE_FORMAT.parseMillis(DATE_FORMAT.print(mCheckOutDate.value)))
    }

    class Factory(var mUser: User,var mBike: Bike) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CheckOutViewModel(mUser, mBike) as T
        }
    }

}