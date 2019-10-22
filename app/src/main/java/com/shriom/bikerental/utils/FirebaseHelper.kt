package com.shriom.bikerental.utils

import android.content.Context
import android.net.Uri
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shriom.bikerental.net.models.User

class FirebaseHelper(context: Context) {

    private var database = FirebaseDatabase.getInstance()

    private var bookingsRef: DatabaseReference = database.getReference("bookings")



}