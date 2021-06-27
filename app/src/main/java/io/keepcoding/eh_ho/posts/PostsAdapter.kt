package io.keepcoding.eh_ho.posts

import android.text.Html
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.eh_ho.databinding.ViewPostBinding
import io.keepcoding.eh_ho.extensions.inflater
import io.keepcoding.eh_ho.model.Post
import io.keepcoding.eh_ho.model.Topic

class PostsAdapter(diffUtilItemCallback: DiffUtil.ItemCallback<Post> = DIFF) :
    ListAdapter<Post,PostsAdapter.PostViewHolder>(diffUtilItemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(parent)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
        }
    }

    class PostViewHolder(
        parent : ViewGroup,
        private val binding: ViewPostBinding = ViewPostBinding.inflate(
            parent.inflater,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.tvTopicName.text = post.username
            binding.tvTopicContent.text = HtmlCompat.fromHtml(post.message, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.repliesDetails.text = post.replyCount.toString()
            binding.dateDetails.text = post.createdAt.dropLast(14)
            binding.likesDetails.text = post.reads.toString()
            binding.lastUser.text = post.username

        }

    }



}