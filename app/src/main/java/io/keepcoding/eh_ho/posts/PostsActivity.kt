package io.keepcoding.eh_ho.posts

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.eh_ho.databinding.ActivityPostsBinding
import io.keepcoding.eh_ho.di.DIProvider

class PostsActivity: AppCompatActivity() {

    private val binding: ActivityPostsBinding by lazy { ActivityPostsBinding.inflate(layoutInflater)}
    private val postsAdapter = PostsAdapter()
    private val vm: PostsViewModel by viewModels { DIProvider.topicsViewModelProviderFactory }

}