package com.solodilov.newsapp.presentation.news.delegateAdapter.model

import com.solodilov.newsapp.domain.entity.Article
import com.solodilov.newsapp.domain.entity.Category
import com.solodilov.newsapp.presentation.common.DisplayableItem

data class ArticleUi(
    val id: Int,
    val category: Category,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
) : DisplayableItem {
    override val itemId: Int = id
}

fun Article.toArticleUi() = ArticleUi(
    id = id,
    category = category,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
)
