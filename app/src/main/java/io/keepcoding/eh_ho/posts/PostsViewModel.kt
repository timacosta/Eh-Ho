package io.keepcoding.eh_ho.posts

import androidx.lifecycle.*
import io.keepcoding.eh_ho.model.Post
import io.keepcoding.eh_ho.model.Topic
import io.keepcoding.eh_ho.posts.PostsViewModel.State.PostsReceived
import io.keepcoding.eh_ho.repository.Repository
import io.keepcoding.eh_ho.topics.TopicsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: Repository): ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData<State>().apply {
        postValue(State.LoadingPosts.Loading)
    }

    val loading : MutableLiveData<Boolean> = MutableLiveData(false)

    val state: LiveData<State> = _state

    fun loadPosts(topic: Topic) {
        loading.postValue(true)

        _state.postValue(
            _state.value.let {
                when(it) {
                    is State.PostsReceived -> State.LoadingPosts.LoadingWithPosts(it.posts)
                    is State.LoadingPosts -> it
                    else -> State.LoadingPosts.Loading
                }
            }
        )
        viewModelScope.launch {
            delay(2000)
            repository.getPosts(topic) {
                it.fold(::onPostsReceived, ::onPostsFailure)
            }
        }
    }

    private fun onPostsReceived(posts: List<Post>) {
        _state.postValue(posts.takeUnless { it.isEmpty() }?.let(::PostsReceived) ?: State.NoPosts)
    }

    private fun onPostsFailure(throwable: Throwable) {
        _state.postValue(State.NoPosts)
    }

    sealed class State {
        sealed class LoadingPosts : State() {
            object Loading: LoadingPosts()
            data class LoadingWithPosts(val posts: List<Post>) : LoadingPosts()
        }
        data class PostsReceived(val posts: List<Post>): State()
        object NoPosts : State()
    }



    class PostsViewModelProviderFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
            PostsViewModel::class.java -> PostsViewModel(repository) as T
            else -> throw IllegalArgumentException("LoginViewModelFactory can only create instances of the LoginViewModel")
        }

    }

}