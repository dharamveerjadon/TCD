package com.jordan.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jordan.R
import com.jordan.home.adapter.ViewPagerAdapter
import com.jordan.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() , View.OnClickListener{

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private val vm: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFindId(view)
        registerListener()
    }


    private fun initFindId(view: View) {
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.vp_homepage)
    }

    private fun registerListener() {

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position

            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Business"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Entertainment"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("General"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("health"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Science"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Sports"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Technology"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_CENTER
        tabLayout!!.tabMode = TabLayout.MODE_SCROLLABLE

        val adapter = activity?.let { ViewPagerAdapter(it, childFragmentManager, tabLayout!!.tabCount) }
        viewPager!!.adapter = adapter
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {

            }
        }
    }

}