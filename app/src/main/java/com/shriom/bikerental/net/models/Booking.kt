package com.shriom.bikerental.net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Booking: Serializable {

    @SerializedName("bookingId")
    @Expose
    var bookingId: Int = 0

    @SerializedName("pickUpDate")
    @Expose
    var pickUpDate: String = ""

    @SerializedName("dropDate")
    @Expose
    var dropDate: String = ""

    @SerializedName("bikeId")
    @Expose
    var bikeId: String = ""

    @SerializedName("bikeName")
    @Expose
    var bikeName: String = ""

    @SerializedName("bikeImage")
    @Expose
    var bikeImage: String = ""

    @SerializedName("userId")
    @Expose
    var userId: String = ""

    @SerializedName("userName")
    @Expose
    var userName: String = ""

    @SerializedName("userEmail")
    @Expose
    var userEmail: String = ""

    @SerializedName("total")
    @Expose
    var total: Int = 0

}