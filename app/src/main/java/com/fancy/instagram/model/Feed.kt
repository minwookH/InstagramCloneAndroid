package com.fancy.instagram.model

data class Feed(
    val feedId: Int,
    val userId: Int,
    val userName: String,
    val userProfileImage: String,
    val feedImage: String,
    val contents: String,
    val likeCount: Int,
    val date: String
)