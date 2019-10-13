package com.shriom.bikerental.ui.onboard.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.FragmentWelcomeBinding
import com.shriom.bikerental.ui.onboard.OnBoardActivity
import com.shriom.bikerental.ui.onboard.OnBoardViewModel
import kotlinx.android.synthetic.main.fragment_welcome.*


class WelcomeFragment : Fragment() {

    companion object {
        const val SCREEN_NAME = "Welcome"

        val SET_CURRENT_PAGE = "current_page"
        val PREPARE_PAGE = 0
        val SHARE_PAGE = 1
        val TRACK_PAGE = 2
    }

    private lateinit var mBinding: FragmentWelcomeBinding
    private lateinit var mOnBoardViewModel: OnBoardViewModel
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        mBinding.lifecycleOwner = this

        mBinding.onPrivacyPolicyClick = onPrivacyPolicyClick
        mBinding.onTermsAndConditionsClick = onTermsAndConditionsClick

        activity?.let {
            mOnBoardViewModel = ViewModelProviders.of(it).get(OnBoardViewModel::class.java)
        }

        mBinding.viewModel = mOnBoardViewModel
        mBinding.onLetsGetStartedClick = View.OnClickListener { (activity as OnBoardActivity).login() }

        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)

        viewPager.adapter = mSectionsPagerAdapter

        indicators.setupWithViewPager(viewPager)

        if (arguments != null && arguments!!.containsKey(SET_CURRENT_PAGE)) {
            viewPager!!.currentItem = arguments!!.getInt(SET_CURRENT_PAGE, PREPARE_PAGE)
        }

    }

    private val onPrivacyPolicyClick: View.OnClickListener = View.OnClickListener {

        //TODO remove privacy policy of the app
        val uri = Uri.parse("http://mounty.co/privacy-policy")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private val onTermsAndConditionsClick: View.OnClickListener = View.OnClickListener {

        //TODO remove the terms and condititon of the app
        val uri = Uri.parse("http://mounty.co/terms-and-conditions")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var rootView: View? = null

            when (arguments!!.getInt(ARG_SECTION_NUMBER, 1)) {

                PREPARE_PAGE -> rootView =
                    inflater.inflate(R.layout.onboarding_welcome_one, container, false)

                SHARE_PAGE -> rootView =
                    inflater.inflate(R.layout.onboarding_welcome_two, container, false)

                TRACK_PAGE -> rootView =
                    inflater.inflate(R.layout.onboarding_welcome_three, container, false)

            }


            return rootView
        }

        companion object {

            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): Fragment {

                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment

            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position)
        }

    }

}