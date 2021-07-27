package com.jordan.home.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jordan.home.ui.CommonFragment

class ViewPagerAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                return CommonFragment.newInstance("business")
            }
            1 -> {
                return CommonFragment.newInstance("entertainment")
            }
            2 -> {
                return CommonFragment.newInstance("general")
            }
            3 -> {
                return CommonFragment.newInstance("health")
            }
            4 -> {
                return CommonFragment.newInstance("science")
            }
            5 -> {
                return CommonFragment.newInstance("sports")
            }
            6 -> {
                return CommonFragment.newInstance("technology")
            }
            else -> return CommonFragment.newInstance("business")

        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}