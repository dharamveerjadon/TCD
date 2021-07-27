package com.jordan.utils

import android.app.Application

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private val TAG = ApplicationClass::class.java.simpleName
    }

}