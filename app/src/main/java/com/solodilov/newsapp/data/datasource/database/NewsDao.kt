package com.solodilov.newsapp.data.datasource.database

import androidx.room.*
import com.solodilov.newsapp.data.datasource.database.entity.ArticleDb

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleDb>)

    @Query("SELECT * FROM article")
    fun getArticles(): List<ArticleDb>

    @Query("SELECT * FROM article WHERE title LIKE '%' || :searchQuery || '%'" +
            " OR description LIKE '%' || :searchQuery || '%'")
    fun getArticlesByTitleOrDescription(searchQuery: String): List<ArticleDb>

}
