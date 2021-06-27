package io.keepcoding.eh_ho.posts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import io.keepcoding.eh_ho.databinding.ActivityPostsBinding
import io.keepcoding.eh_ho.di.DIProvider
import io.keepcoding.eh_ho.model.Topic

class PostsActivity : AppCompatActivity() {

    private val binding: ActivityPostsBinding by lazy { ActivityPostsBinding.inflate(layoutInflater) }
    private val postsAdapter = PostsAdapter()
    private val vm: PostsViewModel by viewModels { DIProvider.postsViewModelProviderFactory }
    private val topic: Topic by lazy { intent.getSerializableExtra(TOPICS_KEY) as Topic }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView = binding.postDetails
        recyclerView.adapter = postsAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this@PostsActivity, LinearLayout.VERTICAL))

        vm.state.observe(this) {
            when (it) {
                is PostsViewModel.State.LoadingPosts -> {
                    renderLoading(it)
                    hideEmptyState()
                }
                is PostsViewModel.State.PostsReceived -> {
                    postsAdapter.submitList(it.posts)
                    hideEmptyState()
                    hideLoading()
                }
                is PostsViewModel.State.NoPosts -> {
                    hideLoading()
                    renderEmptyState()
                }
            }
        }
        binding.swipePullToRefresh.setOnRefreshListener { vm.loadPosts(topic) }
    }

    override fun onResume() {
        super.onResume()
        vm.loadPosts(topic)
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

    private fun renderLoading(loadingState: PostsViewModel.State.LoadingPosts) {
        when (loadingState) {
            is PostsViewModel.State.LoadingPosts.Loading -> {
                binding.viewLoading.root.isVisible = true
            }
            is PostsViewModel.State.LoadingPosts.LoadingWithPosts -> {
                binding.swipePullToRefresh.isRefreshing = true
                postsAdapter.submitList(loadingState.posts)
            }
        }
    }




    companion object {
        const val TOPICS_KEY = "TOPICS_KEY"

        @JvmStatic
        fun createIntent(context: Context, topic: Topic): Intent =
            Intent(context, PostsActivity::class.java).apply {
                putExtra(TOPICS_KEY, topic)
            }
    }

}