package com.shriom.bikerental.net

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.net.models.Home
import com.shriom.bikerental.net.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Repository {

    fun getDiscoverItems(mData: MutableLiveData<List<Home>>): MutableLiveData<List<Home>> {

        RetrofitClient.mBikeService.getDiscoverItems().enqueue(object : Callback<List<Home>> {

            override fun onFailure(call: Call<List<Home>>, t: Throwable) {
                Log.d("error", t.message)
            }

            override fun onResponse(call: Call<List<Home>>, response: Response<List<Home>>) {
                if( response.isSuccessful && response.body() != null ) {
                    mData.value = response.body()
                }
            }

        })

        return mData
        
    }

    fun getAllBikes(mData: MutableLiveData<List<Bike>>): MutableLiveData<List<Bike>> {

        RetrofitClient.mBikeService.getAllBikes().enqueue(object : Callback<List<Bike>> {

            override fun onFailure(call: Call<List<Bike>>, t: Throwable) {
                Log.d("error", t.message)
            }

            override fun onResponse(call: Call<List<Bike>>, response: Response<List<Bike>>) {
                if( response.isSuccessful && response.body() != null ) {
                    mData.value = response.body()
                }
            }

        })

        return mData

    }

    fun registerUser(user: User?, mData: MutableLiveData<User>): LiveData<User> {

        val userJson = JsonObject()
        userJson.addProperty("userId", user?.userId)
        userJson.addProperty("userName", user?.userName)
        userJson.addProperty("userEmail", user?.userEmail)
        userJson.addProperty("userImage", user?.userImage)

        val userPath = """${user?.userName?.replace(" ", "")?.toLowerCase(Locale.getDefault())}.json"""

        RetrofitClient.mBikeService.registerUser(userJson, userPath).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("error", t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if( response.isSuccessful && response.body() != null) {
                    mData.value = response.body()
                }
            }
        })

        return mData
    }

}