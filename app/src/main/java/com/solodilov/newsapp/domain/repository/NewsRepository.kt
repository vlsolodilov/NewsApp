package com.solodilov.newsapp.domain.repository

import com.solodilov.newsapp.domain.entity.Article
import com.solodilov.newsapp.domain.entity.Category

interface NewsRepository {
    suspend fun getArticles(forceRefresh: Boolean): Map<Category, List<Article>>
    fun getArticlesByTitleOrDescription(searchQuery: String, forceRefresh: Boolean): Map<Category, List<Article>>
}