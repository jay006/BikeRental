package com.shriom.bikerental.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shriom.bikerental.R
import com.shriom.bikerental.adapters.GenericRecyclerViewAdapter
import com.shriom.bikerental.databinding.FragmentBookingsBinding
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.net.models.Booking
import com.shriom.bikerental.net.models.Home
import com.shriom.bikerental.ui.home.HomeViewModel
import com.shriom.bikerental.ui.home.viewholders.BookingViewHolder
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_bookings.*

class BookingsFragment : Fragment() {


    private lateinit var mBinding: FragmentBookingsBinding
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var database: FirebaseDatabase
    private lateinit var bookingsRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookings, container, false)
        mBinding.lifecycleOwner = this


        activity?.let {
            mHomeViewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
        }

        database = FirebaseDatabase.getInstance()
        bookingsRef = database.getReference("bookings").child(mHomeViewModel.mUser.value?.userId!!)

        mBinding.viewModel = mHomeViewModel

        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = FirebaseRecyclerOptions.Builder<Booking>().setQuery(
            bookingsRef,
            Booking::class.java
        ).setLifecycleOwner(this).build()

        val adapter = object : FirebaseRecyclerAdapter<Booking, BookingViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
                return object : BookingViewHolder(parent) {
                    override fun onBookingClick(booking: Booking) {
                        //TODO handle booking click
                    }
                }
            }

            override fun onBindViewHolder(holder: BookingViewHolder, position: Int, booking: Booking) {
                holder.bind(booking)
            }

            override fun onDataChanged() {
                mBinding.llNoData.visibility = View.GONE
            }
        }

        bookingsRecycler.layoutManager = LinearLayoutManager(activity)
        bookingsRecycler.adapter = adapter

    }

}