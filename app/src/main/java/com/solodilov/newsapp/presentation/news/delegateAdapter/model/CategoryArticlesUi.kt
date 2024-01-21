package com.solodilov.newsapp.presentation.news.delegateAdapter.model

import com.solodilov.newsapp.domain.entity.Category
import com.solodilov.newsapp.presentation.common.DisplayableItem

data class CategoryArticlesUi(
    val category: Category,
    val articleList: List<DisplayableItem>,
) : DisplayableItem {
    override val itemId: Int = category.name.hashCode()
}
