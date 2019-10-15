package com.shriom.bikerental.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shriom.bikerental.R
import com.shriom.bikerental.adapters.GenericRecyclerViewAdapter
import com.shriom.bikerental.databinding.FragmentDiscoverBinding
import com.shriom.bikerental.net.models.Home
import com.shriom.bikerental.ui.home.HomeViewModel
import com.shriom.bikerental.ui.home.viewholders.DiscoverItemViewHolder

class DiscoverFragment : Fragment() {


    private lateinit var mBinding: FragmentDiscoverBinding
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mDiscoverAdapter: GenericRecyclerViewAdapter<Home>

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false )
        mBinding.lifecycleOwner = this


        activity?.let {
            mHomeViewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
        }

        mBinding.viewModel = mHomeViewModel
        mBinding.onSearchClick = onSearchClick

        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAndSubscribe()

    }

    private fun setupAndSubscribe() {

        mDiscoverAdapter = object : GenericRecyclerViewAdapter<Home>() {

            override fun setViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

                return object : DiscoverItemViewHolder(parent) {
                    override fun onBannerItemClick(id: String?, category: String) {
                        showToast(id!!)
                    }
                }
            }

            override fun onBindData(holder: RecyclerView.ViewHolder, `val`: Home, position: Int) {
                if(holder is DiscoverItemViewHolder){
                    holder.bind(`val`)
                }
            }

            override fun getViewType(position: Int): Int {
                return 1
            }
        }


        mBinding.discoverRecyclerView.layoutManager = LinearLayoutManager(activity)
        mBinding.discoverRecyclerView.hasFixedSize()
        mBinding.discoverRecyclerView.adapter = mDiscoverAdapter

        mHomeViewModel.mDiscover.observe(this, Observer { homes ->
            mDiscoverAdapter.setData(homes)
        })

    }

    private val onSearchClick = View.OnClickListener {
//        TODO create search activity
//        startActivity(Intent(activity, SearchActivity::class.java))
    }

    private fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}