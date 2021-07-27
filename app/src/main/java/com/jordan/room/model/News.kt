package com.jordan.room.model

import androidx.room.*
import com.jordan.home.model.NewsData
import com.jordan.room.NewsTypeConverter

/**
 * Created by nandan on 24,February,2020
 */
//@Entity this is our table
@Entity
@TypeConverters(
   NewsTypeConverter::class)
 class News(
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0,

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "newsList")
    var newsList: List<NewsData>,

)