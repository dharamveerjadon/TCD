package com.jordan.utils

import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.jordan.R
import java.lang.Exception

class ManageFragment {

    companion object {

        /**
         * Replace the fragment using fragmentManager
         *
         * @param frag           : fl_fragment_container will be replaced by frag
         * @param addToBackStack : if empty no back stack else addToBackStack
         */
        fun replaceFrag(activity: FragmentActivity, frag: Fragment, addToBackStack: String) {
            try {
                if (TextUtils.isEmpty(addToBackStack)) {
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, frag)
                        .commit()
                } else {
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, frag)
                        .addToBackStack(addToBackStack)
                        .commit()
                }
            } catch (e: Exception) {
                Log.e("$activity >> ", "Replace Fragment")
            }

        }

        fun addFrag(activity: FragmentActivity, frag: Fragment, addToBackStack: String) {
            try {
                if (TextUtils.isEmpty(addToBackStack)) {
                    activity.supportFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, frag)
                        .commit()
                } else {
                    activity.supportFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, frag)
                        .addToBackStack(addToBackStack)
                        .commit()
                }
            } catch (e: Exception) {
                Log.e("$activity >> ", "Add Fragment")
            }

        }

        fun replaceFragWithAnim(
            activity: FragmentActivity,
            frag: Fragment,
            addToBackStack: String
        ) {
            try {
                if (TextUtils.isEmpty(addToBackStack)) {
                    activity.supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            android.R.animator.fade_in,
                            android.R.animator.fade_out
                        )
                        .replace(R.id.fragment_container, frag)
                        .commit()
                } else {
                    activity.supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            android.R.animator.fade_in,
                            android.R.animator.fade_out
                        )
                        .replace(R.id.fragment_container, frag)
                        .addToBackStack(addToBackStack)
                        .commit()
                }
            } catch (e: Exception) {
                Log.e("$activity >> ", "Replace Fragment")
            }

        }

        fun addFragWithAnim(activity: FragmentActivity, frag: Fragment, addToBackStack: String) {
            try {
                if (TextUtils.isEmpty(addToBackStack)) {
                    activity.supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            android.R.animator.fade_in,
                            android.R.animator.fade_out
                        )
                        .add(R.id.fragment_container, frag)
                        .commit()
                } else {
                    activity.supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            android.R.animator.fade_in,
                            android.R.animator.fade_out
                        )
                        .add(R.id.fragment_container, frag)
                        .addToBackStack(addToBackStack)
                        .commit()
                }
            } catch (e: Exception) {
                Log.e("$activity >> ", "Add Fragment")
            }

        }

        /**
         * To remove all fragments from stack
         */
        fun emptyTheStack(activity: FragmentActivity) {
            try {
                val count = activity.supportFragmentManager.backStackEntryCount
                for (i in 0 until count) {
                    activity.supportFragmentManager.popBackStack()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun clearAndRemoveAllFragments(activity: FragmentActivity) {
            //Here we are clearing back stack fragment entries
            val backStackEntry: Int = activity.supportFragmentManager.backStackEntryCount
            if (backStackEntry > 0) {
                for (i in 0 until backStackEntry) {
                    activity.supportFragmentManager.popBackStackImmediate()
                }
            }

            //Here we are removing all the fragment that are shown here
            if (activity.supportFragmentManager.fragments.size > 0) {
                for (i in 0 until activity.supportFragmentManager.fragments.size) {
                    val mFragment: Fragment = activity.supportFragmentManager.fragments[i]
                    mFragment.onDetach()
                    activity.supportFragmentManager.beginTransaction().remove(mFragment).commit()
                }
            }
        }


        /**
         * To remove a fragments from stack
         */
        fun removeFragment(activity: FragmentActivity) {
            activity.supportFragmentManager.popBackStack()
        }

        fun replaceIfNotInBackStack(
            activity: FragmentActivity,
            frag: Fragment,
            addToBackStack: String
        ) {
            try {
                val backStackName: String = frag.javaClass.name
                val fragmentPopped =
                    activity.supportFragmentManager.popBackStackImmediate(backStackName, 0)
                if (!fragmentPopped) {
                    if (TextUtils.isEmpty(addToBackStack)) {
                        activity.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                android.R.animator.fade_in,
                                android.R.animator.fade_out
                            )
                            .replace(R.id.fragment_container, frag)
                            .commit()
                    } else {
                        activity.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                android.R.animator.fade_in,
                                android.R.animator.fade_out
                            )
                            .replace(R.id.fragment_container, frag)
                            .addToBackStack(addToBackStack)
                            .commit()
                    }
                }

            } catch (e: Exception) {
                Log.e("$activity >> ", "Replace Fragment")
            }

        }

    }
}