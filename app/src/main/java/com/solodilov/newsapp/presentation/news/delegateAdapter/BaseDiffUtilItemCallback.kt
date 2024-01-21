package com.solodilov.newsapp.presentation.news.delegateAdapter

import androidx.recyclerview.widget.DiffUtil
import com.solodilov.newsapp.presentation.common.DisplayableItem

open class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<DisplayableItem>() {
    override fun areItemsTheSame(oldItem: DisplayableItem, newItem: DisplayableItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: DisplayableItem, newItem: DisplayableItem): Boolean {
        return oldItem.equals(newItem)
    }
}