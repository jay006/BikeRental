package com.shriom.bikerental.utils

import android.content.Context
import com.google.gson.Gson
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseUser
import com.shriom.bikerental.net.models.User


class SharedPref(context: Context) {

    private var sharedPreference: SharedPreferences = context.getSharedPreferences("bikerent", Context.MODE_PRIVATE)

    private val USER = "USER"
    private val USER_AUTHENTICATED = "user_authenticated"

    companion object {
        private var sharedPref: SharedPref? = null

        fun inti(context: Context) {
            if (sharedPref == null) {
                sharedPref = SharedPref(context)
            }
        }

        fun getInstance(): SharedPref {
            return sharedPref as SharedPref
        }


    }

    fun saveUser(firebaseUser: FirebaseUser) {

        val editor = sharedPreference.edit()

        val user = User()
        user.userId = firebaseUser.uid
        user.userName = firebaseUser.displayName!!
        user.userEmail = firebaseUser.email!!
        user.userImage = firebaseUser.photoUrl?.toString()!!

        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(USER, json)

        editor.apply()

    }

    fun getUser(): User? {
        val gson = Gson()
        val json = sharedPreference.getString(USER, "")
        return gson.fromJson(json, User::class.java)
    }

}