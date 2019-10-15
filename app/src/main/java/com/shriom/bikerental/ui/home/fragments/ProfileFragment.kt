package com.shriom.bikerental.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.shriom.bikerental.adapters.GenericRecyclerViewAdapter
import com.shriom.bikerental.databinding.FragmentProfileBinding
import com.shriom.bikerental.net.models.Home
import com.shriom.bikerental.ui.home.HomeActivity
import com.shriom.bikerental.ui.home.HomeViewModel
import com.shriom.bikerental.utils.SharedPref


class ProfileFragment : Fragment() {


    private lateinit var mBinding: FragmentProfileBinding
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mDiscoverAdapter: GenericRecyclerViewAdapter<Home>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        mBinding = DataBindingUtil.inflate(inflater, com.shriom.bikerental.R.layout.fragment_profile, container, false )
        mBinding.lifecycleOwner = this

        activity?.let {
            mHomeViewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
        }

        mBinding.viewModel = mHomeViewModel
        mBinding.setOnLogoutClick { (activity as HomeActivity).logOut() }

        return mBinding.root

    }

}