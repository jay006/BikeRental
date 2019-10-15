package com.shriom.bikerental.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.shriom.bikerental.net.models.User
import com.shriom.bikerental.ui.home.HomeActivity
import com.shriom.bikerental.ui.onboard.OnBoardActivity
import com.shriom.bikerental.utils.BikerViewModel

class MasterActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREF_NAME = "myBikeSharedPref"
        const val SHARED_PREF_IS_AUTHENTICATED = "isUserAuthenticated"
        const val SHARED_PREF_USER = "user"
    }

    private lateinit var mBikerViewModel: BikerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBikerViewModel = ViewModelProviders.of(this).get(BikerViewModel::class.java)
        init()

    }

    private fun init() {
        val sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        if (FirebaseAuth.getInstance().currentUser != null) {
            //TODO go to home activity if user object details are filed else go to user profile edit screen
            //for now lets go to user home screen
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        } else {
            startActivity(Intent(this, OnBoardActivity::class.java))
            finish()
        }
    }
}