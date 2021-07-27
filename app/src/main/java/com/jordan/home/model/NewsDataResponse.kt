package com.jordan.home.model

import com.google.gson.annotations.SerializedName

data class NewsDataResponse(
    @SerializedName("status")
    val status: String, @SerializedName("totalResults")
    val totalResults: Int, @SerializedName("articles")
    val articles: List<NewsData>
)
