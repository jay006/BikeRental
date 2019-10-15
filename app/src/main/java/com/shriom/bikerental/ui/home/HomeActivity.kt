package com.shriom.bikerental.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ActivityHomeBinding
import com.shriom.bikerental.ui.home.fragments.BookingsFragment
import com.shriom.bikerental.ui.home.fragments.DiscoverFragment
import com.shriom.bikerental.ui.home.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import android.content.Intent
import android.widget.ShareActionProvider
import com.shriom.bikerental.ui.MasterActivity
import com.shriom.bikerental.utils.SharedPref


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        navigation.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DiscoverFragment()).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_discover -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DiscoverFragment())
                    .commit()
                return true
            }


            R.id.navigation_bookings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BookingsFragment())
                    .commit()
                return true

            }

            R.id.navigation_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .commit()
                return true
            }
        }
        return false
    }

    fun logOut() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener {
            Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MasterActivity::class.java))
            finish()
        }
    }

}
