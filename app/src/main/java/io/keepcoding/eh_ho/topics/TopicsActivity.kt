package io.keepcoding.eh_ho.topics

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import io.keepcoding.eh_ho.databinding.ActivityTopicsBinding
import io.keepcoding.eh_ho.di.DIProvider
import io.keepcoding.eh_ho.model.Topic
import io.keepcoding.eh_ho.posts.PostsActivity

class TopicsActivity : AppCompatActivity() {

    private val binding: ActivityTopicsBinding by lazy { ActivityTopicsBinding.inflate(layoutInflater) }
    private val topicsAdapter = TopicsAdapter()
    private val vm: TopicsViewModel by viewModels { DIProvider.topicsViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.topics.apply {
            adapter = topicsAdapter
            addItemDecoration(DividerItemDecoration(this@TopicsActivity, LinearLayout.VERTICAL))
        }
        vm.state.observe(this) {
            when (it) {
                is TopicsViewModel.State.LoadingTopics -> {
                    renderLoading(it)
                    hideEmptyState()
                }
                is TopicsViewModel.State.TopicsReceived -> {
                    topicsAdapter.submitList(it.topics)
                    hideEmptyState()
                    hideLoading()
                }
                is TopicsViewModel.State.NoTopics -> {
                    hideLoading()
                    renderEmptyState()
                }
            }
        }
        binding.swipePullToRefresh.setOnRefreshListener { vm.loadTopics() }
    }

    override fun onResume() {
        super.onResume()
        vm.loadTopics()
    }

    private fun renderEmptyState() {
        binding.emptyResponse.isVisible = true
    }

    private fun hideEmptyState() {
        binding.emptyResponse.isVisible = false
    }

    private fun hideLoading() {
        binding.viewLoading.root.isVisible = false
        binding.swipePullToRefresh.isRefreshing = false
    }

    private fun renderLoading(loadingState: TopicsViewModel.State.LoadingTopics) {
        when(loadingState) {
            is TopicsViewModel.State.LoadingTopics.Loading -> {
                binding.viewLoading.root.isVisible = true
            }
            is TopicsViewModel.State.LoadingTopics.LoadingWithTopics -> {
                binding.swipePullToRefresh.isRefreshing = true
                topicsAdapter.submitList(loadingState.topics)
            }
        }
    }


    companion object {
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, TopicsActivity::class.java)
    }
}