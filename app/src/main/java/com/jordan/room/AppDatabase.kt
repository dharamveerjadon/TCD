package com.jordan.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jordan.room.daos.NewsDao
import com.jordan.room.model.News

//In the above class we define all the entities and the database version.
@Database(
    entities = [News::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun NewsDao(): NewsDao
}