package com.solodilov.newsapp.domain.entity

data class Article(
    val id: Int,
    val category: Category,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
)
