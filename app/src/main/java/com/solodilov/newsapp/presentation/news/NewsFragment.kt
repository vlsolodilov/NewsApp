package com.solodilov.newsapp.presentation.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.solodilov.newsapp.App
import com.solodilov.newsapp.R
import com.solodilov.newsapp.databinding.FragmentNewsBinding
import com.solodilov.newsapp.presentation.common.BaseFragment
import com.solodilov.newsapp.presentation.common.DisplayableItem
import com.solodilov.newsapp.presentation.common.ViewModelFactory
import com.solodilov.newsapp.presentation.common.viewBinding
import com.solodilov.newsapp.presentation.news.delegateAdapter.NewsScreenAdapter
import com.solodilov.newsapp.presentation.news.delegateAdapter.model.ArticleUi
import javax.inject.Inject

class NewsFragment : BaseFragment(R.layout.fragment_news) {
    private val binding by viewBinding(FragmentNewsBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: NewsViewModel by viewModels { viewModelFactory }

    private var adapter: NewsScreenAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter = NewsScreenAdapter(
            onArticleClick = { startBrowser(it) },
            onRefreshClick = { viewModel.getData(forceRefresh = true) }
        )
        binding.news.adapter = adapter
        viewModel.data.collectWhileStarted { adapter?.items = it }

    }

    private fun startBrowser(item: DisplayableItem) {
        val selectedUrl = (item as ArticleUi).url ?: return
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedUrl))
        startActivity(browserIntent)
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }
}