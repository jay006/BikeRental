package com.shriom.bikerental.ui.onboard

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.shriom.bikerental.ui.onboard.fragments.WelcomeFragment
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ActivityOnBoardBinding
import com.shriom.bikerental.utils.BikerActivity


class OnBoardActivity : BikerActivity() {

    private lateinit var mOnBoardViewModel: OnBoardViewModel
    private lateinit var mBinding: ActivityOnBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_on_board)

        mOnBoardViewModel = ViewModelProviders.of(this).get(OnBoardViewModel::class.java)
        mBinding.lifecycleOwner = this

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeFragment()).commit()

    }

    fun login() {
        //start firebase user loggin here for gmail


    }

}
