package com.shriom.bikerental.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ActivityMasterBinding
import com.shriom.bikerental.net.models.User
import com.shriom.bikerental.ui.home.HomeActivity
import com.shriom.bikerental.ui.onboard.OnBoardActivity
import com.shriom.bikerental.utils.BikerViewModel

class MasterActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREF_NAME = "myBikeSharedPref"
    }

    private lateinit var mBikerViewModel: BikerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBikerViewModel = ViewModelProviders.of(this).get(BikerViewModel::class.java)
        Handler().postDelayed({ init() }, 800 )

    }

    private fun init() {
        val sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        if (FirebaseAuth.getInstance().currentUser != null) {
            //for now lets go to user home screen

            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, OnBoardActivity::class.java))
            finish()
        }
    }
}