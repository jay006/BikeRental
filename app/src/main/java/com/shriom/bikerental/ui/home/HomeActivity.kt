package com.shriom.bikerental.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.shriom.bikerental.R

class HomeActivity : AppCompatActivity() {

    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_home)

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }
}
