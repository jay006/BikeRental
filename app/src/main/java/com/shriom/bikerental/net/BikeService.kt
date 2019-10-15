package com.shriom.bikerental.net

import com.shriom.bikerental.net.models.Home
import retrofit2.Call
import retrofit2.http.GET

interface BikeService {

    @GET("home/platform.json")
    fun getDiscoverItems() : Call<List<Home>>

}