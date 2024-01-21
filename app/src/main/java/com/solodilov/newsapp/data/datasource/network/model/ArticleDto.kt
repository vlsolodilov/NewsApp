package com.solodilov.newsapp.data.datasource.network.model


import com.google.gson.annotations.SerializedName
import com.solodilov.newsapp.data.datasource.database.entity.ArticleDb
import com.solodilov.newsapp.domain.entity.Article
import com.solodilov.newsapp.domain.entity.Category

data class ArticleDto(
    @SerializedName("source")
    val sourceDto: SourceDto?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
)

fun ArticleDto.toArticle(id: Int, category: Category) = Article(
    id = id,
    category = category,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
)

fun ArticleDto.toArticleDb(id: Int, category: Category) = ArticleDb(
    id = id,
    category = category.name,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
)