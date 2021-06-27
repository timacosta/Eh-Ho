package io.keepcoding.eh_ho.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.eh_ho.repository.Repository
import io.keepcoding.eh_ho.topics.TopicsViewModel

class PostsViewModel(private val repository: Repository): ViewModel() {

    class PostsViewModelProviderFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
            PostsViewModel::class.java -> PostsViewModel(repository) as T
            else -> throw IllegalArgumentException("LoginViewModelFactory can only create instances of the LoginViewModel")
        }

    }

}