package com.solodilov.newsapp.data.datasource.network

import com.solodilov.newsapp.BuildConfig
import com.solodilov.newsapp.data.datasource.network.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("category") category: String,
    ): NewsDto
}