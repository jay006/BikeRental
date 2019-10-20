package com.shriom.bikerental.net

import com.google.gson.JsonObject
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.net.models.Booking
import com.shriom.bikerental.net.models.Home
import com.shriom.bikerental.net.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BikeService {

    @GET("home/platform.json")
    fun getDiscoverItems() : Call<List<Home>>

    @GET("bikes.json")
    fun getAllBikes() : Call<List<Bike>>

    @PUT("users/{userpath}")
    fun registerUser(@Body body: JsonObject, @Path("userpath") userPath: String) : Call<User>

    @PUT("bookings/{bookingpath}")
    fun createBooking(@Body body: JsonObject, @Path("bookingpath") bookingPath: String) : Call<Booking>

}