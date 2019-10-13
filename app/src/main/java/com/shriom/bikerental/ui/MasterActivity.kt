package com.shriom.bikerental.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.shriom.bikerental.utils.BikerViewModel

class MasterActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREF_NAME = "myBikeSharedPref"
        const val SHARED_PREF_IS_AUTHENTICATED = "isUserAuthenticated"
    }

    private lateinit var mBikerViewModel: BikerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBikerViewModel = ViewModelProviders.of(this).get(BikerViewModel::class.java)
        init()

    }

    private fun init() {
        val sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        if (sharedPref.getBoolean(SHARED_PREF_IS_AUTHENTICATED, false)) {
            //TODO fetch user details

        } else {
            //TODO start onboarding activity

        }
    }
}