package com.solodilov.newsapp.data.datasource.database.entity

import androidx.room.Entity
import com.solodilov.newsapp.domain.entity.Article

@Entity(tableName = "article", primaryKeys = ["id", "category"])
data class ArticleDb(
    val id: Int,
    val category: String,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
)

fun ArticleDb.toArticle() = Article(
    id = id,
    category = enumValueOf(category.uppercase()),
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
)