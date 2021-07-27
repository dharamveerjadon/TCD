package com.jordan.room.daos

import androidx.room.*
import com.jordan.home.model.NewsData
import com.jordan.room.model.News
import io.reactivex.Observable

//Data access object
//defined all the methods needed for the Create, Read, Update and Delete operation.

@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    fun getAll(): Observable<List<News>>

   /* @Query("SELECT * FROM News WHERE category = category")
    fun getDataByCategory(category: String): List<NewsData>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: News)

    @Delete
    fun delete(task: News)

    @Update
    fun update(task: News)
}