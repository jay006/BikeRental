package com.shriom.bikerental.net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Booking: Serializable {

    @SerializedName("bookingId")
    @Expose
    var bookingId: String = ""

    @SerializedName("rentDate")
    @Expose
    var rentDate: String = ""

    @SerializedName("rentTime")
    @Expose
    var rentTime: String = ""

    @SerializedName("bikeId")
    @Expose
    var bikeId: String = ""

    @SerializedName("bikeName")
    @Expose
    var bikeName: String = ""

    @SerializedName("bikeCategory")
    @Expose
    var bikeCategory: String = ""


}