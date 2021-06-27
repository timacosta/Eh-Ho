package io.keepcoding.eh_ho.posts

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.eh_ho.databinding.ActivityPostsBinding
import io.keepcoding.eh_ho.di.DIProvider
import io.keepcoding.eh_ho.model.Topic

class PostsActivity: AppCompatActivity() {

    private val binding: ActivityPostsBinding by lazy { ActivityPostsBinding.inflate(layoutInflater)}
    private val postsAdapter = PostsAdapter()
    private val vm: PostsViewModel by viewModels { DIProvider.topicsViewModelProviderFactory }
    var topicID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var intent = intent
        topicID = intent.getIntExtra("topicID",0)

        binding.post


    }

}