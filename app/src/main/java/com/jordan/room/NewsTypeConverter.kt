package com.jordan.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jordan.home.model.NewsData
import java.lang.reflect.Type


class NewsTypeConverter {
    @TypeConverter
    fun stringToMeasurements(json: String?): List<NewsData>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<NewsData?>?>() {}.type
        return gson.fromJson<List<NewsData>>(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<NewsData?>?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<NewsData?>?>() {}.type
        return gson.toJson(list, type)
    }
}