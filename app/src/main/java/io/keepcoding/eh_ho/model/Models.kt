package io.keepcoding.eh_ho.model

import java.io.Serializable

sealed class LogIn {
    data class Success(val userName: String) : LogIn()
    data class Error(val error: String) : LogIn()
}

data class Topic(
    val id: Int,
    val title: String,
    val likeCount: Int,
    val replyCount: Int,
    val lastPostedAt: String,
    val lastPosterUsername: String,
) : Serializable

data class Post(
    val id: Int,
    val message: String,
    val username: String,
    val replyCount: Int,
    val createdAt: String,
    val reads: Int,
)