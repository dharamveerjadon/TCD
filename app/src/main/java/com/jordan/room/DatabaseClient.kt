package com.healthybrains.android.room

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.jordan.room.AppDatabase


class DatabaseClient(mCtx: Context) {
    val appDatabase: AppDatabase =
        Room.databaseBuilder(mCtx, AppDatabase::class.java, "TubbrCoding").build()

    /*init {
        //creating the app database with Room database builder
        //HealthBrain is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase::class.java, "HealthBrain").build()
    }
*/
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance!!
        }
    }
}



