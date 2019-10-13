package com.shriom.bikerental.net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable {

    @SerializedName("userId")
    @Expose
    var userId: String = ""

    @SerializedName("userName")
    @Expose
    var userName: String = ""

    @SerializedName("userEmail")
    @Expose
    var userEmail: String = ""

    @SerializedName("userImage")
    @Expose
    var userImage: String = ""

    @SerializedName("bookings")
    @Expose
    var bookings: MutableList<Booking> = mutableListOf()
}