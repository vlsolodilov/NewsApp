package com.solodilov.newsapp.data.repository

import com.solodilov.newsapp.data.datasource.database.NewsDao
import com.solodilov.newsapp.data.datasource.database.entity.ArticleDb
import com.solodilov.newsapp.data.datasource.database.entity.toArticle
import com.solodilov.newsapp.data.datasource.network.NewsApi
import com.solodilov.newsapp.data.datasource.network.model.NewsDto
import com.solodilov.newsapp.data.datasource.network.model.toArticle
import com.solodilov.newsapp.data.datasource.network.model.toArticleDb
import com.solodilov.newsapp.domain.entity.Article
import com.solodilov.newsapp.domain.entity.Category
import com.solodilov.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao,
): NewsRepository {

    override suspend fun getArticles(forceRefresh: Boolean): Map<Category, List<Article>> {
        return if (forceRefresh) {
            refreshAndCache()
        } else {
            val articlesDb = dao.getArticles().also { articlesDb ->
                if (articlesDb.isEmpty()) {
                    return refreshAndCache()
                }
            }
            articlesDb.map { it.toArticle() }.groupBy { it.category }
        }
    }

    private suspend fun refreshAndCache(): Map<Category, List<Article>> {
        val allNews = loadNews()
        val articlesDb = mutableListOf<ArticleDb>()
        val articles = mutableListOf<Article>()
        allNews.entries.forEach { news ->
            if (news.value != null) {
                news.value?.articles?.forEachIndexed { index, articleDto ->
                    articlesDb.add(articleDto.toArticleDb(id = index, category = news.key))
                    articles.add(articleDto.toArticle(id = index, category = news.key))
                }

            }
        }
        if (articlesDb.isNotEmpty()) dao.insertArticles(articlesDb)
        return articles.groupBy { it.category }
    }

    private suspend fun loadNews(): Map<Category, NewsDto?> = coroutineScope {
        Category.entries.map { category ->
            async(Dispatchers.IO){
                try {
                    category to api.getNewsByCategory(category = category.query)
                } catch (e: Exception) {
                    category to null
                }
            }
        }.awaitAll().toMap()
    }

    override fun getArticlesByTitleOrDescription(
        searchQuery: String,
        forceRefresh: Boolean,
    ): Map<Category, List<Article>> {
        //TODO("Not yet implemented")
        return emptyMap()
    }

}