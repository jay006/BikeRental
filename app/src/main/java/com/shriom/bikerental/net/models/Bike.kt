package com.shriom.bikerental.net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Bike : Serializable {

    @SerializedName("bikeId")
    @Expose
    var bikeId: String? = ""

    @SerializedName("bikeName")
    @Expose
    var bikeName: String? = ""

    @SerializedName("category")
    @Expose
    var category: String? = ""

    @SerializedName("bikeCC")
    @Expose
    var bikeCC: Int = 0

    @SerializedName("bikeKmpl")
    @Expose
    var bikeKmpl: Int = 0

    @SerializedName("bikeWeight")
    @Expose
    var bikeWeight: Int = 0

    @SerializedName("bikeRating")
    @Expose
    var bikeRating: Float = 0f

    @SerializedName("price")
    @Expose
    var price: Int = 0

    @SerializedName("image")
    @Expose
    var image: String? = ""

}