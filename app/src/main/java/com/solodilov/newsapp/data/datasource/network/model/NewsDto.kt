package com.solodilov.newsapp.data.datasource.network.model


import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<ArticleDto>
)