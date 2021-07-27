package com.carry.mobile.retrofit

import com.jordan.home.model.NewsDataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun fetchRows(
        @Query("country") country: String, @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): Observable<NewsDataResponse>

}