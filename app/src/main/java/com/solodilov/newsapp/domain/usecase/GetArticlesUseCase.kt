package com.solodilov.newsapp.domain.usecase

import com.solodilov.newsapp.domain.entity.Article
import com.solodilov.newsapp.domain.entity.Category
import com.solodilov.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(forceRefresh: Boolean): Map<Category, List<Article>> =
        newsRepository.getArticles(forceRefresh)

}