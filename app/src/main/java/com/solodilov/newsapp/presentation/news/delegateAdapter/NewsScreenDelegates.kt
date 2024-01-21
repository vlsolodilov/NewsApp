package com.solodilov.newsapp.presentation.news.delegateAdapter

import android.app.Activity
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.solodilov.newsapp.R
import com.solodilov.newsapp.databinding.ErrorLayoutBinding
import com.solodilov.newsapp.databinding.ItemArticleBinding
import com.solodilov.newsapp.databinding.ItemArticleCategoryBinding
import com.solodilov.newsapp.databinding.ItemProgressArticleBinding
import com.solodilov.newsapp.presentation.common.DisplayableItem
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.ArticleUi
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.CategoryArticlesUi
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.NewsScreenErrorItem
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.ProgressArticleItem

object NewsScreenDelegates {

    fun articleCategoryDelegate(onArticleClick: (DisplayableItem) -> Unit) =
        adapterDelegateViewBinding<CategoryArticlesUi, DisplayableItem, ItemArticleCategoryBinding>(
            { inflater, container -> ItemArticleCategoryBinding.inflate(inflater, container, false) }
        ) {

            val adapter = ArticleAdapter(onArticleClick)
            binding.articlesByCategory.adapter = adapter
            bind {
                binding.categoryName.text = item.category.query.replaceFirstChar { it.titlecase() }
                adapter.items = item.articleList
            }
        }

    fun articleDelegate(onArticleClick: (DisplayableItem) -> Unit) =
        adapterDelegateViewBinding<ArticleUi, DisplayableItem, ItemArticleBinding>(
            { inflater, container -> ItemArticleBinding.inflate(inflater, container, false) }
        ) {
            bind {
                Glide.with(binding.root)
                    .load(item.urlToImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.articleImage)

                binding.articleTitle.text = item.title
                binding.root.setOnClickListener { onArticleClick(item) }
            }

            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true) {
                    Glide.with(binding.root).clear(binding.articleImage)
                }
            }
        }

    fun articleProgressDelegate() =
        adapterDelegateViewBinding<ProgressArticleItem, DisplayableItem, ItemProgressArticleBinding>(
            { inflater, container ->
                ItemProgressArticleBinding.inflate(inflater, container, false)
            }
        ) {
            val animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.progress_fade_out)
            binding.root.startAnimation(animation)
        }

    fun mainErrorDelegate(onRefreshClick: () -> Unit) =
        adapterDelegateViewBinding<NewsScreenErrorItem, DisplayableItem, ErrorLayoutBinding>(
            { inflater, container -> ErrorLayoutBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.tryButton.setOnClickListener { onRefreshClick() }
            }
        }
}