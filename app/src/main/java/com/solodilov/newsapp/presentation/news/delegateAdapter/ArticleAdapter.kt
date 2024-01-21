package com.solodilov.newsapp.presentation.news.delegateAdapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.solodilov.newsapp.presentation.common.DisplayableItem

class ArticleAdapter(onArticleClick: (DisplayableItem) -> Unit)
    : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager.addDelegate(NewsScreenDelegates.articleDelegate(onArticleClick))
            .addDelegate(NewsScreenDelegates.articleProgressDelegate())
    }
}