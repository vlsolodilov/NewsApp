package com.solodilov.newsapp.presentation.news.delegateAdapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.solodilov.newsapp.presentation.common.DisplayableItem

class NewsScreenAdapter(
    onArticleClick: (DisplayableItem) -> Unit,
    onRefreshClick: () -> Unit,
) : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(NewsScreenDelegates.articleCategoryDelegate(onArticleClick))
            .addDelegate(NewsScreenDelegates.mainErrorDelegate(onRefreshClick))
    }
}