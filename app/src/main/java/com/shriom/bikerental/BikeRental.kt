package com.shriom.bikerental

import android.app.Application
import com.google.firebase.FirebaseApp
import com.shriom.bikerental.utils.SharedPref

class BikeRental : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPref.inti(applicationContext)
        FirebaseApp.initializeApp(this)
    }

}