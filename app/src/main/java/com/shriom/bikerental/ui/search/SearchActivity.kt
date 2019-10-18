package com.shriom.bikerental.ui.search

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shriom.bikerental.R
import com.shriom.bikerental.adapters.GenericRecyclerViewAdapter
import com.shriom.bikerental.databinding.ActivitySearchBinding
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.utils.RecyclerDiffUtil
import com.shriom.bikerental.utils.getEmptyList
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySearchBinding
    private lateinit var mSearchViewModel: SearchViewModel
    private lateinit var bikesAdapter: GenericRecyclerViewAdapter<Bike>
    private var searched = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        mBinding.lifecycleOwner = this

        mBinding.viewModel = mSearchViewModel
        mBinding.setOnSearchClearClick { resetList() }
        mBinding.setOnBackClick { onBackPressed() }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        bikesAdapter = object : GenericRecyclerViewAdapter<Bike>() {

            override fun setViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : BikeViewHolder(parent) {
                    override fun onBikeClick(bike: Bike) {
                        showToast(bike.bikeName)
                    }
                }
            }

            override fun onBindData(holder: RecyclerView.ViewHolder, `val`: Bike, position: Int) {
                if (holder is BikeViewHolder) {
                    holder.bind(items[position])
                }
            }

            override fun getViewType(position: Int): Int {
                return 1
            }
        }

        bikesRecyclerView.layoutManager = LinearLayoutManager(this)
        bikesRecyclerView.hasFixedSize()

        bikesAdapter.setData(getEmptyList(6))

        bikesRecyclerView.adapter = bikesAdapter

        mSearchViewModel.mBikes.observe(this, Observer { bikes ->

            if (bikes != null && bikes.isNotEmpty()) {

                var newList = bikes.shuffled()

                val diffUtil = object :
                    RecyclerDiffUtil<Bike>(bikesAdapter.items, newList as MutableList<Bike>) {
                    override fun areItemSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        oldItemPosition == newItemPosition

                    override fun areContentsSame(
                        oldItemPosition: Int,
                        newItemPosition: Int
                    ): Boolean {
                        return oldList[oldItemPosition] == newList[newItemPosition]
                    }
                }

                bikesAdapter.updateData(newList, diffUtil)

            }

        })

        mSearchViewModel.mQuery.observe(this, Observer { query ->

            if (query.isNotEmpty()) {
                searched = true
                val querryList = bikesAdapter.items.filter { bike ->
                    bike.bikeName?.contains(query)!! || bike.category?.contains(query)!!
                }

                mBinding.llNoData.visibility = View.GONE

                if(querryList.isNotEmpty()) {

                    val querryDiff = object : RecyclerDiffUtil<Bike>(
                        bikesAdapter.items,
                        querryList as MutableList<Bike>
                    ) {
                        override fun areItemSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean = oldItemPosition == newItemPosition

                        override fun areContentsSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean {
                            return oldList[oldItemPosition] == newList[newItemPosition]
                        }
                    }
                    bikesAdapter.updateData(querryList, querryDiff)

                } else {
                    mBinding.llNoData.visibility = View.VISIBLE
                }


            } else if(searched) {
                searched = false
                mBinding.llNoData.visibility = View.GONE
                mSearchViewModel.mBikes.value = mSearchViewModel.mBikes.value
            }

            mBinding.llNoData.visibility = View.GONE
        })

    }

    private fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun resetList() {
        if(searched) {
            mSearchViewModel.mBikes.value = mSearchViewModel.mBikes.value
            searched = false
            mBinding.llNoData.visibility = View.GONE
            mSearchViewModel.mQuery.value = ""
        }
    }
}