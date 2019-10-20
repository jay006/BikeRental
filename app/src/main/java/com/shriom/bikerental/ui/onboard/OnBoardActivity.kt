package com.shriom.bikerental.ui.onboard

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.firebase.ui.auth.AuthUI
import com.shriom.bikerental.ui.onboard.fragments.WelcomeFragment
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ActivityOnBoardBinding
import com.shriom.bikerental.utils.BikerActivity
import java.util.Arrays.asList
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.shriom.bikerental.ui.MasterActivity
import android.widget.Toast
import com.firebase.ui.auth.IdpResponse
import android.app.Activity
import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import com.shriom.bikerental.utils.SharedPref


class OnBoardActivity : BikerActivity() {

    private lateinit var mOnBoardViewModel: OnBoardViewModel
    private lateinit var mBinding: ActivityOnBoardBinding


    private val RC_SIGN_IN = 100
    var providers = asList(AuthUI.IdpConfig.GoogleBuilder().build())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_on_board)

        mOnBoardViewModel = ViewModelProviders.of(this).get(OnBoardViewModel::class.java)
        mBinding.lifecycleOwner = this

            supportFragmentManager.beginTransaction()
                .replace(com.shriom.bikerental.R.id.container, WelcomeFragment()).commit()

    }

    fun login() {

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            // Create and launch sign-in intent
            startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN
            )

        } else {
            gotoMasterActivity()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                if(SharedPref.getInstance().saveUser(user!!)) {
                    Toast.makeText(this@OnBoardActivity, "Please wait...", Toast.LENGTH_SHORT).show()
                    mOnBoardViewModel.registerUser(SharedPref.getInstance().getUser()).observe(this, Observer { registeredUser ->
                        if( registeredUser != null && registeredUser.userId.isNotEmpty()) {
                            Toast.makeText(this@OnBoardActivity,  "Login Successful", Toast.LENGTH_SHORT).show()
                            gotoMasterActivity()
                        }
                    })
                }

            } else {
                // Sign in failed. If response is null the user canceled the
                Toast.makeText(this, "Authentication Error", Toast.LENGTH_SHORT)
                    .show()
                Log.d("auth_error", response!!.error!!.toString())

            }
        }

    }

    private fun gotoMasterActivity() {
        startActivity(Intent(this, MasterActivity::class.java))
        finish()
    }

}
