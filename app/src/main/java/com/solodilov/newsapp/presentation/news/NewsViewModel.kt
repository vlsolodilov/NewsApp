package com.solodilov.newsapp.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.newsapp.domain.entity.Category
import com.solodilov.newsapp.domain.usecase.GetArticlesUseCase
import com.solodilov.newsapp.presentation.common.DisplayableItem
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.CategoryArticlesUi
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.NewsScreenErrorItem
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.ProgressArticleItem
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.toArticleUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
) : ViewModel() {

    private val _data = MutableStateFlow<List<DisplayableItem>>(emptyList())
    val data: StateFlow<List<DisplayableItem>> = _data

    init {
        getData()
    }

    fun getData(forceRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.value = getLoaders()
            //delay(2000)
            try {
                val articles = getArticlesUseCase(forceRefresh)
                _data.value = articles.entries.map { entry ->
                    CategoryArticlesUi(
                        category = entry.key,
                        articleList = entry.value.map { it.toArticleUi() },
                    )
                }
            } catch (e: Exception) {
                Log.d("TAG", "handleError: ${e.message}")
                _data.value = listOf(NewsScreenErrorItem)
            }
        }
    }

    private fun getLoaders(): List<DisplayableItem> =
        Category.entries.map { category ->
            CategoryArticlesUi(
                category = category,
                articleList = (1..3).map { ProgressArticleItem }
            )
        }
}